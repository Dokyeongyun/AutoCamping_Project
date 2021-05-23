<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>HOME</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/HeaderCSS.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/MainCSS.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabak.css?ver=3" type="text/css">
    <link rel="stylesheet" href="/static/css/boardmain.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/showArticle.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakmain.css?ver=3" type="text/css">
    <link rel="stylesheet" href="/static/css/footer.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/custom_overlay.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakMap.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakDetail.css?ver=2" type="text/css">
    <link rel="stylesheet" href="/static/css/kakaoMap.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/JoinCSS.css?ver=2" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>

    <%--image slide library - slick --%>
    <link rel="stylesheet" type="text/css" href="/static/slick/slick.css?ver=1"/>
    <link rel="stylesheet" type="text/css" href="/static/slick/slick-theme.css?ver=1"/>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <%--image slide library - slick --%>
    <script type="text/javascript" src="/static/slick/slick.min.js"></script>
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
        <div class="bottomHeader">
            <ul class="bottomHeaderMenu">
                <li><a href="${pageContext.request.contextPath}/board/main">커뮤니티</a></li>
                <li><a href="${pageContext.request.contextPath}/chabak/main">차박지</a></li>
                <li><a href="/">고객센터</a></li>
            </ul>
        </div>
    </div>
</nav>
<body>
