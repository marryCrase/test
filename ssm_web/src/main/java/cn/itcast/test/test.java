package cn.itcast.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame{

    JFrame jFrame = new JFrame();
    JButton b = new JButton("dian");
    JScrollPane scroll;
    private JDialog Jdlog1,Jdlog2;
    private JLabel jdlog1Label1;
    private JLabel jdlog1Label2;
    private JButton Jdlog1Btn1;
    private JButton Jdlog1Btn2;
    private JTextArea Jdlog1Area;
    private JTextField Jdlog1Field;

    private JButton Jdlog2btn1;
    private JButton Jdlog2btn2;
    private JTextField Jdlog2Field1;
    private JTextField Jdlog2Field2;

    private JButton Jdlog2btn1Out;
    private JButton Jdlog2btn2Out;
    public test() {

        /**
         * dialog   ---------
         */
        Jdlog1 = new JDialog(jFrame,"文本",false);
        Jdlog1.setLayout(null);
        jdlog1Label1 = new JLabel("文字内容:");
        jdlog1Label2 = new JLabel("文本链接:");

        Jdlog1Btn1 = new JButton("确定");
        Jdlog1Btn2 = new JButton("取消");


        Jdlog1Area = new JTextArea();
        Jdlog1Field = new JTextField(20);
        JScrollPane scroll=new JScrollPane(Jdlog1Area);
        scroll.getVerticalScrollBar().setUI(new ScrollUI());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//垂直滚动
        Jdlog1Field.setText("http://");

        Jdlog1Btn1.setActionCommand("textBtn1");
        Jdlog1Btn2.setActionCommand("textBtn2");


        jdlog1Label1.setBounds(50,50,70,50);
        jdlog1Label2.setBounds(50,260,70,50);
        scroll.setBounds(130,50,300,180);
        Jdlog1Field.setBounds(130,260,300,50);

        Jdlog1Btn1.setBounds(100,360,100,50);
        Jdlog1Btn2.setBounds(300,360,100,50);

        Jdlog1Area.setLineWrap(true);        //激活自动换行功能
        Jdlog1Area.setWrapStyleWord(true);            // 激活断行不断字功能

        Jdlog1.add(jdlog1Label1);
        Jdlog1.add(jdlog1Label2);
        Jdlog1.add(scroll);
        Jdlog1.add(Jdlog1Field);
        Jdlog1.add(Jdlog1Btn1);
        Jdlog1.add(Jdlog1Btn2);

        Jdlog1.setSize(500,500);

        Jdlog1.setResizable(false);
        Jdlog1.setLocationRelativeTo(jFrame);

        //Jdlog2
        Jdlog2btn1 = new JButton("确定");
        Jdlog2btn2 = new JButton("重置");

        Jdlog2btn1Out = new JButton("确定");
        Jdlog2btn2Out = new JButton("重置");

        Jdlog2Field1 = new JTextField(20);
        Jdlog2Field2 = new JTextField(20);

        Jdlog2btn1.setActionCommand("Jdlog2btn1");
        Jdlog2btn2.setActionCommand("Jdlog2btn2");


        Jdlog2btn1Out.setActionCommand("Jdlog2btn1Out");
        Jdlog2btn2Out.setActionCommand("Jdlog2btn2Out");

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jFrame.setSize(500,500);
        Jdlog1.setVisible(true);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }
}
