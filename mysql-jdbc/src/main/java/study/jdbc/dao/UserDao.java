package study.jdbc.dao;

import study.jdbc.moudle.User;
import study.jdbc.utils.DBUtils;

import java.sql.*;
import java.util.List;

public class UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    // 增

    /**
     * 对数据库中进行增加操作，只增加一个值
     * @param user
     * @return -1代表增加失败，其他表示增加的记录条数
     */
    public int add(User user){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(username, password) values(?, ?)";
            // ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUsername());
            ps.setObject(2, user.getPassword());
            int count = ps.executeUpdate();
            // rs = ps.getGeneratedKeys();
            /*if (rs.next()) {
                return rs.getInt(1);
            }*/
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback(conn);
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, rs);
        }
        return -1;
    }
    // 批量增加

    /**
     * 数据库中批量增加数据
     * @param users
     * @return
     */
    public int[] addBatch(List<User> users){
        int[] keys = new int[users.size()];
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(username, password) values(?, ?)";
            // 也可以传入一个主键
            // ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps = conn.prepareStatement(sql);
            for (User user : users) {
                ps.setObject(1, user.getUsername());
                ps.setObject(2, user.getPassword());
                ps.addBatch();
            }
            keys = ps.executeBatch();
            // 让他去获取主键的值，并返回主键的集合
            /*rs = ps.getGeneratedKeys();
            int i = 0;
            while (rs.next()) {
                keys[i] = rs.getInt(1);
                i++;
            }*/
            conn.commit();
            return keys;
        } catch (SQLException throwables) {
            DBUtils.rollback(conn);
            throwables.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return keys;
    }

    /**
     * 对数据库数据的删除操作
     * @param username 所要删除数据的用户名
     * @return -1代表删除失败，其余代表删除成功的条数
     */
    public int delete(String username){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            int count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback(conn);
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, null);
        }
        return -1;
    }

    /**
     * 对数据库数据进行修改操作
     * @param username 用户名
     * @param password 修改之后的密码
     * @return -1代表更新失败，其余代表更新成功的条数
     */
    public int update(String username, String password){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "update user set password = ? where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, password);
            ps.setObject(2, username);
            int count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback(conn);
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, null);
        }
        return -1;
    }

    /**
     * 对数据库数据进行查询操作
     * @param username 用户名
     */
    public void select(String username){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select id, username, password from user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getObject("username") + "\t" + rs.getObject("password"));
            }
            conn.commit();
        } catch (SQLException throwables) {
            DBUtils.rollback(conn);
            throwables.printStackTrace();
        }finally {
            DBUtils.close(conn, ps, rs);
        }
    }
}
