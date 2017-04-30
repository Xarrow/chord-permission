<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="auth" uri="/my-tags" %>
<%@taglib prefix="jian" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wb-zj268791
  Date: 2017/3/30
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>����</h1>

<hr>
<shiro:hasRole name="dev">
    ������
</shiro:hasRole>

<hr>
<shiro:hasRole name="admin">
    ����Ա
</shiro:hasRole>
<hr>
<shiro:hasPermission name="1">
    ������view
</shiro:hasPermission>

<hr>
<shiro:authenticated>
    �Ѿ���֤
</shiro:authenticated>
<hr>
<shiro:hasPermission name="dev:2">
    ������check
</shiro:hasPermission>

<hr>
<shiro:notAuthenticated>
    ��û����֤
</shiro:notAuthenticated>

<auth:auth privilege="7">
    <h1>my auth</h1>
</auth:auth>

<hr>
<jian:hasAllPermissions name="1,2,3">
    i have 1,2,3
</jian:hasAllPermissions>

</body>
</html>
