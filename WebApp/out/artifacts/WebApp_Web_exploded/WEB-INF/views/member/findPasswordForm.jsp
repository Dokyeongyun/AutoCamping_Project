<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<div class="container">
    <div style="margin: 0 auto; width: 70%">
        <h3 class="pageTitle">비밀번호 찾기</h3>
        <div class="joinContent">
            <form:form modelAttribute="loginForm" action="/member/login" method="post">
                <div class="rowGroup">
                        <%-- 아이디 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="memberId">아이디</label></h3>
                        <div><form:input path="memberId" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="memberIdMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                </div>
                <%-- 로그인 버튼 및 비밀번호 찾기 --%>
                <div class="btnArea">
                    <button type="submit" id="submitBtn" class="btnType btnPrimary">로그인</button>
                </div>
                <div class="joinRow" style="text-align: right">
                    <a href="${pageContext.request.contextPath}/member/findIDForm" style="font-weight: bold;">
                        아이디
                    </a> /
                    <a href="${pageContext.request.contextPath}/member/findPasswordForm" style="font-weight: bold;">
                        비밀번호를 잊으셨나요?
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>