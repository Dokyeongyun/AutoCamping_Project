package ROOT.Service.Board;

import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    RestOperations restOperations;

    /**
     * 게시판 메인화면
     */
    @Override
    public String main(Model model) {
//        List<Article> list = articleDAO.get();
//        model.addAttribute("allArticleList", list);
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
//        List<Article> article = articleDAO.getArticle(articleId);
//        List<Comment> commentList = articleDAO.getComments(articleId);
//        model.addAttribute("article", article);
//        model.addAttribute("commentList", commentList);
        return "/board/showArticle";
    }

    /**
     * 게시글 수정하기
     * @param articleId 수정할 게시글 ID
     * @param model View로 보여줄 게시글 정보
     * @return View Name
     */
    @Override
    public String modifyArticle(int articleId, Model model) {
//        List<Article> article = articleDAO.getArticle(articleId);
//        model.addAttribute("article", article);
        return "/board/modifyArticle";
    }

    /**
     * 게시글 검색하기 (제목+내용)
     */
    @Override
    public String getArticleByKeyword(String keyword, Model model) {
//        List<Article> list = articleDAO.get().stream().filter(article -> {
//            String text = article.getContent() + article.getTitle();
//            return text.contains(keyword);
//        }).collect(Collectors.toList());
//        model.addAttribute("allArticleList", list);
        return "/board/boardSearchResult";
    }

    /**
     * 내가 쓴 글 보기
     * @param model
     * @return
     */
    @Override
    public String getArticleByKeyword(Model model, HttpSession session) {
//        List<Article> list = articleDAO.getArticles((String) session.getAttribute("id"));
//        model.addAttribute("myArticleList", list);
        return "/board/showMyArticle";
    }
}
