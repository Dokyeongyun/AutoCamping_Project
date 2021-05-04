package ROOT.Service.Board;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    /**
     * 파일 업로드
     */
    String uploadFile(MultipartFile multipartFile, int articleId, int sequence);

}
