<%@ page import="study.javaweb.model.Book" %>
<%--
  author: dhl
  datetime: 2021/7/16 16:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Book b = (Book) request.getAttribute("updateBook");
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>修改图书信息</title>
    </head>
    <body>
    <h1 align="center">修改图书信息</h1>
    <div class="app" align="center">
        <div id="oldData">
            <h3>原有数据</h3>
            <table border="1">
                <tr>
                    <th>图书编号</th>
                    <th>图书名称</th>
                    <th>图书作者</th>
                    <th>图书页数</th>
                    <th>图书价格</th>
                </tr>
                <tr>
                    <td><%=b.getId()%></td>
                    <td><%=b.getBookName()%></td>
                    <td><%=b.getBookAuthor()%></td>
                    <td><%=b.getBookPage()%></td>
                    <td><%=b.getPrice()%></td>
                </tr>
            </table>
        </div>
        <div id="newData">
            <h3>请修改</h3>
            <form action="/update" method="post">
                图书编号：<input type="text" name="id" value="<%=b.getId()%>" readonly/>
                <br/>
                图书名称：<input type="text" name="book_name" value="<%=b.getBookName()%>"/>
                <br/>
                图书作者：<input type="text" name="book_author" value="<%=b.getBookAuthor()%>"/>
                <br />
                图书页数：<input type="text" name="book_page" value="<%=b.getBookPage()%>"/>
                <br />
                图书价格：<input type="text" name="price" value="<%=b.getPrice()%>"/>
                <br />
                <br />
                <input type="submit" value="提交" />
            </form>
        </div>
        <div style="padding-top: 50px;">
            <a href="/queryBooks">返回图书管理页面</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="index.jsp" <%session.removeAttribute("user");%>>退出登录</a>
        </div>
    </div>
    </body>
</html>
