package study.javaweb.dao;

import study.javaweb.model.User;
import study.javaweb.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 对用户数据进行增删改查操作
 * @author dhl
 */
public class UserDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return User对象里面存储了用户的信息，或许为null
     */
    public User getUserInfo(String username){
        User user = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("username"),rs.getString("password"));
            }
            conn.commit();
            return user;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return user;
    }

    /**
     * 增加数据库中的数据
     * @param user
     * @return 0代表增加失败，大于0表示增加成功的条数
     */
    public int add(User user){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(username, password) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUsername());
            ps.setObject(2, user.getPassword());
            int count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return 0;
    }

    /**
     * 对数据库中的数据进行删除操作
     * @param user
     * @return 0代表删除失败或删除条数为0，大于0的数据表示删除记录的条数
     */
    public int delete(User user){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUsername());
            ps.setObject(2, user.getPassword());
            int count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return 0;
    }

    /**
     * 对数据库中的密码进行修改
     * @param user
     * @return 0代表修改失败或修改条数为0，大于0表示修改数据的条数
     */
    public int update(User user){
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "update set password = ? where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getPassword());
            ps.setObject(2, user.getUsername());
            int count = ps.executeUpdate();
            conn.commit();
            return count;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return 0;
    }

    /**
     * 查询当前用户的密码
     * @param username
     * @return
     */
    public String select(String username){
        String password = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "select password from user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                password = (String) rs.getObject("password");
            }
            conn.commit();
            return password;
        } catch (SQLException throwables) {
            DBUtils.rollback();
            throwables.printStackTrace();
        }finally {
            DBUtils.close();
        }
        return password;
    }
}
