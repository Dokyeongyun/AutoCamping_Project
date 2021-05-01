package ROOT.Service.Member;

import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//
//
//    /**
//     * 닉네임 중복확인
//     */
//    String nickDoubleCheck(String nickName);
//
//    /**
//     * 이메일(아이디) 중복확인
//     */
//    String idDoubleCheck(String memberId);
//
//    /**
//     * 비밀번호 변경
//     */
//    int changePassword(String memberId, String password);
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
//     * 차박지 찜
//     */
//    String jjimDo(String id, String placeName, int placeId);
//
//    /**
//     * 차박지 찜 취소
//     */
//    String jjimUndo(String id, String placeName, int placeId);
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
