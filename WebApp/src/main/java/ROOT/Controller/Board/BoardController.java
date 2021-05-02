package ROOT.Controller.Board;

import ROOT.Service.Board.BoardService;
import ROOT.VO.Article.Article;
import ROOT.VO.Form.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @ModelAttribute
    ArticleForm setUpArticleForm() {
        return new ArticleForm();
    }

    /**
     * 게시판 메인화면
     */
    @GetMapping("/main")
    public String main(Model model) {
        List<Article> allArticleList = boardService.getAllArticleList();
        model.addAttribute("allArticleList", allArticleList);
        return "/board/boardMain";
    }

    /**
     * 게시글 작성 화면
     */
    @GetMapping("/articleWriteForm")
    public void articleWriteForm() {
    }

    /**
     * 게시글 수정 화면
     */
    @GetMapping("/articleUpdateForm/{articleId}")
    public String articleUpdateForm(@PathVariable int articleId, Model model) {
        Article article = boardService.getArticle(articleId);
        model.addAttribute("article", article);
        return "/board/articleUpdateForm";
    }

    /**
     * 게시글 작성
     */
    @PostMapping("/article")
    public String writeArticle(@Validated ArticleForm form,
                               BindingResult result,
                               Errors errors,
                               Model model
    ) {
        // 입력값 검사 실패 시
        if (result.hasErrors() || errors.hasErrors()) {
            return "/board/articleWriteForm";
        }

        Article article = new Article();
        article.setMemberId(form.getMemberId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setUrlPath(form.getPath());

        Integer writeResult = boardService.writeArticle(article);

        if (writeResult != 1) {
            model.addAttribute("writeResult", "Fail");
            return "board/articleWriteForm";
        }

        return "redirect:/board/main";
    }

    /**
     * 게시글 읽기
     */
    @GetMapping("/article/{articleId}")
    public String showArticle(@PathVariable int articleId, Model model) {
        Article article = boardService.getArticle(articleId);
        model.addAttribute("article", article);
        return "/board/showArticle";
    }

    /**
     * 게시글 수정하기
     */
    @PutMapping("/article/{articleId}")
    public String updateArticle(@PathVariable int articleId,
                                @Validated ArticleForm form,
                                BindingResult result
    ) {

        if (result.hasErrors()) {
            return "redirect:/board/articleUpdateForm/" + articleId;
        }

        Article updateArticle = new Article();
        updateArticle.setArticleId(articleId);
        updateArticle.setMemberId(form.getMemberId());
        updateArticle.setTitle(form.getTitle());
        updateArticle.setContent(form.getContent());
        updateArticle.setUrlPath(form.getPath());

        boardService.updateArticle(updateArticle);

        return "redirect:/board/article/" + articleId;
    }

    /**
     * 게시글 삭제하기
     */
    @DeleteMapping("/article/{articleId}")
    public String deleteArticle(@PathVariable int articleId){
        boardService.deleteArticle(articleId);
        return "redirect:/board/main";
    }
}
