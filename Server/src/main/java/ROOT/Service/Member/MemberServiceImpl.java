package ROOT.Service.Member;

import ROOT.DAO.MemberDAO;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.ChabakDibs;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
     * 이메일 중복확인
     */
    @Override
    public String emailCheck(String email) {
        return memberDAO.emailCheck(email);
    }

    /**
     * 이메일을 이용해 회원 아이디 가져오기
     */
    @Override
    public String getUserIDUsingEmail(String email) {
        return memberDAO.getUserIDUsingEmail(email);
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public int changePassword(Member member) {
        return memberDAO.changePassword(member);
    }

    /**
     * 회원가입 시, 로그인 잠금시간 추가 및 초기화
     */
    @Override
    public void insertLoginLockTime(MemberLoginLock loginLock) {
        memberDAO.insertLoginLockTime(loginLock);
    }

    /**
     * 회원 계정 로그인 잠금시간 확인
     */
    @Override
    public MemberLoginLock checkLoginLockTime(String memberId) {
        return memberDAO.checkLoginLockTime(memberId);
    }

    /**
     * 로그인 잠금시간 업데이트
     */
    @Override
    public void updateLoginLockTime(MemberLoginLock loginLock) {
        memberDAO.updateLoginLockTime(loginLock);
    }

    /**
     * 로그인 이력 기록
     */
    @Override
    public void insertLoginHistory(MemberLoginHistory loginHistory) {
        memberDAO.insertLoginHistory(loginHistory);
    }

    /**
     * 최근 5회 로그인 이력 가져오기
     */
    @Override
    public List<MemberLoginHistory> getRecentLoginHistoryList(String memberId) {
        return memberDAO.getRecentLoginHistoryList(memberId);
    }

    /**
     * 차박지 찜 상태 가져오기
     */
    @Override
    public Boolean getChabakDibsStatus(String memberId, int placeId) {
        return memberDAO.getChabakDibsStatus(memberId, placeId);
    }

    /**
     * 차박지 찜
     */
    @Override
    public void dibsChabak(ChabakDibs dibs) {
        memberDAO.dibsChabak(dibs);
    }

    /**
     * 차박지 찜 취소
     */
    @Override
    public void unDibsChabak(ChabakDibs dibs) {
        memberDAO.unDibsChabak(dibs);
    }

    /**
     * 차박지 리뷰 작성
     */
    @Override
    public int writeChabakReview(Review review) {
        return memberDAO.writeChabakReview(review);
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

/*    *//**
     * 사용자가 특정 차박지 평가 여부 확인
     *//*
    @Override
    public String getJJimAndEvaluated(String memberId, String placeId) {
        String isJJim = memberDAO.isJJim(memberId, placeId);
        String isEvaluated = memberDAO.isEvaluated(memberId, placeId);

        return isJJim + " " + isEvaluated;
    }*/

    /**
     * 사용자가 작성한 리뷰 가져오기
     */
    @Override
    public List<Review> getUsersReview(String memberId) {
        return memberDAO.getUsersReview(memberId);
    }
}
