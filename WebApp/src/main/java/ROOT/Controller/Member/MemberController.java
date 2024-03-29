package ROOT.Controller.Member;

import ROOT.Service.Member.MemberService;
import ROOT.Service.Utils.MailService;
import ROOT.Utils.CryptoUtils;
import ROOT.VO.Chabak.ChabakDibs;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Form.JoinForm;
import ROOT.VO.Form.LoginForm;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;
import ROOT.Utils.ClientUtils;
import ROOT.VO.Utils.MailAuth;
import ROOT.Validator.JoinValidator;
import ROOT.Validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@SessionAttributes("sessionMember")
@RequestMapping("/member")
public class MemberController {

    @ModelAttribute("sessionMember")
    Member sessionMember() {
        return new Member();
    }

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;

    @InitBinder("joinForm")
    public void joinInitBinder(WebDataBinder binder) {
        JoinValidator joinValidator = new JoinValidator();
        binder.addValidators(joinValidator);
    }

    @InitBinder("loginForm")
    public void loginInitBinder(WebDataBinder binder) {
        LoginValidator loginValidator = new LoginValidator();
        binder.addValidators(loginValidator);
    }

    @ModelAttribute
    JoinForm setUpJoinForm() {
        return new JoinForm();
    }

    @ModelAttribute
    LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /**
     * 회원가입 화면
     */
    @GetMapping("/joinForm")
    public void joinForm() {
    }

    /**
     * 로그인 화면
     */
    @GetMapping("/loginForm")
    public void loginForm() {
    }

    /**
     * 아이디찾기 화면
     */
    @GetMapping("/findIDForm")
    public void findIDForm() {
    }

    /**
     * 비밀번호찾기 화면
     */
    @GetMapping("/findPasswordForm")
    public void findPasswordForm() {
    }

    /**
     * 비밀번호변경 화면
     */
    @GetMapping("/changePasswordForm")
    public ModelAndView changePasswordForm(@RequestParam String email, @RequestParam String authNum) {
        MailAuth mailAuth = new MailAuth();
        mailAuth.setMailAuthEmail(email);
        mailAuth.setMailAuthNum(authNum);
        String latestAuthNum = mailService.getLatestAuthNum(mailAuth);

        ModelAndView mav = new ModelAndView();
        if (!latestAuthNum.equals(authNum)) {
            // TODO 비정상적 접근 에러 메시지 함께 전송해야 함
            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("/member/changePasswordForm");
            mav.addObject("email", email);
        }

        return mav;
    }

