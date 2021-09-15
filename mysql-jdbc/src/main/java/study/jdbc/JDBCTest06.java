package study.jdbc;

import study.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模糊查询
 * 使用DBUtils
 */
public class JDBCTest06 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select username from user where username like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "_h%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, rs);
        }
    }
}