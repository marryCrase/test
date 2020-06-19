package com.itheima.dao;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao
 */
@Repository
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<UserInfo> findAllUser(int pageNum, int pageSize);

    /**
     * 根据ID查询用户
     */
    @Select("select * from user where id = #{id}")
    UserInfo findUserById(int userId);

    /**
     * 根据条件查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(id = true,property = "username",column = "username"),
            @Result(id = true,property = "password",column = "password"),
            @Result(id = true,property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findUserByName(String username) throws Exception;
}
