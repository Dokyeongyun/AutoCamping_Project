package WebApplication.Service.Chabak;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chabak")
public interface View_ChabakService {

    /**
     * 게시판 메인화면
     */
    @RequestMapping("/main")
    String main(Model model);
}
