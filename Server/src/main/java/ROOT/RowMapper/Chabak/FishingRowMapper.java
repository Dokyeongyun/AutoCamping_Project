package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.facility.Fishing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FishingRowMapper implements RowMapper<Fishing> {
    @Override
    public Fishing mapRow(ResultSet rs, int i) throws SQLException {
        Fishing fishing = new Fishing();
        fishing.setFishingspotId(rs.getInt("fishingspotId"));
        fishing.setName(rs.getString("name"));
        fishing.setAddress(rs.getString("address"));
        fishing.setType(rs.getString("type"));
        fishing.setPhone(rs.getString("phone"));
        fishing.setLatitude(rs.getDouble("ST_X(geom)"));
        fishing.setLongitude(rs.getDouble("ST_Y(geom)"));
        return fishing;
    }
}
