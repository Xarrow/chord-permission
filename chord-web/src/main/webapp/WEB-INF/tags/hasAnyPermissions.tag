<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ tag import="org.apache.shiro.subject.Subject" %>
<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="角色列表" %>
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="角色列表分隔符" %>
<%

    if (!StringUtils.hasText(delimiter)) {
        delimiter = ",";//默认逗号分隔
    }

    if (!StringUtils.hasText(name)) {
%>
<jsp:doBody/>
<%
        return;
    }

    String[] permissions = name.split(delimiter);
    Subject subject = SecurityUtils.getSubject();
    for (String s : permissions) {
        if (subject.isPermitted(s)) {
%>
<jsp:doBody/>
<%
        }
    }
%>
