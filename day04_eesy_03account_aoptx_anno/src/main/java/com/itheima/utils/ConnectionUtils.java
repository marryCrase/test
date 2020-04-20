package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @outhor �����
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
        //1���ȴ�ThreadLocal�ϻ�ȡ
        Connection conn = tl.get();
            //2���жϸ��߳����Ƿ�������
            if (conn == null){
                //3��������Դ�л�ȡһ�����ӣ������뵽ThreadLocal��
                    conn = dataSource.getConnection();
                    tl.set(conn);
            }
            //4�����ص�ǰ�߳��ϵ�����
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * �����Ӻ��߳̽��
     */
    public void removeConnection(){
        tl.remove();
    }

}
