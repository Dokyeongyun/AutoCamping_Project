package ROOT.Service.Chabak;

import ROOT.Utils.APIServerInfo;
import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Facility.Fishing;
import ROOT.VO.Facility.Toilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service("ChabakService")
public class ChabakServiceImpl implements ChabakService {

    @Autowired
    RestOperations restOperations;

    /**
     * 모든 차박지 리스트
     */
    @Override
    public List<Chabak> getAllChabakList() {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/chabaks", List.class);
    }

    /**
     * 하나의 차박지 정보
     */
    @Override
    public Chabak getOne(int placeId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/" + placeId, Chabak.class);
    }

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    @Override
    public List<Chabak> getPopularList(String sortBy) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/popular/" + sortBy, List.class);
    }

    /**
     * 차박지별 화장실 정보
     */
    @Override
    public List<Toilet> getToilets(int placeId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/toilets/" + placeId, List.class);
    }

    /**
     * 차박지별 낚시터 정보
     */
    @Override
    public List<Fishing> getFishings(int placeId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/fishings/" + placeId, List.class);
    }

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     * @return
     */
    @Override
    public Chabak[] chabakSearch(String region, String facility) {
        return restOperations.getForObject(
                UriComponentsBuilder.fromUriString(APIServerInfo.API_SERVER_CONTEXT + "/chabak/search")
                        .queryParam("region", region)
                        .queryParam("facility", facility)
                        .build().toUriString(), Chabak[].class);
    }

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    @Override
    public List<Chabak> getProvinceChabakList(String provinceName) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/province/" + provinceName, List.class);
    }

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    @Override
    public List<Review> getReviews(int placeId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/reviews/" + placeId, List.class);
    }

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    @Override
    public Map<String, BestAndCount> getBestAndCount() {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/chabak/bestAndCount", Map.class);
    }
}
