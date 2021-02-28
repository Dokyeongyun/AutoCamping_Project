package ROOT.RowMapper.Article;

import ROOT.VO.Article.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int i) throws SQLException {
        Article article = new Article();
        article.setArticleId(rs.getInt("articleId"));
        article.setMemberId(rs.getString("memberId"));
        article.setNickName(rs.getString("nickName"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setUrlPath(rs.getString("imagePath"));
        article.setCreateTime(rs.getString("createTime"));
        return article;
    }
}
