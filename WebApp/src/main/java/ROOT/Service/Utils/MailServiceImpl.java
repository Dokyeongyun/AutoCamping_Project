package ROOT.Service.Utils;

import ROOT.VO.Utils.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service("MailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

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
}
