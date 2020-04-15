package com.itheima.utils;

import jdk.Exported;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect//表明该类是一个切面类
public class Logger {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}

    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行(切入点方法就是业务层方法)
     * 前置通知
     */
    @Before("pt1()")
    public void beforePrintlog(){
        System.out.println("logger中的beforePrintlog方法开始记录日志了");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public void afterReturningPrintlog(){
        System.out.println("logger中的afterReturningPrintlog方法开始返回了");
    }
    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void afterThrowingPrintlog(){
        System.out.println("logger中的afterThrowingPrintlog方法开始记录异常了");
    }
    /**
     * 最终通知
     */
    @After("pt1()")
    public void afterPrintlog(){
        System.out.println("logger中的afterPrintlog方法结束了");
    }

    /**
     * 环绕通知
     * 问题:
     *      当我们配置了环绕通知之后,切入点方法没有执行,而通知方法执行了
     * 分析:
     *      通过对比动态代理中的环绕通知代码,发现动态代理的环绕通知中有明确的切入点方法调用,而我们的代码中没有
     * 解决:
     *      Spring框架为我们提供了一个接口,ProceedingJoinPoint.该接口有一个方法proceed(),此方法就相当于明确调用切入点方法.
     *      该接口可以作为环绕通知的方法参数,在程序执行时,spring框架会为我们提供该接口的实现类共我们使用.
     *
     * spring中的环绕通知:
     *      他是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式.
     */
    @Around("pt1()")
    public Object aroundPrintlog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();//得到方法执行所需的参数

            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了...前置");

            rtValue = pjp.proceed(args);//明确调用业务层方法(切入点方法)

            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了...后置");

            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了...异常");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了...最终");
        }

    }

}
