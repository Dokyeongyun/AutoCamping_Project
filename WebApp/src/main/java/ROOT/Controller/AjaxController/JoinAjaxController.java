package ROOT.Controller.AjaxController;

import ROOT.Service.Member.MemberService;
import ROOT.Service.Utils.MailService;
import ROOT.VO.Member.Member;
import ROOT.VO.Utils.Mail;
import ROOT.VO.Utils.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joinAjax")
public class JoinAjaxController {

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;

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

    @GetMapping("/sendAuthNum")
    public String sendAuthNum(@RequestParam String email){
        // TODO Manage String Resource

        // 해당 이메일로 가입된 사용자가 있는지 확인
        String isNotUserEmail = memberService.emailCheck(email);

        // 없으면 NOT_USER 반환
        if("Y".equals(isNotUserEmail)){
            return "NOT_USER";
        }

        // 있으면 인증번호 생성하여 인증번호 전송, 그 후 인증번호 반환
        String authNum = mailService.createAuthNum();
        String sender = "aservmz@gmail.com";
        String title = "[차박, 여기서] 요청하신 인증번호를 알려드립니다.";

        Mail mail = new Mail();
        mail.setMailSender(sender);
        mail.setMailRecipient(email);
        mail.setMailTitle(title);
        mail.setMailContent(authNum);

        mailService.sendEmail(mail);

        MailAuth mailAuth = new MailAuth();
        mailAuth.setMailAuthNum(authNum);
        mailAuth.setMailAuthEmail(email);
        mailService.insertMailAuthLog(mailAuth);

        return authNum;
    }

    @GetMapping("/sendAuthNumForFindPw")
    public String sendAuthNum(@RequestParam String email, @RequestParam String memberId){
        // TODO Manage String Resource

        // 해당 이메일로 가입된 사용자가 있는지 확인
        String selectedMemberId = memberService.getUserIDUsingEmail(email);

        // 없으면 NOT_USER 반환
        if(selectedMemberId == null || "".equals(selectedMemberId)){
            return "NOT_USER";
        }

        // 입력된 아이디와 반환된 아이디가 같은지 확인
        if(!memberId.equals(selectedMemberId)){
            return "NOT_USER";
        }

        // 입력된 정보로 가입된 회원이 있으면 인증번호 생성하여 인증번호 전송, 그 후 인증번호 반환
        String authNum = mailService.createAuthNum();
        String sender = "aservmz@gmail.com";
        String title = "[차박, 여기서] 요청하신 인증번호를 알려드립니다.";

        Mail mail = new Mail();
        mail.setMailSender(sender);
        mail.setMailRecipient(email);
        mail.setMailTitle(title);
        mail.setMailContent(authNum);

        mailService.sendEmail(mail);

        MailAuth mailAuth = new MailAuth();
        mailAuth.setMailAuthNum(authNum);
        mailAuth.setMailAuthEmail(email);
        mailService.insertMailAuthLog(mailAuth);

        return authNum;
    }

    @PostMapping("/getAuthUserID")
    public String getAuthUserID(@RequestBody MailAuth mailAuth) {
        String latestAuthNum = mailService.getLatestAuthNum(mailAuth);
        String authUserID = "";
        if(!latestAuthNum.equals("") && latestAuthNum.equals(mailAuth.getMailAuthNum())){
            authUserID = memberService.getUserIDUsingEmail(mailAuth.getMailAuthEmail());
        }
        return authUserID;
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody Member member) {
        System.out.println(member);
        memberService.changePassword(member);
    }
}
