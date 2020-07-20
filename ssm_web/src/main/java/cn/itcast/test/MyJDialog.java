package cn.itcast.test;

import javax.swing.*;
import java.awt.*;

public class MyJDialog extends JDialog{
    public MyJDialog(MyFrame frame) {
        // TODO Auto-generated constructor stub
        super(frame,"第一个JDialog窗体",true);
        Container container = getContentPane();
        container.add(new JLabel("这是一个对话框"));
        setBounds(100,100,200,120);
    }
}