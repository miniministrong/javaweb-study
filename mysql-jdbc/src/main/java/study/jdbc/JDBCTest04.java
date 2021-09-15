package study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务
 */
public class JDBCTest04 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/stu?useSSl=false&characterEncoding=utf8";
        String user = "root";
        String pwd = "duhonglei";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            conn = DriverManager.getConnection(url, user, pwd);
            // 获取预编译的数据库连接对象
            String sql = "update user set username = ? where id = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, "zhao");
            ps.setInt(2, 3);
            // 执行sql
            int count = ps.executeUpdate();
            System.out.println("执行成功的语句：" + count);

            // 第二次执行sql
            ps.setString(1, "liu");
            ps.setInt(2, 2);
            count = ps.executeUpdate();
            System.out.println(count);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
