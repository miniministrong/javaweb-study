package study.jdbc;

import java.sql.*;

/**
 * 安全的查询
 */
public class JDBCTest02 {
    public static final String URL = "jdbc:mysql://localhost:3306/stu?useSSL=false&characterEncoding=utf8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "duhonglei";

    public static void main(String[] args) {
        String username = "zhang";
        String password = "12";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 获取数据库连接对象
            String sql = "select * from user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            // 执行SQL语句
            rs = ps.executeQuery();
            // 遍历查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("username") + "\t" + rs.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
