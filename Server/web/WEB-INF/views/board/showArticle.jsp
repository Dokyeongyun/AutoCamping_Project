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
                    <div class="ArticleTool">
                        <ul style="list-style: none">
                            <li style="float: left; margin-right: 10px;">
                                <button type="button" class="btn btn-primary">댓글  ${commentList.size()}개</button>
                            </li>
                            <li style="float: left; margin-right: 10px;">
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">메뉴</button>
                                    <ul class="dropdown-menu">
                                        <c:if test="${article[0].memberId == sessionScope.get('id')}">
                                            <li><a class="dropdown-item" href="/board/modifyArticle/${article[0].articleId}">수정하기</a></li>
                                            <li><a class="dropdown-item" href="#" onclick="deleteArticle()">삭제하기</a></li>
                                        </c:if>
                                        <c:if test="${article[0].memberId != sessionScope.get('id')}">
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
                <div class="article_content_txt">${article[0].content}</div>
            </div>
            <div class="article_comment_region">
                <div class="article_comment_header_region">
                    <div class="article_comment_header_txt">댓글  ${commentList.size()}개</div>
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
<style>
    .ArticleTool {
        position: absolute;
        right: 70px;
        bottom: 15px;
        font-size: 13px;
        line-height: 18px;
    }
</style>

<script>
    <%--  댓글쓰기 작업 DB Insert 수행  --%>
    $("#writeCommentBtn").click(function(){
        if(!invalidate_check()) {
            return false;
        }

        if(${sessionScope.get("id") == null}){
            alert('로그인 후 이용해주세요.');
            location.href='/member/login';
        }

        var comment = {
            articleId : '${article[0].articleId}',
            memberId : '${sessionScope.get("id")}',
            content : $("#commentContent").val()
        };

        $.ajax({
            url : "/article/writeComment.do",
            data : comment,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                console.log("댓글 작성하기 결과: " + resp);
                if(resp == "1"){
                    alert("댓글이 성공적으로 작성되었습니다.");
                    window.location.reload();
                }else{
                    alert("댓글 작성에 실패했습니다. 잠시 후 다시 시도해 주세요.");
                }
            },
            error : function() {
                alert("error")
            }
        });
    });

    <!-- 유효성 검사 -->
    function invalidate_check(){
        // 댓글 내용 공백 확인
        if($("#commentContent").val() == ""){
            alert("내용을 입력해주세요.");
            $("#commentContent").focus();
            return false;
        }
        return true;
    }

    function deleteArticle() {
        if(${sessionScope.get("id") == null}){
            alert('로그인 후 이용해주세요.');
            location.href='/member/login';
        }
        var article = {
            articleId : '${article[0].articleId}'
        };
        $.ajax({
            url : "/article/deleteArticle.do",
            data : article,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                console.log("글 삭제하기 결과: " + resp);
                if(resp == "1"){
                    alert("게시글이 성공적으로 삭제되었습니다.");
                    location.href="/board/main";
                }else{
                    alert("게시글 삭제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
                }
            },
            error : function() {
                alert("error")
            }
        });
    }
</script>
