package cn.itcast.test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2020/6/15.
 */
public class test5 {

    public static void main(String[] args) {
       Set<String> sets =  getIpAddress();
        for (String s : sets){
            System.out.println(s);
        }
    }

    /**
     * 获取本机的IP地址（包括ipv4和ipv6）
     * <br>包含回环地址127.0.0.1和0:0:0:0:0:0:0:1
     */
    private static Set<String> getIpAddress() {
        Set<String> ipList = new HashSet<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                //排除虚拟接口和没有启动运行的接口
                if (netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && (ip instanceof Inet4Address || ip instanceof Inet6Address)) {
                            ipList.add(ip.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

}
