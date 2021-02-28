package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.BestAndCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BestAndCountRowMapper implements RowMapper<BestAndCount> {
    @Override
    public BestAndCount mapRow(ResultSet rs, int i) throws SQLException {
        BestAndCount bestAndCount = new BestAndCount();
        bestAndCount.setCityProvince(rs.getString("city_province"));
        bestAndCount.setBestPlaceName(rs.getString("PlaceName"));
        bestAndCount.setBestPlaceImage(rs.getString("filepath"));
        bestAndCount.setCount(rs.getInt("count(*)"));
        bestAndCount.setNumOfJJIM(rs.getInt("avg_point"));
        return bestAndCount;
    }
}
