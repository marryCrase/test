package cn.itcast.test;

import java.io.*;
import java.io.FileWriter;

public class FileTest {
    public static void main(String[] args) {
        File a=new File("D:/javaz.txt"); //新建文件
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
            FileWriter fw = new FileWriter(a, true); //在文件末尾追加形式写文件
            fw.write("123456789");//写入字符串“我爱你”
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
