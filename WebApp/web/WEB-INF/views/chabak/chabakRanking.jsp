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
                <option value="avg_point" <c:if test="${sortBy eq 'avg_point'}">selected</c:if>>별점 순으로 보기</option>
                <option value="jjim" <c:if test="${sortBy eq 'jjim'}">selected</c:if>>찜 개수 순으로 보기</option>
            </select>
            <button type="button" class="btn btn-primary" onclick="function ChangeSortMethod() {
                var sortBy = $('#sortMethod').val();
                location.href='/chabak/ranking/'+sortBy;
            }
            ChangeSortMethod();">확인
            </button>
        </div>
    </div>
    <%-- 차박지 별점 순서대로 나열 --%>
    <c:set var="index" value="0"/>
    <c:forEach var="i" items="${popularList}">
        <div class="cbj_detail_top" style="background-image: url('${i.filePath}'); cursor: pointer;"
             onclick="goChabakDetail('<c:out value="${i.placeId}"/>')">
            <div class="cbj_detail_top_upper">
                <p class="cbj_placeName_txt">${i.placeName}</p>
            </div>
            <div class="cbj_detail_top_lower">
                <div class="cbj_detail_overview">
                    <img src="${pageContext.request.contextPath}/static/img/heart_icon.PNG"
                         style="width: 20px; margin-right: 4px;" alt="찜개수">${i.jjim}
                    <img src="${pageContext.request.contextPath}/static/img/star_icon.PNG"
                         style="width: 20px; margin-right: 4px;" alt="평점">${i.avg_point}
                </div>
                <div class="cbj_detail_menu_img_region" style="float: right">
                    <c:if test="${dibsStatusList.get(index) eq true}">
                        <img src="${pageContext.request.contextPath}/static/img/star_fill_icon.PNG" class="circle_img"
                             id="starIcon${i.placeId}" style="background-color: rgb(230, 232, 169);" alt="찜상태"
                             onclick="changeIconName('dibs')">
                    </c:if>
                    <c:if test="${dibsStatusList.get(index) eq false}">
                        <img src="${pageContext.request.contextPath}/static/img/star_icon.PNG" class="circle_img"
                             id="starIcon${i.placeId}" alt="찜하기" onclick="changeIconName('dibs')">
                    </c:if>
                    <img src="${pageContext.request.contextPath}/static/img/share_icon.PNG" class="circle_img"
                         alt="공유" onclick="changeIconName('share')">
                    <img src="${pageContext.request.contextPath}/static/img/link_icon.PNG" class="circle_img"
                         alt="링크복사" onclick="changeIconName('copy')">
                </div>
            </div>
        </div>
        <c:set var="index" value="${index + 1}"/>
    </c:forEach>
</div>
</body>
<jsp:include page="../footer.jsp"/>

<script>
    const starImgSrc = '/static/img/star_icon.PNG';
    const starFillImgSrc = '/static/img/star_fill_icon.PNG';

    /* 찜하기, 공유, 링크복사 아이콘 클릭을 확인하기 위한 변수 */
    let clickedIconName = '';

    /* 차박지 상세화면 이동 */
    function goChabakDetail(placeId) {
        if (clickedIconName !== '') {
            if (clickedIconName === 'dibs') {
                changeDibsStatus(placeId);
            } else if (clickedIconName === 'share') {
                shareChabakDetail();
            } else if (clickedIconName === 'copy') {
                copyChabakLink();
            }
            clickedIconName = '';
        } else {
            location.href = '/chabak/' + placeId;
        }
    }

    /* 아이콘명 변수 값 변경 */
    function changeIconName(iconName) {
        clickedIconName = iconName;
    }

    /* 찜 아이콘 클릭 이벤트 내용 */
    function changeDibsStatus(placeId) {
        let starIconObj = $("#" + ('starIcon' + placeId));
        let src = starIconObj.attr("src");
        if (src === starImgSrc) {
            if (dibs(placeId)) {
                starIconObj.attr("src", starFillImgSrc);
                starIconObj.css("backgroundColor", '#e6e8a9');
            }
        } else {
            if (!unDibs(placeId)) {
                starIconObj.attr("src", starImgSrc);
                starIconObj.css("backgroundColor", '#fff');
            }
        }
    }

    /* 공유 아이콘 클릭 이벤트 내용 */
    function shareChabakDetail() {
        console.log('share');
    }

    /* 링크복사 아이콘 클릭 이벤트 내용 */
    function copyChabakLink() {
        console.log('copy');
    }

    function isLoginMember() {
        let memberId = '${sessionMember.memberId}';
        if (memberId === '') {
            alert('로그인 후 이용해주세요.');
            location.href = '/member/loginForm';
            return false;
        }
        return true;
    }

    function dibs(placeId) {
        if (!isLoginMember()) {
            return false;
        }

        let result = '';
        let dibs = {
            memberId: '${sessionMember.memberId}',
            placeId: placeId
        };

        $.ajax({
            url: "/member/chabak/dibs",
            data: JSON.stringify(dibs),
            type: "post",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            async: false,
            success: function (resp) {
                result = resp;
            },
            error: function () {
                alert("error")
            }
        });
        return result;
    }

    function unDibs(placeId) {
        let result = '';
        if (!isLoginMember()) {
            return false;
        }
        let dibs = {
            memberId: '${sessionMember.memberId}',
            placeId: placeId
        };

        $.ajax({
            url: "/member/chabak/unDibs",
            data: JSON.stringify(dibs),
            type: "post",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            async: false,
            success: function (resp) {
                result = resp;
            },
            error: function () {
                alert("error")
            }
        });
        return result;
    }
</script>
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