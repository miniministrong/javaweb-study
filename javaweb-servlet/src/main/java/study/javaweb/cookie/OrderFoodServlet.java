package study.javaweb.cookie;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "orderFood", urlPatterns = "/orderFood")
public class OrderFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 饺子的价钱
        int priceDumplings = 30;
        // 面条价钱
        int priceNoodles = 20;
        // 盖饭价钱
        int overRicePrices = 15;
        // 卡中的余额
        int balanceOf = 0;
        // 消费的钱
        int consumptionAmount = 0;
        // 消费之后剩余的钱
        int remainingAmount = 0;
        // 定义一个新的Cookie，用来存放我们进行点餐之后的余额
        Cookie newCard = null;
        // 选择的食物，以及在这里消费人的姓名
        String food, username = null;
        food = request.getParameter("food");
        // 声明一个输出流
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // 定义Cookie数组用来获取我们之前在办卡页面所返回的会员信息
        Cookie[] cookies = null;
        // 获取cookie中的信息
        cookies = request.getCookies();
        // 循环给我们的参数赋值并加以判断
        for (Cookie card : cookies) {
            String key = card.getName();
            String value = card.getValue();
            if ("username".equals(key)) {
                username = value;
            }else if ("money".equals(key)) {
                balanceOf = Integer.valueOf(value);
                if ("饺子".equals(food)) {
                    if (balanceOf < priceDumplings) {
                        writer.write("对不起，" + username + "账户余额不足，请充值后再进行消费");
                    } else {
                        remainingAmount = balanceOf - priceDumplings;
                        newCard = new Cookie("money", (remainingAmount) + "");
                        consumptionAmount = priceDumplings;
                    }
                } else if ("面条".equals(food)) {
                    if (balanceOf < priceNoodles) {
                        writer.write("对不起，" + username + "账户余额不足，请充值后再进行消费");
                    } else {
                        remainingAmount = balanceOf - priceNoodles;
                        newCard = new Cookie("money", (remainingAmount) + "");
                        consumptionAmount = priceNoodles;
                    }
                } else if ("盖饭".equals(food)) {
                    if (balanceOf < overRicePrices) {
                        writer.write("对不起，" + username + "账户余额不足，请充值后再进行消费");
                    } else {
                        remainingAmount = balanceOf - overRicePrices;
                        newCard = new Cookie("money", (remainingAmount) + "");
                        consumptionAmount = overRicePrices;
                    }
                }
            }
        }
        // 将会员卡返还给用户
        response.addCookie(newCard);
        // 将本次消费写到响应体中
        writer.write(username + "先生/女士，您好，您本次在本店一共消费" + consumptionAmount + "元，您的余额为：" + remainingAmount);
    }
}
