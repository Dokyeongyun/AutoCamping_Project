package ROOT.Service.Article;

import ROOT.VO.Article.Article;
import ROOT.VO.Article.ArticleFile;
import ROOT.VO.Article.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public interface ArticleService {

    /**
     * 게시글 리스트 읽어오기
     */
    @GetMapping("/articles")
    List<Article> getAllArticleList();

    // TODO INSERT 후 INSERT된 값 SELECT 하기, 반환값 ResponseEntity<> 이용

    /**
     * 게시글 작성
     */
    @PostMapping("/article")
    int writeArticle(@RequestBody Article article);

    /**
     * 게시글 하나 읽기
     */
    @GetMapping("/article/{articleId}")
    Article getArticle(@PathVariable int articleId);

    /**
     * 게시글 수정
     */
    @PutMapping("/article/{articleId}")
    int updateArticle(@PathVariable int articleId, @RequestBody Article article);

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/article/{articleId}")
    int deleteArticle(@PathVariable int articleId);

    /**
     * 댓글 쓰기
     */
    @PostMapping( "/comment")
    int writeComment(@RequestBody Comment comment);

    /**
     * 댓글 리스트 읽기
     */
    @GetMapping("/comments/{articleId}")
    List<Comment> getCommentList(@PathVariable int articleId);

    /**
     * 댓글 수정하기
     */
    @PutMapping("/comment")
    int updateComment(@RequestBody Comment comment);

    /**
     * 댓글 삭제하기
     */
    @DeleteMapping("/comment/{commentId}")
    int deleteComment(@PathVariable int commentId);

    /**
     * 게시글 첨부파일 입력하기
     */
    @PostMapping("/articleFile")
    int insertArticleFile(@RequestBody ArticleFile file);

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
