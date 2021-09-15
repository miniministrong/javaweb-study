<%@ page import="study.javaweb.model.Book" %>
<%--
  author: dhl
  datetime: 2021/7/19 9:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Book book = (Book) request.getAttribute("queryBook");
%>
<html>
    <head>
        <title>查询页面</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1 align="center">查询结果</h1>
        <div align="center">
            <table border="1">
                <tr>
                    <th>图书名称</th>
                    <th>图书作者</th>
                    <th>图书页数</th>
                    <th>图书价格</th>
                </tr>
                <tr>
                    <td><%=book.getBookName()%></td>
                    <td><%=book.getBookAuthor()%></td>
                    <td><%=book.getBookPage()%></td>
                    <td><%=book.getPrice()%></td>
                </tr>
            </table>
            <br/>
            <br/>
        </div>
        <div align="center">
            <a href="/queryBooks">返回图书管理页面</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="index.jsp" <%session.removeAttribute("user");%>>退出登录</a>
        </div>
    </body>
</html>
