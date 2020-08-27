package cn.itcast.view;

import cn.itcast.dao.QrcodeDao;
import cn.itcast.dao.UrlDao;
import cn.itcast.domain.Qrcode;
import cn.itcast.domain.Url;
import cn.itcast.util.DbUtil;
import cn.itcast.util.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cn.itcast.view.ViewUtil.deleteFile;
import static cn.itcast.view.ViewUtil.readFileByLines;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Administrator on 2020/7/17.
 */
public class QrcodeView extends JDialog implements ActionListener {

    //读取文件内容并返回
    String ip = readFileByLines("D:\\IPConfig.txt");

    private JDialog dialog;
    private Connection conn = null;
    private DbUtil dbUtil = new DbUtil();
    private UrlDao urlDao = new UrlDao();
    private QrcodeDao qrcodeDao = new QrcodeDao();

    private String data[][] = new String[][]{};
    private String column[] = new String[]{"二维码名称"};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane jsp = new JScrollPane(table);
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    private JButton btn1 = new JButton("打印选中");
    private JButton btn2 = new JButton("打印全部");
    private JButton btn4 = new JButton("导出选中");
    private JButton btn5 = new JButton("导出全部");

    private JTextField textField = new JTextField(20);
    private JButton btn3 = new JButton("搜索");

    private JPanel jPanel = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();

    private JLabel label = new JLabel("", JLabel.CENTER);

    private QRCodeUtil qrCodeUtil;

//    public static void main(String[] args) {
//        new QrcodeView();
//    }

    public QrcodeView(JFrame frame, String title, boolean modal) {
        super(frame,title,modal);

        //获取屏幕大小
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        //菜单栏大小
//        Insets scrInsets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

        dialog = new JDialog();
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);

        reJsp();
        jPanel.add(jsp);
        jPanel2.add(label);
        jPanel3.add(textField);
        jPanel3.add(btn3);
        jPanel4.add(btn1);
        jPanel4.add(btn2);
        jPanel4.add(btn4);
        jPanel4.add(btn5);
        dialog.add(jPanel, BorderLayout.WEST);
        dialog.add(jPanel2, BorderLayout.EAST);
        dialog.add(jPanel3, BorderLayout.NORTH);
        dialog.add(jPanel4, BorderLayout.SOUTH);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
                TableModel tableModel = table.getModel();
                if (selectRows == 1) {
                    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
                    String name = (String) tableModel.getValueAt(selectedRowIndex, 0);
                    try {
                        conn = dbUtil.getCon();
                        List<Qrcode> qrcodes = qrcodeDao.findQrcodeByName(conn, name);
                        List<Url> urls = urlDao.findUrlById(conn, qrcodes.get(0).getUid());
                        //  QRcode 二维码生成测试
                        qrCodeUtil.QRCodeCreate("http://" + ip + "/" + urls.get(0).getIhtml(), urls.get(0).getIphoto() + ".jpg", 15, null);
                        ImageIcon icon = new ImageIcon(urls.get(0).getIphoto() + ".jpg");
                        label.setPreferredSize(new Dimension(dialog.getWidth() / 2, jPanel.getHeight()));
                        label.setIcon(icon);
                        deleteFile(urls.get(0).getIphoto() + ".jpg");//删除本地原二维码文件

                    } catch (Exception ee) {
                        ee.printStackTrace();
                    } finally {
                        try {
                            conn.close();
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                    }
                }
            }
        });

        table.setRowHeight(50);

        dialog.setSize(scrSize.width / 2, scrSize.height / 2);

//        label.setBorder(BorderFactory.createLineBorder(Color.cyan,1));
//        jPanel2.setBorder(BorderFactory.createLineBorder(Color.red,1));

