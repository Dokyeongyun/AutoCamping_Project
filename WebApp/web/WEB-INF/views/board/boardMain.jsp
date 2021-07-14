<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<style>
body{background-color: #eee;}
</style>

<div class="fullContainer">
    <div class="leftContainer">
        <div class="leftNav">
            <div class="leftNavMenu">
                <a class="menuGroup all" href="/service/group/board_all">
                    <span class="menuOver">커뮤니티</span>
                </a>
                <a class="menuGroup" href="#">
                    <span class="menuOver">메인메뉴</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">전체글보기</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">공지사항</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">필독사항</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">출석부</span>
                </a>
                <a class="menuGroup" href="#">
                    <span class="menuOver">차박 후기</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">차박캠핑 후기</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">정기캠핑 후기</span>
                </a>
                <a class="menuGroup" href="#">
                    <span class="menuOver">만남의 광장</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">자유게시판</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">질문게시판</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">차박 노하우</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">차박지 공유</span>
                </a>
                <a class="menuList" href="#">
                    <span class="menuOver">차박 장비 후기</span>
                </a>
            </div>
        </div>
        <%--    <div class="boardMenuTitle">나의활동</div>
            <c:if test="${sessionMember.memberId != null}">
                <div class="boardMenuContainer">
                    <div class="myInfo_top">
                        <div class="myInfo_img">
                            <img src="/static/img/profile.PNG" alt="profile" style="max-width: -webkit-fill-available;">
                        </div>
                        <div class="myInfo_txt">
                            <div class="profile_txt">${sessionMember.memberId} 님</div>
                        </div>
                    </div>
                    <div class="myInfo_bottom">
                        <button class="btn button_left" type="button" style="width: 100%"
                                onclick="location.href='/board/showMyArticle'">내가 쓴 글 보기
                        </button>
                        <button class="btn button_left" type="button" style="width: 100%"
                                onclick="location.href='/board/showMyComment'">내가 쓴 댓글 보기
                        </button>
                        <button class="btn button_left" type="button" style="width: 100%"
                                onclick="location.href='/board/articleWriteForm'">글 작성하기
                        </button>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionMember.memberId == null}">
                <div class="boardMenuContainer">
                    <div class="myInfoNoSession">
                        <span style="font-size: 15px;">로그인 후 확인가능합니다.</span>
                        <button type="button" onclick="location.href='/member/loginForm'">로그인</button>
                    </div>
                </div>
            </c:if>
            <hr>
            <div class="boardMenuTitle">게시판</div>
            <div class="boardMenuContainer">
                <div class="board_list_region">
                    <ul class="list-group">
                        <li class="list-group-item board_list_title">전체글보기</li>
                        <div class="board_list_space"></div>
                        <li class="list-group-item">게시판1</li>
                        <li class="list-group-item">게시판2</li>
                        <li class="list-group-item">게시판3</li>
                        <li class="list-group-item">게시판4</li>
                        <li class="list-group-item">게시판5</li>
                    </ul>
                </div>
            </div>--%>
    </div>
    <div class="mainContainer">
        <div class="sectionContentWrap">
            <div class="sectionContent">
                <div class="sectionList notice">
                    <h2 class="sectionTitle">
                        <a class="news_title" href="#" title="공지사항 바로가기">공지사항 ></a>
                    </h2>
                    <div class="sectionBody">
                        <c:forEach begin="1" end="7" var="i">
                            <div class="listItem">
                                <div class="listTitle">
                                    <a class="listSubject" href="#">
                                        <span class="shortname">공지사항</span>
                                        <span class="subject" title="공지사항${i}">공지사항${i}</span>
                                    </a>
                                    <a class="listReply" href="#">
                                        <span>${i*10}</span>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="sectionList hot">
                    <h2 class="sectionTitle">
                        <a class="news_title" href="#" title="HOT 게시글 바로가기">HOT 게시글 ></a>
                    </h2>
                    <div class="sectionBody">
                        <c:forEach begin="1" end="7" var="i">
                            <div class="listItem">
                                <div class="listTitle">
                                    <a class="listSubject" href="#">
                                        <span class="shortname">게시판${i}</span>
                                        <span class="subject" title="HOT${i}">HOT${i}</span>
                                    </a>
                                    <a class="listReply" href="#">
                                        <span>${i*10}</span>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="rightContainer">
        <div class="loginBox">
            <c:if test="${sessionMember.memberId == null}">
                <form:form modelAttribute="loginForm" action="/member/login" method="post">
                    <form:input path="memberId" placeholder="아이디" cssClass="joinFormInputBox" cssStyle="height: 30px"/>
                    <form:password path="password" placeholder="비밀번호" cssClass="joinFormInputBox" cssStyle="height: 30px"/>
                    <div class="loginBoxSubmitRegion">
                        <label class="checkIDRemember"><input type="checkbox" name="remember">아이디기억</label>
                        <button type="submit" class="buttonSubmit">로그인</button>
                    </div>
                    <div class="loginBoxMenu">
                        <a href="${pageContext.request.contextPath}/member/findIDForm" >아이디·</a><a href="${pageContext.request.contextPath}/member/findPasswordForm" >비밀번호 찾기</a>
                        <a href="${pageContext.request.contextPath}/member/joinForm" class="button_signup">회원가입</a>
                    </div>
                </form:form>
            </c:if>
            <c:if test="${sessionMember.memberId != null}">
                <div style="text-align: center; font-weight: bold; padding: 10px;">
                    <c:out value="${sessionMember.memberId}"/>님
                </div>
                <div class="loginBoxMenu">
                    <a href="${pageContext.request.contextPath}/member/myArticles" >내 게시글</a>
                    <a href="${pageContext.request.contextPath}/member/logout" class="button_signup">로그아웃</a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<script>
    $("#searchArticleBtn").click(function () {
        if (!invalidate_check()) {
            return false;
        }
        var keyword = $("#searchKeyword").val();
        location.href = '/board/getArticleByKeyword/' + keyword;
    });

    <!-- 유효성 검사 -->
    function invalidate_check() {
        // 댓글 내용 공백 확인
        if ($("#searchKeyword").val() == "") {
            alert("검색어을 입력해주세요.");
            $("#searchKeyword").focus();
            return false;
        }
        return true;
    }
</script>