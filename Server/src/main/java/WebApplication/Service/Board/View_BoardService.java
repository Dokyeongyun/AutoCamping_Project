package WebApplication.Service.Board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public interface View_BoardService {

    /**
     * 게시판 메인화면
     */
    @RequestMapping(value = "/main")
    String main();
}
