package ROOT.Service.Article;

import ROOT.DAO.ArticleDAO;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    private final String filePath = "/resources/article/";

    @Autowired
    ArticleDAO articleDAO;

    /**
     * 게시글 리스트 읽어오기
     */
    @Override
    public List<Article> getAllArticleList() {
        return articleDAO.getAllArticleList();
    }

    /**
     * 게시글 작성
     */
    @Override
    public int writeArticle(Article article) {
        System.out.println(article);

        String urlPath = "";
        if (article.getUrlPath() != null && !article.getUrlPath().equals("")) {
            urlPath = filePath + article.getUrlPath();
        }
        article.setUrlPath(urlPath);

        int writeResult = articleDAO.writeArticle(article);

        if (writeResult != 1) {
            // TODO WriteFailException
        }

        return writeResult;
    }

    /**
     * 게시글 하나 읽기
     */
    @Override
    public Article getArticle(int articleId) {
        return articleDAO.getArticle(articleId);
    }

    /**
     * 게시글 수정
     */
    @Override
    public int updateArticle(int articleId, Article article) {
        String urlPath = "";
        if (!article.getUrlPath().equals("")) {
            urlPath = filePath + article.getUrlPath();
        }
        article.setUrlPath(urlPath);
        return articleDAO.updateArticle(article);
    }

    /**
     * 게시글 삭제
     */
    @Override
    public int deleteArticle(int articleId) {
        return articleDAO.deleteArticle(articleId);
    }

    /**
     * 댓글 쓰기
     */
    @Override
    public int writeComment(Comment comment) {
        return articleDAO.writeComment(comment);
    }

    /**
     * 댓글 리스트 읽기
     */
    @Override
    public List<Comment> getCommentList(int articleId) {
        return articleDAO.getComments(articleId);
    }

    /**
     * 사용자별 작성한 게시글 읽기
     */
    @Override
    public List<Article> getArticles(String memberId) {
        return articleDAO.getArticles(memberId);
    }

    /**
     * 키워드를 통한 게시글 검색
     */
    @Override
    public List<Article> getArticlesByKeyword(String key) {
        return articleDAO.getAllArticleList().stream().filter(article -> {
            String text = article.getContent() + article.getTitle();
            return text.contains(key);
        }).collect(Collectors.toList());
    }
}
