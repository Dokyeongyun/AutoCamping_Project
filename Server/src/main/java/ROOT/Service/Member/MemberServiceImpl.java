package ROOT.Service.Member;

import ROOT.DAO.MemberDAO;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDAO memberDAO;

    /**
     * 로그인
     */
    @Override
    public Member login(Member member) {
        return memberDAO.login(member);
    }

    /**
     * 로그아웃
     */
    @Override
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    /**
     * 회원가입
     */
    @Override
    public Member join(Member member) {
        return memberDAO.join(member);
    }

    /**
     * 이메일(아이디) 중복확인
     */
    @Override
    public String idCheck(String memberId) {
        return memberDAO.idCheck(memberId);
    }

    /**
     * 닉네임 중복확인
     */
    @Override
    public String nicknameCheck(String nickname) {
        return memberDAO.nicknameCheck(nickname);
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public int changePassword(String memberId, String password) {
        return memberDAO.changePassword(memberId, password);
    }

    /**
     * 닉네임 변경
     */
    @Override
    public int changeNickname(String memberId, String nickName) {
        return memberDAO.changeNickname(memberId, nickName);
    }

    /**
     * 회원 탈퇴
     */
    @Override
    public int withdraw(String memberId) {
        return memberDAO.withdraw(memberId);
    }

    /**
     * 사용자의 차박지 찜 리스트 가져오기
     */
    @Override
    public List<Chabak> getJJimList(String id) {
        return memberDAO.getJJimList(id);
    }

    /**
     * 차박지 찜
     */
    @Override
    public String jjimDo(String id, String placeName, int placeId) {
        return memberDAO.jjimDo(id, placeId, placeName);
    }

    /**
     * 차박지 찜 취소
     */
    @Override
    public String jjimUndo(String id, String placeName, int placeId) {
        return memberDAO.jjimUndo(id, placeId);
    }

    /**
     * 사용자가 특정 차박지 평가 여부 확인
     */
    @Override
    public String getJJimAndEvaluated(String memberId, String placeId) {
        String isJJim = memberDAO.isJJim(memberId, placeId);
        String isEvaluated = memberDAO.isEvaluated(memberId, placeId);

        return isJJim + " " + isEvaluated;
    }

    /**
     * 사용자가 작성한 리뷰 가져오기
     */
    @Override
    public List<Review> getUsersReview(String memberId) {
        return memberDAO.getUsersReview(memberId);
    }
}
