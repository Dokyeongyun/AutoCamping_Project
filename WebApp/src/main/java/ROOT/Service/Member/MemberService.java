package ROOT.Service.Member;

import ROOT.VO.Chabak.ChabakDibs;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {

    /**
     * 로그인
     */
    Member login(Member member);

    /**
     * 회원가입
     */
    Member join(Member member);

    /**
     * 로그아웃
     */
    void logout(HttpSession session);

    /**
     * 이메일(아이디) 중복확인
     */
    String idCheck(String memberId);

    /**
     * 닉네임 중복확인
     */
    String nicknameCheck(String nickname);

    /**
     * 이메일 중복확인
     */
    String emailCheck(String email);

    /**
     * 이메일을 이용해 회원 아이디 가져오기
     */
    String getUserIDUsingEmail(String email);

    /**
     * 비밀번호 변경
     */
    void changePassword(Member member);

    /**
     * 회원가입 시, 로그인 잠금시간 추가 및 초기화
     */
    void insertLoginLockTime(MemberLoginLock loginLock);

    /**
     * 회원 계정 로그인 잠금시간 확인
     */
    MemberLoginLock checkLoginLockTime(String memberId);

    /**
     * 로그인 잠금시간 업데이트
     */
    void updateLoginLockTime(MemberLoginLock loginLock);

    /**
     * 로그인 이력 기록
     */
    void insertLoginHistory(MemberLoginHistory loginHistory);

    /**
     * 최근 5회 로그인 이력 가져오기
     */
    List<MemberLoginHistory> getRecentLoginHistoryList(String memberId);

    /**
     * 차박지 찜 상태 가져오기
     */
    Boolean getChabakDibsStatus(ChabakDibs dibs);

    /**
     * 차박지 찜
     */
    void dibsChabak(ChabakDibs dibs);

    /**
     * 차박지 찜 취소
     */
    void unDibsChabak(ChabakDibs dibs);

    /**
     * 차박지 리뷰 작성
     */
    Integer writeChabakReview(Review review);

//
//    /**
//     * 닉네임 변경
//     */
//    int changeNickname(String memberId, String nickName);
//
//    /**
//     * 회원 탈퇴
//     */
//    int withdraw(String memberId);
//
//    /**
//     * 사용자의 차박지 찜 리스트 가져오기
//     */
//    List<Chabak> getJJimList(String id);
//
//    /**
//     * 사용자의 특정 차박지 찜, 평가 여부 가져오기
//     */
//    String getJJimAndEvaluated(String memberId, String placeId);
//
//    /**
//     * 사용자가 작성한 리뷰 가져오기
//     */
//    List<Review> getUsersReview(String memberId);
}
