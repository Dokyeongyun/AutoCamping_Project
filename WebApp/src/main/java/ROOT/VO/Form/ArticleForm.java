package ROOT.VO.Form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ArticleForm {

    @NotBlank(message = "로그인 후 이용해주세요.")
    private String memberId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String path;

    private List<MultipartFile> files;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "ArticleWriteForm{" +
                "memberId='" + memberId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
