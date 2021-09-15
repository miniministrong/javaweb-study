<%@ page import="study.javaweb.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<html>
    <head>
        <meta charset="utf-8">
        <title>图书信息管理</title>
    </head>
    <body>
        <h1 align="center">图书信息管理</h1>
        <div align="center">
            <a href="insert.jsp">新增图书信息</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="index.jsp" <%session.removeAttribute("user");%>>退出登录</a>
            <br>
            <br>
        </div>

        <div align="center">
            <form action="/query" method="post">
                请选择查询方式：
                <select name="querySelect">
                    <option selected="selected" value="book_name">图书名称</option>
                    <option value="book_author">图书作者</option>
                </select>
                <br />
                <input type="search" name="queryValue"/>
                <input type="submit" value="搜索" />
            </form>
        </div>


        <table align="center" border="1">
            <tr>
                <th>书名</th>
                <th>作者</th>
                <th>页数</th>
                <th>价钱</th>
                <th colspan="2">操作</th>
            </tr>
            <%
                List<Book> bookList = (List<Book>) request.getAttribute("books");
                for (int i = 0; i < bookList.size(); i++) {
                    Book book = bookList.get(i);
            %>
            <tr>
                <td><%=book.getBookName()%></td>
                <td><%=book.getBookAuthor()%></td>
                <td><%=book.getBookPage()%>页</td>
                <td><%=book.getPrice()%>元</td>
                <td><a href="/update?id=<%=book.getId()%>">修改</a></td>
                <td><a href="/delete?id=<%=book.getId()%>">删除</a></td>
            </tr>
            <%
                }
            %>
        </table>

    </body>
</html>
