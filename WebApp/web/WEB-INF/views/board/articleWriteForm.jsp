<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <form:form modelAttribute="articleForm" action="/board/article" method="post" enctype="multipart/form-data">
        <div class="page_title_region">
            <div class="title_txt">글 작성하기</div>
            <form:hidden path="memberId" value="${sessionMember.memberId}"/>
            <button type="submit" class="btn button_right" style="margin-top: 10px;">등록</button>
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
            <form:input path="title" cssClass="form-control input_box" placeholder="제목을 입력해주세요."/>
            <form:textarea path="content" cssClass="form-control" cssStyle="padding: 20px; margin-bottom: 20px;"
                           rows="20" placeholder="내용을 입력해주세요."/>
            <input type="file" name="files" multiple/>
<%--            <form:input path="files" type="file" multiple="multiple"/>--%>
        </div>
        <form:errors path="memberId"/>
        <form:errors path="title"/>
        <form:errors path="content"/>
        <form:errors path="path"/>
    </form:form>
</div>

<jsp:include page="../footer.jsp"/>