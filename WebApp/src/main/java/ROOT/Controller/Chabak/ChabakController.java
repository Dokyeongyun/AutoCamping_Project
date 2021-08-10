package ROOT.Controller.Chabak;

import ROOT.Service.Chabak.ChabakService;
import ROOT.Service.Member.MemberService;
import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.ChabakDibs;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Facility.Fishing;
import ROOT.VO.Facility.Toilet;
import ROOT.VO.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/chabak")
public class ChabakController {

    @Autowired
    ChabakService chabakService;

    @Autowired
    MemberService memberService;

    /**
     * 차박지 탭 메인화면
     */
    @GetMapping("/main")
    public String main(Model model) {
        List<Chabak> chabaks = chabakService.getAllChabakList();
        model.addAttribute("chabakList", chabaks);
        return "/chabak/chabakMain";
    }

    /**
     * 차박지 지도화면
     */
    @GetMapping("/map")
    public String chabakMap(Model model, @RequestParam("province") String province) {
        Map<String, BestAndCount> map = chabakService.getBestAndCount();
        List<Chabak> list = chabakService.getProvinceChabakList(province);
        model.addAttribute("BestAndCount", map);
        model.addAttribute("searchResult", list);
        model.addAttribute("searchProvince", province);
        return "/chabak/chabakMap";
    }

    /**
     * 차박지 상세정보 화면
     * 해당 차박지에 대한 주변 시설 정보(화장실, 낚시터)와 리뷰정보 함께 보여주기
     */
    @GetMapping("/{placeId}")
    public String detailInfo(Model model, @PathVariable int placeId, HttpSession session) {
        Chabak chabakDetail = chabakService.getOne(placeId);
        List<Toilet> toiletList = chabakService.getToilets(placeId);
        List<Fishing> fishingList = chabakService.getFishings(placeId);
        List<Review> reviewList = chabakService.getReviews(placeId);

        Boolean isJjimPlace = false;
        Member sessionMember = (Member) session.getAttribute("sessionMember");

        if (sessionMember != null) {
            ChabakDibs chabakDibs = new ChabakDibs();
            chabakDibs.setMemberId(sessionMember.getMemberId());
            chabakDibs.setPlaceId(placeId);
            isJjimPlace = memberService.getChabakDibsStatus(chabakDibs);
        }
        model.addAttribute("chabakDetail", chabakDetail);
        model.addAttribute("toiletList", toiletList);
        model.addAttribute("fishingList", fishingList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("isJjimPlace", isJjimPlace);
        return "/chabak/chabakDetail";
    }


    /**
     * 차박지 랭킹화면
     *
     * @param model  차박지 랭킹 정보 담을 리스트
     * @param sortBy 정렬 기준
     * @return 차박지 랭킹화면 ViewName
     */
    @RequestMapping("/ranking/{sortBy}")
    public String ranking(Model model, @PathVariable String sortBy, HttpSession session) {
        Chabak[] chabakList = chabakService.getPopularList(sortBy);
        List<Boolean> dibsStatusList = new ArrayList<>();

        Member sessionMember = (Member) session.getAttribute("sessionMember");
        for (Chabak c : chabakList) {
            boolean isDibs = false;
            if (sessionMember != null) {
                ChabakDibs chabakDibs = new ChabakDibs();
                chabakDibs.setMemberId(sessionMember.getMemberId());
                chabakDibs.setPlaceId(c.getPlaceId());
                isDibs = memberService.getChabakDibsStatus(chabakDibs);
            }
            dibsStatusList.add(isDibs);
        }
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("popularList", chabakList);
        model.addAttribute("dibsStatusList", dibsStatusList);
        return "/chabak/chabakRanking";
    }

    /**
     * 차박지 검색 (필터이용)
     *
     * @param model
     * @param region
     * @param facility
     * @param keyword
     * @return
     */
    @RequestMapping("/chabakSearch")
    public String chabakSearch(Model model,
                               @RequestParam("region") String region,
                               @RequestParam("facility") String facility,
                               @RequestParam("keyword") String keyword) {

        Chabak[] filterResult = chabakService.chabakSearch(region, facility);

        System.out.println(Arrays.toString(filterResult));

        List<Chabak> tempList = chabakService.getAllChabakList();
        Chabak[] chabaks = tempList.toArray(new Chabak[tempList.size()]);

        System.out.println(Arrays.toString(chabaks));
        // TODO 수정필요
        List<Chabak> keywordResult = new ArrayList<>();
        for (int i = 0; i < chabaks.length; i++) {
            Chabak temp = chabaks[i];
            String text = temp.getAddress() + temp.getIntroduce() + temp.getPhone_number() + temp.getPlaceName() + temp;
            if (text.contains(keyword)) {
                keywordResult.add(temp);
            }
        }
        System.out.println(keywordResult);

        List<Chabak> finalResult = new ArrayList<>();
        for (int i = 0; i < filterResult.length; i++) {
            for (int j = 0; j < keywordResult.size(); j++) {
                if (filterResult[i].getPlaceId() == keywordResult.get(j).getPlaceId()) {
                    finalResult.add(keywordResult.get(i));
                    break;
                }
            }
        }

        System.out.println(finalResult);

        model.addAttribute("searchResult", finalResult);
        return "/chabak/searchResult";
    }
}
