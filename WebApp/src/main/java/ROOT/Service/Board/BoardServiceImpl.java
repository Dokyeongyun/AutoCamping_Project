package ROOT.Service.Board;

import ROOT.Utils.APIServerInfo;
import ROOT.VO.Article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    RestOperations restOperations;

    /**
     * 모든 게시글 가져오기
     *
     * @return 모든 게시글 리스트
     */
    @Override
    public List<Article> getAllArticleList() {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/board/articles", List.class);
    }

    /**
     * 게시글 작성하기
     *
     * @param article INSERT 게시글 정보
     * @return INSERT 결과
     */
    @Override
    public Integer writeArticle(Article article) {
        return restOperations.postForObject(APIServerInfo.API_SERVER_CONTEXT + "/board/article", article, Integer.class);
    }

    /**
     * 게시글 읽기
     *
     * @param articleId 게시글 번호
     * @return Article
     */
    @Override
    public Article getArticle(int articleId) {
        return restOperations.getForObject(APIServerInfo.API_SERVER_CONTEXT + "/board/article/" + articleId, Article.class);
    }

    /**
     * 게시글 수정하기
     */
    @Override
    public void updateArticle(Article article) {
        restOperations.put(APIServerInfo.API_SERVER_CONTEXT + "/board/article/" + article.getArticleId(), article);
    }

    /**
     * 게시글 삭제하기
     */
    @Override
    public void deleteArticle(int articleId) {
        restOperations.delete(APIServerInfo.API_SERVER_CONTEXT + "/board/article/" + articleId);
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
     *
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
