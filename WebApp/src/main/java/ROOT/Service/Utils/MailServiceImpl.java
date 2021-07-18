package ROOT.Service.Utils;

import ROOT.Utils.APIServerInfo;
import ROOT.VO.Utils.Mail;
import ROOT.VO.Utils.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;


@Service("MailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    RestOperations restOperations;

    @Override
    public void sendEmail(Mail mail) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(mail.getMailRecipient());                   // 수신자
            helper.setFrom(mail.getMailSender());                    // 송신자
            helper.setSubject(mail.getMailTitle());                  // 제목
            helper.setText(mail.getMailContent(), true);        // 내용
        };

        mailSender.send(preparator);
    }

    @Override
    public String createAuthNum() {
        String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        StringBuilder authNum = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            int random = (int) (Math.random() * str.length);
            authNum.append(str[random]);
        }

        return authNum.toString();
    }

    @Override
    public void insertMailAuthLog(MailAuth mailAuth) {
        try {
            restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/mail/mailAuth", mailAuth, MailAuth.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLatestAuthNum(MailAuth mailAuth){
        String latestAuthNum = "";
        try {
            String reqURL = "/mail/mailAuth?email="+mailAuth.getMailAuthEmail();
            latestAuthNum = restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + reqURL, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestAuthNum;
    }
}
