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
import java.util.List;

/**
 * 查询book表中的所有数据
 *
 * @author dhl
 * @datetime 2021/7/16  14:52
 */
@WebServlet(name = "queryBooks",urlPatterns = "/queryBooks")
public class QueryBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        BookService bookService = new BookServiceImpl();
        List<Book> books = bookService.listBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/book.jsp").forward(request, response);
    }
}