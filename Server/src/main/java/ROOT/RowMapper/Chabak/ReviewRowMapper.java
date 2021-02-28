package ROOT.RowMapper.Chabak;

import ROOT.VO.Chabak.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int i) throws SQLException {
        Review review = new Review();
        review.setPlaceId(rs.getInt("placeId"));
        review.setMemberId(rs.getString("memberId"));
        review.setNickName(rs.getString("nickName"));
        review.setReview_content(rs.getString("review_content"));
        review.setEvaluation_point(rs.getDouble("evaluation_point"));
        review.setEval_time(rs.getString("eval_time"));
        return review;
    }
}
