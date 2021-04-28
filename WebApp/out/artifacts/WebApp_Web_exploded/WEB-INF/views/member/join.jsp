<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <h3>Register</h3>
    <main class="register-form">
        <div class="container">
            <div class="row justify-content-center" style="margin-bottom: 30px;">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Register</div>
                        <div class="card-body">
                            <%-- 아이디 --%>
                            <div class="form-group row">
                                <label for="id" class="col-md-4 col-form-label text-md-right">ID</label>
                                <div class="col-md-6">
                                    <input type="text" id="id" class="form-control" name="id" required autofocus>
                                </div>
                                <div class="col-md-2">
                                    <button class="btn" type="button" id="idDoubleCheck" onclick="idDoubleCheck()">중복확인</button>
                                </div>
                            </div>
                            <%-- 닉네임 --%>
                            <div class="form-group row">
                                <label for="nickName" class="col-md-4 col-form-label text-md-right">NickName</label>
                                <div class="col-md-6">
                                    <input type="text" id="nickName" class="form-control" name="nickName" required autofocus>
                                </div>
                                <div class="col-md-2">
                                    <button class="btn" type="button" id="nickDoubleCheck" onclick="nickDoubleCheck()">중복확인</button>
                                </div>
                            </div>
                            <%-- 비밀번호 --%>
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>
                            <%-- 비밀번호 확인 --%>
                            <div class="form-group row">
                                <label for="passwordChk" class="col-md-4 col-form-label text-md-right">Password Check</label>
                                <div class="col-md-6">
                                    <input type="password" id="passwordChk" class="form-control" name="passwordChk" required>
                                </div>
                            </div>
                            <%-- 개인정보 이용동의 --%>
                            <div class="form-group row">
                                <div class="col-md-6 offset-md-4">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember"> I Agree
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <%-- 제출하기, 비밀번호 찾기 버튼 --%>
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" id="submitBtn" class="btn btn-primary"> Register </button>
                                <a href="/member/login" class="btn btn-link">
                                    Already have an account?
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<jsp:include page="../footer.jsp"/>


<script>

    var idCheck = "";
    var nickCheck = "";

    $(document).ready(function (){
        $("#submitBtn").click(function(){
            /*
              회원가입 버튼 클릭 시, 유효성 검사!
              1) 빈 칸 없는지 Check
              2) 정규표현식에 따른 유효성 검사
              3) 아이디, 닉네임 중복확인
             */
            if(invalidate_check_id() && invalidate_check_nickName() && invalidate_check_password() && doubleCheck()){
                var member = {
                    id : $("#id").val(),
                    nickName : $("#nickName").val(),
                    password : $("#password").val()
                };
                $.ajax({
                    url : "/member/insert.do",
                    data : member,
                    type : "post",
                    dataType : "json",
                    async : true,
                    success : function(resp) {
                        console.log("회원가입 결과: " + resp);
                        if(resp === "success"){
                            alert("회원가입에 성공하셨습니다. 로그인 후 이용해주세요.");
                            location.href="/member/login";
                        }else{
                            alert("회원가입에 실패했습니다. 잠시 후 다시 시도해주세요.");
                        }
                    },
                    error : function() {
                        alert("error")
                    }
                });
            }
        });
    });

    <!-- 아이디(이메일) 유효성 검사 -->
    function invalidate_check_id(){
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    //아이디(이메일) 공백 확인
    if($("#id").val() == ""){
        alert("아이디를 입력해주세요.");
        $("#id").focus();
        return false;
    }
    //아이디(이메일) 유효성검사
    if(!getMail.test($("#id").val())){
        alert("아이디를 형식에 맞게 입력해주세요");
        $("#id").val("");
        $("#id").focus();
        return false;
    }
    return true;
}

    <!-- 닉네임 유효성 검사 -->
    function invalidate_check_nickName(){
        var getName= RegExp(/^[가-힣]+$/);

        //닉네임 공백 확인
        if($("#nickName").val() == ""){
            alert("닉네임을 입력해주세요");
            $("#nickName").focus();
            return false;
        }
        //닉네임 유효성검사
        if(!getName.test($("#nickName").val())){
            alert("닉네임을 형식에 맞게 입력해주세요");
            $("#nickName").val("");
            $("#nickName").focus();
            return false;
        }
        return true;
    }

    <!-- 비밀번호 유효성 검사 -->
    function invalidate_check_password(){
        var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);

        //비밀번호 공백 확인
        if($("#password").val() == ""){
            alert("패스워드를 입력해주세요.");
            $("#password").focus();
            return false;
        }
        //비밀번호 유효성검사
        if(!getCheck.test($("#password").val())){
            alert("비밀번호를 형식에 맞게 입력해주세요");
            $("#password").val("");
            $("#password").focus();
            return false;
        }
        //비밀번호 확인란 공백 확인
        if($("#passwordChk").val() == ""){
            alert("패스워드 확인란을 입력해주세요");
            $("#passwordChk").focus();
            return false;
        }
        //비밀번호 일치 확인
        if($("#password").val() != $("#passwordChk").val()){
            alert("비밀번호가 일치하지 않습니다.");
            $("#password").val("");
            $("#passwordChk").val("");
            $("#password").focus();
            return false;
        }
        return true;
    }

    <!-- 아이디 중복확인 -->
    function idDoubleCheck() {
        if(!invalidate_check_id()) return false;

        var member = {
            memberId : $("#id").val(),
        };

        $.ajax({
            url : "/member/idDoubleCheck.do",
            data : member,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                console.log("아이디 중복확인 결과: " + resp);
                if(resp === "1"){
                    alert('사용가능한 이메일입니다.');
                    idCheck = $("#id").val();
                    console.log('idCheck'+idCheck);
                }else{
                    alert('중복된 이메일입니다.');
                }
            },
            error : function() {
                alert("error")
            }
        });
    }

    <!-- 닉네임 중복확인 -->
    function nickDoubleCheck() {
        if(!invalidate_check_nickName()) return false;

        var nickName = {
            nickName : $("#nickName").val(),
        };

        $.ajax({
            url : "/member/nickDoubleCheck.do",
            data : nickName,
            type : "post",
            dataType : "json",
            async : true,
            success : function(resp) {
                console.log("닉네임 중복확인 결과: " + resp);
                if(resp === "1"){
                    alert('사용가능한 닉네임입니다.');
                    nickCheck = $("#nickName").val();
                }else{
                    alert('중복된 닉네임입니다.');
                }
            },
            error : function() {
                alert("error")
            }
        });
    }

    <!-- 중복확인된 아이디, 닉네임이 현재 아이디, 닉네임과 일치하는지 확인 -->
    function doubleCheck(){
        if(idCheck !== $("#id").val()) {
            alert('이메일 중복확인을 해야합니다.')
            return false;
        }
        if(nickCheck !== $("#nickName").val()) {
            alert('닉네임 중복확인을 해야합니다.')
            return false;
        }
        return true;
    }
</script>