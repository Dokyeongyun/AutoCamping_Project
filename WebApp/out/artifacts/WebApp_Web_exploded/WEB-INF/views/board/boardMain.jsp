<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<style>

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
        <div class="page_title_region">
            <div class="title_txt">전체글보기</div>
            <div class="small_txt">${allArticleList.size()}개의 글</div>
        </div>
        <div class="article_list_region">
            <table class="article_list_table">
                <thead>
                <tr>
                    <th width="10%">게시판이름</th>
                    <th width="50%">제목</th>
                    <th width="20%">작성자</th>
                    <th width="10%">작성일</th>
                    <th width="10%">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${allArticleList}">
                    <tr>
                        <td>게시판이름</td>
                        <td><a href="/board/article/${i.articleId}">${i.title}</a></td>
                        <td>${i.nickName}</td>
                        <td>${i.createTime}</td>
                        <td>1</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pagination_region">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </div>
        <div class="article_search_region">
            <select id="searchDate" class="form-control" style="width: 150px; display: inline-block">
                <option value="all">전체기간</option>
                <option value="1d">1일</option>
                <option value="1w">1주</option>
                <option value="1m">1개월</option>
                <option value="6m">6개월</option>
                <option value="1y">1년</option>
            </select>
            <select id="searchBy" class="form-control" style="width: 150px; display: inline-block">
                <option value="titleAndContent">제목+내용</option>
                <option value="title">제목만</option>
                <option value="writer">글작성자</option>
                <option value="comment">댓글내용</option>
                <option value="comment_writer">댓글작성자</option>
            </select>
            <input type="text" id="searchKeyword" class="form-control" placeholder="검색어를 입력해주세요"
                   style="width: 200px; display: inline-block"/>
            <button class="btn" type="button" id="searchArticleBtn" style="background-color: #77b7b4; color: white">검색
            </button>
        </div>
    </div>
    <div class="rightContainer">
        <div class="loginBox">
            <form:form modelAttribute="loginForm" action="/member/login" method="post">
                <form:input path="memberId" placeholder="아이디" cssClass="joinFormInputBox" cssStyle="height: 30px"/>
                <form:password path="password" placeholder="비밀번호" cssClass="joinFormInputBox" cssStyle="height: 30px"/>
                <div class="loginBoxSubmitRegion">
                    <label class="checkIDRemember"><input type="checkbox" name="remember">아이디기억</label>
                    <button type="submit" class="buttonSubmit">로그인</button>
                </div>
                <div class="loginBoxMenu">
                    <a href="#" class="button_find">아이디·비밀번호 찾기</a>
                    <a href="${pageContext.request.contextPath}/member/joinForm" class="button_signup">회원가입</a>
                </div>
            </form:form>
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