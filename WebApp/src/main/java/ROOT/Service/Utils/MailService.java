package ROOT.Service.Utils;

import ROOT.VO.Utils.Mail;
import ROOT.VO.Utils.MailAuth;

public interface MailService {

    void sendEmail(Mail mail);

    String createAuthNum();

    void insertMailAuthLog(MailAuth mailAuth);

    String getLatestAuthNum(MailAuth mailAuth);
}
