package ROOT.DAO;

import ROOT.RowMapper.Article.ArticleRowMapper;
import ROOT.RowMapper.Article.CommentRowMapper;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArticleDAO(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    /**
     * 게시글 작성
     */
    public int writeArticle(String memberId, String title, String content, String path) {
        String sql = "insert into cb_article (memberId,title,content,imagePath) values (?,?,?,?) ";
        return jdbcTemplate.update(sql, memberId, title, content, path);
    }

    /**
     * 게시글 수정
     */
    public int updateArticle(int articleId, String title, String content, String path) {
        String sql = "UPDATE cb_article SET title = ?, content = ?, imagePath = ? WHERE articleId = ?";
        return jdbcTemplate.update(sql, title, content, path, articleId);
    }

    /**
     * 게시글 삭제
     */
    public int deleteArticle(int articleId) {
        String sql = "UPDATE cb_article SET isDeleted = 1 WHERE articleId = ?";
        return jdbcTemplate.update(sql, articleId);
    }

    /**
     * 게시글 리스트 읽어오기
     */
    public List<Article> get() {
        String sql = "SELECT * FROM article_view ORDER BY articleId";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    /**
     * 페이징처리 위해 가장 마지막 articleId 가져오기
     */
    public int getNext() {
        String sql = "SELECT articleId FROM cb_article ORDER BY articleId DESC";
        List<Integer> result = jdbcTemplate.query(sql, (resultSet, i) -> resultSet.getInt(1));
        return result.get(0);
    }

    /**
     * 게시글 하나 읽기
     */
    public List<Article> getArticle(int articleId) {
        String sql = "SELECT * FROM article_view WHERE articleId = ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), articleId);
    }

    /**
     * 댓글 리스트 읽기
     */
    public List<Comment> getComments(int articleId) {
        String sql = "SELECT * FROM comment_view WHERE articleId = ?";
        return jdbcTemplate.query(sql, new CommentRowMapper(), articleId);
    }

    /**
     * 댓글 쓰기
     */
    public int writeComment(int articleId, String memberId, String content) {
        String sql = "INSERT INTO article_comment (articleId, memberId, content) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, articleId, memberId, content);
    }

    /**
     * 사용자별 작성한 게시글 읽기
     */
    public List<Article> getArticles(String memberId) {
        String sql = "SELECT * FROM article_view WHERE memberId = ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), memberId);
    }
}
