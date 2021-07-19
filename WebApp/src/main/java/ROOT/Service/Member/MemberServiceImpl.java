package ROOT.Service.Member;

import ROOT.Utils.APIServerInfo;
import ROOT.Utils.CryptoUtils;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    RestOperations restOperations;

    /**
     * 로그인
     */
    @Override
    public Member login(Member member) {
        Member loginMember = new Member();
        try {
            loginMember = restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/login", member, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginMember;
    }

    /**
     * 회원가입
     */
    @Override
    public Member join(Member member) {
        Member joinMember = new Member();
        try {
            String encrypted = CryptoUtils.encryptAES256(member.getPassword(), member.getPassword().hashCode() + "");
            member.setPassword(encrypted);
            joinMember = restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/join", member, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinMember;
    }

    /**
     * 로그아웃
     */
    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**
     * 이메일(아이디) 중복확인
     */
    @Override
    public String idCheck(String memberId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/idCheck?memberId=" + memberId, String.class);
    }

    /**
     * 닉네임 중복확인
     */
    @Override
    public String nicknameCheck(String nickname) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/nicknameCheck?nickname=" + nickname, String.class);
    }

    /**
     * 이메일 중복확인
     */
    @Override
    public String emailCheck(String email) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/emailCheck?email=" + email, String.class);
    }

    /**
     * 이메일을 이용해 회원 아이디 가져오기
     */
    @Override
    public String getUserIDUsingEmail(String email) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/memberId?email=" + email, String.class);
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public void changePassword(Member member) {
        restOperations.put(APIServerInfo.API_SERVER_CONTEXT + "/member/password", member, Member.class);
    }

    /**
     * 회원가입 시, 로그인 잠금시간 추가 및 초기화
     */
    @Override
    public void insertLoginLockTime(MemberLoginLock loginLock) {
        restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/login/lockTime", loginLock, Void.class);
    }

    /**
     * 회원 계정 로그인 잠금시간 확인
     */
    @Override
    public MemberLoginLock checkLoginLockTime(String memberId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/login/lockTime/" + memberId, MemberLoginLock.class);
    }

    /**
     * 로그인 잠금시간 업데이트
     */
    @Override
    public void updateLoginLockTime(MemberLoginLock loginLock) {
        restOperations.put(APIServerInfo.API_SERVER_CONTEXT + "/member/login/lockTime", loginLock, Void.class);
    }

    /**
     * 로그인 이력 기록
     */
    @Override
    public void insertLoginHistory(MemberLoginHistory loginHistory) {
        restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/login/history", loginHistory, Void.class);
    }

    /**
     * 최근 5회 로그인 이력 가져오기
     */
    @Override
    public List<MemberLoginHistory> getRecentLoginHistoryList(String memberId) {
        MemberLoginHistory[] arr = restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/member/login/history/" + memberId, MemberLoginHistory[].class);
        return Arrays.asList(Objects.requireNonNull(arr));
    }

/**
 * 닉네임 변경
 *//*

    @Override
    public int changeNickname(String memberId, String nickName) {
        return memberDAO.changeNickname(memberId, nickName);
    }

    */
/**
 * 회원 탈퇴
 *//*

    @Override
    public int withdraw(String memberId) {
        return memberDAO.withdraw(memberId);
    }

    */
/**
 * 사용자의 차박지 찜 리스트 가져오기
 *//*

    @Override
    public List<Chabak> getJJimList(String id) {
        return memberDAO.getJJimList(id);
    }

    */
/**
 * 차박지 찜
 *//*

    @Override
    public String jjimDo(String id, String placeName, int placeId) {
        return memberDAO.jjimDo(id, placeId, placeName);
    }

    */
/**
 * 차박지 찜 취소
 *//*

    @Override
    public String jjimUndo(String id, String placeName, int placeId) {
        return memberDAO.jjimUndo(id, placeId);
    }

    */
/**
 * 사용자가 특정 차박지 평가 여부 확인
 *//*

    @Override
    public String getJJimAndEvaluated(String memberId, String placeId) {
        String isJJim = memberDAO.isJJim(memberId, placeId);
        String isEvaluated = memberDAO.isEvaluated(memberId, placeId);

        return isJJim + " " + isEvaluated;
    }

    */
/**
 * 사용자가 작성한 리뷰 가져오기
 *//*

    @Override
    public List<Review> getUsersReview(String memberId) {
        return memberDAO.getUsersReview(memberId);
    }
*/
}
