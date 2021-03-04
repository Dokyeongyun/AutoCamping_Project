package WebApplication.Service.Chabak;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("View_ChabakService")
public class View_ChabakServiceImpl implements View_ChabakService{

    /**
     * 게시판 메인화면
     */
    @Override
    public String main(Model model) {
        return "/chabak/chabakMain";
    }
}
