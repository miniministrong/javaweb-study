package study.javaweb.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "twoServlet", urlPatterns = "/two")
public class TwoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象向全局作用域对象所要ServletContext对象
        ServletContext application = request.getServletContext();
        // 从全局作用域对象中取出数据
        Integer money = (Integer) application.getAttribute("key1");
    }
}
