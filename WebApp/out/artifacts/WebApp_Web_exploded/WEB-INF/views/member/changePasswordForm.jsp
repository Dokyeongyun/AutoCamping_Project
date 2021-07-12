<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>
<div class="container">
    <div style="margin: 0 auto; width: 70%">
        <h3 class="pageTitle">비밀번호 찾기</h3>
        <div class="joinContent">
            <div class="rowGroup">
                <%-- 변경할 비밀번호 --%>
                <div class="joinRow">
                    <h3 class="joinTitle"><label for="password">변경할 비밀번호</label></h3>
                    <div><input type="password" class="joinFormInputBox" id="password" name="password"/></div>
                    <span class="joinErrorText" id="passwordMsg" style="display:none" aria-live="assertive"></span>
                </div>

                <%-- 비밀번호 확인 --%>
                <div class="joinRow">
                    <h3 class="joinTitle"><label for="passwordCheck">비밀번호 확인</label></h3>
                    <div><input type="password" class="joinFormInputBox" id="passwordCheck" name="passwordCheck"/></div>
                    <span class="joinErrorText" id="passwordCheckMsg" style="display:none" aria-live="assertive"></span>
                </div>
            </div>
            <%-- 확인 버튼 --%>
            <div class="btnArea">
                <button type="button" id="submitBtn" class="btnType btnPrimary" onclick="submit()">확인</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>


<script>
    let pwFlag = false;
    let submitFlag = false;

    $(document).ready(function () {
        $("#password").blur(function () {
            pwFlag = false;
            checkPassword();
        }).keypress(function (event) {
            checkCapslock(event);
        });

        $("#passwordCheck").blur(function () {
            checkPassword2();
        }).keypress(function (event) {
            checkCapslock2(event);
        });
    });

    function checkPassword() {
        if (pwFlag) return true;

        let pw = $("#password").val();
        let oMsg = $("#passwordMsg");
        let oInput = $("#password");

        if (pw === "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }
        if (!isValidPasswd(pw)) {
            showErrorMsg(oMsg, "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            setFocusToInputObject(oInput);
            return false;
        }

        pwFlag = true;
        return true;
    }

    function checkPassword2() {
        let pswd1 = $("#password");
        let pswd2 = $("#passwordCheck");
        let oMsg = $("#passwordCheckMsg");
        let oInput = $("#passwordCheck");

        if (pswd2.val() == "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }
        if (pswd1.val() != pswd2.val()) {
            showErrorMsg(oMsg, "비밀번호가 일치하지 않습니다.");
            setFocusToInputObject(oInput);
            return false;
        } else {
            hideMsg(oMsg);
            return true;
        }

        return true;
    }

    function isValidPasswd(str) {
        let cnt = 0;
        if (str == "") {
            return false;
        }

        /* check whether input value is included space or not */
        let retVal = checkSpace(str);
        if (retVal) {
            return false;
        }
        if (str.length < 8) {
            return false;
        }
        for (let i = 0; i < str.length; ++i) {
            if (str.charAt(0) == str.substring(i, i + 1))
                ++cnt;
        }
        if (cnt == str.length) {
            return false;
        }

        let isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
        if (!isPW.test(str)) {
            return false;
        }

        return true;
    }

    function checkCapslock(e) {
        let myKeyCode = 0;
        let myShiftKey = false;
        if (window.event) { // IE
            myKeyCode = e.keyCode;
            myShiftKey = e.shiftKey;
        } else if (e.which) { // netscape ff opera
            myKeyCode = e.which;
            myShiftKey = isShift;
        }

        let oMsg = $("#passwordMsg");
        if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else {
            hideMsg(oMsg);
        }
    }

    function checkCapslock2(e) {
        let myKeyCode = 0;
        let myShiftKey = false;
        if (window.event) { // IE
            myKeyCode = e.keyCode;
            myShiftKey = e.shiftKey;
        } else if (e.which) { // netscape ff opera
            myKeyCode = e.which;
            myShiftKey = isShift;
        }

        let oMsg = $("#passwordCheckMsg");
        if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else {
            hideMsg(oMsg);
        }
    }

    function checkSpace(str) {
        if (str.search(/\s/) != -1) {
            return true;
        } else {
            return false;
        }
    }

    function setFocusToInputObject(obj) {
        if (submitFlag) {
            submitFlag = false;
            obj.focus();
        }
    }

    function submit(){
        if(!pwFlag) return;

        var email = '${email}';
        var password = $("#password").val();

        var member = {
            email: email,
            password: password
        }

        $.ajax({
            type: "POST",
            url: "/joinAjax/changePassword",
            contentType: "application/json; charset=UTF-8;",
            data: JSON.stringify(member),
            success: function () {
                alert("비밀번호가 변경되었습니다. 다시 로그인해주세요.")
                location.href="/member/loginForm";
            },
            error: function () {
            }
        });
    }
</script>