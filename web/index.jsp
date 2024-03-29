<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<jsp:useBean id="userBean" class="bean.UserBean" scope="session"/>
<%
    // 验证是否已登录过
    if (userBean.getAccount() != null)
        request.getRequestDispatcher("/home_ground.jsp").forward(request,response);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="src/libs/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/css/jihu.css">
    <link rel="apple-touch-icon" href="src/img/touch-icon.png" sizes="152x152">
    <link rel="shortcut icon" href="src/img/touch-icon.png" type="img/x-icon">
    <title>基乎 - Bigger than bigger</title>
</head>
<body>
<!--
    作者：吴迈星、杜健聪、蒋斌
    时间：2019/6/4
    描述：登陆注册界面
-->
<!-- 公共部分 -->
<div class="container">
    <div class="logo">
        <img src="src/img/logo.little.png" alt="">
        <h2 class="hidden-xs">寻找好基友、好对象的绝佳去处</h2>
    </div>
    <div class="login-toggle">
        <div class="row">
            <div class="center-block">
                <a href="#" class="active" id="login-toggle-signIn">登陆</a>
                <a href="#" class id="login-toggle-signUp">注册</a>
            </div>
            <span id="login-toggle-bar"></span>
        </div>
    </div>

    <!-- 注册 -->
    <div id="signUp-page" style="display: none">
        <form class="form-horizontal" action="user-login" method="post" id="signUp-form" role="form">
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="text" name="account" id="register-account" class="form-control input-lg" required
                           placeholder="账号">
                    <label class="error-msg" id="error-msg-account"></label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="text" name="mobile" id="mobile" class="form-control input-lg" required
                           placeholder="手机号" maxlength="11" pattern="[0-9]{10}">
                    <label class="error-msg" id="error-msg-phonenum"></label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="text" name="email" id="email" class="form-control input-lg" required placeholder="邮箱">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="text" name="nickname" id="nickname" class="form-control input-lg" required
                           placeholder="昵称">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="password" name="password" id="register-password" class="form-control input-lg" required
                           placeholder="密码">
                    <label class="error-msg" id="error-msg-password"></label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="password" name="re-password" id="re-password" class="form-control input-lg" required
                           placeholder="重复密码">
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                <!-- <a class="btu-submit" type="button" id="sign_submit">注册基乎</a> -->
                <button class="btu-submit" type="button" id="signUp">注册基乎</button>
            </div>
        </div>
        <div class="row">
            <p>点击注册按钮，即代表你同意<a href="#" id="deal">《基乎协议》</a></p>
        </div>
    </div>

    <!-- 登陆 -->
    <div id="signIn-page">
        <form class="form-horizontal" action="user-login" method="post" id="signIn-form" role="form">
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="text" name="account" id="login-account" class="form-control input-lg" required
                           placeholder="账号">
                    <label class="error-msg" id="error-msg-phnum"></label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                    <input type="password" name="password" id="login-password" class="form-control input-lg" required
                           placeholder="密码">
                    <label class="error-msg error-msg-password"></label>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-10 col-sm-8 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-2 col-md-offset-4 col-lg-offset-5">
                <!-- <a href="#" type="submit" class="btu-submit">登陆</a> -->
                <button class="btu-submit" type="button" id="signIn">登陆</button>
            </div>
        </div>
    </div>
    <div class="footer-simple visible-xs">
        <a href="#">联系我们</a>
    </div>
</div>
<div class="footer hidden-xs">
    <span>© 2019 基乎</span>
    <span class="dot">·</span>
    <br>
    <div class="footer-bottom">
        <a target="_blank" href=#>侵权举报</a>
        <span class="dot">·</span>
        <a target="_blank" href="#">网上有害信息举报专区</a>
        <span class="dot">·</span>
        <a target="_blank" href="#">儿童色情信息举报专区</a>
    </div>
    <span class="dot">·</span>
</div>
<canvas id="canvas"></canvas>
<script src="src/libs/jq-3.2.1/jquery-3.2.1.min.js"></script>
<script src="src/libs/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="src/js/login-toggle.js"></script>
<script src='src/js/canvas.js'></script>
<script type="text/javascript">
    $(function () {
        // 注册按钮
        $("#signUp").click(function () {
            if ($("#register-account").val() == "" || ($("#mobile").val() == "" && $("#email").val() == "") || $("#nickname").val() == "" || $("#register-password").val() == "") {
                alert("账号、邮箱、手机、昵称、密码不能为空!");
            } else {
                if ($("#register-password").val() != $("#re-password").val()) {
                    alert("两次密码不相同!");
                    $("#register-password").val("");
                    $("#re-password").val("");
                    $("#register-password").focus();
                } else {
                    //ajax提交后端
                    var account = $("#register-account").val();
                    var mobile = $("#mobile").val();
                    var email = $("#email").val();
                    var nickname = $("#nickname").val();
                    var password = $("#register-password").val();
                    $.ajax({
                        type: 'POST',
                        url: "/register",
                        data: {
                            "account": account,
                            "nickname": nickname,
                            "password": password,
                            "email": email,
                            "mobile": mobile
                        },
                        dataType:
                            "json",
                        error: function () {
                            alert("提交失败！请重试");
                        },
                        success: function (result) {
                            if (!result.isOK) {
                                alert(result.msg);
                                return;
                            }
                            alert("注册成功！");
                            $("#signUp-form").submit();
                        }
                    });
                }
            }
        });
        // 登陆按钮
        $("#signIn").click(function () {
            if ($("#login-account").val() == "" || $("#login-password").val() == "") {
                alert("账号或密码不能为空!");
            } else {
                //ajax提交后端
                var account = $("#login-account").val();
                var password = $("#login-password").val();
                $.ajax({
                    type: 'POST',
                    url: "/login",
                    data: {"account": account, "password": password},
                    dataType: "json",
                    error: function () {
                        alert("登陆失败！请重试");
                    },
                    success: function (result) {
                        if (!result.isOK) {
                            alert(result.msg);
                            return;
                        }
                        alert("欢迎回来");
                        $("#signIn-form").submit();
                    }
                });
            }
        });
        // 协议
        $("#deal").click(function () {
            alert("待完善...敬请期待");
        })
    });
</script>
</body>
</html>