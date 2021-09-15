package study.javaweb.service;

import study.javaweb.model.Book;

import java.util.List;

/**
 * @author dhl
 * @datetime 2021/7/16  13:32
 */
public interface BookService {
    /**
     * 获取当前书籍的所有信息接口
     * @return 书籍对象所处的集合
     */
    public List<Book> listBooks();

    /**
     * 对数据库中的图书信息进行新增
     * @param book
     * @return 0代表新增失败，大于0代表新增成功
     */
    public int insert(Book book);

    /**
     * 对数据库中的某一条数据进行删除操作
     * @param id
     * @return 0代表失败，大于0代表成功
     */
    public int delete(int id);

    /**
     * 通过书名对数据库中的数据进行删除
     * @param bookName
     * @return
     */
    public int delete(String bookName);

    /**
     * 通过id对我们数据库的数据进行更新
     * @param book 更新之后的book对象
     * @param id 不变的id
     * @return
     */
    public int update(Book book, int id);

    /**
     * 通过id获取当前book对象
     * @param id
     * @return book对象
     */
    public Book getBook(int id);

    /**
     * 通过下拉列表框中的选项对我们的数据进行选择
     * @param querySelectStr 下拉列表框值
     * @param querySelectValue 查询的值
     * @return 书籍对象
     */
    public Book getBookByElement(String querySelectStr, String querySelectValue);
}
