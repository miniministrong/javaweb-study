package study.javaweb.cookie;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "handleCard", urlPatterns = "/handleCard")
public class HandleCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username, money;
        // 从请求头中获取信息
        username = request.getParameter("username");
        money = request.getParameter("money");
        // 创建Cookie对象
        Cookie usernameCookie = new Cookie("username", username);
        Cookie moneyCookie = new Cookie("money", money);
        // 设置Cookie存活时间
        moneyCookie.setMaxAge(60); // 1分钟存活时间
        // 将Cookie写入到响应头中交给浏览器
        response.addCookie(usernameCookie);
        response.addCookie(moneyCookie);
        // 通知浏览器将点餐页面的内容写到响应体中交给浏览器
        request.getRequestDispatcher("/order_food.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
