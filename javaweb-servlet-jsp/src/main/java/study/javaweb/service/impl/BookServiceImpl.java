package study.javaweb.service.impl;

import study.javaweb.dao.BookDAO;
import study.javaweb.model.Book;
import study.javaweb.service.BookService;
import study.javaweb.utils.DaoFactoryUtils;

import java.util.List;

/**
 * 对BookService接口的实现
 *
 * @author dhl
 * @datetime 2021/7/16  13:34
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = DaoFactoryUtils.getDao("study.javaweb.dao.BookDAO", BookDAO.class);

    @Override
    public List<Book> listBooks() {
        return bookDAO.listBooks();
    }

    @Override
    public int insert(Book book) {
        return bookDAO.insert(book);
    }

    @Override
    public int delete(int id) {
        return bookDAO.delete(id);
    }

    @Override
    public int delete(String bookName) {
        return bookDAO.delete(bookName);
    }

    @Override
    public int update(Book book, int id) {
        return bookDAO.update(book, id);
    }

    @Override
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    public Book getBookByElement(String querySelectStr, String querySelectValue) {
        return bookDAO.getBookByElement(querySelectStr, querySelectValue);
    }
}