//        label.setBounds(20,20,dialog.getWidth()/2,dialog.getHeight());

        dialog.setLocationRelativeTo(frame);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {//打印选中
            int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
            TableModel tableModel = table.getModel();

            List<String> names = new ArrayList<>();

            if (selectRows > 1) {
                int[] selRowIndexs = table.getSelectedRows(); // 用户所选行的序列
                for (int i = 0; i < selRowIndexs.length; i++) {
                    // 用tableModel.getValueAt(row, column)取单元格数据
                    String cellValue = (String) tableModel.getValueAt(selRowIndexs[i], 0);
                    names.add(cellValue);
                }
            } else if (selectRows == 1) {
                int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
                String name = (String) tableModel.getValueAt(selectedRowIndex, 0);
                names.add(name);
            } else {
                showMessageDialog(null, "请先选中行");
            }
            ImageIcon icon;
            JFrame jf = new JFrame();
            JLabel jLabel = new JLabel("",JLabel.CENTER);
            JLabel jLabel2 = new JLabel("",JLabel.CENTER);
            if (names.size()>0){
                for (String name : names){
                    //  QRcode 二维码生成测试
                    qrCodeUtil.QRCodeCreate("http://" + ip + "/" + name+".html", name + ".jpg", 15, null);
                    icon = new ImageIcon(name + ".jpg");
                    jLabel.setIcon(icon);
                    jLabel2.setText(name);
                    jf.add(jLabel);
                    jf.add(jLabel2,BorderLayout.SOUTH);
                    jf.setBackground(Color.white);
                    jf.setSize(400,400);
                    jf.setUndecorated(true);
                    jf.setLocationRelativeTo(this);
                    jf.setVisible(true);
                    savePic(jf);
                    jf.dispose();
                    //打印
                    new PrintUtil().drawImage("saveScreen.jpg", 1);
                    System.out.println("打印二维码："+name+".jpg");
                    deleteFile("saveScreen.jpg");//删除本地原二维码文件
                }
            }
            showMessageDialog(null,"打印成功");

        } else if (e.getSource() == btn2) {//打印全部
            List<String> names = new ArrayList<>();
            try {
                conn = dbUtil.getCon();
                List<Qrcode> qrcodes = qrcodeDao.findAllQrcode(conn);
                for (Qrcode qrcode : qrcodes) {
                    names.add(qrcode.getName());
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }

            ImageIcon icon;
            JFrame jf = new JFrame();
            JLabel jLabel = new JLabel("",JLabel.CENTER);
            JLabel jLabel2 = new JLabel("",JLabel.CENTER);
            if (names.size()>0){
                for (String name : names){
                    //  QRcode 二维码生成测试
                    qrCodeUtil.QRCodeCreate("http://" + ip + "/" + name+".html", name + ".jpg", 15, null);
                    icon = new ImageIcon(name + ".jpg");
                    jLabel.setIcon(icon);
                    jLabel2.setText(name);
                    jf.add(jLabel);
                    jf.add(jLabel2,BorderLayout.SOUTH);
                    jf.setBackground(Color.white);
                    jf.setSize(400,400);
                    jf.setLocationRelativeTo(this);
                    jf.setVisible(true);
                    savePic(jf);
                    jf.dispose();
                    //打印
                    new PrintUtil().drawImage("saveScreen.jpg", 1);
                    System.out.println("打印二维码："+name+".jpg");
                    deleteFile("saveScreen.jpg");//删除本地原二维码文件
                }
            }
            showMessageDialog(null,"打印成功");
        } else if (e.getSource() == btn3) {//搜索
            List<String> names = new ArrayList<>();

            if (textField.getText().isEmpty()){
                try {
                    conn = dbUtil.getCon();
                    List<Qrcode> qrcodes = qrcodeDao.findAllQrcode(conn);
                    for (Qrcode qrcode : qrcodes) {
                        names.add(qrcode.getName());
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ee) {
                        ee.printStackTrace();
                    }
                }
            }else {
                try {
                    conn = dbUtil.getCon();
                    List<Qrcode> qrcodes = qrcodeDao.findQrcodeByName(conn,textField.getText());

                    for (Qrcode qrcode : qrcodes) {
                        names.add(qrcode.getName());
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ee) {
                        ee.printStackTrace();
                    }
                }
            }

            fillTable(names);
        }else if (e.getSource() == btn4){//导出选中图片
            String filePath = "";
//            java 弹出选择目录框（选择文件夹），获取选择的文件夹路径:
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnVal = fileChooser.showOpenDialog(fileChooser);

            if(returnVal == JFileChooser.APPROVE_OPTION){
                filePath= fileChooser.getSelectedFile().getAbsolutePath();
            }//这个就是你选择的文件夹的
//            System.out.println(fd.getDirectory() + fd.getFile());

            if (filePath.isEmpty()){
                showMessageDialog(null,"请选择路径");
                return;
            }

            int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
            TableModel tableModel = table.getModel();
//            System.out.println("序列："+selectRows);
//            System.out.println("序列："+tableModel.getValueAt(selectRows, 0));
            List<String> names = new ArrayList<>();

            if (selectRows > 1) {
                int[] selRowIndexs = table.getSelectedRows(); // 用户所选行的序列
                for (int i = 0; i < selRowIndexs.length; i++) {
                    // 用tableModel.getValueAt(row, column)取单元格数据
                    String cellValue = (String) tableModel.getValueAt(selRowIndexs[i], 0);
//                    System.out.println("序号"+selRowIndexs[i]);
                    names.add(cellValue);
                }
            } else if (selectRows == 1) {
                int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
                String name = (String) tableModel.getValueAt(selectedRowIndex, 0);
                names.add(name);
            } else {
                showMessageDialog(null, "请先选中行");
            }
            ImageIcon icon;
            JFrame jf = new JFrame();
            JLabel jLabel = new JLabel("",JLabel.CENTER);
            JLabel jLabel2 = new JLabel("",JLabel.CENTER);

            if (names.size()>0){
                for (String name : names){
                    try {
                        conn = dbUtil.getCon();
                        List<Qrcode> qrcodes = qrcodeDao.findQrcodeByName(conn,name);
                        List<Url> urls = urlDao.findUrlById(conn,qrcodes.get(0).getUid());

                    //  QRcode 二维码生成测试
                    qrCodeUtil.QRCodeCreate("http://" + ip + "/" + urls.get(0).getIhtml(), name + ".jpg", 15, null);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    } finally {
                        try {
                            conn.close();
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                    }
                    icon = new ImageIcon(name + ".jpg");
                    jLabel.setIcon(icon);
                    jLabel2.setText(name);
                    jf.add(jLabel);
                    jf.add(jLabel2,BorderLayout.SOUTH);
                    jf.setBackground(Color.white);
                    jf.setSize(400,400);
                    jf.setUndecorated(true);
                    jf.setLocationRelativeTo(this);
                    jf.setVisible(true);
                    savePic2(jf,filePath+"\\"+name+".jpg");
                    jf.dispose();
                }
            }
            showMessageDialog(null,"导出成功");


        }else if (e.getSource() == btn5){//导出所有图片
            String filePath = "";
//            java 弹出选择目录框（选择文件夹），获取选择的文件夹路径:
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnVal = fileChooser.showOpenDialog(fileChooser);

            if(returnVal == JFileChooser.APPROVE_OPTION){
                filePath= fileChooser.getSelectedFile().getAbsolutePath();
            }//这个就是你选择的文件夹的
//            System.out.println(fd.getDirectory() + fd.getFile());

            if (filePath.isEmpty()){
                showMessageDialog(null,"请选择路径");
                return;
            }

            List<String> names = new ArrayList<>();
            try {
                conn = dbUtil.getCon();
                List<Qrcode> qrcodes = qrcodeDao.findAllQrcode(conn);
                for (Qrcode qrcode : qrcodes) {
                    names.add(qrcode.getName());
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }

            ImageIcon icon;
            JFrame jf = new JFrame();
            JLabel jLabel = new JLabel("",JLabel.CENTER);
            JLabel jLabel2 = new JLabel("",JLabel.CENTER);
            if (names.size()>0){
                for (String name : names){
                    try {
                        conn = dbUtil.getCon();
                        List<Qrcode> qrcodes = qrcodeDao.findQrcodeByName(conn,name);
                        List<Url> urls = urlDao.findUrlById(conn,qrcodes.get(0).getUid());

                        //  QRcode 二维码生成测试
                        qrCodeUtil.QRCodeCreate("http://" + ip + "/" + urls.get(0).getIhtml() ,name + ".jpg", 15, null);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    } finally {
                        try {
                            conn.close();
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                    }
                    icon = new ImageIcon(name + ".jpg");
                    jLabel.setIcon(icon);
                    jLabel2.setText(name);
                    jf.add(jLabel);
                    jf.add(jLabel2,BorderLayout.SOUTH);
                    jf.setBackground(Color.white);
                    jf.setSize(400,400);
                    jf.setUndecorated(true);
                    jf.setLocationRelativeTo(this);
                    jf.setVisible(true);
                    savePic2(jf,filePath+"\\"+name+".jpg");
                    jf.dispose();
                }
            }
            showMessageDialog(null,"导出成功");
        }
    }

    //更新列表数据
    public void fillTable(List<String> members) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

// 填充数据
        for (String s : members) {
            String[] arr = new String[1];
            arr[0] = s;

// 添加数据到表格
            tableModel.addRow(arr);
        }

// 更新表格
        table.invalidate();
    }
    /**
     * 刷新列表
     */
    public void reJsp() {
        //子级选择弹出框
        //右侧显示列表
        try {
            conn = dbUtil.getCon();
            List<Qrcode> qrcodes = qrcodeDao.findAllQrcode(conn);

            data = new String[qrcodes.size()][1];
            for (int i = 0; i < qrcodes.size(); i++) {
                data[i][0] = qrcodes.get(i).getName();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }


        model = new DefaultTableModel(data, column){
            public boolean isCellEditable(int rowIndex, int ColIndex){
                return false;
            }
        };
        table = new JTable(model);
        table.updateUI();//刷新table
        jsp.setViewportView(table);//刷新jsp
        table.setRowHeight(40);
//        table.setShowGrid(false);//去除间隔线
//        table.setShowVerticalLines(false);//隐藏左右间隔线
//        table.setShowHorizontalLines(false);//隐藏上下间隔线
//        table.setFont(new Font("黑体",0,14));
        renderer.setHorizontalAlignment(JLabel.CENTER);//内容剧中显示

        //分别设置水平和垂直滚动条自动出现
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //得到JScrollPane中的JScrollBar滚动块
        JScrollBar sBar = jsp.getVerticalScrollBar();
        //设置JScrollBar滚动块的位置在最后
        sBar.setValue(sBar.getMaximum());


        //遍历表格中所有列，将其渲染器设置为renderer
        for (int i = 0; i < column.length; i++) {
            table.getColumn(column[i]).setCellRenderer(renderer);
        }
        table.setGridColor(new Color(238, 238, 238));//设置间隔线颜色
        jsp.setOpaque(false);
        jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
        table.getTableHeader().setVisible(false);

        //刷新滑动框内容
//        jsp.getViewport().setOpaque(false);

//        renderer.setOpaque(false);//render单元格的属性
//        table.setEnabled(false);//不可点击
//        table.setOpaque(false);//将table设置为透明
//        jsp.setOpaque(false);//将jsp根面板设置为透明
//        jsp.getViewport().setOpaque(false);//将jsp的viewport设置为透明
    }

    public void savePic(JFrame jf){
        //得到窗口内容面板
        Container content=jf.getContentPane();
        //创建缓冲图片对象
        BufferedImage img=new BufferedImage(
                jf.getWidth(),jf.getHeight(),BufferedImage.TYPE_INT_RGB);
        //得到图形对象
        Graphics2D g2d = img.createGraphics();
        //将窗口内容面板输出到图形对象中
        content.printAll(g2d);
        //保存为图片
        File f=new File("saveScreen.jpg");
        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //释放图形对象
        g2d.dispose();
    }
    public void savePic2(JFrame jf,String path){
        //得到窗口内容面板
        Container content=jf.getContentPane();
        //创建缓冲图片对象
        BufferedImage img=new BufferedImage(
                jf.getWidth(),jf.getHeight(),BufferedImage.TYPE_INT_RGB);
        //得到图形对象
        Graphics2D g2d = img.createGraphics();
        //将窗口内容面板输出到图形对象中
        content.printAll(g2d);
        //保存为图片
        File f=new File(path);
        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //释放图形对象
        g2d.dispose();
    }
}
