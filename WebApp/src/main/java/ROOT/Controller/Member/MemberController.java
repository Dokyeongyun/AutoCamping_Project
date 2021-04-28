package ROOT.Controller.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    /**
     * 로그인 화면
     */
    @RequestMapping("/login")
    public void login() { }

    /**
     * 회원가입 화면
     */
    @RequestMapping("/join")
    public void join() { }
}
