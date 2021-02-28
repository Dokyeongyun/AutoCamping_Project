package ROOT.Service.Member;

import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/member")
public interface MemberService {

    /**
     * 로그인
     */
    @RequestMapping(value = "/login.do")
    String select(String id, String password);

    /**
     * 회원가입
     */
    @RequestMapping(value = "/insert.do")
    String insert(String id, String nickName, String password);

    /**
     * 닉네임 중복확인
     */
    @RequestMapping(value = "/nickDoubleCheck.do")
    String nickDoubleCheck(String nickName);

    /**
     * 이메일(아이디) 중복확인
     */
    @RequestMapping(value = "/idDoubleCheck.do")
    String idDoubleCheck(String memberId);

    /**
     * 비밀번호 변경
     */
    @RequestMapping(value = "/changePassword.do")
    int changePassword(String memberId, String password);

    /**
     * 닉네임 변경
     */
    @RequestMapping(value = "/changeNickname.do")
    int changeNickname(String memberId, String nickName);

    /**
     * 회원 탈퇴
     */
    @RequestMapping(value = "/withdraw.do")
    int withdraw(String memberId);

    /**
     * 사용자의 차박지 찜 리스트 가져오기
     */
    @RequestMapping(value = "/getJJim.do")
    List<Chabak> getJJimList(String id);

    /**
     * 차박지 찜
     */
    @RequestMapping(value = "/jjim.do")
    String jjimDo(String id, String placeName, int placeId);

    /**
     * 차박지 찜 취소
     */
    @RequestMapping(value = "/jjim.undo")
    String jjimUndo(String id, String placeName, int placeId);

    /**
     * 사용자의 특정 차박지 찜, 평가 여부 가져오기
     */
    @RequestMapping(value = "/getJJimAndEvaluated.do")
    String getJJimAndEvaluated(String memberId, String placeId);

    /**
     * 사용자가 작성한 리뷰 가져오기
     */
    @RequestMapping(value = "/getUsersReview.do")
    List<Review> getUsersReview(String memberId);
}
