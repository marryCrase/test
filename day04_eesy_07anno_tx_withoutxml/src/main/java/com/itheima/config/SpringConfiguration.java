package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring的配置类，相当于bean.xml
 */
@Configuration
//开启扫描
@ComponentScan("com.itheima")
@Import({JdbcConfig.class,TransactionConfig.class})
//启用配置文件
@PropertySource("JdbcConfig.properties")
//开启事务支持
@EnableTransactionManagement
public class SpringConfiguration {
}
