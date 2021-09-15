package study.javaweb.practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "index",urlPatterns = "/index")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从HttpSession获取当前用户名:
        String user = (String) request.getSession().getAttribute("user");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter write = response.getWriter();
        write.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            // 未登录，显示登录链接:
            write.write("<p><a href=\"/signIn\">登录</a></p>");
        } else {
            // 已登录，显示登出链接:
            write.write("<p><a href=\"/signOut\">退出登录</a></p>");
        }
        write.flush();
    }
}
