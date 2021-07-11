package ROOT.Controller.Member;

import ROOT.Service.Member.MemberService;
import ROOT.VO.Form.JoinForm;
import ROOT.VO.Form.LoginForm;
import ROOT.VO.Member.Member;
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
                        @SessionAttribute Member sessionMember
    ) {
        // 입력값 검사 실패 시
        if (result.hasErrors() || errors.hasErrors()) {
            return "/member/loginForm";
        }

        // 입력값 검사 성공 시
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        member.setPassword(form.getPassword());

        // 로그인
        Member loginMember = memberService.login(member);

        // 결과확인, 실패 시 다시 로그인 폼으로
        if (loginMember.getMemberId() == null || !loginMember.getMemberId().equals(member.getMemberId())) {
            model.addAttribute("loginResult", "로그인에 실패했습니다. 입력값을 확인 후 다시 시도해주세요.");
            return "/member/loginForm";
        }

        sessionMember.setMemberId(loginMember.getMemberId());
        sessionMember.setNickName(loginMember.getNickName());
        sessionMember.setPassword(loginMember.getPassword());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
