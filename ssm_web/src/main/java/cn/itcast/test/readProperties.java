package cn.itcast.test;

import java.io.*;
import java.util.Properties;

/**
 * Created by Administrator on 2020/6/16.
 */
public class readProperties {

    public static void main(String[] args) {
        String ip = readFileByLines("D:\\IPConfig.txt");
        System.out.println(ip);
        String filepath = readProperties.class.getClassLoader()
                .getResource("data.properties").getPath();
        System.out.println(filepath);
        file(filepath,"serverIP="+ip);
        System.out.println(readFileByLines(filepath));

    }

    public static void file(String fileName,String context){

        File a=new File(fileName); //新建文件
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
            fw.write(context);//写入字符串
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
