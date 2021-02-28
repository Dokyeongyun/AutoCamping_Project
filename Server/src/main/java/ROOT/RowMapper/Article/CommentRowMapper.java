package ROOT.RowMapper.Article;

import ROOT.VO.Article.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("commentId"));
        comment.setArticleId(rs.getInt("articleId"));
        comment.setMemberId(rs.getString("memberId"));
        comment.setNickName(rs.getString("nickName"));
        comment.setContent(rs.getString("content"));
        comment.setCreateTime(rs.getString("createTime"));
        return comment;
    }
}
