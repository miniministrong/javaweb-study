<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("user");
%>
<html>
    <head>
        <title>主页</title>
        <meta charset="utf-8">
    </head>
    <body>
        <h1 align="center">当前用户为：<%=username != null ? username : "游客"%></h1>
        <%
            if (username != null) {
        %>
            <div align="center"><a href="/queryBooks">图书管理</a></div>
            <div align="center"><a href="index.jsp" <%session.removeAttribute("user");%>>退出</a></div>
        <%
        }else {
        %>
            <div align="center"><a href="login.jsp">登录</a></div>
        <%
            }
        %>
    </body>
</html>
