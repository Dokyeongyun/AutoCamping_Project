<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="searchArea">
        <form name="searchForm" class="searchForm" action="#" method="get">
            <div class="searchFormDiv">
                <input class="searchInputBox" type="text" name="searchKeyword" id="searchKeyword" placeholder="게시판 및 차박지 정보를 통합검색합니다."
                       title="게시판 및 차박지 정보를 통합검색합니다.">
                <input type="image" src="${pageContext.request.contextPath}/static/img/search_icon_blue.PNG" style="height: 100%" alt="검색" title="검색">
            </div>
        </form>
    </div>
    <hr>
    <div class="slideImgArea">
        <div id="slideDiv">
            <div class="slideImgDiv"><img src="${pageContext.request.contextPath}/static/img/notice1.PNG"></div>
            <div class="slideImgDiv"><img src="${pageContext.request.contextPath}/static/img/notice2.PNG"></div>
            <div class="slideImgDiv"><img src="${pageContext.request.contextPath}/static/img/notice3.PNG"></div>
            <div class="slideImgDiv"><img src="${pageContext.request.contextPath}/static/img/notice4.PNG"></div>
        </div>
    </div>
    <hr>
    <div class="contentArea">
        <div class="content1">
            <div class="contentTop">
                <img src="${pageContext.request.contextPath}/static/img/notice_icon.PNG">
                <h2>공지사항</h2>
                <img src="${pageContext.request.contextPath}/static/img/more_icon.PNG" style="float: right">
            </div>
            <div class="contentBottom">
                <div class="noticeList">
                    <ul>
                        <li>
                            <a href="#">Notice1</a>
                            <span>May 23, 2021</span>
                        </li>
                        <li>
                            <a href="#">Notice2</a>
                            <span>May 19, 2021</span>
                        </li>
                        <li>
                            <a href="#">Notice3</a>
                            <span>May 19, 2021</span>
                        </li>
                        <li>
                            <a href="#">Notice4</a>
                            <span>May 17, 2021</span>
                        </li>
                        <li>
                            <a href="#">Notice5</a>
                            <span>May 16, 2021</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>

<jsp:include page="footer.jsp"/>

<%-- 이미지 슬라이드 (Slick 라이브러리) --%>
<script>
    $(function(){
        $('#slideDiv').slick({
            slide: 'div',		//슬라이드 되어야 할 태그 ex) div, li
            infinite : true, 	//무한 반복 옵션
            slidesToShow : 1,		// 한 화면에 보여질 컨텐츠 개수
            slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
            speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
            arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
            dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
            autoplay : true,			// 자동 스크롤 사용 여부
            autoplaySpeed : 2000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
            pauseOnHover : true,		// 슬라이드 이동시 마우스 호버하면 슬라이더 멈추게 설정
            vertical : false,		// 세로 방향 슬라이드 옵션
            prevArrow : "<button type='button' class='slick-prev'>Previous</button>",		// 이전 화살표 모양 설정
            nextArrow : "<button type='button' class='slick-next'>Next</button>",		// 다음 화살표 모양 설정
            dotsClass : "slick-dots", 	//아래 나오는 페이지네이션(점) css class 지정
            draggable : true, 	//드래그 가능 여부
            responsive: [ // 반응형 웹 구현 옵션
                {
                    breakpoint: 960, //화면 사이즈 960px
                    settings: {
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                        slidesToShow:1
                    }
                },
                {
                    breakpoint: 768, //화면 사이즈 768px
                    settings: {
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                        slidesToShow:1
                    }
                }
            ]
        });
    })
</script>
