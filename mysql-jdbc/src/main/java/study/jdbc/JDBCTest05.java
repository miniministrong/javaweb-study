package study.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest05 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/stu?useSSl=false&characterEncoding=utf8";
        String user = "root";
        String pwd = "duhonglei";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
            // 将JDBC的事务设置为手动提交
            conn.setAutoCommit(false);
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 10000);
            ps.setObject(2, 111);
            int count = ps.executeUpdate();

            String s = null;
            s.toString();

            ps.setObject(1, 10000);
            ps.setObject(2, 222);
            count += ps.executeUpdate();
            System.out.println(count == 2 ? "取款成功" : "取款失败");
            // 提交事务
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            if (conn != null) {
                try {
                    // 回滚事务
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
