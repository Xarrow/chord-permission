<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2016/8/24
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500 SERVER ERROR</title>
    <style>
        .tip {
            margin-top: 10%;
            text-align: center;
            color: #ffffff;
        }

        .stack {
            color: #ffffff;
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="tip">
    <h2>500 SERVER ERROR!</h2>
</div>
<div class="stack">
    <h3>Message</h3>
    <small>
        ${pageContext.exception.message}
    </small>
    <h3>Cause</h3>
    <small>
        ${pageContext.exception.cause}
    </small>
    <h3>StackTrace:</h3>
    <small>
        <c:forEach items="${pageContext.exception.stackTrace}" var="trace">
            <p>${trace}</p>
        </c:forEach>
    </small>
</div>
</body>
</html>
