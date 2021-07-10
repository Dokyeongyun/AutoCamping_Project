package ROOT.Service.Member;

import ROOT.Utils.APIServerInfo;
import ROOT.Utils.CryptoUtil;
import ROOT.VO.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpSession;

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
            String encrypted = CryptoUtil.encryptAES256(member.getPassword(), member.getPassword().hashCode() + "");
            member.setPassword(encrypted);
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
            String encrypted = CryptoUtil.encryptAES256(member.getPassword(), member.getPassword().hashCode() + "");
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
 * 비밀번호 변경
 *//*

    @Override
    public int changePassword(String memberId, String password) {
        return memberDAO.changePassword(memberId, password);
    }

    */
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
