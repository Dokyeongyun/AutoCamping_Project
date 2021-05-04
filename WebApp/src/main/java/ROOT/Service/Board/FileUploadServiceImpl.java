package ROOT.Service.Board;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final String SAVE_PATH = "C:/AutoCamping/Board/";

    /**
     * 파일 업로드
     */
    @Override
    public String uploadFile(MultipartFile file, int articleId, int sequence) {
        String url;
        try {
            // 파일 정보
            String originFilename = file.getOriginalFilename();
            String fileExtension = originFilename.substring(originFilename.lastIndexOf("."));

            // 서버에 저장할 파일명 생성
            String storedFileName = getStoredFilename(fileExtension, articleId, sequence);

            // 파일 업로드
            writeFile(file, storedFileName);
            url = SAVE_PATH + storedFileName;
        } catch (IOException e) {
            // throw new FileUploadException();
            throw new RuntimeException(e);
        }
        return url;
    }

    // 현재 시간 + 게시글번호 + 순서 로 파일 이름 생성
    private String getStoredFilename(String fileExtension, int articleId, int sequence) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(new Date()) + "_" + articleId + "_" + sequence + "." + fileExtension;
    }

    // 파일 업로드
    private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(SAVE_PATH + saveFileName);
        fos.write(data);
        fos.close();
    }
}
