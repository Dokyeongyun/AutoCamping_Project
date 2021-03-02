package WebApplication.Service.Board;

import ROOT.DAO.ArticleDAO;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
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

    /**
     * 게시글 읽기 + 게시글에 포함된 댓글 리스트 가져오기
     * @param articleId 게시글 번호
     * @return 글 읽기 화면 View name
     */
    @Override
    public String showArticle(int articleId, Model model) {
        List<Article> article = articleDAO.getArticle(articleId);
        List<Comment> commentList = articleDAO.getComments(articleId);
        model.addAttribute("article", article);
        model.addAttribute("commentList", commentList);
        return "/board/showArticle";
    }
}
