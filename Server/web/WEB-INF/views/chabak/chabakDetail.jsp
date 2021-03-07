<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28c7a8a13fd45258a42b15ec36bd466f"></script>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
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
        <div class="iconAndText_Region">
            <img class="icon" src="/static/img/information_icon.PNG">
            <div class="icon_text sub_title_txt">차박지 상세정보</div>
        </div>
        <div class="cbj_detail_img_region">
            <img src="${chabakDetail[0].filePath}" class="cbj_detail_img"/>
            <div class="cbj_detail_btn_region">
                <button class="btn btn-danger cbj_detail_btn" type="button"><</button>
                <button class="btn btn-primary cbj_detail_btn" type="button">></button>
            </div>
        </div>
        <div class="cbj_detail_info_region">
            <table class="cbj_detail_info_table">
                <tr style="border-top: 2px solid #2999ac">
                    <th>장소명</th>
                    <td>${chabakDetail[0].placeName}</td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>${chabakDetail[0].address}</td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td>${chabakDetail[0].phone_number}</td>
                </tr>
                <tr>
                    <th>소갯말</th>
                    <td>${chabakDetail[0].introduce}</td>
                </tr>
                <tr>
                    <th>평점</th>
                    <td>${chabakDetail[0].avg_point} / 5.0 점</td>
                </tr>
                <tr>
                    <th>이 차박지를 찜한 사람</th>
                    <td>${chabakDetail[0].jjim} 명</td>
                </tr>
            </table>
        </div>
    </div>
    <hr style="border-top: 2px solid black">
    <div class="cbj_detail_map_region">
        <div class="iconAndText_Region">
            <img class="icon" src="/static/img/address_icon.PNG">
            <div class="icon_text sub_title_txt">위치 및 주변정보</div>
        </div>
        <div id="map" class="map_container" style="height: 500px;"></div>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>
<script>
    // 지도 요소 띄우기
    var mapContainer = document.getElementById('map');
    var mapOption = {center: new kakao.maps.LatLng(${chabakDetail[0].latitude}, ${chabakDetail[0].longitude})}; // 지도의 중심좌표
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤 생성
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는 줌 컨트롤 생성
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
</script>
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
    .cbj_detail_bottom{
        overflow: hidden;
    }
    .cbj_detail_map_region{
        overflow: hidden;
    }
    .cbj_detail_img_region {
        width: 50%;
        padding: 10px;
        float: left;
    }
    .cbj_detail_info_region {
        padding: 10px;
        width: 50%;
        float: right;
    }
    .cbj_detail_info_table {
        width: 100%;
        font-size: 14px;
    }
    .cbj_detail_info_table > tbody > tr{
        width: 100%;
        font-size: 14px;
    }
    .cbj_detail_info_table > tbody > tr > th {
        padding: 11px 10px;
        border-bottom: 1px solid #c8c8c8;
        text-align: left;
        color: #000;
        line-height: 25px;
        width: 40%;
    }
    .cbj_detail_info_table > tbody > tr > td {
        padding: 11px 10px;
        border-bottom: 1px solid #c8c8c8;
        line-height: 25px;
    }
    .cbj_detail_img {
        width: 100%;
        border-radius: 10px;
    }
    .cbj_detail_btn_region {
        width: 100%;
        text-align: center;
        padding: 10px 0;
    }
    .cbj_detail_btn {
        width: 45%;
        border-radius: 10px;
        margin: 0 10px;
        height: 40px;
        opacity: 80%;
    }
    .sub_title_txt {
        font-size: 18px; font-family: 'BM_Jua';
    }
</style>

