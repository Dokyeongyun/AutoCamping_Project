package ROOT.DAO;

import ROOT.RowMapper.Member.MemberRowMapper;
import ROOT.VO.Member.Member;
import ROOT.VO.Utils.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public MailDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 이메일 인증번호전송 로그 삽입
     */
    public void insertMailAuthLog(MailAuth mailAuth) {
        try {
            String sql = "INSERT INTO MAIL_AUTH(MAIL_AUTH_NUM, MAIL_AUTH_EMAIL) VALUES (?, ?)";
            jdbcTemplate.update(sql, mailAuth.getMailAuthNum(), mailAuth.getMailAuthEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 해당 이메일로 전송한 가장 최근의 인증번호 반환
     */
    public String getLatestAuthNum(String email){
        String latestAuthNum = "";
        try {
            String sql = "SELECT MAIL_AUTH_NUM " +
                    "FROM MAIL_AUTH " +
                    "WHERE MAIL_AUTH_EMAIL = ? " +
                    "ORDER BY MAIL_AUTH_DATE DESC " +
                    "LIMIT 1 ";
            latestAuthNum = jdbcTemplate.queryForObject(sql, String.class, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestAuthNum;
    }
}
