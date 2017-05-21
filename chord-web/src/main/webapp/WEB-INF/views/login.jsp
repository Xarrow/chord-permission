<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wb-zj268791
  Date: 2017/3/29
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <c:import url="head.jsp"/>
    <style>
        h1 {
            text-align: center;
        }

        .login-logo {
            color: #000000;
        }

        html, body {
            background-image: url("http://bubkoo.com/images/bg.jpg");
            background-size: cover;
        }

        .footer {
            margin-top: 10%;
        }
    </style>
</head>
<body>
<%--<div class="container" style="">--%>
<%--<div class="col s12"><h3>登录</h3></div>--%>

<%--<form class="col s8" method="post" action="/doLogin">--%>
<%--<div class="row">--%>
<%--<div class="input-field col s4 ">--%>
<%--<input id="userKey" type="text" name="userKey" class="validate">--%>
<%--<label for="userKey">用户名</label>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<div class="input-field col s4">--%>
<%--<input id="password" type="password" name="password" class="validate">--%>
<%--<label for="password">密码</label>--%>
<%--</div>--%>
<%--<div class="input-field col s4">--%>
<%--<input type="hidden" id="token" name="token" value="${token_test}">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<button class="col s4 btn waves-effect light-blue z-depth-5" type="submit" name="action">Submit--%>
<%--<i class="material-icons right">send</i>--%>
<%--</button>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<div class="login-logo">
    <h1>登录</h1>
</div>
<div class="app-cam">
    <form method="post" action="/doLogin">
        <input type="text" id="userKey" name="userKey" class="text" placeholder="用户名">
        <input type="password" id="password" name="password" placeholder="密码">

        <input type="hidden" id="token" name="token" value="${token_test}">
        <div class="submit">
            <input type="submit" value="提交">
        </div>
        <ul class="new">
            <li class="new_right"><p class="sign">
                <a href="https://api.weibo.com/oauth2/authorize?client_id=2625619306&redirect_uri=http://127.0.0.1:8080/oauth/weibo/invoke&response_type=code">
                    使用微博账号登陆
                </a>
            </p></li>
            <div class="clearfix"></div>
        </ul>
    </form>
</div>
<div class="copy_layout login">
    <c:import url="footer.jsp"/>
</div>
</body>
<script src="/main/public/js/app/alitrip-login.js"></script>
<%--<script>--%>
<%--${requestScope.get("result")}--%>
<%--</script>--%>
</html>
