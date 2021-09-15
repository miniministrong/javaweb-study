package study.jdbc.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/stu?useSSL=false&characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "duhonglei";
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private DBUtils() {
    }

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库连接
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
     * 数据回滚方法
     */
    public static void rollback(){
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 关闭资源
     */
    public static void close(){
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
}
