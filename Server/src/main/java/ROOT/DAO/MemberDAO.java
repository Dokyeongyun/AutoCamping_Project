package ROOT.DAO;

import ROOT.RowMapper.Chabak.ChabakRowMapper;
import ROOT.RowMapper.Chabak.ReviewRowMapper;
import ROOT.RowMapper.Member.MemberLoginHistoryRowMapper;
import ROOT.RowMapper.Member.MemberLoginLockRowMapper;
import ROOT.RowMapper.Member.MemberRowMapper;
import ROOT.Util.crypto.CryptoUtil;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.ChabakDibs;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 로그인
     */
    public Member login(Member member) {
        Member result = new Member();
        try {
            String sql = "SELECT * FROM cb_member WHERE memberId = ? AND isDeleted = 0";
            List<Member> temp = jdbcTemplate.query(sql, new MemberRowMapper(), member.getMemberId());
            if (temp.size() == 1) {
                System.out.println(temp.get(0));
                return temp.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 회원가입
     */
    public Member join(Member member) {
        try {
            String sql = "INSERT INTO cb_member(memberId, nickName, password, MEM_EMAIL) VALUES (?, ?, ?, ?)";
            int result = jdbcTemplate.update(sql, member.getMemberId(), member.getNickName(), member.getPassword(), member.getEmail());
            if (result == 1) {
                return member;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Member();
    }

    /**
     * 아이디 중복확인
     */
    public String idCheck(String memberId) {
        String sql = "SELECT memberId FROM cb_member WHERE memberId = ?";
        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getString("memberId"), memberId);
        return result.size() > 0 ? "N" : "Y";
    }

    /**
     * 닉네임 중복확인
     */
    public String nicknameCheck(String nickname) {
        String sql = "SELECT nickName FROM cb_member WHERE nickName = ?";
        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getString("nickName"), nickname);
        return result.size() > 0 ? "N" : "Y";
    }

    /**
     * 이메일 중복확인
     */
    public String emailCheck(String email) {
        String sql = "SELECT MEM_EMAIL FROM cb_member WHERE MEM_EMAIL = ?";
        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getString("MEM_EMAIL"), email);
        return result.size() > 0 ? "N" : "Y";
    }

    /**
     * 이메일을 이용해 회원 아이디 가져오기
     */
    public String getUserIDUsingEmail(String email) {
        String sql = "SELECT memberId FROM CB_MEMBER WHERE MEM_EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, String.class, email);
    }

    /**
     * 비밀번호 변경
     */
    public int changePassword(Member member) {
        try {
            String encrypted = CryptoUtil.encryptAES256(member.getPassword(), member.getPassword().hashCode() + "");
            String sql = "UPDATE cb_member SET password = ? WHERE MEM_EMAIL = ?";
            return jdbcTemplate.update(sql, encrypted, member.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 회원가입 시, 로그인 잠금시간 추가 및 초기화
     */
    public void insertLoginLockTime(MemberLoginLock loginLock) {
        String sql = "INSERT INTO MEM_LOGIN_LOCK(MEM_LOGIN_LOCK_MEM_ID, MEM_LOGIN_LOCK_TIME) VALUES (?, ?)";
        jdbcTemplate.update(sql, loginLock.getLoginLockMemberId(), loginLock.getLoginLockTime());
    }

    /**
     * 회원 계정 로그인 잠금시간 확인
     */
    public MemberLoginLock checkLoginLockTime(String memberId) {
        String sql = "SELECT * FROM MEM_LOGIN_LOCK WHERE MEM_LOGIN_LOCK_MEM_ID = ?";
        List<MemberLoginLock> list = jdbcTemplate.query(sql, new MemberLoginLockRowMapper(), memberId);
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * 로그인 잠금시간 업데이트
     */
    public void updateLoginLockTime(MemberLoginLock loginLock) {
        String sql = "UPDATE MEM_LOGIN_LOCK SET MEM_LOGIN_LOCK_TIME = ? WHERE MEM_LOGIN_LOCK_MEM_ID = ?";
        jdbcTemplate.update(sql, loginLock.getLoginLockTime(), loginLock.getLoginLockMemberId());
    }

    /**
     * 로그인 이력 기록
     */
    public void insertLoginHistory(MemberLoginHistory loginHistory) {
        String sql = "INSERT INTO MEM_LOGIN_HISTORY(MEM_LOGIN_MEM_ID, MEM_LOGIN_IP, MEM_LOGIN_YN) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, loginHistory.getLoginMemberId(), loginHistory.getLoginIP(), loginHistory.getLoginYN());
    }

    /**
     * 최근 5회 로그인 이력 가져오기
     */
    public List<MemberLoginHistory> getRecentLoginHistoryList(String memberId) {
        String sql = "SELECT * FROM MEM_LOGIN_HISTORY WHERE MEM_LOGIN_MEM_ID = ? ORDER BY MEM_LOGIN_DT DESC LIMIT 5";
        return jdbcTemplate.query(sql, new MemberLoginHistoryRowMapper(), memberId);
    }

    /**
     * 차박지 찜 상태 가져오기
     */
    public Boolean getChabakDibsStatus(String memberId, int placeId) {
        String sql = "SELECT COUNT(*) FROM CB_JJIM_LIST WHERE memberId = ? AND placeId = ?";
        Integer size = jdbcTemplate.queryForObject(sql, Integer.class, memberId, placeId);
        return size != null && size != 0;
    }

    /**
     * 차박지 찜
     */
    public void dibsChabak(ChabakDibs dibs){
        String sql = "INSERT INTO CB_JJIM_LIST(memberId, placeId) VALUES(?, ?)";
        jdbcTemplate.update(sql, dibs.getMemberId(), dibs.getPlaceId());
    }

    /**
     * 차박지 찜 취소
     */
    public void unDibsChabak(ChabakDibs dibs){
        String sql = "DELETE FROM CB_JJIM_LIST WHERE memberId = ? AND placeId = ?";
        jdbcTemplate.update(sql, dibs.getMemberId(), dibs.getPlaceId());
    }

    /**
     * 닉네임 변경
     */
    public int changeNickname(String memberId, String nickName) {
        String sql = "UPDATE cb_member SET nickname = ? WHERE memberId = ?";
        return jdbcTemplate.update(sql, nickName, memberId);
    }

    /**
     * 회원 탈퇴
     */
    public int withdraw(String memberId) {
        String sql = "UPDATE cb_member SET isDeleted = 1 WHERE memberId = ?";
        return jdbcTemplate.update(sql, memberId);
    }

    /**
     * 차박지 찜
     */
    public String jjimDo(String id, int placeId, String placeName) {
        String sql = "INSERT INTO cb_jjim_list values (?,?,?)";
        int result = jdbcTemplate.update(sql, id, placeId, placeName);
        if (result > 0) {
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 차박지 찜 취소
     */
    public String jjimUndo(String memberId, int placeId) {
        String sql = "DELETE FROM cb_jjim_list where memberId = ? AND placeId = ?";
        int result = jdbcTemplate.update(sql, memberId, placeId);
        if (result > 0) {
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 사용자의 차박지 찜 리스트 가져오기
     */
    public List<Chabak> getJJimList(String id) {
        String sql = "SELECT c.* from cb_jjim_list l, chabak_info_view c " +
                "WHERE memberId = ? AND l.placeId = c.placeId";
        return jdbcTemplate.query(sql, new ChabakRowMapper(), id);
    }

    /**
     * 사용자가 특정 차박지 찜 여부 확인
     */
    public String isJJim(String memberId, String placeId) {
        String sql = "SELECT COUNT(*) as isJJim FROM cb_jjim_list " +
                "WHERE memberId = ? AND placeId = ? GROUP BY memberId";
        List<Integer> result = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getInt(1), memberId, placeId);

        if (result.size() > 0) {
            return String.valueOf(result.get(0));
        } else {
            return "0";
        }
    }

    /**
     * 사용자가 특정 차박지 평가 여부 확인
     */
    public String isEvaluated(String memberId, String placeId) {
        String sql = "SELECT evaluation_point FROM user_evaluation WHERE memberId = ? AND placeId = ?";
        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getString("evaluation_point"), memberId, placeId);

        if (result.size() > 0) {
            return String.valueOf(result.get(0));
        } else {
            return "0";
        }
    }

    /**
     * 사용자가 작성한 리뷰 가져오기
     */
    public List<Review> getUsersReview(String memberId) {
        String sql = "SELECT * FROM review_view WHERE memberId = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), memberId);
    }
}
