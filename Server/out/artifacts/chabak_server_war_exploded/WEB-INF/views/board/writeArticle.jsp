<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="page_title_region">
        <div class="title_txt">글 작성하기</div>
        <button type="button" class="btn button_right" style="margin-top: 10px;">등록</button>
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
        <input type="text" class="form-control input_box" id="title" placeholder="제목을 입력해 주세요."/>
        <textarea id="content" class="form-control" rows="20" placeholder="내용을 입력해 주세요." style="padding: 20px; margin-bottom: 20px;"></textarea>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<style>
    .page_content_region {
        padding: 20px;
    }
    .input_box {
        height: 40px;
        margin-bottom: 10px;
    }
</style>