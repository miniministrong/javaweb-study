package study.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 重定向的Servlet
 */
@WebServlet(name = "redirect", urlPatterns = "/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>重定向</h1>");
        // 重定向
        // 这是普通的重定向，临时的，状态码：302
        // response.sendRedirect("/hello");
        // 如果要设置永久的重定向需要将状态码设置为301，然后将其重定向
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "/hello");
        writer.flush();
    }
}
