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
    <div style="margin: 0 auto; width: 70%">
        <h3 class="pageTitle">회원가입</h3>
        <div class="joinContent">
            <form:form modelAttribute="joinForm" action="/member/join" method="post">
                <div class="rowGroup">
                        <%-- 아이디 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="memberId">아이디</label></h3>
                        <div><form:input path="memberId" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="memberIdMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                        <%-- 닉네임 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="nickName">닉네임</label></h3>
                        <div><form:input path="nickName" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="nickNameMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                        <%-- 이메일 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="email">이메일</label></h3>
                        <div><form:input path="email" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="emailMsg" style="display:none" aria-live="assertive"></span>
                    </div
                        <%-- 비밀번호 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="password">비밀번호</label></h3>
                        <div><form:password path="password" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="passwordMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                        <%-- 비밀번호 확인 --%>
                    <div class="joinRow">
                        <h3 class="joinTitle"><label for="passwordCheck">비밀번호 확인</label></h3>
                        <div><form:password path="passwordCheck" cssClass="joinFormInputBox"/></div>
                        <span class="joinErrorText" id="passwordCheckMsg" style="display:none" aria-live="assertive"></span>
                    </div>
                        <%-- 개인정보 이용동의 --%>
                    <div class="joinRow">
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="check"/> 개인정보 이용에 동의합니다.
                            </label>
                        </div>
                    </div>
                </div>
                <%-- 제출하기, 아이디/비밀번호 찾기 버튼 --%>
                <div class="btnArea">
                    <button type="button" id="btnJoin" class="btnType btnPrimary">회원가입</button>
                </div>
                <div class="joinRow" style="text-align: right">
                    <a href="/member/loginForm" style="font-weight: bold;">
                        이미 계정이 있으신가요?
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>

