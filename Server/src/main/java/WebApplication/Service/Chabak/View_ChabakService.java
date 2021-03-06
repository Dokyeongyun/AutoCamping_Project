package WebApplication.Service.Chabak;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chabak")
public interface View_ChabakService {

    /**
     * 차박지 메인화면
     */
    @RequestMapping("/main")
    String main(Model model);

    /**
     * 차박지 지도화면
     */
    @RequestMapping("/map")
    String chabakMap(Model model);
}
