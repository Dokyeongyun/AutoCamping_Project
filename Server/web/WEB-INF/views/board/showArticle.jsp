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
            <div class="title_txt">게시글읽기</div>
        </div>
        <div class="article_info_region">
            <div class="article_info_header_region">
                <div class="board_type_nav_txt"><a href="#">자유게시판 ></a></div>
                <div class="article_title_txt">${article[0].title}</div>
                <div class="article_writer_info_region">
                    <img src="/static/img/profile.PNG" class="profile_sm"/>
                    <div class="article_writer_txt">${article[0].nickName}</div>
                    <div class="article_reg_time_txt">${article[0].createTime}    조회수 : 1</div>
                </div>
            </div>
            <div class="article_info_content_region">
                <div class="article_content_txt">${article[0].content}</div>
            </div>
            <div class="article_comment_region">
                <div class="article_comment_header_region">
                    <div class="article_comment_header_txt">댓글</div>
                </div>
                <div class="article_comment_list_region">
                    <c:forEach var="i" items="${commentList}">
                        <div class="article_writer_info_region">
                            <img src="/static/img/profile.PNG" class="profile_sm"/>
                            <div class="article_writer_txt">${i.nickName}</div>
                            <div class="comment_content_txt">${i.content}</div>
                            <div class="article_reg_time_txt">${i.createTime}</div>
                            <div class="horizontal_gray"></div>
                        </div>
                    </c:forEach>
                </div>
                <div class="article_comment_write_region">
                    <div class="article_writer_txt">
                        <c:if test="${sessionScope.get('id')==null}">
                            손님
                        </c:if>
                        <c:if test="${sessionScope.get('id')!=null}">
                            ${sessionScope.get("id")}
                        </c:if>
                    </div>
                    <div class="article_comment_write_content">
                        <textarea class="form-control noresize" rows="5" placeholder="댓글을 남겨보세요." id="commentContent" style="padding: 20px"></textarea>
                    </div>
                    <div class="article_comment_menu">
                        <button type="button" class="btn button_right" id="writeCommentBtn">등록</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>

