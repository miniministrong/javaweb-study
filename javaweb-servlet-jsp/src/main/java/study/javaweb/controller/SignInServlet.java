package study.javaweb.controller;

import study.javaweb.model.User;
import study.javaweb.service.UserService;
import study.javaweb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取登录界面中的数据
 * @author dhl
 */
@WebServlet(name = "signIn",urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        // 获取前端提交过来的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 创建获取对象信息的实体类
        UserService userService = new UserServiceImpl();
        User user = userService.getUser(username);
        PrintWriter writer = response.getWriter();
        // 对我们的信息进行判断
        if (user != null && user.getPassword().equals(password)) {
            // 将我们的数据存入session中
            request.getSession().setAttribute("user", username);
            // 将数据存储在我们的request中，以便于请求转发的时候方便使用数据
            // request.setAttribute("username", username);
            // 将我们的数据返回到页面中去
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            // request.getRequestDispatcher("/login.jsp").forward(request, response);
            writer.write("<span style='color:red'>您输入的用户名或密码有误，请重新输入</span>");
        }
    }
}
