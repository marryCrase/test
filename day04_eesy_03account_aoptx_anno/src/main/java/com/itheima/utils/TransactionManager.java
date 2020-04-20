package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-04-08
 * Time:13:53
 *
 * 和事务相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放链接
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放链接
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回链接池中
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            //1、获取参数
            Object[] args = pjp.getArgs();
            //2、开启事务
            this.beginTransaction();
            //3、执行方法
            rtValue = pjp.proceed(args);
            //4、提交事务
            this.commit();
            //返回数据
            return rtValue;
        }catch (Throwable throwable){
            //6、回滚事务
            this.rollback();
            throw new RuntimeException(throwable);
        }finally {
            //7、释放连接
            this.release();
        }
    }
}
