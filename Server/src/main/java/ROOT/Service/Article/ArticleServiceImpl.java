package ROOT.Service.Article;

import ROOT.DAO.ArticleDAO;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    private final String filePath = "/resources/article/";

    @Autowired
    ArticleDAO articleDAO;

    /**
     * 게시글 작성
     */
    @Override
    public int writeArticle(String memberId, String title, String content, String fileName) {
        String urlPath = "";
        if (!fileName.equals("")) {
            urlPath = filePath + fileName;
        }
        return articleDAO.writeArticle(memberId, title, content, urlPath);
    }

    /**
     * 게시글 수정
     */
    @Override
    public int updateArticle(int articleId, String title, String content, String fileName) {
        String urlPath = "";
        if (!fileName.equals("")) {
            urlPath = filePath + fileName;
        }
        return articleDAO.updateArticle(articleId, title, content, urlPath);
    }

    /**
     * 게시글 삭제
     */
    @Override
    public int deleteArticle(int articleId) {
        return articleDAO.deleteArticle(articleId);
    }

    /**
     * 게시글 리스트 읽어오기
     */
    @Override
    public List<Article> get() {
        return articleDAO.get();
    }

    /**
     * 게시글 하나 읽기
     */
    @Override
    public List<Article> getArticle(int articleId) {
        return articleDAO.getArticle(articleId);
    }

    /**
     * 댓글 리스트 읽기
     */
    @Override
    public List<Comment> getComments(int articleId) {
        return articleDAO.getComments(articleId);
    }

    /**
     * 댓글 쓰기
     */
    @Override
    public int writeComment(int articleId, String memberId, String content) {
        return articleDAO.writeComment(articleId, memberId, content);
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
        return articleDAO.get().stream().filter(article -> {
            String text = article.getContent() + article.getTitle();
            return text.contains(key);
        }).collect(Collectors.toList());
    }
}
