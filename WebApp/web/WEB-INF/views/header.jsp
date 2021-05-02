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
    <link rel="stylesheet" href="/static/css/chabak.css?ver=3" type="text/css">
    <link rel="stylesheet" href="/static/css/main.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/boardmain.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/showArticle.css?ver=2" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakmain.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/footer.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/custom_overlay.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakMap.css?ver=1" type="text/css">
    <link rel="stylesheet" href="/static/css/chabakDetail.css?ver=2" type="text/css">
    <link rel="stylesheet" href="/static/css/kakaoMap.css?ver=1" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">차박러 모여라</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/board/main">게시판</a></li>
                <li><a href="/chabak/main">차박지</a></li>
                <c:if test="${sessionMember.memberId != null}">
                    <li>
                        <div class="greeting">${sessionMember.memberId} 님, 환영합니다.</div>
                    </li>
                    <li class="active"><a href="/member/modifyInfo">회원정보</a></li>
                    <li><a href="/member/logout">로그아웃</a></li>
                </c:if>
                <c:if test="${sessionMember.memberId == null}">
                    <li class="active"><a href="/member/loginForm">로그인</a></li>
                    <li><a href="/member/joinForm">회원가입</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<body>

