package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Service
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus()== 0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    //返回一个权限集合
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role: roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /*
    *//**
     * 查询所有用户
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @Override
    public List<UserInfo> findAllUser(int pageNum, int pageSize) {
        return null;
    }

    *//**
     * 根据ID查询用户
     * @param userId
     * @return
     *//*
    @Override
    public UserInfo findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    *//**
     * 根据条件查询用户
     * @param user
     * @return
     *//*
    @Override
    public UserInfo findUser(UserInfo user){
        return userDao.findUser(user);
    }*/
}
