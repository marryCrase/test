package com.scy.config;

import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        User createUser = (User) subject.getPrincipal();//拿到user对象

        if (createUser.getPerms()!=null){
            //设置当前用户的权限
            info.addStringPermission(createUser.getPerms());
        }

        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //用户名  密码  数据库中取
        User user = userMapper.findUserByName(userToken.getUsername());

        if (user==null){//没有这个人
            return null;
        }

        //密码认证  shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
