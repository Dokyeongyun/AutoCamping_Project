<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28c7a8a13fd45258a42b15ec36bd466f"></script>

<jsp:include page="../header.jsp"/>
<jsp:include page="../chabakHeader.jsp"/>
<body style="background-color: black">

<div class="container chabakMain_Container">
    <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">차박지 지도</div>
    <div class="title_sub_txt">지도를 통해 차박지를 검색하실 수 있습니다.</div>
    <div class="page_notice_txt"></div>
    <ul class="notice_ul">
        <li class="notice_li">도 단위의 지역을 선택하시면 해당 지역이 확대되며, 인기있는 차박지를 확인하실 수 있습니다.</li>
        <li class="notice_li">> 버튼을 누르면, 해당 지역의 모든 차박지 정보를 확인할 수 있습니다.</li>
        <li class="notice_li">지도 우측의 컨트롤도구를 통해 지도를 조작하실 수 있습니다.</li>
    </ul>

    <!-- 지도 표시할 div태그 -->
    <div id="map" class="map_container"></div>

    <%-- 검색 결과를 출력할 container --%>
    <c:if test="${searchProvince != 'all'}">
        <div>
            <div class="title_txt" style="float: none; border-bottom: 1px solid #ddd">검색 결과</div>
            <c:if test="${searchResult.size()!=0}">
                <div class="title_sub_txt">"${searchProvince}" 검색 결과 : 총 ${searchResult.size()} 개의 결과가 있습니다.</div>
            </c:if>
            <c:if test="${searchResult.size()==0}">
                <div class="title_sub_txt">"${searchProvince}" 검색 결과가 없습니다.</div>
            </c:if>
        </div>

        <%-- 차박지 정보 한 줄에 3개씩 출력하기 --%>
        <div class="chabakjiList_row">
            <c:forEach items="${searchResult}" var="i">
                <div class="chabakjiInfo" onclick="location.href='/chabak/${i.placeId}'">
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
    </c:if>
</div>
</body>

<%--변수--%>
<script>
    // 1) 선택된 도의 이름을 저장하고 있는 변수
    var nameTemp = "";

    // 2) JSP의 BestAndCount를 javascript에서 사용하기 위한 배열
    var list = [];

    // 3) customOverlay 객체
    var customOverlay = new kakao.maps.CustomOverlay({
        position: null,
        content: null,
        xAnchor: 0.3,
        yAnchor: 0.91
    });

    // 4) 지도를 표시할 div(mapContainer)와 지도 옵션(mapOption)으로 지도(map)를 생성
    var mapContainer = document.getElementById('map')
    var mapOption = {center: new kakao.maps.LatLng(35.470507, 127.582846)}; // 지도의 중심좌표
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 5) 지도를 재설정할 범위정보를 가지고 있는 LatLngBounds 객체를 생성
    var bounds = new kakao.maps.LatLngBounds();
    bounds.extend(new kakao.maps.LatLng(38.493487983622714, 129.62012593938164));
    bounds.extend(new kakao.maps.LatLng(33.198604099249614, 125.80787037497862));
    map.setBounds(bounds);

    // 6) 도 단위의 경계 Polygon 좌표정보를 가지고 있는 json 파일의 위치
    var jsonLocation = "/static/json/TL_SCCO_CTPRVN.json";

    // 7) 도 단위의 폴리곤 정보를 저장할 배열
    var polygons = [];

    // 8) 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤 생성
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 9) 지도 확대 축소를 제어할 수 있는 줌 컨트롤 생성
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
</script>

