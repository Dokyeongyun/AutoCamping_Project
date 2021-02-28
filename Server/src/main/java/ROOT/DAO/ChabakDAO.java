package ROOT.DAO;

import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Chabak.facility.Fishing;
import ROOT.VO.Chabak.facility.Toilet;
import ROOT.RowMapper.Chabak.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChabakDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ChabakDAO(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    /**
     * 모든 차박지 리스트
     */
    public List<Chabak> getAllChabakList(){
        String sql = "SELECT * FROM chabak_info_view";
        return jdbcTemplate.query(sql, new ChabakRowMapper());
    }

    /**
     * 하나의 차박지 정보
     */
    public List<Chabak> getOne(int placeId){
        String sql = "SELECT * FROM chabak_info_view WHERE placeId = ?";
        return jdbcTemplate.query(sql, new ChabakRowMapper(), placeId);
    }

    /**
     * 현재 인기있는 차박지 리스트 (별점 기준 상위 10개)
     */
    public List<Chabak> getPopularList(){
        String sql = "SELECT * FROM chabak_info_view ORDER BY avg_point DESC LIMIT 10";
        return jdbcTemplate.query(sql, new ChabakRowMapper());
    }

    /**
     * 차박지별 화장실 정보
     */
    public List<Toilet> getToilets(int placeId){
        String sql = "SELECT t.toiletId, address, open_time, ST_X(geom), ST_Y(geom) " +
                "FROM relation_chabak_and_toilet r, cb_toilet t " +
                "WHERE r.toiletId = t.toiletId AND placeId = ?";
        return jdbcTemplate.query(sql, new ToiletRowMapper(), placeId);
    }

    /**
     * 차박지별 낚시터 정보
     */
    public List<Fishing> getFishings(int placeId){
        String sql = "SELECT f.fishingspotId, name, address, type, phone, ST_X(geom), ST_Y(geom) " +
                "FROM relation_chabak_and_toilet r, cb_fishing f " +
                "WHERE r.fishingspotId = f.fishingspotId AND placeId = ?";
        return jdbcTemplate.query(sql, new FishingRowMapper(), placeId);
    }

    /**
     * 차박지 평가 및 리뷰 작성
     */
    public int userEval(String memberId, int placeId, String placeName, double eval, String review) {
        String sql = "INSERT INTO user_evaluation (memberId,placeId,placeName,evaluation_point,review_content)" +
                " VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE evaluation_point = ?, review_content = ?";
        return jdbcTemplate.update(sql, memberId, placeId, placeName, eval, review, eval, review);
    }

    /**
     * 사용자가 설정한 조건에 따른 차박지 필터링
     */
    public List<Chabak> getFilteredList(String[] addresses, String[] flags) {
        String queryPrefix = "SELECT * FROM chabak_info_view WHERE (";
        String querySuffix = parsingAddressQuery(addresses) + parsingFilterQuery(flags);
        String sql = queryPrefix + querySuffix;
        return jdbcTemplate.query(sql, new ChabakRowMapper());
    }

    /**
     * 주소 파싱 및 SQL문 작성
     */
    public String parsingAddressQuery (String[] addresses){
        StringBuilder addressQuery = new StringBuilder();
        for(int i=0; i<addresses.length; i++){
            if(i==addresses.length-1){
                addressQuery.append("address LIKE '%").append(addresses[i]).append("%')");
            }else{
                addressQuery.append("address LIKE '%").append(addresses[i]).append("%' OR ");
            }
        }
        return addressQuery.toString();
    }

    /**
     * 필터 파싱 및 SQL문 작성
     */
    public String parsingFilterQuery (String[] flags){
        StringBuilder filterQuery = new StringBuilder();
        if(flags[0].equals("T")){ // 화장실
            filterQuery.append("AND toiletCount != 0 ");
        }
        if(flags[1].equals("T")){ // 낚시터
            filterQuery.append("AND fishingCount != 0");
        }
        return filterQuery.toString();
    }

    /**
     * 특별시, 광역시, 도 단위 차박지 리스트
     */
    public List<Chabak> getProvinceChabakList(String province){
        String sql = "SELECT v.* FROM chabak_info_view v, cb_chabak_location l " +
                "WHERE v.placeId = l.placeId AND l.city_province = ? ORDER BY avg_point DESC";
        return jdbcTemplate.query(sql, new ChabakRowMapper(), province);
    }

    /**
     * 차박지 등록하기
     */
    public int suggest(String placeName, String address, String introduce,
                       String phone, String urlPath, double latitude, double longitude){

        String sql = "INSERT INTO cb_chabak_location " +
                "(placeName, address, phone_number, introduce, filepath, latitude, longitude, user_suggest)" +
                " values (?,?,?,?,?,?,?,?) ";
        return jdbcTemplate.update(sql, placeName, address, phone, introduce, urlPath, latitude, longitude, 1);
    }

    /**
     * 차박지별 등록된 리뷰 읽기
     */
    public List<Review> getReviews(int placeId){
        String sql = "SELECT * FROM review_view WHERE placeId = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), placeId);
    }

    /**
     * 특별시, 광역시, 도 단위의 총 차박지 개수, 인기 있는 차박지 정보
     */
    public Map<String, BestAndCount> getBestAndCount(){
        String sql = "SELECT * FROM cityProvince_bestAndCount";
        List<BestAndCount> list = jdbcTemplate.query(sql, new BestAndCountRowMapper());

        Map<String, BestAndCount> map = new HashMap<>();
        for (BestAndCount bestAndCount : list) {
            map.put(bestAndCount.getCityProvince(), bestAndCount);
        }
        return map;
    }
}
