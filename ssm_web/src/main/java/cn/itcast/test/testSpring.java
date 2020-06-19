package cn.itcast.test;

import cn.itcast.domain.Audio;
import cn.itcast.domain.Url;
import cn.itcast.service.IAudioService;
import cn.itcast.service.IUrlService;
import cn.itcast.service.impl.UrlServiceImpl;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * 测试spring
 */
@ContextConfiguration(classes = SpringConfiguration.class)
public class testSpring {
//    ApplicationContext ac;

    private UrlServiceImpl urlService = new UrlServiceImpl();

    public testSpring() {
//        ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        UrlServiceImpl urlService = (UrlServiceImpl) ac.getBean("urlService");
        List<Url> urls = urlService.findAllUrl();
        for (Url url : urls){
            System.out.println(url);
        }
    }

    public static void main(String[] args) {
        new testSpring();
    }

    /**
     * 加载
     * @param service
     */
    public void before(String service){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        IAudioService as = (IAudioService) ac.getBean(service);

    }
    @Test
    public void audio(){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        UrlServiceImpl as = (UrlServiceImpl) ac.getBean("urlService");

        /*Url url = new Url();
        url.setIphoto("1");
        url.setIphotoUrl("1");
        url.setO_id(0);
        url.setIhtml("1");
        as.saveUrl(url);
        System.out.println(url);
        System.out.println(url.getId());*/
        //调用方法
        /*List<Audio> audios = as.findAllAudio();
        for (Audio audio : audios){
            System.out.println(audio);
        }
        Audio a = new Audio();
        a.setAudio("1");
        a.setAudioUrl("1");
        a.setU_id(64);
        as.saveAudio(a);

        List<Audio> audios = as.findAudioByUid(64);
        for (Audio audio : audios){
            System.out.println(audio);
        }*/
    }



}
