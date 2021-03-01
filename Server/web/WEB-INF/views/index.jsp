<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"/>

<div class="full_container">
    <div class="left_container">
        <h3>내 정보</h3>
        <div class="myInfo_container">
            <div class="myInfo_top">
                <div class="myInfo_img">
                    <img src="/static/img/profile.PNG" alt="profile" style="max-width: -webkit-fill-available;">
                </div>
                <div class="myInfo_txt">
                    <div class="profile_txt">${sessionScope.get("id")} 님</div>
                </div>
            </div>
            <div class="myInfo_bottom">
                <button class="btn button_left" type="button">마이페이지</button>
                <button class="btn button_right" type="button">로그아웃</button>
            </div>
        </div>

        <h3>게시판</h3>
        <div class="board_container">
        </div>
    </div>
    <div class="right_container">
        <h1>차박 여행</h1>
        <img src="/static/img/img1.PNG" alt="차박이미지1" style="float: left; padding: 20px; width: 50%; height: 400px;">
        <img src="/static/img/img2.PNG" alt="차박이미지2" style="float: right; padding: 20px; width: 50%; height: 400px;">
    </div>
</div>

<jsp:include page="footer.jsp"/>

