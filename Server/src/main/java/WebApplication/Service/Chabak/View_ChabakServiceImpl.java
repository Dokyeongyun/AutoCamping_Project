package WebApplication.Service.Chabak;

import ROOT.DAO.ArticleDAO;
import ROOT.DAO.ChabakDAO;
import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Chabak.facility.Fishing;
import ROOT.VO.Chabak.facility.Toilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    /**
     * 차박지 지도화면
     */
    @Override
    public String chabakMap(Model model, String province) {
        Map<String, BestAndCount> map = chabakDAO.getBestAndCount();
        List<Chabak> list = chabakDAO.getProvinceChabakList(province);
        model.addAttribute("BestAndCount", map);
        model.addAttribute("searchResult", list);
        model.addAttribute("searchProvince", province);
        return "/chabak/chabakMap";
    }

    /**
     * 차박지 상세정보 + 주변 시설 정보
     */
    @Override
    public String detailInfo(Model model, int placeId) {
        List<Chabak> chabakDetail = chabakDAO.getOne(placeId);
        List<Toilet> toiletList = chabakDAO.getToilets(placeId);
        List<Fishing> fishingList = chabakDAO.getFishings(placeId);
        List<Review> reviewList = chabakDAO.getReviews(placeId);
        model.addAttribute("chabakDetail", chabakDetail);
        model.addAttribute("toiletList", toiletList);
        model.addAttribute("fishingList", fishingList);
        model.addAttribute("reviewList", reviewList);
        return "/chabak/chabakDetail";
    }
}
