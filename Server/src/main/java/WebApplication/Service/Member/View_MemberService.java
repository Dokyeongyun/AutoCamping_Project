package WebApplication.Service.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public interface View_MemberService {

    /**
     * 로그인
     */
    @RequestMapping(value = "/login")
    String login(String id, String password);

    /**
     * 회원가입
     */
    @RequestMapping(value = "/join")
    String join(String id, String nickName, String password);
}
