package study.jdbc.lock;

import study.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 这个类是专门对我们的数据进行修改的
 */
public class pessimisticLockTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "update user set password = password + '12' where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 3);
            int count = ps.executeUpdate();
            System.out.println(count);
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
            DBUtils.close(conn, ps, null);
        }
    }
}
