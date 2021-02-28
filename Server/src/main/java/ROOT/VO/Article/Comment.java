package ROOT.VO.Article;

public class Comment {
    private int commentId;
    private int articleId;
    private String memberId;
    private String nickName;
    private String content;
    private String createTime;

    public Comment() { }

    public int getArticleId() { return articleId; }
    public String getMemberId() { return memberId; }
    public int getCommentId() { return commentId; }
    public String getNickName() { return nickName; }
    public String getContent() { return content; }
    public String getCreateTime() { return createTime; }

    public void setCommentId(int commentId) { this.commentId = commentId; }
    public void setArticleId(int articleId) { this.articleId = articleId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setContent(String content) { this.content = content; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

}
