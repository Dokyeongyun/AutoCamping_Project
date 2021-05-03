<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${sessionMember.memberId == article.memberId}"><c:set var="isSessionMember" value="true"/></c:if>
<c:if test="${sessionMember.memberId != article.memberId}"><c:set var="isSessionMember" value="false"/></c:if>
<c:if test="${sessionMember.memberId != null}"><c:set var="isLoginMember" value="true"/></c:if>
<c:if test="${sessionMember.memberId == null}"><c:set var="isLoginMember" value="false"/></c:if>
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
                <div class="article_title_txt">${article.title}</div>
                <div class="article_writer_info_region">
                    <img src="/static/img/profile.PNG" class="profile_sm"/>
                    <div class="article_writer_txt">${article.nickName}</div>
                    <div class="article_reg_time_txt">${article.createTime} 조회수 : 1</div>
                    <div class="ArticleTool">
                        <ul style="list-style: none">
                            <li style="float: left; margin-right: 10px;">
                                <button type="button" class="btn btn-primary">댓글 ${commentList.size()}개</button>
                            </li>
                            <li style="float: left; margin-right: 10px;">
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">메뉴</button>
                                    <ul class="dropdown-menu" style="display: contents">
                                        <c:if test="${isSessionMember}">
                                            <li><a class="dropdown-item"
                                                   href="/board/articleUpdateForm/${article.articleId}">수정하기</a></li>
                                            <li>
                                                <form:form action="/board/article/${article.articleId}" method="post">
                                                    <input type="hidden" name="_method" value="delete"/>
                                                    <input type="submit" class="dropdown-item" value="삭제하기"/>
                                                </form:form>
                                            </li>
                                        </c:if>
                                        <c:if test="${!isSessionMember}">
                                            <li><a class="dropdown-item" href="#">스크랩하기</a></li>
                                            <li><a class="dropdown-item" href="#">URL복사</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="article_info_content_region">
                <div class="article_content_txt">${article.content}</div>
            </div>

            <div class="article_comment_region">
                <%-- 댓글 헤더 --%>
                <div class="article_comment_header_region">
                    <div class="article_comment_header_txt">댓글 ${commentList.size()}개</div>
                </div>
                <%-- 댓글 리스트 --%>
                <div class="article_comment_list_region">
                    <c:forEach var="i" items="${commentList}">
                        <div class="article_writer_info_region">
                            <div class="commentInfo" id="commentInfoRegion${i.commentId}">
                                <img src="/static/img/profile.PNG" class="profile_sm"/>
                                <div class="article_writer_txt">${i.nickName}</div>
                                <div class="comment_content_txt">${i.content}</div>
                                <div class="article_reg_time_txt">${i.createTime}</div>

                                <div class="commentMenu" style="margin-left: 55px;">
                                    <c:if test="${sessionMember.memberId == i.memberId}">
                                        <button type="button" onclick="showCommentUpdateForm(${i.commentId})">수정
                                        </button>
                                        <form action="/board/comment/${i.commentId}" method="post" style="display: inline">
                                            <input type="hidden" name="_method" value="delete"/>
                                            <input type="hidden" name="commentId" value="${i.commentId}"/>
                                            <input type="hidden" name="articleId" value="${articleId}"/>
                                            <input type="submit" value="삭제"/>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                                <%-- 댓글 수정 폼 --%>
                            <div id="commentUpdateFormRegion${i.commentId}"
                                 style="display: none">
                                <form action="/board/comment" method="post">
                                    <div class="commentInfo" id="commentInfoRegion${i.commentId}">
                                        <img src="/static/img/profile.PNG" class="profile_sm"/>
                                        <div class="article_writer_txt">${i.nickName}</div>
                                        <textarea rows="2" name="content" class="form-control noresize"
                                                  style="padding: 20px; margin-top: 40px">${i.content}</textarea>

                                        <div class="commentMenu" style="margin-top: 20px;">
                                            <c:if test="${sessionMember.memberId == i.memberId}">
                                                <input type="hidden" name="_method" value="put"/>
                                                <input type="hidden" name="articleId" value="${articleId}"/>
                                                <input type="hidden" name="commentId" value="${i.commentId}"/>
                                                <input type="hidden" name="memberId" value="${sessionMember.memberId}"/>
                                                <input type="submit" value="등록"/>
                                                <button type="button" id="commentUpdateCancelBtn"
                                                        onclick="commentUpdateCancel(${i.commentId})">취소
                                                </button>
                                            </c:if>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="horizontal_gray"></div>
                        </div>
                    </c:forEach>
                </div>

                <%-- 댓글 작성 --%>
                <div class="article_comment_write_region">
                    <form:form modelAttribute="commentForm" action="/board/comment" method="post">
                        <div class="article_writer_txt">
                            <c:if test="${!isLoginMember}">손님</c:if>
                            <c:if test="${isLoginMember}">${sessionMember.nickName} 님</c:if>
                        </div>
                        <div class="article_comment_write_content">
                            <form:textarea path="content" cssClass="form-control noresize" rows="5"
                                           cssStyle="padding: 20px;" placeholder="댓글을 남겨보세요."/>
                        </div>
                        <div class="article_comment_menu">
                            <form:hidden path="articleId" value="${articleId}"/>
                            <form:hidden path="memberId" value="${sessionMember.memberId}"/>
                            <button type="submit" class="btn button_right">등록</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<script>

    function showCommentUpdateForm(commentId) {
        $('#' + 'commentUpdateFormRegion' + commentId).show();
        $('#' + 'commentInfoRegion' + commentId).hide();
    }

    function commentUpdateCancel(commentId) {
        $('#' + 'commentUpdateFormRegion' + commentId).hide();
        $('#' + 'commentInfoRegion' + commentId).show();
    }

</script>