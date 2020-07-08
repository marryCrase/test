package cn.itcast.util;

import java.sql.Connection;
import java.sql.DriverManager;

import static cn.itcast.view.ViewUtil.readFileByLines;

/**
 * Created by Administrator on 2019-08-22.
 */
public class DbUtil {
    //��ȡ�ļ����ݲ�����
    String ip = readFileByLines("D:\\IPConfig.txt");

    private String dbUrl = "jdbc:mysql://"+ip+"/plm2";
    private String dbUserName = "root";
    private String dbPassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * ��ȡ���ݿ�����
     * @return
     * @throws Exception
     */
    public Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    /**
     * �ر����ݿ�����
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
            System.out.println("���ݿ����ӳɹ�");
        } catch (Exception e) {
            System.out.println("���ݿ�����ʧ��");
            e.printStackTrace();
        }
    }
}
