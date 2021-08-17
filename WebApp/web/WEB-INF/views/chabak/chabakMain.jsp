<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">차박지를 검색해보세요.</div>
    <div style="overflow: hidden; width: 100%">
        <div style="float: left; width: 48%; display: inline-block">
            <div class="small_txt">지역</div>
            <div class="search_option_region">
                <table id="searchRegionCheckArea">
                    <tr>
                        <td onclick="changeSearchRegionCheck('region_ALL')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_ALL"
                                   value="전지역" onclick="changeSearchRegionCheckColor()"/>
                            <label for="region_ALL"></label>전지역
                        </td>
                        <td onclick="changeSearchRegionCheck('region_Seoul')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_Seoul"
                                   value="서울"/>
                            <label for="region_Seoul"></label>서울
                        </td>
                        <td onclick="changeSearchRegionCheck('region_InCheon')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_InCheon"
                                   value="인천"/>
                            <label for="region_InCheon"></label>인천
                        </td>
                        <td onclick="changeSearchRegionCheck('region_DaeJeon')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_DaeJeon"
                                   value="대전"/>
                            <label for="region_DaeJeon"></label>대전
                        </td>
                        <td onclick="changeSearchRegionCheck('region_DaeGu')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_DaeGu"
                                   value="대구"/>
                            <label for="region_DaeGu"></label>대구
                        </td>
                    </tr>
                    <tr>
                        <td onclick="changeSearchRegionCheck('region_BuSan')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_BuSan"
                                   value="부산"/>
                            <label for="region_BuSan"></label>부산
                        </td>
                        <td onclick="changeSearchRegionCheck('region_UlSan')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_UlSan"
                                   value="울산"/>
                            <label for="region_UlSan"></label>울산
                        </td>
                        <td onclick="changeSearchRegionCheck('region_GwangJu')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_GwangJu"
                                   value="광주"/>
                            <label for="region_GwangJu"></label>광주
                        </td>
                        <td onclick="changeSearchRegionCheck('region_SeJong')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_SeJong"
                                   value="세종"/>
                            <label for="region_SeJong"></label>세종
                        </td>
                        <td onclick="changeSearchRegionCheck('region_GyeongGi')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_GyeongGi"
                                   value="경기"/>
                            <label for="region_GyeongGi"></label>경기도
                        </td>
                    </tr>
                    <tr>
                        <td onclick="changeSearchRegionCheck('region_ChungBuk')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_ChungBuk"
                                   value="충북"/>
                            <label for="region_ChungBuk"></label>충청북도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_ChungNam')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_ChungNam"
                                   value="충남"/>
                            <label for="region_ChungNam"></label>충청남도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_GangWon')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_GangWon"
                                   value="강원"/>
                            <label for="region_GangWon"></label>강원도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_GyeongBuk')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_GyeongBuk"
                                   value="경북"/>
                            <label for="region_GyeongBuk"></label>경상북도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_GyeongNam')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_GyeongNam"
                                   value="경남"/>
                            <label for="region_GyeongNam"></label>경상남도
                        </td>
                    </tr>
                    <tr>
                        <td onclick="changeSearchRegionCheck('region_JeonBuk')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_JeonBuk"
                                   value="전북"/>
                            <label for="region_JeonBuk"></label>전라북도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_JeonNam')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_JeonNam"
                                   value="전남"/>
                            <label for="region_JeonNam"></label>전라남도
                        </td>
                        <td onclick="changeSearchRegionCheck('region_JeJuDo')">
                            <input type="checkbox" class="region_check" name="region_check" id="region_JeJuDo"
                                   value="제주도"/>
                            <label for="region_JeJuDo"></label>제주도
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div style="float: right; width:48%; display: inline-block">
            <div class="small_txt">옵션</div>
            <div class="search_option_region">
                <table style="width: 100%; font-size: 14px;">
                    <tr>
                        <td><input type="checkbox" name="option_check" value="F"/>화장실</td>
                        <td><input type="checkbox" name="option_check" value="F"/>낚시터</td>
                        <td><input type="checkbox" name="option_check" value="F"/>편의점/마트</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="small_txt">검색어</div>
    <div class="article_search_region">
        <input type="text" id="searchKeyword" class="form-control" placeholder="검색어를 입력해주세요"
               style="width: 200px; display: inline-block"/>
        <button class="btn" type="button" id="searchChabakBtn" onclick="filterSearch()"
                style="background-color: #77b7b4; color: white">검색
        </button>
    </div>
</div>

<%-- 추천 차박지 보여주기, 차박지 랭킹 등 --%>
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">추천 차박지</div>
    <div class="title_sub_txt">총 ${fn:length(chabakList)} 개의 차박지가 있습니다.</div>

    <%-- 차박지 정보 한 줄에 3개씩 출력하기 --%>
    <div class="chabakjiList_row">
        <c:forEach items="${chabakList}" var="i">
            <div class="chabakjiInfo" onclick="location.href='/chabak/${i.placeId}'">
                <div class="chabakji_img_region">
                    <c:if test="${i.filePath == 'No image'}">
                        <img class="chabakji_img"
                             src="${pageContext.request.contextPath}/static/img/no_image_icon.PNG"/>
                    </c:if>
                    <c:if test="${i.filePath != 'No image'}">
                        <img class="chabakji_img" src="${i.filePath}"/>
                    </c:if>
                </div>
                <div class="chabakji_info_region">
                    <div class="chabakji_name_txt">${i.placeName}</div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="${pageContext.request.contextPath}/static/img/address_icon.PNG">
                        <div class="icon_text">${i.address}</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="${pageContext.request.contextPath}/static/img/star_icon.PNG">
                        <div class="icon_text">${i.avg_point} / 5.0</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="${pageContext.request.contextPath}/static/img/phone_icon.PNG">
                        <div class="icon_text">${i.phone_number}</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="${pageContext.request.contextPath}/static/img/message_icon.PNG">
                        <div class="icon_text">${i.introduce}</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>


<script>
    function changeSearchRegionCheck(checkId) {
        let regionCheckObj = $("#" + checkId);
        let regionCheckParentObj = regionCheckObj.parent("td");
        if (regionCheckObj.is(":checked")) {
            regionCheckObj.prop('checked', false);
            regionCheckParentObj.css("background", "#e1e1e1")
            regionCheckParentObj.css("color", "black")
        } else {
            regionCheckObj.prop('checked', true);
            regionCheckParentObj.css("background", "crimson")
            regionCheckParentObj.css("color", "white")
        }
    }
</script>