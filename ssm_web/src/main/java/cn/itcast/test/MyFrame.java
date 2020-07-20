package cn.itcast.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new MyFrame();
    }
    
    public MyFrame() {
        // TODO Auto-generated constructor stub
        
        Container container = getContentPane();
        container.setLayout(null);
        JLabel jl = new JLabel("这是一个JFrame窗体");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jl);
        JButton bl = new JButton("弹出对话框");
        bl.setBounds(10,10,100,50);
        bl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new MyJDialog(MyFrame.this).setVisible(true);
            }
        });
        container.add(bl);
        setBackground(Color.white);
        setVisible(true);
        setSize(200,120);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}