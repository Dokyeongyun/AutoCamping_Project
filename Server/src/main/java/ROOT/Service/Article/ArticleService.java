package ROOT.Service.Article;

import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/article")
public interface ArticleService {

    /**
     * 게시글 작성
     */
    @RequestMapping(value = "/writeArticle.do")
    int writeArticle(String memberId, String title, String content, String fileName);

    /**
     * 게시글 수정
     */
    @RequestMapping(value = "/updateArticle.do")
    int updateArticle(int articleId, String title, String content, String fileName);

    /**
     * 게시글 삭제
     */
    @RequestMapping(value = "/deleteArticle.do")
    int deleteArticle(int articleId);

    /**
     * 게시글 리스트 읽어오기
     */
    @RequestMapping(value = "/get.do")
    List<Article> get();

    /**
     * 게시글 하나 읽기
     */
    @RequestMapping(value = "/getArticle.do")
    List<Article> getArticle(int articleId);

    /**
     * 댓글 리스트 읽기
     */
    @RequestMapping(value = "/getComments.do")
    List<Comment> getComments(int articleId);

    /**
     * 댓글 쓰기
     */
    @RequestMapping(value = "/writeComment.do")
    int writeComment(int articleId, String memberId, String content);

    /**
     * 사용자별 작성한 게시글 읽기
     */
    @RequestMapping(value = "/getArticles.do")
    List<Article> getArticles(String memberId);

    /**
     * 키워드를 통한 게시글 검색
     */
    @RequestMapping(value = "/getArticleByKeyword.do")
    List<Article> getArticlesByKeyword(String key);
}
