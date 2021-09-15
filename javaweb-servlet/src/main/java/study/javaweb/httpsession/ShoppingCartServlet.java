package study.javaweb.httpsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "shoppingCart", urlPatterns = "/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // 向Tomcat所要我们的session对象
        HttpSession session = request.getSession();
        // 将session中所有的key读取出来，存放在一个枚举对象中
        Enumeration goodsNames = session.getAttributeNames();
        writer.write("<table border='2' align='center'>");
        writer.write("<tr>");
        writer.write("<th>商品名称</th>");
        writer.write("<th>商品数量</th>");
        writer.write("</tr>");
        // 遍历我们的集合
        while (goodsNames.hasMoreElements()) {
            String goodsName = (String)goodsNames.nextElement();
            int goodsNum = (int) session.getAttribute(goodsName);
            writer.write("<tr>");
            writer.write("<td>" + goodsName + "</td>");
            writer.write("<td>" + goodsNum + "</td>");
            writer.write("</tr>");
        }
        writer.write("</table>");
    }
}
