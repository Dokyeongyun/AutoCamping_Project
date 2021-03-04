<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<nav class="navbar navbar">
    <div style="border-top: 1px solid black; border-bottom: 1px solid black; margin-top: -22px;">
        <ul class="ul_center">
            <li class="li_center"><a href="#">차박지검색</a></li>
            <li class="li_center"><a href="#">차박지지도</a></li>
            <li class="li_center"><a href="#">차박지순위</a></li>
            <li class="li_center"><a href="#">차박후기</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="title_txt" style="float: none">
        차박지를 검색해보세요.
    </div>
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

<jsp:include page="../footer.jsp"/>


<style>
    .search_option_region {
        padding: 20px 40px;
        background-color: #f9f9f8;
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
    }
    .li_center {
        width: 150px;
        font-size: 15px;
        font-weight: bold;
        padding: 20px;
        display: inline-block;
        border-right: 1px solid #ddd;
        border-left: 1px solid #ddd;
    }
</style>
