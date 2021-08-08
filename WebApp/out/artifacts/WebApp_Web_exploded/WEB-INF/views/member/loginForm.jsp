<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${loginResult != null && loginResult != ''}">
    <script>
        alert('${loginResult}');
    </script>
</c:if>

<jsp:include page="../header.jsp"/>
<div class="container">
    <div style="margin: 0 auto; width: 70%">
        <h3 class="pageTitle">로그인</h3>
        <div class="joinContent">
            <form:form modelAttribute="loginForm" action="/member/login" method="post">
                <div class="rowGroup">
                        <%-- 아이디 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="memberId">아이디</label></h3>
                        <div><form:input path="memberId" cssClass="joinFormInputBox"/></div>
                        <span><form:errors cssClass="joinErrorText" id="memberIdMsg" path="memberId"/></span>
                    </div>
                        <%-- 비밀번호 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="password">비밀번호</label></h3>
                        <div><form:password path="password" cssClass="joinFormInputBox"/></div>
                        <span><form:errors cssClass="joinErrorText" id="passwordMsg" path="password"/></span>
                    </div>
                        <%-- 자동로그인 및 아이디 기억하기 --%>
                    <div class="joinRow">
                        <div class="checkboxWrap">
                            <input type="checkbox" id="saveIdYN" name="saveIdYN" value="N" onchange="changeSaveIdCheckbox()"/>
                            <label for="saveIdYN"><span></span>아이디 기억하기</label>
                        </div>
                        <div class="checkboxWrap">
                            <input type="checkbox" id="autoLoginYN" name="autoLoginYN" value="N" onchange="changeAutoLoginCheckbox()"/>
                            <label for="autoLoginYN"><span></span>자동로그인</label>
                        </div>
                    </div>
                </div>
                <%-- 로그인 버튼 및 비밀번호 찾기 --%>
                <div class="btnArea">
                    <button type="submit" id="submitBtn" class="btnType btnPrimary">로그인</button>
                </div>
                <div class="joinRow" style="text-align: right">
                    <a href="${pageContext.request.contextPath}/member/findIDForm" class="bold">
                        아이디
                    </a> /
                    <a href="${pageContext.request.contextPath}/member/findPasswordForm" class="bold">
                        비밀번호를 잊으셨나요?
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<script>
    $(function(){

        // 1. 쿠키값에 따른 아이디 기억하기 체크박스, 아이디 값 설정
        initSaveIdCookie();
    });

    // 쿠키값에 따른 아이디 기억하기 체크박스, 아이디 값 설정
    function initSaveIdCookie() {
        let saveIdCookie = $.cookie("saveIdCookie");

        if(saveIdCookie != null && saveIdCookie !== "") {
            let saveIdYNObj = $("#saveIdYN");
            saveIdYNObj.val("Y");
            saveIdYNObj.attr("checked", "checked");
            $("#memberId").val(saveIdCookie);
            $("#password").focus();
        } else {
            $("#memberId").focus();
        }
    }

    // =============================================
    //      onClick and onChange Event Function
    // =============================================

    // 자동로그인 체크박스 변경 이벤트
    function changeAutoLoginCheckbox() {
        let autoLoginYNObj = $("#autoLoginYN");
        autoLoginYNObj.val(autoLoginYNObj.val() === "N" ? "Y" : "N");
        alert(autoLoginYNObj.val());
    }

    // 아이디 기억하기 체크박스 변경 이벤트
    function changeSaveIdCheckbox() {
        let saveIdYNObj = $("#saveIdYN");
        saveIdYNObj.val(saveIdYNObj.val() === "N" ? "Y" : "N");
        alert(saveIdYNObj.val());
    }
</script>
