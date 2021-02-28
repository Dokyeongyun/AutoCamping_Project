package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.facility.Toilet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToiletRowMapper implements RowMapper<Toilet> {
    @Override
    public Toilet mapRow(ResultSet rs, int i) throws SQLException {
        Toilet toilet = new Toilet();
        toilet.setToiletId(rs.getInt("toiletId"));
        toilet.setAddress(rs.getString("address"));
        toilet.setOpen_time(rs.getString("open_time"));
        toilet.setLatitude(rs.getDouble("ST_X(geom)"));
        toilet.setLongitude(rs.getDouble("ST_Y(geom)"));
        return toilet;
    }
}
