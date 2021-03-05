<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<body style="background-color: black">
<nav class="navbar">
    <div style="border-top: 1px solid black; border-bottom: 1px solid black; margin-top: -22px;">
        <ul class="ul_center">
            <li class="li_center"><a href="#">차박지검색</a></li>
            <li class="li_center"><a href="#">차박지지도</a></li>
            <li class="li_center"><a href="#">차박지순위</a></li>
            <li class="li_center"><a href="#">차박후기</a></li>
        </ul>
    </div>
</nav>
<div class="sub_bg_w">
    <div class="search_bg_01">
        <span class="skip">캠핑장검색의 배경이미지</span>
    </div>
</div>
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">차박지를 검색해보세요.</div>
    <div class="small_txt">지역</div>
    <div class="search_option_region">
        <input type="checkbox" name="region_check" value="전지역"/>전지역
        <input type="checkbox" name="region_check" value="수도권"/>수도권
        <input type="checkbox" name="region_check" value="강원도"/>강원도
        <input type="checkbox" name="region_check" value="충청도"/>충청도
        <input type="checkbox" name="region_check" value="경상도"/>경상도
        <input type="checkbox" name="region_check" value="전라도"/>전라도
        <input type="checkbox" name="region_check" value="제주도"/>제주도
    </div>
    <div class="small_txt">옵션</div>
    <div class="search_option_region">
        <input type="checkbox" name="option_check" value="화장실"/>화장실
        <input type="checkbox" name="option_check" value="낚시터"/>낚시터
        <input type="checkbox" name="option_check" value="편의점/마트"/>편의점/마트
    </div>
    <div class="small_txt">검색어</div>
    <div class="article_search_region">
        <select id="searchRegion" class="form-control" style="width: 150px; display: inline-block">
            <option value="all">전지역</option>
            <option value="1">수도권</option>
            <option value="2">강원도</option>
            <option value="3">충청도</option>
            <option value="4">경상도</option>
            <option value="5">전라도</option>
            <option value="6">제주도</option>
        </select>
        <input type="text" id="searchKeyword" class="form-control" placeholder="검색어를 입력해주세요" style="width: 200px; display: inline-block"/>
        <button class="btn" type="button" id="searchChabakBtn" style="background-color: #77b7b4; color: white">검색</button>
    </div>
</div>

<%-- 추천 차박지 보여주기, 차박지 랭킹 등 --%>
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">추천 차박지</div>

    <%-- 차박지 정보 한 줄에 3개씩 출력하기 --%>
    <div class="chabakjiList_row">
        <c:forEach items="${chabakList}" var="i">
            <div class="chabakjiInfo">
                <div class="chabakji_img_region" >
                    <img class="chabakji_img" src="/static/img/camping1.PNG"/>
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


