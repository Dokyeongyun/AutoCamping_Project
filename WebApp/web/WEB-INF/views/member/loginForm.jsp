<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${loginResult != null && loginResult != ''}">
    <script>
        alert('${loginResult}');
    </script>
</c:if>

<jsp:include page="../header.jsp"/>
<div class="container">
    <h3>로그인</h3>
    <div class="container">
        <div class="card">
            <div class="card-header">로그인</div>
            <div class="card-body">
                <form:form modelAttribute="loginForm" action="/member/login" method="post">
                    <%-- 아이디 --%>
                    <div class="form-group row">
                        <label for="memberId" class="col-md-4 col-form-label text-md-right">ID</label>
                        <div class="col-md-6">
                            <form:input path="memberId" cssClass="form-control"/>
                        </div>
                    </div>
                    <%-- 비밀번호 --%>
                    <div class="form-group row">
                        <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                        <div class="col-md-6">
                            <form:password path="password" cssClass="form-control"/>
                        </div>
                    </div>
                    <%-- 아이디 기억하기 --%>
                    <div class="form-group row">
                        <div class="col-md-6 offset-md-4">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="remember">아이디 기억하기
                                </label>
                            </div>
                        </div>
                    </div>
                    <%-- 제출하기, 비밀번호 찾기 버튼 --%>
                    <div class="col-md-6 offset-md-4">
                        <button type="submit" id="submitBtn" class="btn btn-primary">로그인</button>
                        <a href="#" class="btn btn-link">비밀번호를 잊으셨나요?</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>