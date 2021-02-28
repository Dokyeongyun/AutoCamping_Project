package WebApplication.Service.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeService {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
