package ROOT.Service.Chabak;

import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Facility.Fishing;
import ROOT.VO.Facility.Toilet;

import java.util.List;
import java.util.Map;

public interface ChabakService {

    /**
     * 모든 차박지 리스트
     */
    List<Chabak> getAllChabakList();

    /**
     * 하나의 차박지 정보
     */
    Chabak getOne(int placeId);

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    List<Chabak> getPopularList(String sortBy);

    /**
     * 차박지별 화장실 정보
     */
    List<Toilet> getToilets(int placeId);

    /**
     * 차박지별 낚시터 정보
     */
    List<Fishing> getFishings(int placeId);

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     * @return
     */
    Chabak[] chabakSearch(String region, String facility);

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    List<Chabak> getProvinceChabakList(String provinceName);

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    List<Review> getReviews(int placeId);

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    Map<String, BestAndCount> getBestAndCount();
}
