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
import java.math.BigDecimal;

/**
 * 对增加图书信息前后端交互
 *
 * @author dhl
 * @datetime 2021/7/16  13:39
 */
@WebServlet(name = "insert", urlPatterns = "/insert")
public class InsertBookInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String bookName = request.getParameter("book_name");
        String bookAuthor = request.getParameter("book_author");
        Integer bookPage = Integer.parseInt(request.getParameter("book_page"));
        String price = request.getParameter("price");
        BigDecimal bookPrice = new BigDecimal(price);

        BookService bookService = new BookServiceImpl();
        Book book = new Book(bookName, bookAuthor, bookPage, bookPrice);
        int count = bookService.insert(book);
        if (count <= 0) {
            writer.write("<h1 style='color:red'>新增数据失败，请重试</h1>");
        } else {
            request.getRequestDispatcher("/queryBooks").forward(request, response);
        }
    }
}