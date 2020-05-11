package cn.itcast.test;

import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试mybatis
 */
public class TestMybatids {

    /**
     * 测试查询
     * @throws IOException
     */
    @Test
    public void run1() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //配置工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理dao对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //调用方法查询数据
        List<Account> accounts = accountDao.findAllAccount();
        //遍历
        for (Account account : accounts){
            System.out.println(account);
        }
        //关闭资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试保存
     * @throws IOException
     */
    @Test
    public void run2() throws IOException {

        Account account = new Account();
        account.setName("弟弟");
        account.setMoney(1000d);

        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //配置工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理dao对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //调用方法
        accountDao.saveAccount(account);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
        in.close();
    }
}
