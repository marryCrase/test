package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-04-08
 * Time:11:23
 */
@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    public Connection getThreadConnection(){
        try {
        //1、先从ThreadLocal上获取
        Connection conn = tl.get();
            //2、判断该线程上是否有链接
            if (conn == null){
                //3、从数据源中获取一个链接，并存入到ThreadLocal中
                    conn = dataSource.getConnection();
                    tl.set(conn);
            }
            //4、返回当前线程上的链接
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 把链接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }

}
