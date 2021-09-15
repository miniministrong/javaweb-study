package study.javaweb.controller;

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
 * 对数据库中的某条数据进行删除操作
 *
 * @author dhl
 * @datetime 2021/7/16  15:16
 */
@WebServlet(name = "delete", urlPatterns = "/delete")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        BookService bookService = new BookServiceImpl();
        int count = bookService.delete(id);
        if (count <= 0) {
            writer.write("<h1 style='color:red'>删除数据失败，请重新尝试</h1>");
        }else {
            request.getRequestDispatcher("/queryBooks").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}