package ROOT.DAO;

import ROOT.RowMapper.Article.ArticleRowMapper;
import ROOT.RowMapper.Article.CommentRowMapper;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.ArticleFile;
import ROOT.VO.Article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 게시글 리스트 읽어오기
     */
    public List<Article> getAllArticleList() {
        String sql = "SELECT * FROM article_view ORDER BY articleId";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    /**
     * 게시글 작성
     */
    public Integer writeArticle(Article article) {
        String sql = "INSERT INTO cb_article (memberId,title,content,imagePath) VALUES (?,?,?,?) ";
        jdbcTemplate.update(sql, article.getMemberId(), article.getTitle(), article.getContent(), article.getUrlPath());
        String sql2 = "SELECT articleId FROM cb_article WHERE memberId = ? AND title = ? AND content = ? ORDER BY articleId DESC";
        return jdbcTemplate.queryForObject(sql2, Integer.class, article.getMemberId(), article.getTitle(), article.getContent());
    }

    /**
     * 게시글 수정
     */
    public int updateArticle(Article article) {
        String sql = "UPDATE cb_article SET title = ?, content = ?, imagePath = ? WHERE articleId = ?";
        return jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getUrlPath(), article.getArticleId());
    }

    /**
     * 게시글 삭제
     */
    public int deleteArticle(int articleId) {
        String sql = "UPDATE cb_article SET isDeleted = 1 WHERE articleId = ?";
        return jdbcTemplate.update(sql, articleId);
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
    public Article getArticle(int articleId) {
        String sql = "SELECT * FROM article_view WHERE articleId = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), articleId);
    }

    /**
     * 댓글 쓰기
     */
    public int writeComment(Comment comment) {
        String sql = "INSERT INTO article_comment (articleId, memberId, content) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, comment.getArticleId(), comment.getMemberId(), comment.getContent());
    }

    /**
     * 댓글 리스트 읽기
     */
    public List<Comment> getComments(int articleId) {
        String sql = "SELECT * FROM comment_view WHERE articleId = ?";
        return jdbcTemplate.query(sql, new CommentRowMapper(), articleId);
    }

    /**
     * 댓글 수정하기
     */
    public int updateComment(Comment comment) {
        String sql = "UPDATE article_comment SET content = ? WHERE commentId = ?";
        return jdbcTemplate.update(sql, comment.getContent(), comment.getCommentId());
    }

    /**
     * 댓글 삭제하기
     */
    public int deleteComment(int commentId) {
        String sql = "UPDATE article_comment SET isDeleted = 1 WHERE commentId = ?";
        return jdbcTemplate.update(sql, commentId);
    }

    /**
     * 게시글 첨부파일 입력하기
     */
    public int insertArticleFile(ArticleFile file) {
        String sql = "INSERT INTO article_file(article_id, original_filename, stored_filename, file_size) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, file.getArticleId(), file.getOriginalFilename(), file.getStoredFilename(), file.getFileSize());
    }

    /**
     * 사용자별 작성한 게시글 읽기
     */
    public List<Article> getArticles(String memberId) {
        String sql = "SELECT * FROM article_view WHERE memberId = ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), memberId);
    }
}
