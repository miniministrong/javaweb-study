package study.jdbc.lock;

import study.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个方法专门负责查询语句，开启一个事务，并且使用行级锁/悲观锁，对我们的数据进行封锁操作
 */
public class pessimisticLockTest01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select id, username, password from user where id = ? for update";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "," + rs.getString("username") + "," + rs.getString("password"));
            }
            conn.commit();
        } catch (SQLException throwables) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, rs);
        }
    }
}
