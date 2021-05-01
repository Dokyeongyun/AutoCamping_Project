<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${joinResult != null}">
    <script>
        alert('${joinResult}');
    </script>
</c:if>

<jsp:include page="../header.jsp"/>
<div class="container">
    <h3>회원가입</h3>
    <div class="card" style="margin: 30px 0">
        <div class="card-header">회원가입</div>
        <div class="card-body">
            <form:form modelAttribute="joinForm" action="/member/join" method="post">
                <%-- 아이디 --%>
                <div class="form-group row">
                    <label for="memberId" class="col-md-4 col-form-label text-md-right">ID</label>
                    <div class="col-md-6">
                        <form:input path="memberId" cssClass="form-control"/>
                        <form:errors path="memberId"/>
                    </div>
                    <div class="col-md-2">
                        <button class="btn" type="button" id="idDoubleCheck" onclick="idDoubleCheck()">중복확인
                        </button>
                    </div>
                </div>
                <%-- 닉네임 --%>
                <div class="form-group row">
                    <label for="nickName" class="col-md-4 col-form-label text-md-right">NickName</label>
                    <div class="col-md-6">
                        <form:input path="nickName" cssClass="form-control"/>
                        <form:errors path="nickName"/>
                    </div>
                    <div class="col-md-2">
                        <button class="btn" type="button" id="nickDoubleCheck" onclick="nickDoubleCheck()">
                            중복확인
                        </button>
                    </div>
                </div>
                <%-- 비밀번호 --%>
                <div class="form-group row">
                    <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                    <div class="col-md-6">
                        <form:password path="password" cssClass="form-control"/>
                        <form:errors path="password"/>
                    </div>
                </div>
                <%-- 비밀번호 확인 --%>
                <div class="form-group row">
                    <label for="passwordCheck" class="col-md-4 col-form-label text-md-right">Password Check</label>
                    <div class="col-md-6">
                        <form:password path="passwordCheck" cssClass="form-control"/>
                        <form:errors path="passwordCheck"/>
                    </div>
                </div>
                <%-- 개인정보 이용동의 --%>
                <div class="form-group row">
                    <div class="col-md-6 offset-md-4">
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="check"/> 개인정보 이용에 동의합니다.
                            </label>
                        </div>
                    </div>
                </div>
                <%-- 제출하기, 비밀번호 찾기 버튼 --%>
                <div class="col-md-6 offset-md-4">
                    <button type="submit" id="submitBtn" class="btn btn-primary">회원가입</button>
                    <a href="/member/login" class="btn btn-link">
                        이미 계정이 있으신가요?
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>