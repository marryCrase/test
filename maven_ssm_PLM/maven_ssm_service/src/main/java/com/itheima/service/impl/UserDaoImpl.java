package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service
 */
@Service("userService")
public class UserDaoImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 查询所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        return null;
    }

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    @Override
    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
    @Override
    public User findUser(User user){
        return userDao.findUser(user);
    }
}
