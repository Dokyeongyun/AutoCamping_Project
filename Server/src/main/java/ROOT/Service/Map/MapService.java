package ROOT.Service.Map;

import ROOT.DAO.ChabakDAO;
import ROOT.VO.Chabak.BestAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/map")
public class MapService {

    @Autowired
    ChabakDAO chabakDAO;

    @RequestMapping("/")
    public ModelAndView map(){
        ModelAndView mav = new ModelAndView();
        Map<String, BestAndCount> map = chabakDAO.getBestAndCount();
        mav.addObject("BestAndCount", map );
        mav.setViewName("map/map");
        return mav;
    }
}
