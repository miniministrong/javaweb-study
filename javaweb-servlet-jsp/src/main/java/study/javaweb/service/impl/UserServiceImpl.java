package study.javaweb.service.impl;

import study.javaweb.dao.UserDAO;
import study.javaweb.model.User;
import study.javaweb.service.UserService;
import study.javaweb.utils.DaoFactoryUtils;

/**
 * @author dhl
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDao = DaoFactoryUtils.getDao("study.javaweb.dao.UserDAO", UserDAO.class);
    @Override
    public User getUser(String username) {
        return userDao.getUserInfo(username);
    }
}
