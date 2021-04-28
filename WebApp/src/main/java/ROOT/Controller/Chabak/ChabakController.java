package ROOT.Controller.Chabak;

import ROOT.Utils.APIServerInfo;
import ROOT.VO.Chabak.BestAndCount;
import ROOT.VO.Chabak.Chabak;
import ROOT.VO.Chabak.Review;
import ROOT.VO.Facility.Fishing;
import ROOT.VO.Facility.Toilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/chabak")
public class ChabakController {

    @Autowired
    RestOperations restOperations;

    String serverContextPath = APIServerInfo.API_SERVER_CONTEXT;

    /**
     * 차박지 탭 메인화면
     */
    @GetMapping("/main")
    public String main(Model model) {
        List<Chabak> chabaks = restOperations.getForObject(serverContextPath + "/chabak/chabaks", List.class);
        model.addAttribute("chabakList", chabaks);
        return "/chabak/chabakMain";
    }

    /**
     * 차박지 지도화면
     */
    @GetMapping("/map")
    public String chabakMap(Model model, @RequestParam("province") String province) {
        Map<String, BestAndCount> map = restOperations.getForObject(serverContextPath + "/chabak/bestAndCount", Map.class);
        List<Chabak> list = restOperations.getForObject(serverContextPath + "/chabak/province/" + province, List.class);
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
        Chabak chabakDetail = restOperations.getForObject(serverContextPath + "/chabak/" + placeId, Chabak.class);
        List<Toilet> toiletList = restOperations.getForObject(serverContextPath + "/chabak/toilets/" + placeId, List.class);
        List<Fishing> fishingList = restOperations.getForObject(serverContextPath + "/chabak/fishings/" + placeId, List.class);
        List<Review> reviewList = restOperations.getForObject(serverContextPath + "/chabak/reviews/" + placeId, List.class);

        String isJjimPlace = "0";
        if (session.getAttribute("id") != null) {
//            isJjimPlace = memberDAO.isJJim((String) session.getAttribute("id"), String.valueOf(placeId));
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
     * @param model 차박지 랭킹 정보 담을 리스트
     * @param order 정렬 기준
     * @return 차박지 랭킹화면 ViewName
     */
    @RequestMapping("/ranking/{order}")
    public String ranking(Model model, @PathVariable String order) {
        List<Chabak> list = restOperations.getForObject(serverContextPath + "/chabak/popular/" + order, List.class);
        model.addAttribute("popularList", list);
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

        Chabak[] filterResult = restOperations.getForObject(
                UriComponentsBuilder.fromUriString(serverContextPath + "/chabak/search")
                        .queryParam("region", region)
                        .queryParam("facility", facility)
                        .build().toUriString(), Chabak[].class);

        System.out.println(Arrays.toString(filterResult));

        Chabak[] chabaks = restOperations.getForObject(serverContextPath + "/chabak/chabaks", Chabak[].class);

        System.out.println(Arrays.toString(chabaks));
        // TODO 수정필요
        List<Chabak> keywordResult = new ArrayList<>();
        for(int i=0; i<chabaks.length; i++){
            Chabak temp = chabaks[i];
            String text = temp.getAddress()+temp.getIntroduce()+temp.getPhone_number()+temp.getPlaceName()+temp;
            if(text.contains(keyword)) {
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