<%--메서드--%>
<script>
    // 1) BestAndCount 생성자
    function BestAndCount(cityProvince, bestPlaceName, bestPlaceImage, count, numOfJJIM) {
        this.cityProvince = cityProvince;
        this.bestPlaceName = bestPlaceName;
        this.bestPlaceImage = bestPlaceImage;
        this.count = count;
        this.numOfJJIM = numOfJJIM;
    }

    // 2) 다각형 그리는 함수
    function displayArea(coordinates, name) {
        var path = [];
        var points = [];

        $.each(coordinates[0], function(i, coordinate) {
            var point = {};
            point.x = coordinate[1];
            point.y = coordinate[0];
            points.push(point);
            path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
        });

        var polygon = new kakao.maps.Polygon({
            path: path, // 그려질 다각형의 좌표 배열입니다
            strokeWeight: 2, // 선의 두께입니다
            strokeColor: '#004C80', // 선의 색깔입니다
            strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            fillColor: '#fff', // 채우기 색깔입니다
            fillOpacity: 0.7 // 채우기 불투명도 입니다
        });

        polygons.push(polygon);
        polygon.setMap(map);

        /*

<%--kakao.maps.event.addListener(polygon, 'mouseover', function(mouseEvent) {
        polygon.setOptions({
            fillColor : '#09f'
        });
        customOverlay.setContent('<div class="area">' + name + '</div>');
        customOverlay.setPosition(mouseEvent.latLng);
        customOverlay.setMap(map);
    });--%>
    <%--kakao.maps.event.addListener(polygon, 'mousemove', function(mouseEvent) {
        customOverlay.setPosition(mouseEvent.latLng);
    });--%>
    <%--kakao.maps.event.addListener(polygon, 'mouseout', function() {
        polygon.setOptions({
            fillColor : '#fff'
        });
        customOverlay.setMap(null);
    });--%>
*/

        // 폴리곤 클릭 이벤트
        kakao.maps.event.addListener(polygon, 'click', function() {
            // 색 변화시키기 (선택: 파란색, 비선택: 하얀색)
            deletePolygon(polygons);
            polygon.setOptions({ fillColor : '#09f' });

            // 기존 CustomOverlay 제거
            customOverlay.setMap(null);

            // 폴리곤 내의 차박지 개수 + 가장 찜이 많은 차박지 정보 가져오기
            var count = 0;
            var bestPlaceName = "";
            var bestPlaceImage = "";
            var numOfJJIM = 0;
            $.each(list, function(i, val){
                if(val.cityProvince == name){
                    count = val.count;
                    bestPlaceName = val.bestPlaceName;
                    bestPlaceImage = val.bestPlaceImage;
                    numOfJJIM = val.numOfJJIM;
                    return false;
                }
                count = 0;
            });

            nameTemp = name;

            // 커스텀 오버레이에 그릴 내용
            var content = '<div class="overlaybox">' +
                '    <div class="boxtitle">' + name + '</div>' +
                '    <div class="first" style="background-image: url(' + bestPlaceImage + '); background-size : cover" >' +
                '        <div class="triangle text">1</div>' +
                '        <li class="movietitle text">' +
                '           <span>' + bestPlaceName + '</span>' +
                '           <span class="count">' + numOfJJIM + '</span>' +
                '        </li>' +
                '    </div>' +
                '    <ul>' +
                '        <li class="up">' +
                '            <span class="number">총 ' + count + '개의 차박지</span>' +
                '            <input type="button" value="클릭" onclick="provinceSearch()"/>' +
                '        </li>' +
                '    </ul>' +
                '</div>';

            // 새로운 CustomOverlay 생성
            customOverlay.setContent(content);
            customOverlay.setPosition(centroid(points));
            customOverlay.setMap(map);

            setBounds(points);
        });

        // 전체 폴리곤 색 변경
        function deletePolygon(polygons) {for(var i = 0; i < polygons.length; i++) {polygons[i].setOptions({ fillColor : '#fff' });}}

        // 커스텀 오버레이를 닫기 위해 호출되는 함수
        function closeOverlay() {customOverlay.setMap(null);}

        // 지도 범위 재설정 (지도에 표시할 좌표들을 추가하면 해당 좌표를 모두 보여줄 수 있는 범위로 재설정)
        function setBounds(points) {
            var bounds = new kakao.maps.LatLngBounds();
            for (var i = 0; i < points.length; i++) {bounds.extend(new kakao.maps.LatLng(points[i].x, points[i].y));}
            map.setBounds(bounds);
        }
    }

    // 3) centroid 알고리즘 (폴리곤 중심좌표)
    function centroid(points){
        var i, j, len, p1, p2, f, area, x, y;
        area = x = y = 0;
        for(i = 0, len = points.length, j = len-1; i<len; j = i++){
            p1 = points[i];
            p2 = points[j];

            f = p1.y * p2.x - p2.y * p1.x;
            x += (p1.x + p2.x) * f;
            y += (p1.y + p2.y) * f;
            area += f * 3;
        }
        return new kakao.maps.LatLng(x/area, y/area);
    }

    // 4) 도, 특별시, 광역시 별 차박지 리스트 읽어오기
    function provinceSearch() {
        location.href = "/chabak/map?province="+encodeURI(nameTemp, "UTF-8");
    }

</script>

<%--작업 수행--%>
<script>
    <c:forEach items="${BestAndCount}" var="item">
    var bestAndCount = new BestAndCount("${item.value.cityProvince}", "${item.value.bestPlaceName}",
        "${item.value.bestPlaceImage}", ${item.value.count}, ${item.value.numOfJJIM});
    list.push(bestAndCount);
    </c:forEach>

    // JSON에서 좌표 읽어서 폴리곤마다 폴리곤 그리는 함수 호출
    $.getJSON(jsonLocation, function(data){
        var data = data.features;
        var coordinates = [];
        var name = '';
        $.each(data, function(i, val){
            if(val.geometry.type == "MultiPolygon"){
                name = val.properties.CTP_KOR_NM;
                var polygons = val.geometry.coordinates;

                var polygons_co = [];
                $.each(polygons, function(i, val){

                    displayArea(val, name);
                });
            }else if(val.geometry.type == "Polygon"){
                coordinates = val.geometry.coordinates;
                name = val.properties.CTP_KOR_NM;

                displayArea(coordinates, name);
            }
        });
    });
</script>

<jsp:include page="../footer.jsp"/>


