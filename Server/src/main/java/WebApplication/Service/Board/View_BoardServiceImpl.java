package WebApplication.Service.Board;

import ROOT.DAO.ArticleDAO;
import ROOT.VO.Article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service("View_BoardService")
public class View_BoardServiceImpl implements View_BoardService{

    @Autowired
    ArticleDAO articleDAO;

    /**
     * 게시판 메인화면
     */
    @Override
    public String main(Model model) {
        List<Article> list = articleDAO.get();
        model.addAttribute("allArticleList", list);
        return "/board/boardMain";
    }

    /**
     * 게시글 작성하기
     */
    @Override
    public String writeArticle() {
        return "/board/writeArticle";
    }
}
