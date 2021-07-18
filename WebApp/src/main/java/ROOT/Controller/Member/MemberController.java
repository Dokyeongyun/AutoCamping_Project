package ROOT.Controller.Member;

import ROOT.Service.Member.MemberService;
import ROOT.Service.Utils.MailService;
import ROOT.Utils.CryptoUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        // 입력값 검사 성공 시
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        member.setNickName(form.getNickName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        // 회원가입
        Member joinMember = memberService.join(member);

        // 결과확인, 실패 시 다시 회원가입 폼으로
        if (joinMember == null || !joinMember.getMemberId().equals(member.getMemberId())) {
            model.addAttribute("joinResult", "회원가입에 실패했습니다. 잠시 후 다시 시도해주세요.");
            return "/member/joinForm";
        }

        return "redirect:/member/loginForm";
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

        // 입력값 검사 성공 시
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        Member loginMember = memberService.login(member);

        if (loginMember.getMemberId() == null) { // ID 없음
            model.addAttribute("loginResult", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        } else {
            MemberLoginLock memberLoginLock = memberService.checkLoginLockTime(loginMember.getMemberId()); // 잠금상태인지 확인

            if (memberLoginLock != null) { // 로그인 잠금기록 있음
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String lockTimeString = memberLoginLock.getLoginLockTime();
                long lockTime = sdf.parse(lockTimeString).getTime();
                long curTime = System.currentTimeMillis();
                long diffTime = (curTime - lockTime) / 1000;

                if (diffTime <= 300) { // 로그인 잠금상태
                    model.addAttribute("loginResult", "현재 비밀번호 5회 연속 오류로 인해 회원님의 계정이 잠금상태입니다.\n5분 후에 다시 시도해주세요.");
                }
            }

            String inputPassword = form.getPassword();
            String encryptedPassword = CryptoUtils.encryptAES256(inputPassword, inputPassword.hashCode() + "");

            if (encryptedPassword.equals(loginMember.getPassword())) { // 로그인 성공
                isSuccess = true;
            } else {
                model.addAttribute("loginResult", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
            }
        }

        // 로그인 이력 기록
        MemberLoginHistory loginHistory = new MemberLoginHistory();
        loginHistory.setLoginMemberId(loginMember.getMemberId());
        loginHistory.setLoginIP(ClientUtils.getRemoteIP(request));
        loginHistory.setLoginYN(isSuccess ? "Y" : "N");
        memberService.insertLoginHistory(loginHistory);

        if (isSuccess) { // 로그인 성공
            sessionMember.setMember(loginMember);
        }

        return isSuccess ? "redirect:/" : "/member/loginForm";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
