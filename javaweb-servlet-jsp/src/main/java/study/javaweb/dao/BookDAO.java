package study.javaweb.dao;

import study.javaweb.model.Book;
import study.javaweb.model.User;
import study.javaweb.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对图书的增删改查操作
 *
 * @author dhl
 * @datetime 2021/7/16  10:37
 */
public class BookDAO {
    private Connection conn = null;
    Statement stmt = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * 对book进行增加操作
     * @param book
     * @return
     */
    public int insert(Book book) {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into book(book_name, book_author, book_page, price) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setInt(3, book.getBookPage());
            ps.setBigDecimal(4, book.getPrice());
            count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return count;
    }

    /**
     * 删除当前书名所在的图书
     * @param id
     * @return 0表示删除失败，大于0表示删除的条数
     */
    public int delete(int id) {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from book where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return count;
    }

    /**
     * 通过书名对数据进行删除
     * @param bookName
     * @return
     */
    public int delete(String bookName) {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from book where book_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookName);
            count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return count;
    }

    /**
     * 更新数据库中该图书的数据
     * @param book 新改变之后的图书对象
     * @param id 原图书id
     * @return 0代表更新失败，大于0代表更新的条数
     */
    public int update(Book book, int id) {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "update book set book_name = ?, book_author = ?, book_page = ?, price = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setInt(3, book.getBookPage());
            ps.setBigDecimal(4, book.getPrice());
            ps.setInt(5, id);
            count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return count;
    }

    /**
     * 通过id获取我们的book对象
     * @param id
     * @return book对象
     */
    public Book getBook(int id){
        Book book = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from book where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getInt("id"),rs.getString("book_name"), rs.getString("book_author"), rs.getInt("book_page"), rs.getBigDecimal("price"));
            }
            conn.commit();
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return book;
    }

    /**
     * 查询当前书籍的所有信息
     * @return 书籍对象
     */
    public List<Book> listBooks() {
        Book book = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from book";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getInt("id"),rs.getString("book_name"), rs.getString("book_author"), rs.getInt("book_page"), rs.getBigDecimal("price"));
                books.add(book);
            }
            conn.commit();
            return books;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return books;
    }

    /**
     * 通过下拉列表框中的选项对我们的数据进行选择
     * @param querySelectStr 下拉列表框值
     * @param querySelectValue 查询的值
     * @return 书籍对象
     */
    public Book getBookByElement(String querySelectStr, String querySelectValue) {
        String bookName = "book_name";
        String bookAuthor = "book_author";
        Book book = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "";
            if (bookName.equals(querySelectStr)) {
                sql = "select * from book where book_name = ?";
            } else if (bookAuthor.equals(querySelectStr)) {
                sql = "select * from book where book_author = ?";
            } else {
                return book;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, querySelectValue);
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getInt("id"), rs.getString("book_name"), rs.getString("book_author"),
                        rs.getInt("book_page"), rs.getBigDecimal("price"));
            }
            conn.commit();
            return book;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return book;
    }
}