package WebApplication.Service.Board;

import ROOT.VO.Article.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
