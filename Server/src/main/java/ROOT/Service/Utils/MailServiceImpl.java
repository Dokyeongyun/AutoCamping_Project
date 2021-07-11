package ROOT.Service.Utils;

import ROOT.DAO.MailDAO;
import ROOT.VO.Utils.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailServiceImpl implements MailService{

    @Autowired
    MailDAO mailDAO;

    /**
     * 이메일 인증번호전송 로그 삽입
     */
    @Override
    public void insertMailAuthLog(MailAuth mailAuth) {
        mailDAO.insertMailAuthLog(mailAuth);
    }

    /**
     * 해당 이메일로 전송한 가장 최근의 인증번호 반환
     */
    @Override
    public String getLatestAuthNum(String email) {
        return mailDAO.getLatestAuthNum(email);
    }
}
