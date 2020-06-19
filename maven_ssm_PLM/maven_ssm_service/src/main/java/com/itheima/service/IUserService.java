package com.itheima.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户dao
 */
public interface IUserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     */
//    List<UserInfo> findAllUser(int pageNum, int pageSize);

    /**
     * 根据ID查询用户
     */
//    UserInfo findUserById(int userId);

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
//    UserInfo findUser(UserInfo user);
}
