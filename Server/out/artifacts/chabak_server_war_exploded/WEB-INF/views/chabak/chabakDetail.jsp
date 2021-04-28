<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=12a5252e62de7f45904fd0889eea405d"></script>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>

<body style="background-color: black">
<div class="container chabakMain_Container">
    <%-- 차박지 이름 및 각종 메뉴 --%>
    <div class="cbj_detail_top">
        <div class="cbj_detail_top_upper">
            <p class="cbj_placeName_txt">${chabakDetail[0].placeName}</p>
        </div>
        <div class="cbj_detail_top_lower">
            <div class="cbj_detail_menu_img_region" style="float: right">
                <img src="/static/img/star_icon.PNG" class="circle_img star_icon_region" id="star_icon" onclick="changeStarIcon();">
                <img src="/static/img/share_icon.PNG" class="circle_img">
                <img src="/static/img/link_icon.PNG" class="circle_img">
            </div>
        </div>
    </div>
    <hr style="border-top: 2px solid black"/>
    <%-- 차박지 사진 및 상세 정보 --%>
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
                    <td>${chabakDetail[0].avg_point} / 5.0 점 (${reviewList.size()})</td>
                </tr>
                <tr>
                    <th>이 차박지를 찜한 사람</th>
                    <td>${chabakDetail[0].jjim} 명</td>
                </tr>
            </table>
        </div>
    </div>
    <hr style="border-top: 2px solid black"/>
    <%-- 차박지 위치 및 주변 정보 지도상 표시 --%>
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
                    <li id="locationMenu" onclick="panTo()">
                        <span class="cur_location_icon"></span>
                        <div>차박지위치</div>
                    </li>
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
        <div class="cbj_detail_facility">
            <div class="cbj_detail_toilet">
                <div class="cbj_detail_facility_name">화장실 정보 (${toiletList.size()})</div>
                <c:if test="${toiletList.size()==0}">
                    <div style="padding: 10px">차박지 주변의 화장실 정보가 없습니다.</div>
                </c:if>
                <c:if test="${toiletList.size()!=0}">
                    <table class="cbj_detail_info_table">
                        <tr><th>번호</th><td id="toilet_num">1</td></tr>
                        <tr><th>주소</th><td id="toilet_address">${toiletList[0].address}</td></tr>
                        <tr><th>개방시간</th><td id="toilet_openTime">${toiletList[0].open_time}</td></tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <button id="toilet_previous" onclick="toiletPrevious()" type="button" class="btn btn-default" style="width: 100px;">이전</button>
                                <button id="toilet_next" onclick="toiletNext()" type="button" class="btn btn-default" style="width: 100px;">이후</button>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </div>
            <div class="cbj_detail_fishing">
                <c:set var="fishing_index" value="0"/>
                <div class="cbj_detail_facility_name">낚시터 정보 (${fishingList.size()})</div>
                <c:if test="${fishingList.size()==0}">
                    <div style="padding: 10px">차박지 주변의 낚시터 정보가 없습니다.</div>
                </c:if>
                <c:if test="${fishingList.size()!=0}">
                    <table class="cbj_detail_info_table">
                        <tr><th>번호</th><td id="fishing_num">1</td></tr>
                        <tr><th>낚시터명</th><td id="fishing_name">${fishingList[0].name}</td></tr>
                        <tr><th>주소</th><td id="fishing_address">${fishingList[0].address}</td></tr>
                        <tr><th>유형</th><td id="fishing_type">${fishingList[0].type}</td></tr>
                        <tr><th>전화번호</th><td id="fishing_phone">${fishingList[0].phone}</td></tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <button id="fishing_previous" onclick="fishingPrevious()" type="button" class="btn btn-default" style="width: 100px;">이전</button>
                                <button id="fishing_next" onclick="fishingNext()" type="button" class="btn btn-default" style="width: 100px;">이후</button>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <hr style="border-top: 2px solid black">
    <%-- 차박지 리뷰 --%>
    <div class="cbj_detail_review_region">
            <div class="iconAndText_Region">
                <img class="icon" src="/static/img/review_icon.PNG">
                <div class="icon_text sub_title_txt">차박지 리뷰</div>
                <div class="article_comment_region">
                    <div class="article_comment_header_region">
                        <div class="article_comment_header_txt">리뷰  ${reviewList.size()}개</div>
                    </div>
                    <div class="article_comment_list_region">
                        <c:forEach var="i" items="${reviewList}">
                            <div class="article_writer_info_region">
                                <img src="/static/img/profile.PNG" class="profile_sm"/>
                                <div style="overflow: hidden">
                                    <div style="float: left; margin-right: 10px;" class="article_writer_txt">${i.nickName}</div>
                                    <c:forEach begin="1" end="${i.evaluation_point}">
                                        <img src="/static/img/star_icon.PNG" class="cbj_detail_review_star"/>
                                    </c:forEach>
                                </div>
                                <div class="comment_content_txt">${i.review_content}</div>
                                <div class="article_reg_time_txt">${i.eval_time}</div>
                                <div class="horizontal_gray"></div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="article_comment_write_region">
                        <div style="overflow: hidden">
                            <div class="article_writer_txt" style="float: left; margin-right: 10px;">
                                <c:if test="${sessionScope.get('id')==null}">
                                    손님
                                </c:if>
                                <c:if test="${sessionScope.get('id')!=null}">
                                    ${sessionScope.get("id")}
                                </c:if>
                            </div>
                            <img src="/static/img/star_icon.PNG" class="cbj_detail_review_star"/>
                            <select id="evalPoint" class="form-control" style="float: left; width: 100px;margin-left: 10px;height: 30px;">
                                <option value="0">점수</option>
                                <option value="1">1점</option>
                                <option value="2">2점</option>
                                <option value="3">3점</option>
                                <option value="4">4점</option>
                                <option value="5">5점</option>
                            </select>
                        </div>

                        <div class="article_comment_write_content">
                            <textarea class="form-control noresize" rows="5" placeholder="차박지에 대한 느낀점을 남겨보세요." id="reviewContent" style="padding: 20px"></textarea>
                        </div>
                        <div class="article_comment_menu">
                            <button type="button" class="btn button_right" id="writeReviewBtn">등록</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
