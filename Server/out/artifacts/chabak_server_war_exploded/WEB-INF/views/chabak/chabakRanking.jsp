<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
    <div style="border: 1px solid;border-radius: 10px;padding: 10px; overflow: hidden">
        <div style="font-family: 'BM_Jua'; font-size: 18px; float: right">
            정렬방법
            <select id="sortMethod" class="form-control" style="width: 200px; display: inline-block; margin:0 10px;">
                <option value="avg_point">별점 순으로 보기</option>
                <option value="jjim">찜 개수 순으로 보기</option>
            </select>
            <button type="button" class="btn btn-primary" onclick="function ChangeSortMethod() {
                var method = $('#sortMethod').val();
                if(method === 'avg_point') location.href='/chabak/ranking/avg_point';
                if(method === 'jjim') location.href='/chabak/ranking/jjim';
            }
            ChangeSortMethod();">확인</button>
        </div>
    </div>
    <%-- 차박지 별점 순서대로 나열 --%>
    <c:forEach var="i" items="${popularList}">
        <div class="cbj_detail_top" style="background-image: url('${i.filePath}'); cursor: pointer;" onclick="location.href='/chabak/detailInfo/${i.placeId}'">
            <div class="cbj_detail_top_upper">
                <p class="cbj_placeName_txt">${i.placeName}</p>
            </div>
            <div class="cbj_detail_top_lower">
                <div class="cbj_detail_overview">
                    <img src="/static/img/heart_icon.PNG" style="width: 20px; margin-right: 4px;">${i.jjim}
                    <img src="/static/img/star_icon.PNG" style="width: 20px; margin-right: 4px;">${i.avg_point}
                </div>
                <div class="cbj_detail_menu_img_region" style="float: right">
                    <img src="/static/img/star_icon.PNG" class="circle_img">
                    <img src="/static/img/share_icon.PNG" class="circle_img">
                    <img src="/static/img/link_icon.PNG" class="circle_img">
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
<jsp:include page="../footer.jsp"/>

<style>
    .cbj_detail_overview {
        float: left;
        font-family: 'BM_Jua';
        font-size: 20px;
        background-color: aliceblue;
        border-radius: 10px;
        padding: 5px;
    }
</style>