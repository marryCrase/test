package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.io.*;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-04-01
 * Time:13:53
 *
 * 和spring链接数据库相关的配置类
 */
@Configuration
@MapperScan("cn.itcast.dao")
public class JdbcConfig {

//    @Value("${jdbc.driver}")
    private String driver ="com.mysql.jdbc.Driver";

//    @Value("${jdbc.url}")
    private String url;

//    @Value("${jdbc.user}")
    private String username = "root";

//    @Value("${jdbc.password}")
    private String password ="123456";

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("ds") DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="ds")
    public DataSource createDataSource(){
        //读取文件内容并返回
        String ip = readFileByLines("D:\\IPConfig.txt");
        try{
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl("jdbc:mysql://"+ip+"/plm2?useUnicode=true&amp;characterEncoding=UTF-8");
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取sessionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                Thread.sleep(500);
                System.out.println(tempString);
                if (tempString!=null){
                    return tempString;
                }
                line++;
            }
            reader.close();
            return tempString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return "";

    }
}
