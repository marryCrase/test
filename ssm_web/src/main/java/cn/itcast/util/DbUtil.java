package cn.itcast.util;

import java.sql.Connection;
import java.sql.DriverManager;

import static cn.itcast.view.ViewUtil.readFileByLines;

/**
 * Created by Administrator on 2019-08-22.
 */
public class DbUtil {
    //读取文件内容并返回
    String ip = readFileByLines("D:\\IPConfig.txt");

    private String dbUrl = "jdbc:mysql://"+ip+"/plm2";
    private String dbUserName = "root";
    private String dbPassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    /**
     * 关闭数据库连接
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con)throws Exception{
        if (con!=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }
}
