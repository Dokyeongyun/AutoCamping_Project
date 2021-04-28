package ROOT.VO.Article;

public class Article {
    private int articleId;
    private String memberId;
    private String nickName;
    private String title;
    private String content;
    private String urlPath;
    private String createTime;

    public Article() { }

    public int getArticleId() { return articleId; }
    public String getMemberId() { return memberId; }
    public String getNickName() { return nickName; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getUrlPath() { return urlPath; }
    public String getCreateTime() { return createTime; }

    public void setArticleId(int articleId) { this.articleId = articleId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setUrlPath(String urlPath) { this.urlPath = urlPath; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
}
