<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">차박지를 검색해보세요.</div>
    <div class="small_txt">지역</div>
    <div class="search_option_region">
        <table style="width: 100%; font-size: 14px;">
            <tr>
                <td><input type="checkbox" name="region_check" value="전지역"/>전지역</td>
                <td><input type="checkbox" name="region_check" value="서울"/>서울</td>
                <td><input type="checkbox" name="region_check" value="인천"/>인천</td>
                <td><input type="checkbox" name="region_check" value="경기"/>경기도</td>
                <td><input type="checkbox" name="region_check" value="충북"/>충청북도</td>
                <td><input type="checkbox" name="region_check" value="충남"/>충청남도</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="region_check" value="대전"/>대전</td>
                <td><input type="checkbox" name="region_check" value="세종"/>세종</td>
                <td><input type="checkbox" name="region_check" value="강원"/>강원도</td>
                <td><input type="checkbox" name="region_check" value="경북"/>경상북도</td>
                <td><input type="checkbox" name="region_check" value="경남"/>경상남도</td>
                <td><input type="checkbox" name="region_check" value="대구"/>대구</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="region_check" value="부산"/>부산</td>
                <td><input type="checkbox" name="region_check" value="울산"/>울산</td>
                <td><input type="checkbox" name="region_check" value="전북"/>전라북도</td>
                <td><input type="checkbox" name="region_check" value="전남"/>전라남도</td>
                <td><input type="checkbox" name="region_check" value="광주"/>광주</td>
                <td><input type="checkbox" name="region_check" value="제주도"/>제주도</td>
            </tr>
        </table>
    </div>
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
    <div class="small_txt">검색어</div>
    <div class="article_search_region">
        <input type="text" id="searchKeyword" class="form-control" placeholder="검색어를 입력해주세요" style="width: 200px; display: inline-block"/>
        <button class="btn" type="button" id="searchChabakBtn" onclick="filterSearch()" style="background-color: #77b7b4; color: white">검색</button>
    </div>
</div>

<%-- 추천 차박지 보여주기, 차박지 랭킹 등 --%>
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">추천 차박지</div>
    <div class="title_sub_txt">총 ${searchResult.size()} 개의 차박지가 있습니다.</div>

    <%-- 차박지 정보 한 줄에 3개씩 출력하기 --%>
    <div class="chabakjiList_row">
        <c:forEach items="${searchResult}" var="i">
            <div class="chabakjiInfo" onclick="location.href='/chabak/detailInfo/${i.placeId}'">
                <div class="chabakji_img_region" >
                    <c:if test="${i.filePath == 'No image'}">
                        <img class="chabakji_img" src="/static/img/no_image_icon.PNG"/>
                    </c:if>
                    <c:if test="${i.filePath != 'No image'}">
                        <img class="chabakji_img" src="${i.filePath}"/>
                    </c:if>
                </div>
                <div class="chabakji_info_region">
                    <div class="chabakji_name_txt">${i.placeName}</div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/address_icon.PNG">
                        <div class="icon_text">${i.address}</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/star_icon.PNG">
                        <div class="icon_text">${i.avg_point} / 5.0</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/phone_icon.PNG">
                        <div class="icon_text">${i.phone_number}</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/message_icon.PNG">
                        <div class="icon_text">${i.introduce}</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>


