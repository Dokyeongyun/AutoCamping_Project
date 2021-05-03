package ROOT.Service.Board;

import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {

    /**
     * 모든 게시글 읽어오기
     *
     * @return 모든 게시글 리스트
     */
    List<Article> getAllArticleList();

    /**
     * 게시글 작성하기
     */
    Integer writeArticle(Article article);

    /**
     * 게시글 읽기
     */
    Article getArticle(int articleId);

    /**
     * 게시글 수정하기
     */
    void updateArticle(Article article);

    /**
     * 게시글 삭제하기
     */
    void deleteArticle(int articleId);

    /**
     * 댓글 작성하기
     */
    Integer writeComment(Comment comment);

    /**
     * 게시글별 댓글 리스트 가져오기
     */
    List<Comment> getCommentList(int articleId);

    /**
     * 댓글 수정하기
     */
    void updateComment(Comment comment);

    /**
     * 댓글 삭제하기
     */
    void deleteComment(int commentId);

    /**
     * 게시글 검색하기 (제목+내용)
     */
    @RequestMapping("/getArticleByKeyword/{keyword}")
    String getArticleByKeyword(@PathVariable String keyword, Model model);

    /**
     * 내가 쓴 글 보기
     */
    @RequestMapping("/showMyArticle")
    String getArticleByKeyword(Model model, HttpSession session);
}
