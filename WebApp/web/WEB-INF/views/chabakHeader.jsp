<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/chabak.js?ver=2"></script>

<nav class="navbar">
    <div style="border-top: 1px solid black; border-bottom: 1px solid black;">
        <ul class="ul_center">
            <li class="li_center"><a href="${pageContext.request.contextPath}/chabak/main">차박지검색</a></li>
            <li class="li_center"><a href="${pageContext.request.contextPath}/chabak/map?province=all">차박지지도</a></li>
            <li class="li_center"><a href="${pageContext.request.contextPath}/chabak/ranking/avg_point">차박지순위</a></li>
            <li class="li_center"><a href="#">차박후기</a></li>
        </ul>
    </div>
</nav>
<div class="sub_bg_w">
    <div class="search_bg_01">
        <span class="skip">캠핑장검색의 배경이미지</span>
    </div>
</div>