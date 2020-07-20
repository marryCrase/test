package com.plm;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Test6 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        InetAddress ip;
        try {
            // 正确的IP拿法
            System.out.println("get LocalHost LAN Address : " + getLocalHostLANAddress().getHostAddress());
            file(getLocalHostLANAddress().getHostAddress());
        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
    }

    // 正确的IP拿法，即优先拿site-local地址
    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    public static void file(String ip){

        File a=new File("D:\\IPConfig.txt"); //新建文件
        System.out.println(a.getPath());
        try {
            if(!a.exists()) {    //如果文件不存在则新建文件
                a.createNewFile();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {
            //FileOutputStream in=new FileOutputStream(a);
            FileWriter fw = new FileWriter(a, false); //在文件末尾追加形式写文件
            fw.write(ip);//写入字符串
            fw.flush();//刷新缓存
            fw.close();//关闭输入流
        }catch(IOException e) {
            e.printStackTrace();
        }


        try {
            FileReader fw = new FileReader(a);
            BufferedReader bf = new BufferedReader(fw);
            String str=null;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                System.out.println(str);//输出文件中的字符串
            }
            bf.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}