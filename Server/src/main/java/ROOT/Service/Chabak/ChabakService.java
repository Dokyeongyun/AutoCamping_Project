package ROOT.Service.Chabak;

import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Chabak.facility.Fishing;
import ROOT.VO.Chabak.facility.Toilet;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/chabak")
public interface ChabakService {

    /**
     * 모든 차박지 리스트
     */
    @GetMapping(value = "/chabaks")
    List<Chabak> getAllChabakList();

    /**
     * 하나의 차박지 정보
     */
    @GetMapping("/{placeId}")
    Chabak getOne(@PathVariable int placeId);

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    @GetMapping(value = "/popular/{sortBy}")
    List<Chabak> getPopularList(@PathVariable String sortBy);

    /**
     * 차박지별 화장실 정보
     */
    @GetMapping("/toilets/{placeId}")
    List<Toilet> getToilets(@PathVariable int placeId);

    /**
     * 차박지별 낚시터 정보
     */
    @GetMapping("/fishings/{placeId}")
    List<Fishing> getFishings(@PathVariable int placeId);

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     */
    @GetMapping( "/search")
    List<Chabak> getFilteredList(@RequestParam String region, @RequestParam String facility);

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    @GetMapping("/province/{provinceName}")
    List<Chabak> getProvinceChabakList(@PathVariable String provinceName);

    /**
     * 차박지 등록하기
     */
    @RequestMapping(value = "/suggest.do")
    int suggest(String placeName, String address, String introduce, String phone,
                String urlPath, double latitude, double longitude);

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    @GetMapping("/reviews/{placeId}")
    List<Review> getReviews(@PathVariable int placeId);

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    @GetMapping("/bestAndCount")
    Map<String, BestAndCount> getBestAndCount();

    /**
     * 키워드를 통한 차박지 검색
     */
    @RequestMapping(value = "/getByKey.do")
    List<Chabak> getChabaksByKeyword(String key);
}
