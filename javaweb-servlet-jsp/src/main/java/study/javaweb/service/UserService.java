package study.javaweb.service;

import study.javaweb.model.User;

/**
 * @author dhl
 */
public interface UserService {
    /**
     * 获取我们的用户信息
     * @param username 用户名
     * @return 用户信息
     */
    public User getUser(String username);
}
