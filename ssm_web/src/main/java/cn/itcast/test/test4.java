package cn.itcast.test;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class test4 {

    public static void main(String[] args) {
        /*String IP = null;
        List<String> strings = getIPs();
        for (String s : strings){
            System.out.println(s.replaceAll(" ",""));
            //加载驱动类
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn= DriverManager.getConnection("jdbc:mysql://"+s.replaceAll(" ","")+"/plm2?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT","root","123456");
                System.out.println(conn);
                if (conn!=null){
                    System.out.println("IP:"+s.replaceAll(" ",""));
                    IP = "jdbcUrl="+s.replaceAll(" ","");
                    break;
                }
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/

        //创建文件对象
        File file = new File("/data.properties");
//        read(file.getPath());
        /*if (IP!=null){
            System.out.println(file.toPath()+file.getPath()+","+IP);
            write(file.getPath().replace("\\","/"),"123");
        }*/
        //3.写入数据
        //创建文件字节输出流
        FileOutputStream fos = null;
        String path=System.getProperty("/data.properties");

        System.out.println("路径:"+path);
        try {
            fos = new FileOutputStream(file.getPath());
            //开始写
            System.out.println("前"+read("/data.properties"));
            String str = "1111";
            byte[] bytes = str.getBytes();
            //将byte数组中的所有数据全部写入
            fos.write(bytes);
            System.out.println("后"+read("/data.properties"));
            //关闭流
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断文件 ，返回布尔
        file.isFile();
        System.out.println(file.isFile());
        //路径（文件夹）
        file.isDirectory();
        System.out.println(file.isDirectory());
//        System.out.println(getHostIP());

//        System.out.println(getHostIP1());

    }
    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     */
    public static String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                // 此处根据实际需要修改某些行的内容
                if (line.startsWith("    upstream localhosttest {")) {
                    buf.append(line).append("server 127.0.0.1:7001;");
                }
                else if (line.startsWith("b")) {
                    buf.append(line).append(" start with b");
                }
                // 如果不用修改, 则按原来的内容回写
                else {
                    buf.append(line);
                }
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        System.out.println(buf.toString());
        return buf.toString();
    }

    /**
     * 将内容回写到文件中
     *
     * @param filePath
     * @param content
     */
    public static void write(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }


    public static List<String>  getIPs()
    {
        List<String> list = new ArrayList<String>();
        boolean flag = false;
        int count=0;
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("arp -a");
            BufferedReader br = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String inline;
            while ((inline = br.readLine()) != null) {
                if(inline.indexOf("接口") > -1){
                    flag = !flag;
                    if(!flag){
                        //碰到下一个"接口"退出循环
                        break;
                    }
                }
                if(flag){
                    count++;
                    if(count > 2){
                        //有效IP
                        String[] str=inline.split(" {4}");
                        list.add(str[0]);
                    }
                }
                System.out.println(inline);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }


    private static String getHostIP(){
        String tempIP = "127.0.0.1";
        try {
            tempIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(tempIP);
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(tempIP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            return tempIP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getHostIP1(){

        Enumeration<NetworkInterface> allNetInterfaces = null;
        String resultIP=null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    if(resultIP==null)
                        resultIP= ip.getHostAddress();
                    System.out.println("本机IP地址为："+ip.getHostAddress());

                }
            }
        }
        return resultIP;

    }

}
