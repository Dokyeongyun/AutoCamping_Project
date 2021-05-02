<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="page_title_region">
        <div class="title_txt">글 수정하기</div>
        <button type="button" class="btn button_right" style="margin-top: 10px;" id="modifyArticleBtn">수정하기</button>
    </div>
    <div class="page_content_region">
        <select id="board_type" class="form-control input_box" style="width: 50%">
            <option value="">게시판을 선택해 주세요.</option>
            <option value="notice">공지사항</option>
            <option value="board1">게시판1</option>
            <option value="board2">게시판2</option>
            <option value="board3">게시판3</option>
            <option value="board4">게시판4</option>
            <option value="board5">게시판5</option>
        </select>
        <input type="text" class="form-control input_box" id="title" placeholder="제목을 입력해 주세요."
               value="${article.title}"/>
        <textarea id="content" class="form-control" rows="20" placeholder="내용을 입력해 주세요."
                  style="padding: 20px; margin-bottom: 20px;">${article.content}</textarea>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
<%--

<script>
    &lt;%&ndash;  글쓰기 작업 DB Insert 수행  &ndash;%&gt;
    $("#modifyArticleBtn").click(function(){
        if(!invalidate_check()) {
            return false;
        }

        if(${sessionScope.get("id") == null}){
            alert('로그인 후 이용해주세요.');
            location.href='/member/login';
        }

        var article = {
            title : $("#title").val(),
            content : $("#content").val(),
            fileName : "",
            articleId : '${article[0].articleId}'
        };
        $.ajax({
            url : "/article/updateArticle.do",
            data : article,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                console.log("글 수정하기 결과: " + resp);
                if(resp == "1"){
                    alert("게시글이 성공적으로 수정되었습니다.");
                    location.href="/board/main";
                }else{
                    alert("게시글 수정에 실패했습니다. 잠시 후 다시 시도해 주세요.");
                }
            },
            error : function() {
                alert("error")
            }
        });
    });

    <!-- 유효성 검사 -->
    function invalidate_check(){
        // 제목 공백 확인
        if($("#title").val() == ""){
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }
        // 내용 공백 확인
        if($("#content").val() == ""){
            alert("내용을 입력해주세요.");
            $("#content").focus();
            return false;
        }
        return true;
    }
</script>
--%>
