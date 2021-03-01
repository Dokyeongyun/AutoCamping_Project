package WebApplication.Service.Board;

import org.springframework.stereotype.Service;

@Service("View_BoardService")
public class View_BoardServiceImpl implements View_BoardService{

    /**
     * 게시판 메인화면
     */
    @Override
    public String main() {
        return "/board/boardMain";
    }
}
