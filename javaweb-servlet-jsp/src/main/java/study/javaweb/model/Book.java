package study.javaweb.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 图书数据的实例类
 *
 * @author dhl
 * @datetime 2021/7/16  10:32
 */
public class Book {
    private int id;
    private String bookName;
    private String bookAuthor;
    private int bookPage;
    private BigDecimal price;

    public Book() {
    }

    public Book(String bookName, String bookAuthor, int bookPage, BigDecimal price) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPage = bookPage;
        this.price = price;
    }

    public Book(int id, String bookName, String bookAuthor, int bookPage, BigDecimal price) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPage = bookPage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPage() {
        return bookPage;
    }

    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPage=" + bookPage +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return getId() == book.getId() && getBookPage() == book.getBookPage() && Objects.equals(getBookName(), book.getBookName()) && Objects.equals(getBookAuthor(), book.getBookAuthor()) && Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookName(), getBookAuthor(), getBookPage(), getPrice());
    }
}