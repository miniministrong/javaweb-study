package study.javaweb.httpsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "shopping", urlPatterns = "/shop")
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 定义商品名称
        String goodsName;
        // 获取商品名称
        goodsName = request.getParameter("goodsName");
        // 向Tomcat所要私人储物柜HttpSession
        HttpSession session = request.getSession();
        // 将用户选购的商品添加到session中
        // 获取我们当前商品的商品数量
        Integer goodsNum = (Integer) session.getAttribute(goodsName);
        // 并进行判断
        // 如果我们在session中没有添加商品，那么我们就将商品加入session
        if (goodsNum == null) {
            session.setAttribute(goodsName, 1);
        } else {
            // 否则我们对session中商品的数量做加一处理
            session.setAttribute(goodsName, goodsNum + 1);
        }
    }
}
