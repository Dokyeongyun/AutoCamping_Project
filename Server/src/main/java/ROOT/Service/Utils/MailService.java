package ROOT.Service.Utils;

import ROOT.VO.Utils.MailAuth;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public interface MailService {

    /**
     * 이메일 인증번호전송 로그 삽입
     */
    @PostMapping( "/mailAuth")
    void insertMailAuthLog(@RequestBody MailAuth mailAuth);

    /**
     * 해당 이메일로 전송한 가장 최근의 인증번호 반환
     */
    @GetMapping("/mailAuth")
    String getLatestAuthNum(@RequestParam String email);
}
