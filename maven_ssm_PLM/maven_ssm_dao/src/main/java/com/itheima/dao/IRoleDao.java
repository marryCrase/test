package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-20
 * Time:10:30
 * 角色Dao
 */
@Repository
public interface IRoleDao {
    /**
     * 根据用户id查询出所有对应的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from user_role where userId = #{userId})")
    List<Role> findRoleByUserId(String userId) throws Exception;

}