    /**
     * 회원가입 폼으로부터 받은 입력값 검사 및 로직 실행
     * 입력값 검사 통과 시 - 회원가입 Logic 실행
     * 입력값 검사 실패 시 - 다시 회원가입 폼 화면으로 이동 (입력값 검사 결과와 함께)
     *
     * @param form   회원가입 폼 정보를 담을 ModelAttribute 클래스
     * @param result 입력값 검사 결과
     * @return 이동할 화면
     */
    @PostMapping("/join")
    public String join(@Validated JoinForm form,
                       BindingResult result,
                       Errors errors,
                       Model model
    ) {
        // 입력값 검사 실패 시
        if (result.hasErrors() || errors.hasErrors()) {
            return "/member/joinForm";
        }

        boolean isSuccess = false;

        // 입력값 검사 성공 시
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        member.setNickName(form.getNickName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        // 회원가입
        Member joinMember = memberService.join(member);

        // 결과확인, 실패 시 다시 회원가입 폼으로 이동.
        if (joinMember == null || !joinMember.getMemberId().equals(member.getMemberId())) {
            model.addAttribute("joinResult", "회원가입에 실패했습니다. 잠시 후 다시 시도해주세요.");
        } else {
            isSuccess = true;

            // 회원가입 성공 시, 로그인 잠금시간 행 추가 및 초기화
            MemberLoginLock loginLock = new MemberLoginLock();
            loginLock.setLoginLockMemberId(joinMember.getMemberId());
            loginLock.setLoginLockTime("20000101010101");
            memberService.insertLoginLockTime(loginLock);
        }

        return isSuccess ? "redirect:/member/loginForm" : "/member/joinForm";
    }

    /**
     * 로그인 폼으로부터 받은 입력값 검사 및 로직 실행
     * 입력값 검사 통과 시 - 로그인 Logic 실행
     * 입력값 검사 실패 시 - 다시 로그인 폼 화면으로 이동 (입력값 검사 결과와 함께)
     *
     * @param form   로그인 폼 정보를 담을 ModelAttribute 클래스
     * @param result 입력값 검사 결과
     * @return 이동할 화면
     */
    @PostMapping("/login")
    public String login(@Validated LoginForm form,
                        BindingResult result,
                        Errors errors,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @SessionAttribute Member sessionMember
    ) throws Exception {
        // 입력값 검사 실패 시
        if (result.hasErrors() || errors.hasErrors()) {
            return "/member/loginForm";
        }

        /*
         로그인 Process
         ==============================================
         1. ID 이용하여 조회
            1.1 결과값 ID가 NULL이면, "ID 없음" => FAIL
            1.2 결과값 ID가 NULL이 아니면, NEXT
         2. 잠금상태인 계정인지 조회
            2.1 잠금상태면, 현재시간 - 최근잠금시간이 M분 이내인지 체크
            2.2 잠금상태 아니면, NEXT
                2.2.1 M분 이내 아니면, "잠금상태계정" => FAIL
                2.2.2 M분 이내이면, NEXT
         3. 입력된 비밀번호 암호화 및 조회 비밀번호와 비교
            3.1 일치하지 않으면, "비밀번호 불일치" => FAIL
            3.2 일치하면, NEXT
         4. 비밀번호 변경주기 확인
            4.1 최근 변경일자로부터 M일 지났으면, NEXT && 다음 페이지 "비밀번호 변경 페이지"
            4.2 최근 변경일자로부터 M일 이내이면, NEXT
         5. 로그인되어있는 ID인지 확인
            5.1 로그인 되어있는 ID이면, NEXT && "접속해제 하시겠습니까?" 메시지 출력
            5.2 로그인 되어있지 않은 ID이면, NEXT
         6. 현재 IP와 최근 로그인 IP 비교
            6.1 일치하지 않으면, NEXT && 회원에게 안내하기
            6.2 일치하면, NEXT
         7. 로그인 이력 기록
         ==============================================
         */

        boolean isSuccess = false; // 로그인 성공여부, 성공하면 이전페이지 리디렉션, 실패하면 로그인폼 재이동
        String loginStatusCode;

        // ================================================
        // 입력값 및 회원 계정 상태에 따른 loginStatusCode 설정
        // ================================================

        Member member = new Member();
        member.setMemberId(form.getMemberId());
        Member loginMember = memberService.login(member);

        if (loginMember.getMemberId() == null) { // ID 없음
            loginStatusCode = "ID_INVALID";
        } else {
            MemberLoginLock memberLoginLock = memberService.checkLoginLockTime(loginMember.getMemberId()); // 잠금상태인지 확인

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lockTimeString = memberLoginLock.getLoginLockTime();
            long lockTime = sdf.parse(lockTimeString).getTime();
            long curTime = System.currentTimeMillis();
            long diffTime = (curTime - lockTime) / 1000;

            if (diffTime <= 300) {      // 로그인 잠금상태
                loginStatusCode = "ACCOUNT_LOGIN_LOCKED";
            } else {                    // 로그인 잠금상태 X
                String inputPassword = form.getPassword();
                String encryptedPassword = CryptoUtils.encryptAES256(inputPassword, inputPassword.hashCode() + "");

                if (encryptedPassword.equals(loginMember.getPassword())) { // 로그인 성공
                    loginStatusCode = "LOGIN_SUCCESS";
                } else {
                    loginStatusCode = "PASSWORD_INVALID";
                }
            }
        }

        // ================================================
        // loginStatusCode에 따른 분기처리
        // ================================================

        boolean isIDInvalid = false;
        boolean isLoginLocked = false;
        switch (loginStatusCode) {
            case "ID_INVALID":
                isIDInvalid = true;
                model.addAttribute("loginResult", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
                break;
            case "ACCOUNT_LOGIN_LOCKED":
                isLoginLocked = true;
                model.addAttribute("loginResult", "현재 비밀번호 5회 연속 오류로 인해 회원님의 계정이 잠금상태입니다. 5분 후에 다시 시도해주세요.");
                break;
            case "PASSWORD_INVALID":
                model.addAttribute("loginResult", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
                break;
            case "LOGIN_SUCCESS":
                isSuccess = true;

                // 세션 값 설정
                sessionMember.setMember(loginMember);

                // 아이디 기억하기 쿠키 설정
                String isSaveId = form.getSaveIdYN();
                if ("Y".equals(isSaveId)) {
                    Cookie saveIdCookie = new Cookie("saveIdCookie", loginMember.getMemberId());
                    saveIdCookie.setMaxAge(30 * 24 * 60 * 60); // 30일 설정
                    saveIdCookie.setPath("/");
                    response.addCookie(saveIdCookie);
                } else {
                    Cookie saveIdCookie = new Cookie("saveIdCookie", null);
                    saveIdCookie.setMaxAge(0);
                    saveIdCookie.setPath("/");
                    response.addCookie(saveIdCookie);
                }

                // 로그인 락 해제
                MemberLoginLock loginLock = new MemberLoginLock();
                loginLock.setLoginLockMemberId(loginMember.getMemberId());
                loginLock.setLoginLockTime("20000101010101");
                memberService.updateLoginLockTime(loginLock);
                break;
        }

        // 로그인 이력 기록
        if (!isIDInvalid) {
            MemberLoginHistory loginHistory = new MemberLoginHistory();
            loginHistory.setLoginMemberId(loginMember.getMemberId());
            loginHistory.setLoginIP(ClientUtils.getRemoteIP(request));
            loginHistory.setLoginYN(isSuccess ? "Y" : "N");
            memberService.insertLoginHistory(loginHistory);
        }

        // 잠금상태가 아니면, 로그인 연속 실패횟수 체크
        if (!isLoginLocked) {
            List<MemberLoginHistory> loginHistories = memberService.getRecentLoginHistoryList(loginMember.getMemberId());

            int failCount = 0;
            for (MemberLoginHistory history : loginHistories) {
                String loginYN = history.getLoginYN();
                if ("N".equals(loginYN)) failCount++;
            }

            if (failCount == 5) { // 5회 연속 실패했으면, 계정 잠금
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                MemberLoginLock loginLock = new MemberLoginLock();
                loginLock.setLoginLockMemberId(loginMember.getMemberId());
                loginLock.setLoginLockTime(sdf.format(new Date()));
                memberService.updateLoginLockTime(loginLock);
            }
        }

        return isSuccess ? "redirect:/" : "/member/loginForm";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }


    //=================================================================//
    //                           차박지 관련                             //
    //=================================================================//

    /**
     * 차박지 찜 AJAX
     */
    @ResponseBody
    @PostMapping("/chabak/dibs")
    public boolean chabakDibsAjax(@RequestBody ChabakDibs dibs) {
        memberService.dibsChabak(dibs);
        return memberService.getChabakDibsStatus(dibs);
    }

    /**
     * 차박지 찜 취소 AJAX
     */
    @ResponseBody
    @PostMapping("/chabak/unDibs")
    public boolean chabakUnDibsAjax(@RequestBody ChabakDibs dibs) {
        memberService.unDibsChabak(dibs);
        return memberService.getChabakDibsStatus(dibs);
    }

    /**
     * 차박지 리뷰
     */
    @ResponseBody
    @PostMapping("/chabak/review")
    public int writeChabakReview(@RequestBody Review review) {
        return memberService.writeChabakReview(review);
    }
}
