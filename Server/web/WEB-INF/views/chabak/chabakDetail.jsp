<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">차박지 상세정보</div>
    <div class="cbj_detail_top">
        <div class="cbj_detail_top_upper">
            <p class="cbj_placeName_txt">${chabakDetail[0].placeName}</p>
        </div>
        <div class="cbj_detail_top_lower">
            <div class="cbj_detail_menu_img_region" style="float: right">
                <img src="/static/img/star_icon.PNG" class="circle_img">
                <img src="/static/img/share_icon.PNG" class="circle_img">
                <img src="/static/img/link_icon.PNG" class="circle_img">
            </div>
        </div>
    </div>
    <hr style="border-top: 2px solid black">
    <div class="cbj_detail_bottom">
        <div class="cbj_detail_img_region">
            <img src="${chabakDetail[0].filePath}"/>
        </div>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>

<style>
    @font-face {
        src : url("/static/fonts/BMJUA_ttf.ttf");
        font-family: "BM_Jua";
    }
    .cbj_detail_top {
        background-image: url('/static/img/img2.PNG');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        background-position: center center;
        border-radius: 10px;
        overflow: hidden;
        margin: 20px 0;
        opacity: 90%;
        padding: 20px 30px;
    }
    .cbj_detail_top_upper {
        border-bottom: 2px solid lavenderblush;
        padding: 40px 10px;
    }
    .cbj_placeName_txt {
        font-size: 45px;
        font-family: "BM_Jua";
        line-height: 40px;
        color: black !important;
        text-shadow: 5px 5px 5px darkgrey;
    }
    .cbj_detail_top_lower {
        padding: 20px;
    }
    .circle_img {
        border-radius: 15px;
        object-fit: cover;
        width: 50px;
        background-color: white;
        padding: 12px;
        overflow: hidden;
        cursor: pointer;
    }
    .cbj_detail_img_region {
        width: 50%;
    }
    .cbj_detail_img {
        width: 100%;
        border-radius: 10px;
    }
</style>

