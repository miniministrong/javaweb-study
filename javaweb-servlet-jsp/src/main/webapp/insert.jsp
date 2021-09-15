<%--
  author: dhl
  datetime: 2021/7/16 12:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>新增图书</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <h1 align="center">新增图书</h1>
    <form action="/insert" method="post">
        <div align="center">
            图书名称：<input type="text" name="book_name">
            <br/>
            图书作者：<input type="text" name="book_author">
            <br/>
            图书页数：<input type="text" name="book_page">
            <br/>
            图书价格：<input type="text" name="price">
            <br/>
            <br/>
            <input type="submit" value="提交新增图书">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" value="重置">
        </div>
    </form>
    <br/>
    <br/>

    <div align="center">
        <a href="/queryBooks">返回图书管理页面</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="index.jsp" <%session.removeAttribute("user");%>>退出登录</a>
    </div>
    </body>
</html>
