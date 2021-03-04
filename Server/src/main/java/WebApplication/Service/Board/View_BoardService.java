package WebApplication.Service.Board;

import ROOT.VO.Article.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public interface View_BoardService {

    /**
     * 게시판 메인화면
     */
    @RequestMapping("/main")
    String main(Model model);

    /**
     * 게시글 작성하기
     */
    @RequestMapping("/writeArticle")
    String writeArticle();

    /**
     * 게시글 읽기
     */
    @RequestMapping("/showArticle/{articleId}")
    String showArticle(@PathVariable int articleId, Model model);

    /**
     * 게시글 수정하기
     */
    @RequestMapping("/modifyArticle/{articleId}")
    String modifyArticle(@PathVariable int articleId, Model model);

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
