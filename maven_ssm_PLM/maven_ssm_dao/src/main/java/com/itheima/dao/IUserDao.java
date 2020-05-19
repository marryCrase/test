package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao
 */
@Repository("userDao")
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAllUser(int pageNum,int pageSize);

    /**
     * 根据ID查询用户
     */
    @Select("select * from user where id = #{id}")
    User findUserById(int userId);

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
    @Select("selectt * from where username = #{username},password=#{passwordss}")
    User findUser(User user);
}
