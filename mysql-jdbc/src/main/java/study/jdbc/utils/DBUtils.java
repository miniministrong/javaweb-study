package study.jdbc.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * JDBC工具类
 */
public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/stu?useSSL=false&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "duhonglei";
    private DBUtils(){}
    // 静态代码块，只在类加载的时候执行，并且只执行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用连接池建立连接
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        HikariConfig config = new HikariConfig();
        // 设置数据库地址URL
        config.setJdbcUrl(URL);
        // 设置数据库用户名
        config.setUsername(USERNAME);
        // 设置数据库密码
        config.setPassword(PASSWORD);
        // 设置连接超时时间
        config.addDataSourceProperty("connectionTimeout", "1000");
        // 设置空闲时间
        config.addDataSourceProperty("idleTimeout", "60000");
        // 设置最大连接数
        config.addDataSourceProperty("maximumPoolSize", "10");
        // 获取数据资源
        DataSource ds = new HikariDataSource(config);
        return ds.getConnection();
    }

    /**
     * 获取数据库连接对象（未使用连接池）
     * @return 连接对象
     * @throws SQLException
     */
    /*public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }*/

    /**
     * 关闭连接
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
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
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 回滚
     * @param conn
     */
    public static void rollback(Connection conn){
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
