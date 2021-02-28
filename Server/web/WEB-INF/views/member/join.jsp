<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container">
    <h3>Register</h3>
    <main class="register-form">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Register</div>
                        <div class="card-body">
                            <form action="/member/insert.do" method="post">
                                <%-- 아이디 --%>
                                <div class="form-group row">
                                    <label for="id" class="col-md-4 col-form-label text-md-right">ID</label>
                                    <div class="col-md-6">
                                        <input type="text" id="id" class="form-control" name="id" required autofocus>
                                    </div>
                                </div>
                                <%-- 닉네임 --%>
                                <div class="form-group row">
                                    <label for="nickName" class="col-md-4 col-form-label text-md-right">NickName</label>
                                    <div class="col-md-6">
                                        <input type="text" id="nickName" class="form-control" name="nickName" required autofocus>
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
                                    <button type="submit" class="btn btn-primary"> Register </button>
                                    <a href="#" class="btn btn-link">
                                        Already have an account?
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<jsp:include page="../footer.jsp"/>
