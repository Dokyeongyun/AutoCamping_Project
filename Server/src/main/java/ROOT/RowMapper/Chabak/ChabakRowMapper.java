package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.Chabak;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChabakRowMapper implements RowMapper<Chabak> {
    @Override
    public Chabak mapRow(ResultSet rs, int rowNum) throws SQLException {
        Chabak chabak = new Chabak();
        chabak.setPlaceId(rs.getInt("placeId"));
        chabak.setPlaceName(rs.getString("placeName"));
        chabak.setAddress(rs.getString("address"));
        chabak.setPhone_number(rs.getString("phone_number"));
        chabak.setIntroduce(rs.getString("introduce"));
        chabak.setFilePath(rs.getString("filepath"));
        chabak.setJjim(rs.getInt("jjim"));
        chabak.setAvg_point(rs.getDouble("avg_point"));
        chabak.setLatitude(rs.getDouble("ST_X(geom)"));
        chabak.setLongitude(rs.getDouble("ST_Y(geom)"));

        Map<String, Integer> utility = new HashMap<>();
        utility.put("toilet", rs.getInt("toiletCount"));
        utility.put("fishing", rs.getInt("fishingCount"));
        chabak.setUtilityCount(utility);
        return chabak;
    }
}
