<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2016/8/24
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>404 NOT FOUND</title>
    <style>
        .tip {
            margin-top: 10%;
            text-align: center;
            color: #ffffff;
        }
    </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="tip">
    <h2>404 NOT FOUND!</h2>
    <h3>StackTrace:</h3>
    <code>
        <c:forEach items="${pageContext.exception.stackTrace}" var="trace">
            <p>${trace}</p>
        </c:forEach>
    </code>
</div>
</body>
</html>
