package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int i) throws SQLException {
        Review review = new Review();
        review.setPlaceId(rs.getInt("CB_REVIEW_PLC_ID"));
        review.setPlaceName(rs.getString("CB_REVIEW_PLC_NM"));
        review.setMemberId(rs.getString("CB_REVIEW_MEM_ID"));
        review.setMemberNickname(rs.getString("CB_REVIEW_MEM_NICK"));
        review.setReviewContent(rs.getString("CB_REVIEW_CONTENT"));
        review.setEvalPoint(rs.getDouble("CB_REVIEW_EVAL_PNT"));
        review.setEvalDate(rs.getString("CB_REVIEW_EVAL_DT"));
        return review;
    }
}
