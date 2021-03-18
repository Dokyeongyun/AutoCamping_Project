package WebApplication.Service.Chabak;

import ROOT.DAO.ArticleDAO;
import ROOT.DAO.ChabakDAO;
import ROOT.DAO.MemberDAO;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("View_ChabakService")
public class View_ChabakServiceImpl implements View_ChabakService{

    @Autowired
    ChabakDAO chabakDAO;

    @Autowired
    MemberDAO memberDAO;

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
    public String detailInfo(Model model, int placeId, HttpSession session) {
        List<Chabak> chabakDetail = chabakDAO.getOne(placeId);
        List<Toilet> toiletList = chabakDAO.getToilets(placeId);
        List<Fishing> fishingList = chabakDAO.getFishings(placeId);
        List<Review> reviewList = chabakDAO.getReviews(placeId);
        String isJjimPlace = "0";
        if(session.getAttribute("id") != null){
            isJjimPlace = memberDAO.isJJim((String) session.getAttribute("id"), String.valueOf(placeId));
        }
        model.addAttribute("chabakDetail", chabakDetail);
        model.addAttribute("toiletList", toiletList);
        model.addAttribute("fishingList", fishingList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("isJjimPlace", isJjimPlace);
        return "/chabak/chabakDetail";
    }

    /**
     * 차박지 순위화면
     */
    @Override
    public String ranking(Model model, String order) {
        List<Chabak> list = new ArrayList<>();
        if(order.equals("avg_point")){
            list = chabakDAO.getPopularList();
        }else if (order.equals("jjim")){
            list = chabakDAO.getPopularList2();
        }
        model.addAttribute("popularList", list);
        return "/chabak/chabakRanking";
    }

    /**
     * 차박지 검색 (필터)
     */
    @Override
    public String chabakSearch(Model model, String region, String facility, String keyword) {
        String[] regions = region.split("_");
        String[] facilities = facility.split("_");

        System.out.println(Arrays.toString(regions));
        System.out.println(Arrays.toString(facilities));

        List<Chabak> filterResult = chabakDAO.getFilteredList(regions, facilities);

        System.out.println(filterResult);

        List<Chabak> keywordResult = chabakDAO.getAllChabakList().stream().filter(cha -> {
            String text = cha.getAddress() + cha.getIntroduce() + cha.getPhone_number() +
                    cha.getPlaceName() + cha;
            return text.contains(keyword);
        }).collect(Collectors.toList());

        System.out.println(keywordResult);

        List<Chabak> finalResult = new ArrayList<>();
        for(int i=0; i<filterResult.size(); i++){
            for(int j=0; j<keywordResult.size(); j++){
                if(filterResult.get(i).getPlaceId() == keywordResult.get(j).getPlaceId()){
                    finalResult.add(filterResult.get(i));
                    break;
                }
            }
        }

        System.out.println(finalResult);

        model.addAttribute("searchResult", finalResult);
        return "/chabak/searchResult";
    }
}
