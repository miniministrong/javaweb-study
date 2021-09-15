package study.jdbc;

import java.sql.*;

/**
 * 增删改
 */
public class JDBCTest03 {
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
            // 增加
            /*String sql = "insert into user(id, username, password) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.setString(2, "li");
            ps.setString(3, "1111");*/
            // 修改
            /*String sql = "update user set username = ?, password = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "zhao");
            ps.setString(2, "2222");
            ps.setInt(3, 4);*/
            // 删除
            String sql = "delete from user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 4);
            // 执行sql
            int count = ps.executeUpdate();
            System.out.println("执行成功的语句：" + count);

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