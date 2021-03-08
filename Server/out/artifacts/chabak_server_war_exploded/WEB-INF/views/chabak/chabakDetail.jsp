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
        <div id="mapwrap">
            <div id="map" class="map_container" style="height: 500px;"></div>
            <!-- 지도 위에 표시될 마커 카테고리 -->
            <div class="category">
                <ul>
                    <li id="allMenu" onclick="changeMarker('all')">
                        <span class="all_icon"></span>
                        <div>모두</div>
                    </li>
                    <li id="toiletMenu" onclick="changeMarker('toilet')">
                        <span class="toilet_icon"></span>
                        <div>화장실</div>
                    </li>
                    <li id="fishingMenu" onclick="changeMarker('fishing')">
                        <span class="fishing_icon"></span>
                        <div>낚시터</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>

<jsp:include page="../footer.jsp"/>
<script>
    var toiletMarkers = [];
    var fishingMarkers = [];

    // 차박지 위치
    var placePosition = new kakao.maps.LatLng(${chabakDetail[0].latitude}, ${chabakDetail[0].longitude});

    // 지도 요소 띄우기
    var mapContainer = document.getElementById('map');
    var mapOption = {center: placePosition, level: 4}; // 지도의 중심좌표
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤 생성
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는 줌 컨트롤 생성
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 차박지 위치에 커스텀 마커 띄우기
    var imageSrc = '/static/img/campingcar_marker_icon.PNG',
        imageSize = new kakao.maps.Size(48, 60),
        imageOption = {};

    // 마커 이미지정보를 가지고 있는 마커이미지 생성
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        position: placePosition,
        image: markerImage
    });

    // 지도 위에 마커 설정
    marker.setMap(map);

    // 커스텀 오버레이 내용
    var content = '<div class="customoverlay">' +
        '  <a href="" target="_blank">' +
        '    <span class="title">${chabakDetail[0].placeName}</span>' +
        '  </a>' +
        '</div>';

    // 커스텀 오버레이 생성
    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: placePosition,
        content: content,
        yAnchor: 1
    });

    // 화장실 마커 표시 및 정보 띄우기
    var toiletPositions = [];

    <c:forEach var="i" items="${toiletList}">
    toiletPositions.push({
        content: '<div style="padding:5px; background-color: white; border: 1px solid black">'+
            '<div>주소: ${i.address}</div>' +
            '<div>개방시간: ${i.open_time}</div></div>',
        title: '${i.address}',
        latlng: new kakao.maps.LatLng(${i.latitude}, ${i.longitude})
    });
    </c:forEach>

    var toiletImageSrc = '/static/img/toilet_marker_icon.PNG';

    for (var i = 0; i < toiletPositions.length; i ++) {
        var toiletImageSize = new kakao.maps.Size(36, 42);
        var toiletImage = new kakao.maps.MarkerImage(toiletImageSrc, toiletImageSize);
        var toiletMarker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: toiletPositions[i].latlng, // 마커의 위치
            image : toiletImage // 마커 이미지
        });

        toiletMarkers.push(toiletMarker);

        var toiletInfowindow = new kakao.maps.InfoWindow({
            content: toiletPositions[i].content // 인포윈도우에 표시할 내용
        });

        (function(toiletMarker, toiletInfowindow) {
            kakao.maps.event.addListener(toiletMarker, 'mouseover', function() {
                toiletInfowindow.open(map, toiletMarker);
            });
            kakao.maps.event.addListener(toiletMarker, 'mouseout', function() {
                toiletInfowindow.close();
            });
        })(toiletMarker, toiletInfowindow);
    }

    // 낚시터 마커 표시 및 정보 띄우기
    var fishingPositions = [];

    <c:forEach var="i" items="${fishingList}">
    fishingPositions.push({
        content: '<div style="padding:5px; background-color: white; border: 1px solid black">'+
            '<div>낚시터명: ${i.name}</div>' +
            '<div>낚시터유형: ${i.type}</div>' +
            '<div>전화번호: ${i.phone}</div></div>',
        title: '${i.address}',
        latlng: new kakao.maps.LatLng(${i.latitude}, ${i.longitude})
    });
    </c:forEach>

    var fishingImageSrc = '/static/img/fishing_marker_icon.PNG';

    for (var j = 0; j < fishingPositions.length; j++) {
        var fishingImageSize = new kakao.maps.Size(36, 42);
        var fishingImage = new kakao.maps.MarkerImage(fishingImageSrc, fishingImageSize);
        var fishingMarker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: fishingPositions[j].latlng, // 마커의 위치
            image: fishingImage // 마커 이미지
        });

        fishingMarkers.push(fishingMarker);

        var fishingInfowindow = new kakao.maps.InfoWindow({
            content: fishingPositions[j].content // 인포윈도우에 표시할 내용
        });

        (function(fishingMarker, fishingInfowindow) {
            kakao.maps.event.addListener(fishingMarker, 'mouseover', function() {
                fishingInfowindow.open(map, fishingMarker);
            });
            kakao.maps.event.addListener(fishingMarker, 'mouseout', function() {
                fishingInfowindow.close();
            });
        })(fishingMarker, fishingInfowindow);
    }
</script>
<script>

    changeMarker('all');

    // 카테고리를 클릭했을 때 type에 따라 카테고리의 스타일과 지도에 표시되는 마커를 변경
    function changeMarker(type){
        var toiletMenu = document.getElementById('toiletMenu');
        var fishingMenu = document.getElementById('fishingMenu');
        var allMenu = document.getElementById('allMenu');

        if (type === 'toilet') {
            allMenu.className = '';
            toiletMenu.className = 'menu_selected';
            fishingMenu.className = '';

            setToiletMarkers(map);
            setFishingMarkers(null);
        } else if (type === 'fishing') {
            allMenu.className = '';
            toiletMenu.className = '';
            fishingMenu.className = 'menu_selected';

            setToiletMarkers(null);
            setFishingMarkers(map);
        } else {
            allMenu.className = 'menu_selected';
            toiletMenu.className = '';
            fishingMenu.className = '';

            showAllMarkers();
        }
    }

    // 모든 마커 보여주기
    function showAllMarkers(){
        setToiletMarkers(map);
        setFishingMarkers(map);
    }

    // 화장실 마커들의 지도 표시 여부를 설정하는 함수입니다
    function setToiletMarkers(map) {
        for (var i = 0; i < toiletMarkers.length; i++) {
            toiletMarkers[i].setMap(map);
        }
    }
    // 낚시터 마커들의 지도 표시 여부를 설정하는 함수입니다
    function setFishingMarkers(map) {
        for (var i = 0; i < fishingMarkers.length; i++) {
            fishingMarkers[i].setMap(map);
        }
    }
</script>

