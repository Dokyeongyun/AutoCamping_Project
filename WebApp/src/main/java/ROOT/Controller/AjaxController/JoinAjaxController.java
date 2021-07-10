package ROOT.Controller.AjaxController;

import ROOT.Service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joinAjax")
public class JoinAjaxController {

    @Autowired
    MemberService memberService;

    @GetMapping("/checkId")
    public String checkId(@RequestParam String id){
        // 요청 파라미터로 ID를 받은 후, 중복검사
        // 중복된다면 "N", 아니라면 "Y" 리턴
        return memberService.idCheck(id);
    }

    @GetMapping("/checkNickname")
    public String checkNickname(@RequestParam String nickname){
        // 요청 파라미터로 nickname을 받은 후, 중복검사
        // 중복된다면 "N", 아니라면 "Y" 리턴
        return memberService.nicknameCheck(nickname);
    }

    @GetMapping("/checkEmail")
    public String checkEmail(@RequestParam String email){
        // 요청 파라미터로 email 받은 후, 중복검사
        // 중복된다면 "N", 아니라면 "Y" 리턴
        return memberService.emailCheck(email);
    }
}
