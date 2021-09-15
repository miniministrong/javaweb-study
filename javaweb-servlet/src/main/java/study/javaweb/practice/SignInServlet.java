package study.javaweb.practice;

import study.jdbc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signIn", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String value = request.getParameter("value");
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>请输入账号密码以便登录</h1>");
        writer.write("<form action='/signIn' method='post'>");
        writer.write("<p>用户名: <input name='username'></p>");
        writer.write("<p>密码: <input name='password' type='password'></p>");
        writer.write("<p><button type='submit'>登录</button> <a href='/'>取消</a></p>");
        if (value != null && "signInFail".equals(value)) {
            writer.write("<span style='color:red'>用户名或密码错误</span>");
        }
        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String expectedPassword = userDao.select(username);
        if (expectedPassword != null && expectedPassword.equals(password)) {
            // 登录成功:
            request.getSession().setAttribute("user", username);
            response.sendRedirect("/index");
        } else {
            response.sendRedirect("/signIn?value=signInFail");
        }
    }
}
