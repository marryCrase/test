package cn.itcast.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**

 * 实现了ApplicationContextAware 接口，必须实现该方法；

 *通过传递applicationContext参数初始化成员变量applicationContext

 */

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private ApplicationContext context;//声明一个静态变量保存
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context=applicationContext;
    }

    public  ApplicationContext getContext(){
        return context;
    }
}