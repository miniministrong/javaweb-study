package study.javaweb.controller;

import study.javaweb.model.Book;
import study.javaweb.service.BookService;
import study.javaweb.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 查询单个图书的信息
 *
 * @author dhl
 * @datetime 2021/7/19  9:41
 */
@WebServlet(name = "query", urlPatterns = "/query")
public class QueryBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String querySelect = request.getParameter("querySelect").trim();
        String queryValue = request.getParameter("queryValue").trim();
        if (queryValue == null || "".equals(queryValue)) {
            writer.write("<h1 style = 'color:red'>您所输入的信息为空，请重新输入</h1>");
        }
        BookService bookService = new BookServiceImpl();
        Book book = bookService.getBookByElement(querySelect, queryValue);
        if (book != null) {
            request.setAttribute("queryBook", book);
            request.getRequestDispatcher("/query.jsp").forward(request, response);
        } else {
            writer.write("<h1 style = 'color:red'>您所要搜索的信息没有查询到，请查证后再次进行搜索，谢谢！</h1>");
        }
    }
}