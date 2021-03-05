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
        <c:forEach begin="1" end="3">
            <div class="chabakjiInfo">
                <div class="chabakji_img_region" >
                    <img class="chabakji_img" src="/static/img/camping1.PNG"/>
                    <div class="chabakji_name_txt">[지역] 차박지 이름</div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/address_icon.PNG">
                        <div class="icon_text">인천광역시 미추홀구 용현동 198-1 </div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/star_icon.PNG">
                        <div class="icon_text">4.6 / 5.0 </div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/phone_icon.PNG">
                        <div class="icon_text">032-860-5555</div>
                    </div>
                    <div class="iconAndText_Region">
                        <img class="icon" src="/static/img/message_icon.PNG">
                        <div class="icon_text">차박지에 대한 정보를 출력합니다.</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>


<style>
    .search_option_region {
        padding: 20px 40px;
        background-color: #f9f9f8;
        border-radius: 10px;
    }
    .search_option_region > input[type="checkbox"]{
        margin-left: 30px;
        margin-right: 10px;
    }
    .ul_center {
        padding-left: 0;
        margin-bottom: 0;
        list-style: none;
        text-align: center;
        margin-left: 300px;
        margin-right: 300px;
    }
    .li_center {
        width: 20%;
        font-size: 18px;
        font-weight: bold;
        padding: 20px 30px;
        display: inline-block;
    }
    .li_center > a{
        color: white;
    }
    .sub_bg_w {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        z-index: -1;
    }
    .sub_bg_w .search_bg_01 {
        min-height: 1000px;
        background: #000 url(/static/img/camping1.PNG) no-repeat center top;
        padding-bottom: 50px;
        opacity: 90%;
    }
    .skip {
        overflow: hidden;
        position: absolute;
        top: -10000px;
        left: -10000px;
        width: auto;
        height: 1px;
        text-indent: -999px;
        z-index: -999;
    }
    .chabakMain_Container {
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: white;
        margin-bottom: 30px;
    }
    .chabakjiList_row {
        margin-top: 10px;
        border: 1px solid #ddd;
        border-radius: 10px;
        overflow: hidden;
        text-align: center;
    }
    .chabakjiInfo {
        display: inline-block;
        width: 30%;
        border: 1px solid #ddd;
        border-radius: 10px;
        margin: 10px;
    }
    .chabakji_img {
        width: 100%;
        border: 1px solid #ddd;
        border-radius: 10px;
    }
    .chabakji_name_txt {
        font-size: 20px;
        font-weight: 400;
        text-align: left;
        padding: 10px 20px;
        border-bottom: 1px solid #ddd;
    }
    .iconAndText_Region {
        margin: 10px;
    }
    .icon {
        width: 20px;
        margin: 0 10px 0 20px;
        float: left;
    }
    .icon_text {
        text-align: left;
    }
</style>
