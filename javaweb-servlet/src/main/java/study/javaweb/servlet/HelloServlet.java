package study.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "zhang";
        // 设置连接类型
        response.setContentType("text/html");
        // 接收返回的信息并转换成为可以打印的方式
        PrintWriter writer = response.getWriter();
        // 打印我们页面代码
        writer.write("<html><head><title>helloServlet</title></head><body><h1>Hello " + name + " </h1></body></html>");
        // 刷新
        writer.flush();
    }
}
