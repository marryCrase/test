package com.scy.mapper;

import com.scy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2020/6/28.
 */
@Mapper
@Repository
public interface UserMapper {

    User findUserByName(String username);

}
