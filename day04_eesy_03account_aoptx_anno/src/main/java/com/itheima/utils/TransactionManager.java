package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @outhor �����
 * User:Administrator
 * Date:2020-04-08
 * Time:13:53
 *
 * ��������صĹ����࣬�������ˣ����������ύ���񣬻ع�������ͷ�����
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}

    /**
     * ��������
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ύ����
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ع�����
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ͷ�����
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//�������ӳ���
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����֪ͨ
     * @param pjp
     * @return
     */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            //1����ȡ����
            Object[] args = pjp.getArgs();
            //2����������
            this.beginTransaction();
            //3��ִ�з���
            rtValue = pjp.proceed(args);
            //4���ύ����
            this.commit();
            //��������
            return rtValue;
        }catch (Throwable throwable){
            //6���ع�����
            this.rollback();
            throw new RuntimeException(throwable);
        }finally {
            //7���ͷ�����
            this.release();
        }
    }
}
