<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <form action="/signIn" method="post">
            <h1 align="center">登录</h1>
            <table align="center">
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="提交"/></td>
                    <td><input type="reset" value="重置"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
