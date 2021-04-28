package ROOT;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/aa")
    public String home(){
        return "index";
    }
}
