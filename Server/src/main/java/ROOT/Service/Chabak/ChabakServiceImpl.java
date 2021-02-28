package ROOT.Service.Chabak;

import ROOT.DAO.ChabakDAO;
import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Chabak.facility.Fishing;
import ROOT.VO.Chabak.facility.Toilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("ChabakService")
public class ChabakServiceImpl implements ChabakService {

    @Autowired
    ChabakDAO chabakDAO;

    /**
     * 모든 차박지 리스트
     */
    @Override
    public List<Chabak> getAllChabakList() { return chabakDAO.getAllChabakList(); }

    /**
     * 하나의 차박지 정보
     */
    @Override
    public List<Chabak> getOne(int placeId) { return chabakDAO.getOne(placeId); }

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    @Override
    public List<Chabak> getPopularList() { return chabakDAO.getPopularList(); }

    /**
     * 차박지별 화장실 정보
     */
    @Override
    public List<Toilet> getToilets(int placeId) { return chabakDAO.getToilets(placeId); }

    /**
     * 차박지별 낚시터 정보
     */
    @Override
    public List<Fishing> getFishings(int placeId) { return chabakDAO.getFishings(placeId); }

    /**
     * 차박지 평가 및 리뷰 작성
     */
    @Override
    public int userEval(String memberId, int placeId, String placeName, double eval, String review) {
        return chabakDAO.userEval(memberId, placeId, placeName, eval, review);
    }

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     */
    @Override
    public List<Chabak> getFilteredList(String add, String flags) {
        String[] addresses = add.split("/");
        String[] split = flags.split("/");
        return chabakDAO.getFilteredList(addresses, split);
    }

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    @Override
    public List<Chabak> getProvinceChabakList(String province) {
        return chabakDAO.getProvinceChabakList(province);
    }

    /**
     * 차박지 등록하기
     */
    @Override
    public int suggest(String placeName, String address, String introduce,
                          String phone, String urlPath, double latitude, double longitude) {
        return chabakDAO.suggest(placeName, address, introduce, phone, urlPath, latitude, longitude);
    }

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    @Override
    public List<Review> getReviews(int placeId) {
        return chabakDAO.getReviews(placeId);
    }

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    @Override
    public Map<String, BestAndCount> getBestAndCount() {
        return chabakDAO.getBestAndCount();
    }

    /**
     * 키워드를 통한 차박지 검색
     */
    @Override
    public List<Chabak> getChabaksByKeyword(String key) {
        return chabakDAO.getAllChabakList().stream().filter(cha -> {
            String text = cha.getAddress() + cha.getIntroduce() + cha.getPhone_number() +
                    cha.getPlaceName() + cha;
            return text.contains(key);
        }).collect(Collectors.toList());
    }
}
