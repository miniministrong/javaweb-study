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
 * 通过id属性对我们的数据进行更新
 *
 * @author dhl
 * @datetime 2021/7/16  16:43
 */
@WebServlet(name = "update",urlPatterns = "/update")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        BookService bookService = new BookServiceImpl();
        Book book = bookService.getBook(id);
        request.setAttribute("updateBook",book);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String idStr = request.getParameter("id").trim();
        String bookName = request.getParameter("book_name").trim();
        String bookAuthor = request.getParameter("book_author").trim();
        String bookPageStr = request.getParameter("book_page").trim();
        String price = request.getParameter("price");
        if (bookName == null || bookAuthor == null || bookPageStr == null || price == null) {
            writer.write("<h1 style = 'color:red'>您输入的信息其中一项为空，请重新输入您修改的信息！</h1>");
        }
        if ("".equals(bookName) || "".equals(bookAuthor) || "".equals(bookPageStr) || "".equals(price)) {
            writer.write("<h1 style = 'color:red'>您输入的信息其中一项为空，请重新输入您修改的信息！</h1>");
        }
        Integer id = Integer.parseInt(idStr);
        Integer bookPage = Integer.parseInt(bookPageStr);
        BigDecimal bookPrice = new BigDecimal(price);
        Book book = new Book(id, bookName, bookAuthor, bookPage, bookPrice);
        BookService bookService = new BookServiceImpl();
        int count = bookService.update(book, id);
        if (count <= 0) {
            writer.write("<h1 style='color:red'>修改数据失败，请重试</h1>");
        } else {
            request.getRequestDispatcher("/queryBooks").forward(request, response);
        }

    }
}