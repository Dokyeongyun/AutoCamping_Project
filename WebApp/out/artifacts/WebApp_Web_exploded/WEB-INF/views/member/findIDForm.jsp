<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<div class="container">
    <div style="margin: 0 auto; width: 70%">
        <h3 class="pageTitle">아이디 찾기</h3>
        <div class="joinContent">
            <form id="findIDForm" method="post" action="${pageContext.request.contextPath}/member/findID">
                <div class="rowGroup">
                    <%-- 이메일 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="email">이메일</label></h3>
                        <div><input type="text" class="joinFormInputBox" id="email" name="email"/></div>
                        <span class="joinErrorText" id="emailMsg" style="display:none" aria-live="assertive"></span>
                    </div>

                    <%-- 인증번호 --%>
                    <div class="joinRow" id="authNumArea" style="display: none">
                        <h3 class="joinTitle"><label for="authNum">인증번호</label></h3>
                        <div><input type="password" class="joinFormInputBox" id="authNum" name="authNum"/></div>
                        <span class="joinErrorText" id="authNumMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                </div>
                <%-- 인증번호 전송 버튼 + 확인 버튼 --%>
                <div class="btnArea">
                    <button type="button" id="sendAuthBtn" class="btnType btnPrimary" onclick="sendAuthNum()">인증번호 전송</button>
                    <button type="button" id="submitBtn" class="btnType btnPrimary" onclick="authSubmit()" style="display: none">확인</button>
                </div>

                <!-- 추가 메뉴 -->
                <div class="joinRow" style="text-align: right">
                    <a href="javascript:void(0);" onclick="sendAuthNum();" id="resendAuthNumBtn" style="font-weight: bold; float: left; display: none">인증번호 재전송
                    </a>
                    <a href="${pageContext.request.contextPath}/member/findPasswordForm" style="font-weight: bold;">
                        비밀번호를 잊으셨나요?
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>


<script>
    let emailFlag = false;
    let submitFlag = false;
    let sendFlag = false;
    let isUserEmail = false;

    let authNum = '';

    $(document).ready(function () {
        $("#email").blur(function () {
            emailFlag = false;
            checkEmail();
        });
    });

    function checkEmail() {
        if (emailFlag) return true;

        let $email = $("#email");
        let oMsg = $("#emailMsg");
        let email = $email.val();
        let oInput = $email;

        if (email === "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        const isEmailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
        if (!isEmailRule.test(email)) {
            showErrorMsg(oMsg, "이메일 형식을 확인해주세요.");
            setFocusToInputObject(oInput);
            return false;
        }

        emailFlag = true;
        hideMsg(oMsg);
        showSuccessMsg(oMsg, "인증번호 전송버튼을 눌러주세요.");
        return true;
    }

    function sendAuthNum() {
        if (!emailFlag) return;

        alert("입력하신 이메일로 인증번호를 전송했습니다.\n메일함을 확인해주세요.")
        $("#authNumArea").show();           // 인증번호 입력란 show
        $("#submitBtn").show();             // 확인버튼 show
        $("#sendAuthBtn").hide();           // 인증번호 전송버튼 hide
        $("#resendAuthNumBtn").show();      // 인증번호 재전송버튼 show3

        let email = $("#email").val();
        $.ajax({
            type: "GET",
            url: "/joinAjax/sendAuthNum?email=" + email,
            success: function (data) {
                if(data === "NOT_USER"){
                    isUserEmail = false;
                }else{
                    authNum = data;
                    sendFlag = true;
                    isUserEmail = true;
                }
            },
            error: function () {
                isUserEmail = false;
                alert("인증번호 전송에 실패했습니다. 잠시 후 다시 시도해주세요.")
            }
        });
    }

    function authSubmit() {
        if (!sendFlag || !isUserEmail) return;
        let oMsg = $("#authNumMsg");
        let inputAuthNum = $("#authNum").val();
        let oInput = $("#authNum");

        if (inputAuthNum === authNum) {
            showSuccessMsg(oMsg, "확인되었습니다. 잠시만 기다려주세요.");
            $("#submitBtn").attr("disabled", true);
            $("#submitBtn").addClass("disabled");

            $("#email").attr("disabled", true);
            $("#email").addClass("disabled");

            $("#authNum").attr("disabled", true);
            $("#authNum").addClass("disabled");

            $("#resendAuthNumBtn").unbind('click');

            getAuthUserID();
        } else {
            showErrorMsg(oMsg, "인증번호를 다시 확인해주세요.");
            setFocusToInputObject(oInput);
        }
    }

    function getAuthUserID(){
        let email = $("#email").val();
        let authNum = $("#authNum").val();
        let mailAuth = {
            mailAuthEmail : email,
            mailAuthNum : authNum
        }
        $.ajax({
            type: "POST",
            url: "/joinAjax/getAuthUserID",
            data : JSON.stringify(mailAuth),
            contentType : "application/json; charset=UTF-8",
            success: function (data) {
                if(data !== ""){
                    alert('입력하신 정보로 가입된 아이디는 ['+data+']입니다.\n로그인 페이지로 이동합니다.');
                    location.href="/member/loginForm";
                }
            }
        });
    }

    function setFocusToInputObject(obj) {
        if (submitFlag) {
            submitFlag = false;
            obj.focus();
        }
    }
</script>