<jsp:include page="../footer.jsp"/>
<script>
    var starImgSrc = '/static/img/star_icon.PNG';
    var starFillImgSrc = '/static/img/star_fill_icon.PNG';

    if(${isJjimPlace != "0"}){
        $("#star_icon").attr("src", starFillImgSrc);
        $(".star_icon_region").css("backgroundColor", '#e6e8a9');
    }

    function changeStarIcon() {
        var src = jQuery('#star_icon').attr("src");
        if(src === starImgSrc){
            if(jjim() === "success"){
                $("#star_icon").attr("src", starFillImgSrc);
                $(".star_icon_region").css("backgroundColor", '#e6e8a9');
            }
        }else{
            if(unjjim() === "success"){
                $("#star_icon").attr("src", starImgSrc);
                $(".star_icon_region").css("backgroundColor", '#fff');
            }
        }
    }
    function isLoginMember(){
        if(${sessionScope.get("id") == null}){
            alert('로그인 후 이용해주세요.');
            location.href='/member/login';
            return false;
        }
        return true;
    }
    function jjim() {
        var result = '';
        if(!isLoginMember()) {return false;}
        var jjim = {
            id : '${sessionScope.get("id")}',
            placeName : '${chabakDetail[0].placeName}',
            placeId : '${chabakDetail[0].placeId}'
        };

        $.ajax({
            url : "/member/jjim.do",
            data : jjim,
            type : "post",
            dataType : "json",
            async : false,
            success : function(resp) {result = resp;},
            error : function() {alert("error")}
        });
        return result;
    }
    function unjjim() {
        var result = '';
        if(!isLoginMember()) {return false;}
        var jjim = {
            id : '${sessionScope.get("id")}',
            placeName : '${chabakDetail[0].placeName}',
            placeId : '${chabakDetail[0].placeId}'
        };
        $.ajax({
            url : "/member/jjim.undo",
            data : jjim,
            type : "post",
            dataType : "json",
            async : false,
            success : function(resp) {result = resp;},
            error : function() {alert("error")}
        });
        return result;
    }

    var toiletMarkers = [];
    var fishingMarkers = [];

    // 차박지 위치
    var placePosition = new kakao.maps.LatLng(${chabakDetail[0].latitude}, ${chabakDetail[0].longitude});

    // 지도 요소 띄우기
    var mapContainer = document.getElementById('map');
    var mapOption = {center: placePosition, level: 5}; // 지도의 중심좌표
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
            map: map,
            position: toiletPositions[i].latlng,
            image : toiletImage
        });

        toiletMarkers.push(toiletMarker);

        var toiletInfowindow = new kakao.maps.InfoWindow({
            content: toiletPositions[i].content
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
            map: map,
            position: fishingPositions[j].latlng,
            image: fishingImage
        });

        fishingMarkers.push(fishingMarker);

        var fishingInfowindow = new kakao.maps.InfoWindow({
            content: fishingPositions[j].content
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

        if(type === 'all') {
            allMenu.className = 'menu_selected';
            toiletMenu.className = '';
            fishingMenu.className = '';

            showAllMarkers();
        }else if (type === 'toilet') {
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
        }
    }
    // 모든 마커 보여주기
    function showAllMarkers(){
        setToiletMarkers(map);
        setFishingMarkers(map);
    }
    // 화장실 마커들의 지도 표시 여부를 설정하는 함수
    function setToiletMarkers(map) {
        for (var i = 0; i < toiletMarkers.length; i++) {
            toiletMarkers[i].setMap(map);
        }
    }
    // 낚시터 마커들의 지도 표시 여부를 설정하는 함수
    function setFishingMarkers(map) {
        for (var i = 0; i < fishingMarkers.length; i++) {
            fishingMarkers[i].setMap(map);
        }
    }
    // 차박지 위치로 지도 이동하기
    function panTo() {
        map.setLevel(5);
        map.panTo(placePosition);
    }
</script>
<script>
    <%--  리뷰작성 작업 DB Insert 수행  --%>
    $("#writeReviewBtn").click(function(){
        if(!invalidate_check()) {
            return false;
        }

        if(${sessionScope.get("id") == null}){
            alert('로그인 후 이용해주세요.');
            location.href='/member/login';
        }

        var review = {
            memberId : '${sessionScope.get("id")}',
            placeId : ${chabakDetail[0].placeId},
            placeName : '${chabakDetail[0].placeName}',
            eval : $("#evalPoint").val(),
            review : $("#reviewContent").val()
        };

        $.ajax({
            url : "/chabak/eval.do",
            data : review,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                if(resp != "1"){
                    alert("리뷰가 성공적으로 작성되었습니다.");
                    window.location.reload();
                }else{
                    alert("리뷰 작성에 실패했습니다. 잠시 후 다시 시도해 주세요.");
                }
            },
            error : function() {
                alert("error")
            }
        });
    });

    <!-- 유효성 검사 -->
    function invalidate_check(){
        // 리뷰 내용 공백 확인
        if($("#reviewContent").val() == ""){
            alert("내용을 입력해주세요.");
            $("#reviewContent").focus();
            return false;
        }
        if($("#evalPoint").val() == 0){
            alert("점수를 선택해주세요.");
            $("#evalPoint").focus();
            return false;
        }
        return true;
    }
