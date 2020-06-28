package com.scy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{

    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager ){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         * anon:无需认证就能访问
         * authc:必须认证才能访问
         * user:必须有  记住我  功能才能访问
         * perms:必须有对某资源的权限才能访问
         * role:必须有某角色的权限才能访问
         */
        /**
         * ①anon 未认证可以访问
          ②authc 认证后可以访问
          ③perms 需要特定权限才能访问
          ④roles 需要特定角色才能访问
      ⑤user 需要特定用户才能访问
      ⑥port 需要特定端口才能访问
      ⑦reset 根据指定 HTTP 请求访问才能访问
         */
        //拦截
        Map<String,String> filterMap = new HashMap<String,String>();

        //授权，正常情况下，没有权限会跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

//        filterMap.put("/user/add","anon");
        filterMap.put("/user/*","authc");

        factoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录页面
        factoryBean.setLoginUrl("/toLogin");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/noauth");

        return factoryBean;
    }

    //DefaultWebSecurityManager:2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建 Realm类  需要自定义对象：1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
