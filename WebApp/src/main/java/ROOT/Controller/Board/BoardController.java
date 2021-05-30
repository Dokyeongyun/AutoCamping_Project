package ROOT.Controller.Board;

import ROOT.Service.Board.BoardService;
import ROOT.Service.Board.FileUploadService;
import ROOT.VO.Article.Article;
import ROOT.VO.Article.ArticleFile;
import ROOT.VO.Article.Comment;
import ROOT.VO.Form.ArticleForm;
import ROOT.VO.Form.CommentForm;
import ROOT.VO.Form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    FileUploadService fileUploadService;

    @ModelAttribute
    LoginForm setUpLoginForm() { return new LoginForm(); }

    @ModelAttribute
    ArticleForm setUpArticleForm() {
        return new ArticleForm();
    }

    @ModelAttribute
    CommentForm setUpCommentForm() {
        return new CommentForm();
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

        // 게시글 정보 생성
        Article article = new Article();
        article.setMemberId(form.getMemberId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setUrlPath(form.getPath());

        // 게시글 작성
        Integer createdArticleId = boardService.writeArticle(article);

        // Multipart 요청에서 파일 꺼내오기
        List<MultipartFile> files = form.getFiles();

        // 다중 파일 업로드
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);

            // 게시글에 첨부된 파일 업로드
            String storedFileName = fileUploadService.uploadFile(file, createdArticleId, i);

            ArticleFile articleFile = new ArticleFile();
            articleFile.setArticleId(createdArticleId);
            articleFile.setOriginalFilename(file.getOriginalFilename());
            articleFile.setStoredFilename(storedFileName);
            articleFile.setFileSize(file.getSize());

            boardService.insertArticleFile(articleFile);
        }

        return "redirect:/board/article/"+createdArticleId;
    }

    /**
     * 게시글 읽기
     */
    @GetMapping("/article/{articleId}")
    public String showArticle(@PathVariable int articleId, Model model) {
        Article article = boardService.getArticle(articleId);
        List<Comment> commentList = boardService.getCommentList(articleId);
        model.addAttribute("article", article);
        model.addAttribute("commentList", commentList);
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
    public String deleteArticle(@PathVariable int articleId) {
        boardService.deleteArticle(articleId);
        return "redirect:/board/main";
    }

    /**
     * 댓글 작성하기
     */
    @PostMapping("/comment")
    public String writeComment(@Validated CommentForm form,
                               BindingResult result,
                               Model model
    ) {
        if (result.hasErrors()) {
            return "redirect:/board/article/" + form.getArticleId();
        }

        Comment comment = new Comment();
        comment.setArticleId(form.getArticleId());
        comment.setMemberId(form.getMemberId());
        comment.setContent(form.getContent());

        int writeCommentResult = boardService.writeComment(comment);
        model.addAttribute("writeCommentResult", writeCommentResult);

        return "redirect:/board/article/" + form.getArticleId();
    }

    /**
     * 댓글 수정하기
     */
    @PutMapping("/comment")
    public String updateComment(@Validated CommentForm form,
                                BindingResult result,
                                Model model
    ) {
        if (result.hasErrors()) {
            return "redirect:/board/article/" + form.getArticleId();
        }

        Comment comment = new Comment();
        comment.setCommentId(form.getCommentId());
        comment.setArticleId(form.getArticleId());
        comment.setContent(form.getContent());

        boardService.updateComment(comment);

        return "redirect:/board/article/" + form.getArticleId();
    }

    /**
     * 댓글 삭제하기
     */
    @DeleteMapping("/comment/{commentId}")
    public String deleteComment(@PathVariable int commentId, Comment comment) {
        boardService.deleteComment(commentId);

        return "redirect:/board/article/" + comment.getArticleId();
    }
}
