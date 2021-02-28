package ROOT.Service.Chabak;

import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Chabak.facility.Fishing;
import ROOT.VO.Chabak.facility.Toilet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/chabak")
public interface ChabakService {

    /**
     * 모든 차박지 리스트
     */
    @RequestMapping(value = "/get.do")
    List<Chabak> getAllChabakList();

    /**
     * 하나의 차박지 정보
     */
    @RequestMapping(value = "/getOne.do")
    List<Chabak> getOne(int placeId);

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    @RequestMapping(value = "/getPopularList.do")
    List<Chabak> getPopularList();

    /**
     * 차박지별 화장실 정보
     */
    @RequestMapping(value = "/getToilets.do")
    List<Toilet> getToilets(int placeId);

    /**
     * 차박지별 낚시터 정보
     */
    @RequestMapping(value = "/getFishings.do")
    List<Fishing> getFishings(int placeId);

    /**
     * 차박지 평가 및 리뷰 작성
     */
    @RequestMapping(value = "/eval.do")
    int userEval(String memberId, int placeId, String placeName, double eval, String review);

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     */
    @RequestMapping(value = "/filter.do")
    List<Chabak> getFilteredList(String add, String flags);

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    @RequestMapping(value = "/getProvinceChabakList.do")
    List<Chabak> getProvinceChabakList(String province);

    /**
     * 차박지 등록하기
     */
    @RequestMapping(value = "/suggest.do")
    int suggest(String placeName, String address, String introduce, String phone,
                          String urlPath, double latitude, double longitude);

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    @RequestMapping(value = "/getReviews.do")
    List<Review> getReviews(int placeId);

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    Map<String, BestAndCount> getBestAndCount();

    /**
     * 키워드를 통한 차박지 검색
     */
    @RequestMapping(value = "/getByKey.do")
    List<Chabak> getChabaksByKeyword(String key);
}
