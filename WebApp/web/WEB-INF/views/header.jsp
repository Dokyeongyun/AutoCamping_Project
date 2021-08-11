<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>HOME</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=df0498b349cbd74c43b9ccfb23dba540"></script>

    <%-- CSS --%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/HeaderCSS.css?ver=2" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/MainCSS.css?ver=3" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/chabak.css?ver=3" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/boardmain.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/showArticle.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/chabakmain.css?ver=3" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/footer.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom_overlay.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/chabakMap.css?ver=1" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/chabakDetail.css?ver=2" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/kakaoMap.css?ver=2" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/JoinCSS.css?ver=3" type="text/css">

    <%-- JS --%>
    <script src="${pageContext.request.contextPath}/static/js/validate.js" type="text/javascript" ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.cookie.js" type="text/javascript" ></script>

    <%--image slide library - slick --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/slick/slick.css?ver=1"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/slick/slick-theme.css?ver=1"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <%--image slide library - slick --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/slick/slick.min.js"></script>
</head>
<body>
<nav style="background: white;">
    <div class="headerWrap">
        <div class="topHeader">
            <div class="navbar-header">
                <a class="navbar-brand" href="/" style="font-family: 'BM_Jua'; color: black;">차박, 여기서</a>
            </div>
            <div style="overflow: hidden">
                <ul class="topHeaderMenu">
                    <c:if test="${sessionMember.memberId != null}">
                        <li><div class="greeting">${sessionMember.memberId} 님, 환영합니다.</div></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/member/modifyInfo">회원정보</a></li>
                        <li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
                    </c:if>
                    <c:if test="${sessionMember.memberId == null}">
                        <li><a href="${pageContext.request.contextPath}/member/loginForm">로그인</a></li>
                        <li><a href="${pageContext.request.contextPath}/member/joinForm">회원가입</a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}#">사이트맵</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="headerWrap">
        <div class="bottomHeader" id="bottomHeader">
            <ul class="bottomHeaderMenu">
                <li>
                    <a href="${pageContext.request.contextPath}/board/main">커뮤니티</a>
                    <ul class="herderSubMenu">
                        <li><a class="headerSubMenuItem" href="${pageContext.request.contextPath}/board/main">공지사항</a></li>
                        <li><a class="headerSubMenuItem" href="#">게시판1</a></li>
                        <li><a class="headerSubMenuItem" href="#">게시판2</a></li>
                        <li><a class="headerSubMenuItem" href="#">게시판3</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/chabak/main">차박지둘러보기</a>
                    <ul class="herderSubMenu">
                        <li><a class="headerSubMenuItem" href="${pageContext.request.contextPath}/chabak/main">차박지검색</a></li>
                        <li><a class="headerSubMenuItem" href="${pageContext.request.contextPath}/chabak/map?province=all">차박지지도</a></li>
                        <li><a class="headerSubMenuItem" href="${pageContext.request.contextPath}/chabak/ranking/avg_point">차박지순위</a></li>
                        <li><a class="headerSubMenuItem" href="#">차박후기</a></li>
                    </ul>
                </li>
                <li>
                    <a href="/">고객센터</a>
                    <ul class="herderSubMenu">
                        <li><a class="headerSubMenuItem" href="#">Q&A</a></li>
                        <li><a class="headerSubMenuItem" href="#">#</a></li>
                        <li><a class="headerSubMenuItem" href="#">#</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script>
    // navbar mouseover event
    $("#bottomHeader").hover(function() {
        $(".herderSubMenu").addClass('active');
        $("#bottomHeader").css("background","#f6f6f6");
    }, function(){
        $(".herderSubMenu").removeClass('active');
        $("#bottomHeader").css("background", "white");
    });
</script>
<body>
