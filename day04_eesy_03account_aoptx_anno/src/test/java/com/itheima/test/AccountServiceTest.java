package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-03-30
 * Time:16:58
 *
 * 使用Junit单元测试，测试我们的配置
 * spring整合junity的配置
 *      1、导入spring整合junit的jar（坐标）
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *          @Runwith
 *      3、告知spring的运行期，spring的ioc创建事基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *                  location:指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在位置
 *  当我们使用spring 5.X版本的时候，要求junit的jar必须是4.12及以上
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService as = null;

    @Test
    public void testTransfer(){
        as.transfer("aaa","bbb",100f);
    }


}
