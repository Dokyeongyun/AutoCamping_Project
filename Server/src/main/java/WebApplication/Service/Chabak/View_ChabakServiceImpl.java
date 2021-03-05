package WebApplication.Service.Chabak;

import ROOT.DAO.ArticleDAO;
import ROOT.DAO.ChabakDAO;
import ROOT.VO.Chabak.Chabak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("View_ChabakService")
public class View_ChabakServiceImpl implements View_ChabakService{

    @Autowired
    ChabakDAO chabakDAO;

    /**
     * 게시판 메인화면
     */
    @Override
    public String main(Model model) {
        List<Chabak> list = chabakDAO.getAllChabakList();
        model.addAttribute("chabakList", list);
        return "/chabak/chabakMain";
    }
}
