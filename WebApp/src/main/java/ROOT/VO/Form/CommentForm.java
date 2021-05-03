package ROOT.VO.Form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentForm {

    @NotNull
    private int articleId;

    @NotNull
    private String memberId;

    @NotNull
    @NotBlank
    private String content;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
