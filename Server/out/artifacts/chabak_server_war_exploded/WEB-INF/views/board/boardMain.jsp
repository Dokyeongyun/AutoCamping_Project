<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="full_container">
    <div class="left_container">
        <h3>나의활동</h3>
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
                <div class="small_txt"><a href="/board/showMyArticle">내가 쓴 글 보기</a></div>
                <div class="small_txt"><a href="/board/showMyComment">내가 쓴 댓글 보기</a></div>
                <button class="btn button_left" type="button" style="width: 100%" onclick="location.href='/board/writeArticle'">글 작성하기</button>
            </div>
        </div>

        <h3>게시판</h3>
        <div class="board_container">
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
        </div>
    </div>
    <div class="right_container">
        <div class="page_title_region">
            <div class="title_txt">전체글보기</div>
            <div class="small_txt">5,000 개의 글</div>
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
                        <td>${i.title}</td>
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
            <form name="frmSearch" action="" onsubmit="return false;">
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
                <input type="text" id="searchWord" class="form-control" placeholder="검색어를 입력해주세요" style="width: 200px; display: inline-block"/>
                <button class="btn" type="button" style="background-color: #77b7b4; color: white">검색</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>