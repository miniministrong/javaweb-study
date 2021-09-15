package study.jdbc;

import java.sql.*;

public class JDBCTest01 {
    public static final String URL = "jdbc:mysql://localhost:3306/stu?useSSL=false&characterEncoding=utf8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "duhonglei";

    public static void main(String[] args) {
        String username = "zhang";
        String password = "12";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 获取数据库连接对象
            stmt = conn.createStatement();
            // 执行SQL语句
            String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);
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
            if (stmt != null) {
                try {
                    stmt.close();
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
