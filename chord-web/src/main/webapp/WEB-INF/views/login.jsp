<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wb-zj268791
  Date: 2017/3/29
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/core.js"></script>--%>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/enc-base64.js"></script>--%>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/hmac-sha1.js"></script>--%>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/hmac-md5.js"></script>--%>

    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/evpkdf.js"></script>--%>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/cipher-core.js"></script>--%>
    <%--<script src="https://cdn.staticfile.org/crypto-js/3.1.9/aes.js"></script>--%>
    <%--<script src="http://apps.bdimg.com/libs/crypto-js/3.1.2/components/base64.min.js"></script>--%>
    <c:import url="head.jsp"/>
    <style>
        h1 {
            text-align: center;
        }

        .login-logo {
            color: #000000;
        }

        html, body {
            background-image: url("/main/public/images/bg.jpg");
            background-size: cover;
        }

        .footer {
            margin-top: 10%;
        }
    </style>
</head>
<body>
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
            <li class="new_right"><p class="sign">New here ?<a href="register.html"> Sign Up</a></p></li>
            <div class="clearfix"></div>
        </ul>
    </form>
</div>
<div class="copy_layout login">
    <c:import url="footer.jsp"/>
</div>
</body>
<script src="/main/public/js/app/alitrip-login.js"></script>
<script>
    ${requestScope.get("result")}
</script>
</html>
