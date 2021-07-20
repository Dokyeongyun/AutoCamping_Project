package ROOT.Service.Member;

import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Member.Member;
import ROOT.VO.Member.MemberLoginHistory;
import ROOT.VO.Member.MemberLoginLock;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/member")
public interface MemberService {

    /**
     * 로그인
     */
    @PostMapping("/login")
    Member login(@RequestBody Member member);

    /**
     * 로그아웃
     */
    @RequestMapping("/logout.do")
    String logout(HttpSession session);

    /**
     * 회원가입
     */
    @PostMapping("/join")
    Member join(@RequestBody Member member);

    /**
     * 아이디 중복확인
     */
    @GetMapping("/idCheck")
    String idCheck(@RequestParam String memberId);

    /**
     * 닉네임 중복확인
     */
    @GetMapping("/nicknameCheck")
    String nicknameCheck(@RequestParam String nickname);

    /**
     * 이메일 중복확인
     */
    @GetMapping("/emailCheck")
    String emailCheck(@RequestParam String email);

    /**
     * 이메일을 이용해 회원 아이디 가져오기
     */
    @GetMapping("/memberId")
    String getUserIDUsingEmail(String email);

    /**
     * 비밀번호 변경
     */
    @PutMapping("/password")
    int changePassword(@RequestBody Member member);

    /**
     * 회원가입 시, 로그인 잠금시간 추가 및 초기화
     */
    @PostMapping("/login/lockTime")
    void insertLoginLockTime(@RequestBody MemberLoginLock loginLock);

    /**
     * 회원 계정 로그인 잠금시간 확인
     */
    @GetMapping("/login/lockTime/{memberId}")
    MemberLoginLock checkLoginLockTime(@PathVariable String memberId);

    /**
     * 로그인 잠금시간 업데이트
     */
    @PutMapping("/login/lockTime")
    void updateLoginLockTime(@RequestBody MemberLoginLock loginLock);

    /**
     * 로그인 이력 기록
     */
    @PostMapping("/login/history")
    void insertLoginHistory(@RequestBody MemberLoginHistory loginHistory);

    /**
     * 최근 5회 로그인 이력 가져오기
     */
    @GetMapping("/login/history/{memberId}")
    List<MemberLoginHistory> getRecentLoginHistoryList(@PathVariable String memberId);

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
