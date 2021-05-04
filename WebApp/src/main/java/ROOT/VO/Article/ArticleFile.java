package ROOT.VO.Article;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleFile {

    @JsonProperty("file_id")
    private int fileId;

    @JsonProperty("article_id")
    private int articleId;

    @JsonProperty("original_filename")
    private String originalFilename;

    @JsonProperty("stored_filename")
    private String storedFilename;

    @JsonProperty("file_size")
    private long fileSize;

    @JsonProperty("reg_date")
    private String regDate;

    @JsonProperty("is_deleted")
    private int isDeleted;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getStoredFilename() {
        return storedFilename;
    }

    public void setStoredFilename(String storedFilename) {
        this.storedFilename = storedFilename;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return fileId+" "+articleId+" "+originalFilename+" "+storedFilename+" "+fileSize+" "+regDate+" "+isDeleted;
    }
}