</script>
<script>
    var fishingIndex = 0;
    var fishingSize = ${fishingList.size()};
    var fishingList = [];

    <c:forEach var="i" items="${fishingList}">
    var fishing = {
        fishingspotId : '${i.fishingspotId}',
        name : '${i.name}',
        address : '${i.address}',
        type : '${i.type}',
        phone : '${i.phone}',
        latitude : '${i.latitude}',
        longitude : '${i.longitude}'
    };
    fishingList.push(fishing);
    </c:forEach>

    var toiletIndex = 0;
    var toiletSize = ${toiletList.size()};
    var toiletList = [];

    <c:forEach var="i" items="${toiletList}">
    var toilet = {
        toiletId : '${i.toiletId}',
        address : '${i.address}',
        open_time : '${i.open_time}',
        latitude : '${i.latitude}',
        longitude : '${i.longitude}'
    };
    toiletList.push(toilet);
    </c:forEach>

    function toiletPrevious(){
        if(toiletIndex-1 < 0){toiletIndex = 0;
        }else{toiletIndex--;}
        setToiletInfo();
    }

    function toiletNext(){
        if(toiletIndex+1 >= toiletSize){toiletIndex = toiletSize-1;
        }else{toiletIndex++;}
        setToiletInfo();
    }

    function fishingPrevious(){
        if(fishingIndex-1 < 0){fishingIndex = 0;
        }else{fishingIndex--;}
        setFishingInfo();
    }

    function fishingNext(){
        if(fishingIndex+1 >= fishingSize){fishingIndex = fishingSize-1;
        }else{fishingIndex++;}
        setFishingInfo();
    }

    function setFishingInfo(){
        $("#fishing_num").html(fishingIndex+1);
        $("#fishing_name").html(fishingList[fishingIndex].name);
        $("#fishing_address").html(fishingList[fishingIndex].address);
        $("#fishing_type").html(fishingList[fishingIndex].type);
        $("#fishing_phone").html(fishingList[fishingIndex].phone);
    }

    function setToiletInfo(){
        $("#toilet_num").html(toiletIndex+1);
        $("#toilet_address").html(toiletList[toiletIndex].address);
        $("#toilet_openTime").html(toiletList[toiletIndex].open_time);
    }
</script>