<script type="text/JavaScript">
    //region define, setter
    var idFlag = false;
    var nicknameFlag = false;
    var emailFlag = false;
    var pwFlag = false;
    var submitFlag = false;

    $(document).ready(function () {

        //region unreal id
        $("#memberId").blur(function () {
            idFlag = false;
            checkId("first");
        });

        $("#nickName").blur(function () {
            nicknameFlag = false;
            checkNickname("first");
        });

        $("#email").blur(function () {
            emailFlag = false;
            checkEmail("first");
        });

        $("#password").blur(function () {
            pwFlag = false;
            checkPassword();
        }).keyup(function (event) {
            checkShiftUp(event);
        }).keypress(function (event) {
            checkCapslock(event);
        }).keydown(function (event) {
            checkShiftDown(event);
        });

        $("#passwordCheck").blur(function () {
            checkPassword2();
        }).keyup(function (event) {
            checkShiftUp(event);
        }).keypress(function (event) {
            checkCapslock2(event);
        }).keydown(function (event) {
            checkShiftDown(event);
        });
        //endregion

        $("#btnJoin").click(function () {
            submitClose();
            if (idFlag && nicknameFlag && emailFlag && pwFlag) {
                mainSubmit();
            } else {
                setTimeout(function () {
                    mainSubmit();
                }, 700);
            }
        });

    });
    //endregion

    //region mainSubmit
    function mainSubmit() {
        if (!checkUnrealInput()) {
            submitOpen();
            return false;
        }
        if (idFlag && nicknameFlag && emailFlag && pwFlag) {
            $("#joinForm").submit();
        } else {
            submitOpen();
            return false;
        }
    }

    function checkUnrealInput() {
        if (checkId('join')
            & checkPassword()
            & checkPassword2()
            & checkNickname()
        ) {
            return true;
        } else {
            return false;
        }
    }

    //endregion

    //region unreal 가입
    function checkId(event) {
        if (idFlag) return true;

        var id = $("#memberId").val();
        var oMsg = $("#memberIdMsg");
        var oInput = $("#memberId");

        if (id == "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
        if (!isID.test(id)) {
            showErrorMsg(oMsg, "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        idFlag = false;
        $.ajax({
            type: "GET",
            url: "/joinAjax/checkId?id=" + id,
            success: function (data) {
                if (data == "Y") {
                    if (event == "first") {
                        showSuccessMsg(oMsg, "사용가능한 아이디입니다.");
                    } else {
                        hideMsg(oMsg);
                    }
                    idFlag = true;
                } else {
                    showErrorMsg(oMsg, "이미 사용중이거나 탈퇴한 아이디입니다.");
                    setFocusToInputObject(oInput);
                }
            }
        });
        return true;
    }

    function checkNickname(event) {
        if (nicknameFlag) return true;

        var oMsg = $("#nickNameMsg");
        var nickname = $("#nickName").val();
        var oInput = $("#nickName");
        if (nickname == "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        var isNickname = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$/;
        if (!isNickname.test(nickname)) {
            showErrorMsg(oMsg, "2~10자의 한글, 영문, 숫자만 사용 가능합니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        nicknameFlag = false;
        $.ajax({
            type: "GET",
            url: "/joinAjax/checkNickname?nickname=" + nickname,
            success: function (data) {
                if (data == "Y") {
                    if (event == "first") {
                        showSuccessMsg(oMsg, "사용가능한 닉네임입니다.");
                    } else {
                        hideMsg(oMsg);
                    }
                    nicknameFlag = true;
                } else {
                    showErrorMsg(oMsg, "이미 사용중인 닉네임입니다.");
                    setFocusToInputObject(oInput);
                }
            }
        });
        return true;
    }

    function checkEmail(event) {
        if (emailFlag) return true;

        var $email = $("#email");

        var oMsg = $("#emailMsg");
        var email = $email.val();
        var oInput = $email;
        if (email === "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }

        var isEmailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
        if (!isEmailRule.test(email)) {
            showErrorMsg(oMsg, "이메일 형식을 확인해주세요.");
            setFocusToInputObject(oInput);
            return false;
        }

        emailFlag = false;
        $.ajax({
            type: "GET",
            url: "/joinAjax/checkEmail?email=" + email,
            success: function (data) {
                if (data === "Y") {
                    if (event === "first") {
                        showSuccessMsg(oMsg, "사용가능한 이메일입니다.");
                    } else {
                        hideMsg(oMsg);
                    }
                    emailFlag = true;
                } else {
                    showErrorMsg(oMsg, "해당 이메일로 가입된 정보가 이미 존재합니다.");
                    setFocusToInputObject(oInput);
                }
            }
        });
        return true;
    }

    function checkPassword() {
        if (pwFlag) return true;

        var pw = $("#password").val();
        var oMsg = $("#passwordMsg");
        var oInput = $("#password");

        if (pw == "") {
            showErrorMsg(oMsg, "필수 정보입니다.");
            setFocusToInputObject(oInput);
            return false;
        }
        if (isValidPasswd(pw) != true) {
            showErrorMsg(oMsg, "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            setFocusToInputObject(oInput);
            return false;
        }

        pwFlag = true;
        return true;
    }

    function checkPassword2() {
        var pswd1 = $("#password");
        var pswd2 = $("#passwordCheck");
        var oMsg = $("#passwordCheckMsg");
        var oInput = $("#passwordCheck");

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

    //endregion

    //region 공통 함수
    function getLenChar(texts) {
        texts = texts + '';
        return String.fromCharCode(texts.length);
    }

    function checkSpace(str) {
        if (str.search(/\s/) != -1) {
            return true;
        } else {
            return false;
        }
    }

    function isValidPasswd(str) {
        var cnt = 0;
        if (str == "") {
            return false;
        }

        /* check whether input value is included space or not */
        var retVal = checkSpace(str);
        if (retVal) {
            return false;
        }
        if (str.length < 8) {
            return false;
        }
        for (var i = 0; i < str.length; ++i) {
            if (str.charAt(0) == str.substring(i, i + 1))
                ++cnt;
        }
        if (cnt == str.length) {
            return false;
        }

        var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
        if (!isPW.test(str)) {
            return false;
        }

        return true;
    }

    var isShift = false;

    function checkShiftUp(e) {
        if (e.which && e.which == 16) {
            isShift = false;
        }
    }

    function checkShiftDown(e) {
        if (e.which && e.which == 16) {
            isShift = true;
        }
    }

    function checkCapslock(e) {
        var myKeyCode = 0;
        var myShiftKey = false;
        if (window.event) { // IE
            myKeyCode = e.keyCode;
            myShiftKey = e.shiftKey;
        } else if (e.which) { // netscape ff opera
            myKeyCode = e.which;
            myShiftKey = isShift;
        }

        var oMsg = $("#passwordMsg");
        if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else {
            hideMsg(oMsg);
        }
    }

    function checkCapslock2(e) {
        var myKeyCode = 0;
        var myShiftKey = false;
        if (window.event) { // IE
            myKeyCode = e.keyCode;
            myShiftKey = e.shiftKey;
        } else if (e.which) { // netscape ff opera
            myKeyCode = e.which;
            myShiftKey = isShift;
        }

        var oMsg = $("#passwordCheckMsg");
        if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
            showErrorMsg(oMsg, "Caps Lock이 켜져 있습니다.");
        } else {
            hideMsg(oMsg);
        }
    }

    function submitClose() {
        submitFlag = true;
        $("#btnJoin").attr("disabled", true);
    }

    function submitOpen() {
        $("#btnJoin").attr("disabled", false);
    }

    function setFocusToInputObject(obj) {
        if (submitFlag) {
            submitFlag = false;
            obj.focus();
        }
    }
    //endregion
</script>