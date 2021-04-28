<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <h3>Login</h3>
    <main class="login-form">
        <div class="container">
            <div class="row justify-content-center" style="margin-bottom: 30px;">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Login</div>
                        <div class="card-body">
                            <%-- 아이디 --%>
                            <div class="form-group row">
                                <label for="id" class="col-md-4 col-form-label text-md-right">ID</label>
                                <div class="col-md-6">
                                    <input type="text" id="id" class="form-control" name="id" required autofocus>
                                </div>
                            </div>
                            <%-- 비밀번호 --%>
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>
                            <%-- 아이디 기억하기 --%>
                            <div class="form-group row">
                                <div class="col-md-6 offset-md-4">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember"> Remember Me
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <%-- 제출하기, 비밀번호 찾기 버튼 --%>
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" id="submitBtn" class="btn btn-primary">Login</button>
                                <a href="#" class="btn btn-link">Forgot Your Password?</a>
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
    $(document).ready(function (){
        $("#submitBtn").click(function(){
            /*
              로그인 버튼 클릭 시, 유효성 검사!
              1) 빈 칸 없는지 Check
             */
            var invalidate = invalidate_check();
            if(invalidate){
                var member = {
                    id : $("#id").val(),
                    password : $("#password").val()
                };
                $.ajax({
                    url : "/member/login.do",
                    data : member,
                    type : "post",
                    dataType : "json",
                    async : true,
                    success : function(resp) {
                        console.log("로그인 결과: " + resp);
                        if(resp === member.id){
                            alert("로그인 성공! 메인 페이지로 이동합니다.");
                            location.href="/";
                        }else{
                            alert("일치하는 회원정보가 없습니다.");
                        }
                    },
                    error : function() {
                        alert("error")
                    }
                });
            }
        });
    });

    <!-- 유효성 검사 -->
    function invalidate_check(){
        //아이디(이메일) 공백 확인
        if($("#id").val() == ""){
            alert("아이디를 입력해주세요.");
            $("#id").focus();
            return false;
        }
        //비밀번호 공백 확인
        if($("#password").val() == ""){
            alert("패스워드를 입력해주세요.");
            $("#password").focus();
            return false;
        }
        return true;
    }
</script>