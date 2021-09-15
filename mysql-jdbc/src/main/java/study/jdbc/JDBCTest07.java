package study.jdbc;

import study.jdbc.dao.UserDao;
import study.jdbc.moudle.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 批处理，测试UserDao类
 */
public class JDBCTest07 {
    public static void main(String[] args) {
        // 测试新增方法
        UserDao userDao = new UserDao();
        // int count = userDao.add(new User("li", "li"));
        // System.out.println(count);
        // 测试批量新增方法
        /*List<User> users = new ArrayList<User>();
        users.add(new User("1a", "1a"));
        users.add(new User("2b", "2b"));
        users.add(new User("3c", "3c"));
        for (int i = 0; i < users.size(); i++) {
            System.out.println(userDao.add(users.get(i)));
        }*/
        // 测试修改方法
        // userDao.update("zhang", "1234");
        // 测试删除方法
        //userDao.delete("li");
        // 测试查询方法
        userDao.select("wang");
    }
}
