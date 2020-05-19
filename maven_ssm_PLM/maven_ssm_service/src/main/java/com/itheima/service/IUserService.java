package com.itheima.service;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户dao
 */
public interface IUserService {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser(int pageNum, int pageSize);

    /**
     * 根据ID查询用户
     */
    User findUserById(int userId);

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
    User findUser(User user);
}
