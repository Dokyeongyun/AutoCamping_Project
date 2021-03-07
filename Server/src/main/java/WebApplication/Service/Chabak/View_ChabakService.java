package WebApplication.Service.Chabak;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    String chabakMap(Model model, @RequestParam("province") String province);

    /**
     * 차박지 상세정보
     */
    @RequestMapping("/detailInfo/{placeId}")
    String detailInfo(Model model, @PathVariable int placeId);
}
