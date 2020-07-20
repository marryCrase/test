package cn.itcast.view;

import cn.itcast.dao.*;
import cn.itcast.domain.*;
import cn.itcast.test.readProperties;
import cn.itcast.util.DbUtil;
import cn.itcast.util.FtpUtils;
import cn.itcast.util.QRCodeUtil;
import org.springframework.context.ApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:29
 */
//启用配置文件
public class ViewUtil extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    //    private String serverIP = "192.168.1.3";
//    ApplicationContext aca = new ClassPathXmlApplicationContext("applicationContext.xml");
    private String serverIP;

    private String fileName = "view.jpg";

    private ApplicationContext ac;

    private Dimension scrSize;//获取屏幕大小
    private Insets scrInsets;//获取菜单栏大小

    private JFrame jFrame = new JFrame();
    private BgPanel framePanel;
    private BorderLayout borderLayout;
    private ImageIcon background;

    private BgPanel panelTop;
    private BgPanel panelBottom;
    private BgPanel panelLeft;
    private BgPanel panelRight;
    private BgPanel panelRightList;
    private BgPanel panelRightBom;
    private BgPanel panelCenter;
    private MyPanel myPanel = new MyPanel();//中间画图panel

    private JTextArea rightTip = new JTextArea();//右侧注释面板

    private JButton jb = new JButton(); //输入
    private JButton jb2 = new JButton();//输出
    private JButton jb3 = new JButton();//保存
    private JButton jb4 = new JButton();//读取
    private JButton jb5 = new JButton();//文字
    private JButton jb6 = new JButton();//图片
    private JButton jb7 = new JButton();//视频
    private JButton jb8 = new JButton();//音频
    private JButton jb9 = new JButton();//关闭
    private JButton jb10 = new JButton();//缩小放大
    private JButton jb11 = new JButton();//最小化
    private JButton jb12 = new JButton(); //上传文件
    private JButton jb13 = new JButton(); //二维码库

    private JLabel labelIP = new JLabel("服务器IP：" + serverIP, JLabel.CENTER);//IP地址
    private JLabel labelUrl = new JLabel("", JLabel.CENTER);//IP地址
    private JLabel labelPx = new JLabel("",JLabel.CENTER);//提示使用图片大小

    /**
     * scrollpane
     */
    private JLabel jLabel = new JLabel("项目名称", JLabel.CENTER);
    private String data[][] = new String[][]{};
    private String column[] = new String[]{""};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane jsp = new JScrollPane(table);
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();


    private int options = 1;//现在点击的是哪个按钮
    private int lastOptions = 1;//前一个点击的是哪个按钮

    int x = 0;//鼠标按下时的x坐标
    int y = 0;//鼠标按下时的y坐标
    int x1 = 0;//鼠标抬起时的x坐标
    int y1 = 0;//鼠标抬起时的x坐标
    String banner = null; //鼠标点下并拖动后暂时保存所有坐标

    /**
     * 集合数据
     */
    private JLabel centerLabel = new JLabel();//主图显示面板label
    private List<JLabel> jLabels = new ArrayList<JLabel>();//文字label框集合

    private List<String> list = new ArrayList();//鼠标点下并拖动后的所有坐标
    /*private List dataList = new ArrayList();//数据类型集合
    private List uriList = new ArrayList<>();//链接类型集合
    //文字
    private List<String> listSpan = new ArrayList<>();//文字集合
    private List videoList = new ArrayList<>();//视频集合
    private List audioList = new ArrayList<>();//音频集合
    private List photoList = new ArrayList<>();//图片集合

    private List textListUri = new ArrayList();//文本坐标集合
    private List photoListUri = new ArrayList();//图片坐标集合
    private List audioListUri = new ArrayList();//音频坐标集合
    private List videoListUri = new ArrayList();//视频坐标集合

    private List textUrl = new ArrayList();//文本链接集合
    private List photoUrl = new ArrayList();//图片链接集合*/

    private ImageIcon images;//输入图片
    private String path = null;//输入图片路径
    private String urlName = null;//主图名称
    private String pageName = null;//主网页名称
    private String annotation = null;//网页注释
    //输出弹框
    private FileDialog fdopen;
    //传输文件到远程服务器FTP
    FtpUtils ftp = new FtpUtils();
    //生成二维码
    QRCodeUtil qrCodeUtil = new QRCodeUtil();
    /**
     * Dialog选择框
     */
    private JDialog Jdlog1, Jdlog2, Jdlog3;//Jdlog1是文字弹框，Jdlog2是输出弹框,Jdlog3是提示框
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

    private int o_id = 0;
    //子级选择弹出框
    private static final long serialVersionUID = 1L;
    private MyDialog myDialog;//保存框选择父类

    private JDialog loadDialog;

    private Object[] cum = {"名称", "所属"};
    private String[][] photo;
    private JTable jTable;
    private JScrollPane jScrollPane;

    private JButton btn1 = new JButton("确定");
    private JButton btn2 = new JButton("取消");

    private JPanel jPanel = new JPanel();

    //数据处理
    private Connection conn = null;
    private DbUtil dbUtil = new DbUtil();
    private UrlDao urlDao = new UrlDao();
    private DataDao dataDao = new DataDao();
    private List<Data> listData = new ArrayList<>();//数据集合
    private List<Data> listType = new ArrayList<>();//类型集合
    private List<Data> listUrl = new ArrayList<>();//链接集合
    private List<Data> listCoord = new ArrayList<>();//坐标集合
    private List<Data> listText = new ArrayList<>();//文字集合
    private List<Data> listPhoto = new ArrayList<>();//图片集合
    private List<Data> listAudio = new ArrayList<>();//音频集合
    private List<Data> listVideo = new ArrayList<>();//视频集合
    private List<Data> listPath = new ArrayList<>();//路径集合

    private String QRname = ""; //二维码名称
    private QrcodeDao qrcodeDao = new QrcodeDao();

    /**
     * main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        new ViewUtil();
    }

    /**
     * 构造方法
     */
    public ViewUtil() {
        //读取文件内容并返回
        String ip = readFileByLines("D:\\IPConfig.txt");
        //获取路径
        String filepath = readProperties.class.getClassLoader()
                .getResource("data.properties").getPath();
        //写入文件内容
        file(filepath, "serverIP=" + ip);
        serverIP = ip;
        labelIP.setText("服务器IP：" + serverIP);
        //获取屏幕大小
        scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        //菜单栏大小
        scrInsets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

        //设置borderLayout布局默认间距为0
        borderLayout = (BorderLayout) getLayout();
        borderLayout.setHgap(0);//水平间距
        borderLayout.setVgap(0);//组件垂直间距

        /**
         * 初始化控件
         */
        //Image image=new ImageIcon("img/bg.png").getImage();
        framePanel = new BgPanel("img/bg.png");
        panelTop = new BgPanel("img/top.png");

        panelLeft = new BgPanel("img/left.png");
        panelCenter = new BgPanel("img/center.png");
        panelRight = new BgPanel("");
        panelRightList = new BgPanel("img/rightList.png");
        panelRightBom = new BgPanel("img/rightBom.png");
        panelBottom = new BgPanel("");

        //设置按钮监听
        jb.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb5.addActionListener(this);
        jb6.addActionListener(this);
        jb7.addActionListener(this);
        jb8.addActionListener(this);
        jb9.addActionListener(this);
        jb10.addActionListener(this);
        jb11.addActionListener(this);
        jb12.addActionListener(this);
        jb13.addActionListener(this);

        //设置按钮鼠标监听
        jb.addMouseListener(this);
        jb2.addMouseListener(this);
        jb3.addMouseListener(this);
        jb4.addMouseListener(this);
        jb5.addMouseListener(this);
        jb6.addMouseListener(this);
        jb7.addMouseListener(this);
        jb8.addMouseListener(this);
        jb9.addMouseListener(this);
        jb10.addMouseListener(this);
        jb11.addMouseListener(this);
        jb12.addMouseListener(this);
        jb13.addMouseListener(this);

        //设置控件边距
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        jb.setBorder(emptyBorder);
        jb2.setBorder(emptyBorder);
        jb3.setBorder(emptyBorder);
        jb4.setBorder(emptyBorder);
        jb5.setBorder(emptyBorder);
        jb6.setBorder(emptyBorder);
        jb7.setBorder(emptyBorder);
        jb8.setBorder(emptyBorder);
        jb9.setBorder(emptyBorder);
        jb10.setBorder(emptyBorder);
        jb11.setBorder(emptyBorder);
        jb12.setBorder(emptyBorder);
        jb13.setBorder(emptyBorder);

        Border center = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        myPanel.setBorder(center);
        //设置空背景
        myPanel.setBackground(null);
        panelBottom.setBackground(null);
        rightTip.setBackground(null);

        //设置控件透明
        jb.setOpaque(false);
        jb2.setOpaque(false);
        jb3.setOpaque(false);
        jb4.setOpaque(false);
        jb5.setOpaque(false);
        jb6.setOpaque(false);
        jb7.setOpaque(false);
        jb8.setOpaque(false);
        jb9.setOpaque(false);
        jb10.setOpaque(false);
        jb11.setOpaque(false);
        myPanel.setOpaque(false);
        labelIP.setOpaque(false);
        labelUrl.setOpaque(false);
        labelPx.setOpaque(false);
        panelBottom.setOpaque(false);
        rightTip.setOpaque(false);
        jb12.setOpaque(false);
        jb13.setOpaque(false);

        //设置按钮透明
        jb.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);
        jb4.setContentAreaFilled(false);
        jb5.setContentAreaFilled(false);
        jb6.setContentAreaFilled(false);
        jb7.setContentAreaFilled(false);
        jb8.setContentAreaFilled(false);
        jb9.setContentAreaFilled(false);
        jb10.setContentAreaFilled(false);
        jb11.setContentAreaFilled(false);
        jb12.setContentAreaFilled(false);
        jb13.setContentAreaFilled(false);

        //去掉按钮文字周围的焦点框
        jb.setFocusPainted(false);
        jb2.setFocusPainted(false);
        jb3.setFocusPainted(false);
        jb4.setFocusPainted(false);
        jb5.setFocusPainted(false);
        jb6.setFocusPainted(false);
        jb7.setFocusPainted(false);
        jb8.setFocusPainted(false);
        jb9.setFocusPainted(false);
        jb10.setFocusPainted(false);
        jb11.setFocusPainted(false);
        jb12.setFocusPainted(false);
        jb13.setFocusPainted(false);

        //设置布局
        framePanel.setLayout(null);
        panelTop.setLayout(null);
        panelRight.setLayout(borderLayout);
        panelLeft.setLayout(null);
        myPanel.setLayout(null);//设置myPanel为空布局
        panelCenter.setLayout(null);

        //添加控件
        jFrame.add(framePanel);
        framePanel.add(panelTop);
        framePanel.add(panelLeft);
        framePanel.add(panelCenter);
        framePanel.add(panelRight);
        framePanel.add(panelBottom);

        panelTop.add(jb);
        panelTop.add(jb2);
        panelTop.add(jb3);
        panelTop.add(jb4);
        panelTop.add(jb12);
        panelTop.add(jb13);

        /*panelTop.add(jb9);
        panelTop.add(jb10);
        panelTop.add(jb11);*/

        panelLeft.add(jb5);
        panelLeft.add(jb6);
        panelLeft.add(jb7);
        panelLeft.add(jb8);

        panelRight.add(panelRightList, BorderLayout.NORTH);
        panelRight.add(panelRightBom, BorderLayout.SOUTH);

        panelBottom.add(labelUrl);
        panelBottom.add(labelPx);

        panelRightList.add(rightTip);
        panelRightBom.add(labelIP);

        rightTip.setLineWrap(true);//设置文本域自动换行
        rightTip.setEnabled(false);
        rightTip.setFont(new Font("黑体", Font.BOLD, 18));
        rightTip.setAlignmentX(5);
        //设置控件位置大小
        jFrame.setSize(scrSize.width - scrInsets.left - scrInsets.right, scrSize.height - scrInsets.top - scrInsets.bottom);
        framePanel.setSize(jFrame.getWidth(), jFrame.getHeight());
        panelTop.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() / 10);
        panelLeft.setBounds(0, jFrame.getHeight() / 10, jFrame.getWidth() / 13, (jFrame.getHeight() - jFrame.getHeight() / 10 - jFrame.getHeight() / 15) / 2);
        panelCenter.setBounds(jFrame.getWidth() / 13 - 11, jFrame.getHeight() / 10, jFrame.getWidth() - jFrame.getWidth() / 7 - jFrame.getWidth() / 12 + 10, jFrame.getHeight() - jFrame.getHeight() / 10 - jFrame.getHeight() / 15);
        panelRight.setBounds(jFrame.getWidth() - jFrame.getWidth() / 7 - 25, jFrame.getHeight() / 10, jFrame.getWidth() / 7 + 23, jFrame.getHeight() - jFrame.getHeight() / 10 - jFrame.getHeight() / 15);
        panelBottom.setBounds(0, jFrame.getHeight() - jFrame.getHeight() / 15, jFrame.getWidth(), jFrame.getHeight() / 15);
        panelRightList.setPreferredSize(new Dimension(jFrame.getWidth() / 7 + 23, panelRight.getHeight() - panelRight.getHeight() / 10));
        panelRightBom.setPreferredSize(new Dimension(jFrame.getWidth() / 7 + 23, panelRight.getHeight() / 10));

        rightTip.setBounds(15, panelRight.getHeight() - panelRight.getHeight() / 10 * 3 - 50 + jLabel.getHeight(), jFrame.getWidth() / 7 + 10, panelRight.getHeight() / 5);
        myPanel.setBounds(9, 8, panelCenter.getWidth() - 18, panelCenter.getHeight() - 16);
        labelIP.setBorder(BorderFactory.createEmptyBorder(panelRight.getHeight() / 20, 10, 10, 10));

        labelPx.setText("请使用小于" + myPanel.getWidth() + "×" + myPanel.getHeight() + "的图片");
        labelPx.setForeground(Color.gray);

        /*Border bor = BorderFactory.createLineBorder(Color.ORANGE,1);
        rightTip.setBorder(bor);*/

        //设置按钮背景
        jb.setIcon(bgImg("img/input.jpg"));
        jb2.setIcon(bgImg("img/output.jpg"));
        jb3.setIcon(bgImg("img/save.jpg"));
        jb4.setIcon(bgImg("img/load.jpg"));
        jb12.setIcon(bgImg("img/upload.png"));
        jb13.setIcon(bgImg("img/qrcode.png"));
        //设置按钮位置大小
        jb.setBounds(jFrame.getWidth() / 15, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());
        jb2.setBounds(jFrame.getWidth() / 15 + jFrame.getWidth() / 13, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());
        jb3.setBounds(jFrame.getWidth() / 15 + jFrame.getWidth() / 13 * 2, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());
        jb4.setBounds(jFrame.getWidth() / 15 + jFrame.getWidth() / 13 * 3, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());
        jb12.setBounds(jFrame.getWidth() / 15 + jFrame.getWidth() / 13 * 4, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());
        jb13.setBounds(jFrame.getWidth() / 15 + jFrame.getWidth() / 13 * 5, (panelTop.getHeight() - background.getIconHeight()) / 2, background.getIconWidth(), background.getIconHeight());

        //设置按钮背景
        jb5.setIcon(bgImg("img/hText.jpg"));
        jb6.setIcon(bgImg("img/photo.jpg"));
        jb7.setIcon(bgImg("img/music.jpg"));
        jb8.setIcon(bgImg("img/video.jpg"));
        //设置按钮位置大小
        jb5.setBounds((panelLeft.getWidth() - background.getIconWidth()) / 2, panelLeft.getWidth() / 3, background.getIconWidth(), background.getIconHeight());
        jb6.setBounds((panelLeft.getWidth() - background.getIconWidth()) / 2, panelLeft.getWidth() / 3 + panelLeft.getWidth() / 5 * 2, background.getIconWidth(), background.getIconHeight());
        jb7.setBounds((panelLeft.getWidth() - background.getIconWidth()) / 2, panelLeft.getWidth() / 3 * 2 + panelLeft.getWidth() / 5 * 7 / 3, background.getIconWidth(), background.getIconHeight());
        jb8.setBounds((panelLeft.getWidth() - background.getIconWidth()) / 2, panelLeft.getWidth() + panelLeft.getWidth() / 2, background.getIconWidth(), background.getIconHeight());

        //设置按钮背景
        jb9.setIcon(bgImg("img/close.jpg"));
        jb10.setIcon(bgImg("img/big.jpg"));
        jb11.setIcon(bgImg("img/smole.jpg"));

        //设置按钮位置大小

        jb9.setBounds(jFrame.getWidth() - background.getIconWidth(), 0, background.getIconWidth(), background.getIconHeight());
        jb10.setBounds(jFrame.getWidth() - background.getIconWidth() * 2 - 10, 0, background.getIconWidth(), background.getIconHeight());
        jb11.setBounds(jFrame.getWidth() - background.getIconWidth() * 3 - 20, 0, background.getIconWidth(), background.getIconHeight());

        /**
         * JScrollpane
         */
        panelCenter.add(myPanel);
        Border border = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        panelRightList.add(jLabel);
        jLabel.setFont(new Font("黑体", Font.BOLD, 20));
        jLabel.setBorder(border);
        jLabel.setBounds(0, 0, panelRight.getWidth(), 40);
        reJsp();
//        ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                rightTip.setText("");
            }
        });
        table.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = 0, col = 0;
                int x = table.rowAtPoint(point);
                int y = table.columnAtPoint(point);
                if (x != row || y != col) {
                    row = x;
                    col = y;
                }
                Object tip = table.getValueAt(row, col);

                if (tip != null) {
                    try {
                        conn = dbUtil.getCon();
                        List<Url> urls = urlDao.findUrlByName(conn, tip.toString());
                        table.setToolTipText(urls.get(0).getAnnotation());
                        rightTip.setText(urls.get(0).getAnnotation());
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

        /**
         * dialog   ---------
         */
        Jdlog1 = new JDialog(jFrame, "文本", false);
        Jdlog1.setLayout(null);
        jdlog1Label1 = new JLabel("文字内容:");
        jdlog1Label2 = new JLabel("文本链接:");

        Jdlog1Btn1 = new JButton("确定");
        Jdlog1Btn2 = new JButton("重置");


        Jdlog1Area = new JTextArea();
        Jdlog1Field = new JTextField(20);
        JScrollPane scroll = new JScrollPane(Jdlog1Area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//垂直滚动
        Jdlog1Field.setText("");

        Jdlog1Btn1.setActionCommand("textBtn1");
        Jdlog1Btn2.setActionCommand("textBtn2");


        jdlog1Label1.setBounds(50, 50, 70, 50);
        jdlog1Label2.setBounds(50, 260, 70, 50);
        scroll.setBounds(130, 50, 300, 180);
        Jdlog1Field.setBounds(130, 260, 300, 50);

        Jdlog1Btn1.setBounds(100, 360, 100, 50);
        Jdlog1Btn2.setBounds(300, 360, 100, 50);

        Jdlog1Area.setLineWrap(true);        //激活自动换行功能
        Jdlog1Area.setWrapStyleWord(true);            // 激活断行不断字功能

        Jdlog1.add(jdlog1Label1);
        Jdlog1.add(jdlog1Label2);
        Jdlog1.add(scroll);
        Jdlog1.add(Jdlog1Field);
        Jdlog1.add(Jdlog1Btn1);
        Jdlog1.add(Jdlog1Btn2);
        Jdlog1.setSize(500, 500);

        Jdlog1.setResizable(false);
        Jdlog1.setLocationRelativeTo(jFrame);
        Jdlog1Btn1.addActionListener(this);
        Jdlog1Btn2.addActionListener(this);

        //Jdlog2
        Jdlog2btn1 = new JButton("确定");
        Jdlog2btn2 = new JButton("重置");

        Jdlog2btn1Out = new JButton("确定");
        Jdlog2btn2Out = new JButton("重置");

        Jdlog2Field1 = new JTextField(20);
        Jdlog2Field2 = new JTextField(20);

        Jdlog2btn1.addActionListener(this);
        Jdlog2btn2.addActionListener(this);

        Jdlog2btn1Out.addActionListener(this);
        Jdlog2btn2Out.addActionListener(this);
        /**
         * dialog END   ---------
         */

        // 设置窗口为无标题（无菜单栏）
//        jFrame.setUndecorated(true);
//        com.sun.awt.AWTUtilities.setWindowOpaque(jFrame, false);// 设置窗体透明
        //com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.3f);//或者也可以自定义设置整个窗体的透明度(上一行代码等价于第二个参数设置为0f的情况)
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.setVisible(true);


        /**
         * 测试
         */
//        myPanel.setBorder(BorderFactory.createLineBorder(Color.magenta,1));

        /**
         * myPanel所有监听事件
         */
        myPanel.addMouseListener(new MouseAdapter() {  //匿名内部类，鼠标事件

            /*public void mouseClicked(MouseEvent e) {   //鼠标完成点击事件
                if (e.getButton() == MouseEvent.BUTTON1) { //e.getButton就会返回点鼠标的那个键，左键还是右健，3代表右键
                    int x = e.getX();  //得到鼠标x坐标
                    int y = e.getY();  //得到鼠标y坐标

                    if (list.size() > 0) {
                        for (int i = 0; i <= list.size(); i++) {

                            String s1 = String.valueOf(list.get(i));
                            System.out.println(s1);

                            x2 = s1.substring(0, s1.indexOf(","));
                            y2 = s1.substring(s1.indexOf(",") + 1, s1.indexOf(":"));
                            x3 = s1.substring(s1.indexOf(":") + 1, s1.lastIndexOf(","));
                            y3 = s1.substring(s1.lastIndexOf(",") + 1, s1.length());

                            if (x > Integer.parseInt(x2) && x < Integer.parseInt(x3) && y > Integer.parseInt(y2) && y < Integer.parseInt(y3)) {
                                System.out.println("在范围" + x2 + "," + y2 + ":" + x3 + "," + y3);

                                Desktop desktop = Desktop.getDesktop();
                                URI uri = null;
                                try {
                                    uri = new URI("www.baidu.com");
                                    desktop.browse(uri);
                                } catch (URISyntaxException e1) {
                                    e1.printStackTrace();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                            }
                        }
                    }

                    System.out.println("鼠标当前点击位置的坐标是" + x + "," + y);
                }
            }*/

            public void mousePressed(MouseEvent e) {   //鼠标完成点击事件
                if (e.getButton() == MouseEvent.BUTTON1) { //e.getButton就会返回点鼠标的那个键，左键还是右健，3代表右键
                    x = e.getX();  //得到按下时鼠标x坐标
                    y = e.getY();  //得到按下时鼠标y坐标
                }
            }

            public void mouseReleased(MouseEvent e) {   //鼠标完成点击事件
                if (e.getButton() == MouseEvent.BUTTON1) { //e.getButton就会返回点鼠标的那个键，左键还是右健，3代表右键
                    x1 = e.getX();  //得到抬起时鼠标x坐标
                    y1 = e.getY();  //得到抬起时鼠标y坐标
                    if (x == x1 && y == y1) {

                    } else {
                        banner = x + "," + y + ":" + x1 + "," + y1; //得到鼠标按下并抬起时的起始和终止的坐标（鼠标滑动的方框左上角和右下角的坐标）
//                        System.out.println(banner);
                        list.add(banner);//添加操作记录为坐标集合
//                    System.out.println(list.size());

                        switch (options) {
                            case 1:
                                Jdlog1.setVisible(true);
                                break;
                            case 2:
                                Data data = new Data();
                                open();
//                                dataList.add("@PHOTO:");
//                                photoListUri.add(banner);//图片坐标
//                                photoList.add(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());//图片路径
                                String inputValue2 = showInputDialog("请输入跳转地址", "");
                                if (inputValue2.equals("") || inputValue2 == null) {
//                                    uriList.add("");//所有链接
//                                    photoUrl.add("");//图片链接
                                    data.setUrl("");
                                } else {
//                                    uriList.add("http://"+inputValue2+".html");//所有链接
//                                    photoUrl.add("http://"+inputValue2+".html");//图片链接
                                    data.setUrl("http://" + inputValue2 + ".html");
                                }
                                data.setType("@PHOTO:");
                                data.setCoord(banner);
                                data.setText("");
                                data.setPath(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());
                                listData.add(data);
                                break;
                            case 3:
//                                uriList.add(null);
                                open();
//                                dataList.add("@AUDIO:");
//                                audioListUri.add(banner);
//                                audioList.add(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());//音频路径集合
                                Data data2 = new Data();
                                data2.setType("@AUDIO:");
                                data2.setUrl("");
                                data2.setCoord(banner);
                                data2.setText("");
                                data2.setPath(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());
                                listData.add(data2);
                                break;
                            case 4:
//                                uriList.add(null);
                                open();
//                                dataList.add("@VIDEO:");
//                                videoListUri.add(banner);
//                                videoList.add(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());//视频路径集合
                                Data data3 = new Data();
                                data3.setType("@VIDEO:");
                                data3.setUrl("");
                                data3.setText("");
                                data3.setCoord(banner);
                                data3.setPath(fdopen.getDirectory().replace("\\", "/") + fdopen.getFile());
                                listData.add(data3);
                                break;
                        }
                        //展示图片
                        images = new ImageIcon(path);
                        //缩放图片
//                        images.setImage(images.getImage().getScaledInstance(myPanel.getWidth(), myPanel.getHeight(), Image.SCALE_DEFAULT));
                        centerLabel.setIcon(images);
                        myPanel.add(centerLabel, new Integer(Integer.MIN_VALUE));
                        centerLabel.setBounds(0, 0, images.getIconWidth(), images.getIconHeight());
//                        myPanel.setBounds(9, 8, panelCenter.getWidth() - 18, panelCenter.getHeight() - 16);
//                        myPanel.setBounds((myPanel.getWidth()-centerLabel.getWidth())/2,(myPanel.getHeight()-centerLabel.getHeight())/2,centerLabel.getWidth(),centerLabel.getHeight());
                        myPanel.rePaint(0, 0, 0, 0, list);
                    }
                }
            }
        });
        myPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                myPanel.rePaint(x, y, e.getX(), e.getY(), list);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                /*if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String s1 = String.valueOf(list.get(i));

                        int x = Integer.parseInt(s1.substring(0, s1.indexOf(",")));
                        int y = Integer.parseInt(s1.substring(s1.indexOf(",") + 1, s1.indexOf(":")));
                        int xx = Integer.parseInt(s1.substring(s1.indexOf(":") + 1, s1.lastIndexOf(",")));
                        int yy = Integer.parseInt(s1.substring(s1.lastIndexOf(",") + 1, s1.length()));

                        if (e.getX() >= x && e.getX() <= xx && e.getY() >= y && e.getY() <= yy) {
                            if (uriList.size()>i){
                                labelUrl.setText("链接:" + uriList.get(i));
                            }
                            break;
                        } else {
                            labelUrl.setText("");
                        }
                    }
                } else {
                    labelUrl.setText("");
                }*/
                if (listData.size() > 0) {
                    for (Data data : listData) {
                        String s1 = String.valueOf(data.getCoord());
                        int x = Integer.parseInt(s1.substring(0, s1.indexOf(",")));
                        int y = Integer.parseInt(s1.substring(s1.indexOf(",") + 1, s1.indexOf(":")));
                        int xx = Integer.parseInt(s1.substring(s1.indexOf(":") + 1, s1.lastIndexOf(",")));
                        int yy = Integer.parseInt(s1.substring(s1.lastIndexOf(",") + 1, s1.length()));

                        if (e.getX() >= x && e.getX() <= xx && e.getY() >= y && e.getY() <= yy) {
                            if (data.getType().equals("@TEXT:") || data.getType().equals("@PHOTO:"))
                                labelUrl.setText("链接:" + data.getUrl());
                                labelPx.setVisible(false);
                            break;
                        } else {
                            labelUrl.setText("");
                            labelPx.setVisible(true);
                        }
                    }
                } else {
                    labelUrl.setText("");
                    labelPx.setVisible(true);
                }
            }
        });
    }

    /**
     * 设置背景图片
     */
    public ImageIcon bgImg(String path) {
        //给JLabel设置背景图片
        URL url = ViewUtil.class.getClassLoader().getResource(path);

        background = new ImageIcon(url);//test为当前的类，jpanel_pci.jpg自动添加到当前项目的包中
        background.setImage(
                background.getImage().
                        //图片剪裁为15,6
                                getScaledInstance(background.getIconWidth(), background.getIconHeight(), Image.SCALE_DEFAULT));
        return background;
    }

    /**
     * 点击事件监听
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //头部菜单按钮
        if (e.getSource() == jb) {
            /*JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(this);//显示打开的文件对话框
            File f = jf.getSelectedFile();//使用文件类获取选择器选择的文件*/
            FileDialog fd = new FileDialog(jFrame, "文件", FileDialog.LOAD);
            FilenameFilter ff = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith("jpg")) {
                        return true;
                    }
                    return false;
                }
            };
            fd.setFile("*.JPG;*.png;*.gif");
            fd.setFilenameFilter(ff);
            fd.setLocationRelativeTo(null);
            fd.setVisible(true);
            System.out.println(fd.getDirectory() + fd.getFile());
            //如果路径是空的，则不写入文本框
            if (!fd.getDirectory().equals("") || !fd.getFile().equals("")) {
                pageName = null;
                urlName = null;
                path = null;
                list.clear();
                /*uriList.clear();
                dataList.clear();
                textListUri.clear();
                listSpan.clear();
                photoListUri.clear();
                photoList.clear();
                audioListUri.clear();
                audioList.clear();
                videoListUri.clear();
                videoList.clear();*/
                listData.clear();
                listText.clear();
                listPhoto.clear();
                listAudio.clear();
                listVideo.clear();
                listCoord.clear();
                listType.clear();
                listUrl.clear();
                listPath.clear();
                x = 0;
                y = 0;
                x1 = 0;
                y1 = 0;
                banner = "0,0:0,0";
                for (int i = 0; i < jLabels.size(); i++) {
                    jLabels.get(i).setBounds(0, 0, 0, 0);
                }
                jLabels.clear();
                myPanel.rePaint(0, 0, 0, 0, list);
                String s = fd.getDirectory() + fd.getFile();//返回路径名
                path = s.replace("\\", "/");
            } else {
                showMessageDialog(null, "没输入内容");
            }

        } else if (e.getSource() == jb2) {



            //判断主图坐标是否为空
            if (path != null) {
                StringBuffer sb = new StringBuffer();//地图热区
                StringBuffer js = new StringBuffer();//热区js
                StringBuffer span = new StringBuffer();//文字span
                StringBuffer classSpan = new StringBuffer();//span文字的js
                StringBuffer visibleAbles = new StringBuffer();//文字的长按js
                StringBuffer videos = new StringBuffer();//视频video
                StringBuffer classVideos = new StringBuffer();//视频video的CSS样式
                StringBuffer videosJS = new StringBuffer();//视频video的JS样式

                StringBuffer audios = new StringBuffer();//音频audio
                StringBuffer classAudios = new StringBuffer();//音频audio的CSS样式
                StringBuffer audiosJS = new StringBuffer();//音频audio的JS样式
                //判断坐标集合是否为空
                if (listData.size() > 0) {
                    //"\t\t\t<area shape=\"rect\" coords=\""+xy+x1+","+y1+"\" href=\""+uriList.get(uriList.size()-1)+"\" />\n"
                    for (Data data : listData){
                        if (data.getType().equals("@TEXT:")){
                            listText.add(data);
                        }else if (data.getType().equals("@PHOTO:")){
                            listPhoto.add(data);
                        }else if (data.getType().equals("@AUDIO:")){
                            listAudio.add(data);
                        }else if (data.getType().equals("@VIDEO:")){
                            listVideo.add(data);
                        }
                    }
                    for (int i = 0; i < listText.size(); i++) {
                        //"+list.get(i).replace(":",",")+"
                        String s = "\t\t\t<area id=\"11" + i + "\" shape=\"rect\" coords=\"0,0,200,200\" href=\"" + listText.get(i).getUrl() + "\" />\n";
                        sb.append(s);

                        //循环集合数据获取坐标后赋值给js代码
                        String zuobiao = listText.get(i).getCoord();
                        int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                        int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                        int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                        int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                        double picx1 = (double) x / images.getIconWidth();
                        double picy1 = (double) y / images.getIconHeight();
                        double picx2 = (double) xx / images.getIconWidth();
                        double picy2 = (double) yy / images.getIconHeight();
                        String jspinjie = "\t            var x" + i + " = parseInt(picw*" + picx1 + ");          //计算出坐标与总长度的商，当做参数\\n\" +\n" +
                                "\t            var y" + i + " = parseInt(pich*" + picy1 + ");\n" +
                                "\t            var xx" + i + " = parseInt(picw*" + picx2 + ");\n" +
                                "\t            var yy" + i + " = parseInt(pich*" + picy2 + ");\n" +
                                "\t           document.getElementById('span" + i + "').style.top=obj.top+y" + i + "+\"px\";\n" +
                                "\t           document.getElementById('span" + i + "').style.left=obj.left+x" + i + "+\"px\";\n" +
                                "\t           document.getElementById('span" + i + "').style.width=xx" + i + "-x" + i + "+\"px\";\n" +
                                "\t           document.getElementById('span" + i + "').style.height=yy" + i + "-y" + i + "+\"px\";\n" +
                                "\t           document.getElementById('11" + i + "').setAttribute(\"coords\",'\"'+x" + i + "+','+y" + i + "+','+xx" + i + "+','+yy" + i + "+'\"');\n" +
                                "\t           document.getElementById('span" + i + "').style.overflow=\"hidden\";\n" +
                                "document.getElementById(\"span"+i+"\").style.lineHeight=document.getElementById(\"span"+i+"\").offsetHeight+'px';\n";
                        js.append(jspinjie);

                        String sClassSpan = "#span" + i + "{\n" +
                                "\t\t\t\tmargin: auto;\n" +
                                "\t\t\t\tposition: absolute;\n" +
                                "\t\t\t\ttext-align: center;\n" +
                                "\t\t\t\tline-height: 100px;\n" +
                                "\t\t\t\ttop:auto;\n" +
                                "\t\t\t\tleft: auto;\n" +
                                "\t\t\t\twidth: auto;\n" +
                                "\t\t\t\theight: auto;\n" +
                                "\t\t\t}\n";
                        classSpan.append(sClassSpan);
//                        System.out.println(textUrl.get(i));
                        String sSpan = "<a href=\"" + listText.get(i).getUrl() + "\"><span id=\"span" + i + "\" onmousedown=\"holdDown" + i + "()\" onmouseup=\"holdUp" + i + "()\">" + listText.get(i).getText() + "</span></a>";
                        span.append(sSpan);

                        String visibleAble = "//申明全局变量\n" +
                                "    var timeStart, timeEnd, time,visibleAble" + i + "=false;\n" +
                                "\n" +
                                "    //获取此刻时间\n" +
                                "    function getTimeNow() {\n" +
                                "        var now = new Date();\n" +
                                "        return now.getTime();\n" +
                                "    }\n" +
                                "\n" +
                                "    //鼠标按下时触发\n" +
                                "    function holdDown" + i + "() {\n" +
                                "        //获取鼠标按下时的时间\n" +
                                "        timeStart = getTimeNow();\n" +
                                "\n" +
                                "        //setInterval会每100毫秒执行一次，也就是每100毫秒获取一次时间\n" +
                                "        time = setInterval(function () {\n" +
                                "            timeEnd = getTimeNow();\n" +
                                "\n" +
                                "            //如果此时检测到的时间与第一次获取的时间差有1000毫秒\n" +
                                "            if (timeEnd - timeStart > 500) {\n" +
                                "                //便不再继续重复此函数 （clearInterval取消周期性执行）\n" +
                                "                clearInterval(time);\n" +
                                "                //字体变红\n" +
                                "                if(visibleAble" + i + "){\n" +
                                "                \tdocument.getElementById(\"span" + i + "\").style.overflow=\"hidden\";\n" +
                                "                \tvisibleAble" + i + "=false;\n" +
                                "                }else{\n" +
                                "                \tdocument.getElementById(\"span" + i + "\").style.overflow=\"visible\";\n" +
                                "                \tvisibleAble" + i + "=true;\n" +
                                "                }\n" +
                                "                \n" +
                                "            }\n" +
                                "        }, 100);\n" +
                                "    }\n" +
                                "    function holdUp" + i + "() {\n" +
                                "        //如果按下时间不到1000毫秒便弹起，\n" +
                                "        clearInterval(time);\n" +
                                "    }";
                        visibleAbles.append(visibleAble);
                    }
                    for (int i = 0; i < listVideo.size(); i++) {
                        //循环集合数据获取坐标后赋值给js代码
                        String zuobiao = listVideo.get(i).getCoord();
                        int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                        int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                        int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                        int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                        double picx1 = (double) x / images.getIconWidth();
                        double picy1 = (double) y / images.getIconHeight();
                        double picx2 = (double) xx / images.getIconWidth();
                        double picy2 = (double) yy / images.getIconHeight();
                        String videoclass = ".video" + i + "{\n" +
                                "\t\tmargin: auto;\n" +
                                "\t    position: absolute;\n" +
                                "\t    background-size: contain;\n" +
                                "\t    top: 0px;\n" +
                                "\t    left: 0px;\n" +
                                "\t    width: 0px;" +
                                "    }";
                        classVideos.append(videoclass);
                        ftp.uploadFile("", listVideo.get(i).getPath().substring(listVideo.get(i).getPath().lastIndexOf("/") + 1, listVideo.get(i).getPath().length()),listVideo.get(i).getPath());
                        String video = "<video id=\"video" + i + "\" class=\"video" + i + "\" src=\"./" + listVideo.get(i).getPath().substring(listVideo.get(i).getPath().lastIndexOf("/") + 1, listVideo.get(i).getPath().length()) + "\" controls=\"controls\"></video>";
                        videos.append(video);
                        String videoJs = "\t            var vx" + i + " = parseInt(picw*" + picx1 + ");          //计算出坐标与总长度的商，当做参数\\n\" +\n" +
                                "\t            var vy" + i + " = parseInt(pich*" + picy1 + ");\n" +
                                "\t            var vxx" + i + " = parseInt(picw*" + picx2 + ");\n" +
                                "\t            var vyy" + i + " = parseInt(pich*" + picy2 + ");\n" +
                                "\t           document.getElementById('video" + i + "').style.top=obj.top+vy" + i + "+\"px\";\n" +
                                "\t           document.getElementById('video" + i + "').style.left=obj.left+vx" + i + "+\"px\";\n" +
                                "\t           document.getElementById('video" + i + "').style.width=vxx" + i + "-vx" + i + "+\"px\";\n";
                        videosJS.append(videoJs);
                    }
                    for (int i = 0; i < listAudio.size(); i++) {
                        //循环集合数据获取坐标后赋值给js代码
                        String zuobiao = listAudio.get(i).getCoord();
                        int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                        int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                        int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                        int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                        double picx1 = (double) x / images.getIconWidth();
                        double picy1 = (double) y / images.getIconHeight();
                        double picx2 = (double) xx / images.getIconWidth();
                        double picy2 = (double) yy / images.getIconHeight();
                        String audioclass = ".audio" + i + "{\n" +
                                "\t\tmargin: auto;\n" +
                                "\t    position: absolute;\n" +
                                "\t    background-size: contain;\n" +
                                "\t    top: 0px;\n" +
                                "\t    left: 0px;\n" +
                                "\t    width: 0px;" +
                                "    }";
                        classAudios.append(audioclass);
                        ftp.uploadFile("", listAudio.get(i).getPath().substring(listAudio.get(i).getPath().lastIndexOf("/") + 1, listAudio.get(i).getPath().length()), listAudio.get(i).getPath());
                        String audio = "<audio id=\"audio" + i + "\" class=\"audio" + i + "\" src=\"./" + listAudio.get(i).getPath().substring(listAudio.get(i).getPath().lastIndexOf("/") + 1, listAudio.get(i).getPath().length()) + "\" controls=\"controls\"></audio>";
                        audios.append(audio);
                        String audioJs = "\t            var ax" + i + " = parseInt(picw*" + picx1 + ");          //计算出坐标与总长度的商，当做参数\\n\" +\n" +
                                "\t            var ay" + i + " = parseInt(pich*" + picy1 + ");\n" +
                                "\t            var axx" + i + " = parseInt(picw*" + picx2 + ");\n" +
                                "\t            var ayy" + i + " = parseInt(pich*" + picy2 + ");\n" +
                                "\t           document.getElementById('audio" + i + "').style.top=obj.top+ay" + i + "+\"px\";\n" +
                                "\t           document.getElementById('audio" + i + "').style.left=obj.left+ax" + i + "+\"px\";\n" +
                                "\t           document.getElementById('audio" + i + "').style.width=axx" + i + "-ax" + i + "+\"px\";\n";
                        audiosJS.append(audioJs);
                    }
                    for (int i = 0; i < listPhoto.size(); i++) {
                        //循环集合数据获取坐标后赋值给js代码
                        String zuobiao = listPhoto.get(i).getCoord();
                        int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                        int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                        int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                        int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                        double picx1 = (double) x / images.getIconWidth();
                        double picy1 = (double) y / images.getIconHeight();
                        double picx2 = (double) xx / images.getIconWidth();
                        double picy2 = (double) yy / images.getIconHeight();
                        String audioclass = ".photo" + i + "{\n" +
                                "\t\tmargin: auto;\n" +
                                "\t    position: absolute;\n" +
                                "\t    background-size: contain;\n" +
                                "\t    top: 0px;\n" +
                                "\t    left: 0px;\n" +
                                "\t    width: 0px;" +
                                "    }";
                        classAudios.append(audioclass);
                        ftp.uploadFile("", listPhoto.get(i).getPath().substring(listPhoto.get(i).getPath().lastIndexOf("/") + 1, listPhoto.get(i).getPath().length()), listPhoto.get(i).getPath());
                        String audio = "<a href=\"" + listPhoto.get(i).getUrl() + "\"><img id=\"photo" + i + "\" class=\"photo" + i + "\" src=\"./" + listPhoto.get(i).getPath().substring(listPhoto.get(i).getPath().lastIndexOf("/") + 1, listPhoto.get(i).getPath().length()) + "\" controls=\"controls\"></img></a>";
                        audios.append(audio);
                        String audioJs = "\t            var px" + i + " = parseInt(picw*" + picx1 + ");          //计算出坐标与总长度的商，当做参数\\n\" +\n" +
                                "\t            var py" + i + " = parseInt(pich*" + picy1 + ");\n" +
                                "\t            var pxx" + i + " = parseInt(picw*" + picx2 + ");\n" +
                                "\t            var pyy" + i + " = parseInt(pich*" + picy2 + ");\n" +
                                "\t           document.getElementById('photo" + i + "').style.top=obj.top+py" + i + "+\"px\";\n" +
                                "\t           document.getElementById('photo" + i + "').style.left=obj.left+px" + i + "+\"px\";\n" +
                                "\t           document.getElementById('photo" + i + "').style.width=pxx" + i + "-px" + i + "+\"px\";\n" +
                                "\t           document.getElementById('photo" + i + "').style.height=pyy" + i + "-py" + i + "+\"px\";\n";
                        audiosJS.append(audioJs);
                    }
                    String imgpath = path.substring(path.lastIndexOf("/") + 1, path.length());
                    try {
                        conn = dbUtil.getCon();
                        List<Url> urls = urlDao.findUrlByName(conn, urlName);
                        if (urls.size()<=0){
                            JOptionPane.showMessageDialog(null,"请先保存数据");
                            return;
                        }
                        String str = "<!DOCTYPE html>\n" +
                                "<html>\n" +
                                " <head>\n" +
                                "  <meta charset=\"utf-8\">\n" +
                                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" />\n" +
                                "  <title></title>\n" +
                                "  <style type=\"text/css\">\n" +
                                "   .imgh{\n" +
                                "    margin: auto;\n" +
                                "    position: absolute;\n" +
                                "    background-size: contain;\n" +
                                "    top:0;\n" +
                                "    left: 0;\n" +
                                "    right: 0;\n" +
                                "    bottom: 0;\n" +
                                "    max-width: 100%;//图片宽度大小\n" +
                                "    max-height: 100%;//图片高度大小" +
                                "    width:auto;\n" +
                                "    height: auto;\n" +
                                "   }\n" +
                                classSpan + "\n" +
                                classVideos + "\n" +
                                classAudios + "\n" +
                                "  </style>\n" +
                                " </head>\n" +
                                "  <body onLoad=\"displayDate()\">\n" +
                                "<a href=\"http://" + serverIP + ":8001/fb?id=" + urls.get(0).getId() + "\">给点建议</a>" +
                                "   \t\t<img id=\"banner1\" ondblclick=\"displayDate()\" usemap=\"#banner\" class=\"imgh\" src=\"" + "./" + imgpath + "\" />\n" +
                                videos + "\n" +
                                audios + "\n" +
                                span + "\n" +
                                "   \t\t<map name=\"banner\">\n" +
                                sb +
                                "\t\t</map>\n" +
                                "<script>\n" +
                                "\t\t\t\n" +
                                "\t\t\tfunction displayDate(){\n" +
                                "\t\t\t\tvar picw = document.getElementById(\"banner1\").width;\n" +
                                "\t\t\t\tvar pich = document.getElementById(\"banner1\").height;\n" +
                                "var box = document.getElementById('banner1');\n" +
                                "var obj = getRect(box);\n" +
                                js + "\n" +
                                videosJS + "\n" +
                                audiosJS + "\n" +
                                "\t\t\t}\n" +
                                "\tfunction getRect(elements) {\n" +
                                "hengshuping(elements);\n" +
                                "\tvar rect = elements.getBoundingClientRect();\n" +
                                "\tw = rect.width || rect.right - rect.left;\n" +
                                "\th = rect.height || rect.bottom - rect.top;\n" +
                                "\tww = document.documentElement.clientWidth; //浏览器可见宽\n" +
                                "\thh = document.documentElement.clientHeight; //浏览器可见高\n" +
                                "\treturn { // 兼容ie多出的两个px\n" +
                                "\ttop: Math.floor(rect.top), // 元素顶边到浏览器底边距离\n" +
                                "\tbottom: Math.floor(hh - rect.bottom), // 元素底边到浏览器底边距离\n" +
                                "\tleft: Math.floor(rect.left), // 元素左边到浏览器底边距离\n" +
                                "\tright: Math.floor(ww - rect.right) // 元素底右到浏览器底边距离\n" +
                                "\t};\n" +
                                "\t};\n" +
                                "window.addEventListener(\"onorientationchange\" in window ? \"orientationchange\" : \"resize\", displayDate, false);\n" +
                                "            function hengshuping(elements) {\n" +
                                "            \t\n" +
                                "                        var shu=window.localStorage.getItem('name')\n" +
                                "                if (window.orientation == 0 || window.orientation == 180) {\n" +
                                "                \n" +
                                "                        \n" +
                                "                        if(shu=='a'){\n" +
                                "                                window.location.reload();\n" +
                                "                                window.localStorage.setItem('name','b');\n" +
                                "                        }\n" +
                                "                        orientation = 'portrait';\n" +
                                "                        return false;\n" +
                                "                    }else if (window.orientation == 90 || window.orientation == -90) {\n" +
                                "                        if(shu=='b'){\n" +
                                "                                window.location.reload();\n" +
                                "                                window.localStorage.setItem('name','a');\n" +
                                "                        }\n" +
                                "                       \n" +
                                "                        elements.style.top=elements.height+50+\"px\";\n" +
                                "                        orientation = 'landscape';\n" +
                                "                        return false;\n" +
                                "                    }\n" +
                                "              }\n" +
                                visibleAbles +
                                "\t\t</script>\n" +
                                "</body>\n" +
                                "</html>";

                        writeLocalStrTwo(str, "D:\\tupian.html");

                        //输入二维码名称并保存
                        QRname = JOptionPane.showInputDialog(this,"请输入二维码名称");
                        if (QRname.isEmpty()){
                            showMessageDialog(null,"二维码名称不能为空");
                            return;
                        }
                        Qrcode qrcode = new Qrcode();
                        qrcode.setName(QRname);
                        qrcode.setUid(urls.get(0).getId());
                        qrcodeDao.saveQrcode(conn,qrcode);

                    } catch (Exception ee) {
                        ee.printStackTrace();
                    } finally {
                        try {
                            conn.close();
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                    }

                    if (urlName != null) {
                        String Cpath = path.replace("/", "\\");
                        String p = Cpath.substring(Cpath.lastIndexOf("\\") + 1, Cpath.length());
                        System.out.println(p + "," + Cpath);
                        ftp.uploadFile("", p, Cpath);
                        ftp.uploadFile("", pageName + ".html", "D:\\tupian.html");
                        fileName = urlName + ".jpg";
//                        erweima();
                        //  QRcode 二维码生成测试
                        qrCodeUtil.QRCodeCreate("http://" + serverIP + "/" + pageName + ".html", fileName, 15, null);
                        ImageIcon icon = new ImageIcon(fileName);
                        showMessageDialog(null, null, "生成的二维码", 0, icon);
                        deleteFile(fileName);//删除本地原二维码文件
                    } else {
                        if (pageName == null || urlName == null) {
                            Jdlog2 = new JDialog(jFrame, "请输入");
                            Jdlog2.setLayout(null);
                            JLabel jLabel = new JLabel("输入原名字为替换,输入新名字为创建");

                            JLabel jLabel1 = new JLabel("主图名称:");
                            JLabel jLabel2 = new JLabel("网页注释:");

                            jLabel.setBounds(150, 30, 300, 30);
                            jLabel1.setBounds(50, 80, 70, 50);
                            jLabel2.setBounds(50, 150, 70, 50);
                            Jdlog2Field1.setBounds(130, 80, 300, 50);
                            Jdlog2Field2.setBounds(130, 150, 300, 50);

                            Jdlog2btn1Out.setBounds(100, 250, 100, 50);
                            Jdlog2btn2Out.setBounds(300, 250, 100, 50);

                            Jdlog2.add(jLabel);
                            Jdlog2.add(jLabel1);
                            Jdlog2.add(jLabel2);
                            Jdlog2.add(Jdlog2Field1);
                            Jdlog2.add(Jdlog2Field2);
                            Jdlog2.add(Jdlog2btn1Out);
                            Jdlog2.add(Jdlog2btn2Out);

                            Jdlog2.setSize(500, 350);

                            Jdlog2Field1.setText(urlName);
                            pageName = urlName;
                            if (urlName != null) {
                                try {
                                    conn = dbUtil.getCon();
                                    List<Url> urls = urlDao.findUrlByName(conn, urlName);
                                    Jdlog2Field2.setText(urls.get(0).getAnnotation());
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

                            Jdlog2.setResizable(false);
                            Jdlog2.setLocationRelativeTo(this);
                            Jdlog2.setVisible(true);
                        }
                    }
                } else {
                    showMessageDialog(null, "内容不能为空");
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "请先选择图片");
            }
        } else if (e.getSource() == jb3) {
            save();
        } else if (e.getSource() == jb4) {
            load();
        }else if (e.getSource()== jb12){//上传文件
            FileDialog fd = new FileDialog(jFrame, "文件", FileDialog.LOAD);
            FilenameFilter ff = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith("jpg")) {
                        return true;
                    }
                    return false;
                }
            };
            fd.setFilenameFilter(ff);
            fd.setLocationRelativeTo(null);
            fd.setVisible(true);
            //上传文件
            boolean b = ftp.uploadFile("", fd.getFile(), fd.getDirectory()+fd.getFile());
            if (b){
                showMessageDialog(null,"上传成功");
            }else{
                showMessageDialog(null,"上传失败");
            }
        }else if (e.getSource() == jb13){//二维码窗口
            new QrcodeView(jFrame,"二维码列表",true);
//            QrcodeView qrcodeView =
//            qrcodeView.setVisible(true);
        }else if (e.getSource() == jb9) {//关闭
            jFrame.dispose();
        } else if (e.getSource() == jb10) {//放大缩小
//            Toolkit.getDefaultToolkit().getScreenSize()  屏幕大小
            jFrame.setSize(scrSize.width - scrInsets.left - scrInsets.right, scrSize.height - scrInsets.top - scrInsets.bottom);
            if (jFrame.getWidth() == scrSize.width - scrInsets.left - scrInsets.right) {
                jFrame.setSize(jFrame.getWidth() / 2, jFrame.getHeight() / 2);
                jFrame.setLocation(0, 0);
                jFrame.setVisible(true);
            }
        } else if (e.getSource() == jb11) {//最小化
            jFrame.setExtendedState(JFrame.ICONIFIED);
        }
        //左侧工具按钮
        else if (e.getSource() == jb5) {
            option();//判断上一个点击的哪个添加框  并消除颜色
            options = 1;
            lastOptions = 1;
            //设置按钮背景
            jb5.setIcon(bgImg("img/hText.jpg"));
        } else if (e.getSource() == jb6) {
            option();//判断上一个点击的哪个添加框  并消除颜色
            options = 2;
            lastOptions = 2;
            jb6.setIcon(bgImg("img/hPhoto.jpg"));
        } else if (e.getSource() == jb7) {
            option();//判断上一个点击的哪个添加框  并消除颜色
            options = 3;
            lastOptions = 3;
            jb7.setIcon(bgImg("img/hMusic.jpg"));
        } else if (e.getSource() == jb8) {
            option();//判断上一个点击的哪个添加框  并消除颜色
            options = 4;
            lastOptions = 4;
            jb8.setIcon(bgImg("img/hVideo.jpg"));
        } else if (e.getSource() == Jdlog1Btn1) {//输入文本确定按钮
            String inputValue = "http://" + Jdlog1Field.getText();// + ".html"
            if (Jdlog1Field.getText().equals("") || Jdlog1Field.getText() == null) {
                inputValue = "";
            }
//            uriList.add(inputValue);//类型链接集合
//            textUrl.add(inputValue);//文字链接链接
//            String s = JOptionPane.showInputDialog("请输入文字内容");
            String s = Jdlog1Area.getText();
//            listSpan.add(s);//文字内容链接
//            textListUri.add(banner);//文字坐标集合
            jLabels.add(new JLabel(s, JLabel.CENTER));
//            jLabels.get(jLabels.size() - 1).setText(s);
            jLabels.get(jLabels.size() - 1).setBounds(x, y, x1 - x, y1 - y);

            myPanel.add(jLabels.get(jLabels.size() - 1));

//            dataList.add("@TEXT:");
            Data data = new Data();
            data.setType("@TEXT:");
            data.setUrl(inputValue);
            data.setCoord(banner);
            data.setText(s);
            data.setPath("");
            listData.add(data);
            JOptionPane.showMessageDialog(Jdlog1, "添加成功");
            Jdlog1.dispose();
        } else if (e.getSource() == Jdlog1Btn2) {//输入文本重置按钮
            Jdlog1Area.setText("");
            Jdlog1Field.setText("");
        } else if (e.getSource() == Jdlog2btn1Out) {//输出弹出框确定按钮
            //|| Jdlog2Field2.getText().isEmpty()
            if (Jdlog2Field1.getText().isEmpty()) {
                showMessageDialog(null, "文本内容不能为空");
                return;
            }
            try {
                conn = dbUtil.getCon();
                List<Url> allUrl = urlDao.findAllUrl(conn);
                for (Url url : allUrl) {
                    if (url.getIphoto().equals(urlName)) {
                        showMessageDialog(Jdlog2, "名称重复，请重新输入");
                        return;
                    }
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
            urlName = Jdlog2Field1.getText();
            pageName = urlName;

            String Cpath = path.replace("/", "\\");
            String p = Cpath.substring(Cpath.lastIndexOf("\\") + 1, Cpath.length());
            System.out.println(p + "," + Cpath);
            ftp.uploadFile("", p, Cpath);
            ftp.uploadFile("", pageName + ".html", "D:\\tupian.html");
            fileName = urlName + ".jpg";
//            erweima();//保存二维码生成路径
            //QRcode 二维码生成测试
            qrCodeUtil.QRCodeCreate("http://" + serverIP + "/" + pageName + ".html", fileName, 15, null);
            ImageIcon icon = new ImageIcon(fileName);
            showMessageDialog(null, null, "生成的二维码", 0, icon);
            deleteFile(fileName);//删除本地原二维码文件
            Jdlog2.dispose();
        } else if (e.getSource() == Jdlog2btn2Out) {//输出弹出框重置按钮
            Jdlog2Field1.setText("");
            Jdlog2Field2.setText("");
        } else if (e.getSource() == Jdlog2btn1) {//保存弹出框确定按钮
            //保存   || Jdlog2Field2.getText().isEmpty()
            if (Jdlog2Field1.getText().isEmpty()) {
                showMessageDialog(null, "文本内容不能为空");
                return;
            }
            urlName = Jdlog2Field1.getText();
            pageName = urlName;
            annotation = Jdlog2Field2.getText();//获取注释

            try {
                conn = dbUtil.getCon();
                List<Url> allUrl = urlDao.findAllUrl(conn);
                for (Url url : allUrl) {
                    if (url.getIphoto().equals(urlName)) {
                        showMessageDialog(Jdlog2, "名称重复，请重新输入");
                        return;
                    }
                }
                Url url = new Url();
                url.setIphoto(urlName);
                url.setIphotoUrl(path);
                url.setO_id(o_id);
                url.setIhtml(pageName + ".html");
                url.setAnnotation(annotation);
                urlDao.saveUrl(conn, url);
                List<Url> urls = urlDao.findUrlByName(conn, urlName);
                //保存信息
                for (Data data : listData) {
                    data.setUid(urls.get(0).getId());
                    dataDao.saveData(conn, data);
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
            JOptionPane.showMessageDialog(null, "保存成功");
            Vector rowData = new Vector();
            rowData.add(urlName);
            model.insertRow(model.getRowCount(), rowData);
            Jdlog2.dispose();
        } else if (e.getSource() == Jdlog2btn2) {//保存弹出框重置按钮
            Jdlog2Field1.setText("");
            Jdlog2Field2.setText("");
        } else if (e.getSource() == btn1) {//加载弹出框确定按钮

            pageName = null;
            urlName = null;
            path = null;
            list.clear();
            /*uriList.clear();
            dataList.clear();
            textListUri.clear();
            listSpan.clear();
            photoListUri.clear();
            photoList.clear();
            audioListUri.clear();
            audioList.clear();
            videoListUri.clear();
            videoList.clear();*/
            listData.clear();
            listText.clear();
            listPhoto.clear();
            listAudio.clear();
            listVideo.clear();
            listCoord.clear();
            listType.clear();
            listUrl.clear();
            listPath.clear();
            x = 0;
            y = 0;
            x1 = 0;
            y1 = 0;
            banner = "0,0:0,0";
            for (int i = 0; i < jLabels.size(); i++) {
                jLabels.get(i).setBounds(0, 0, 0, 0);
            }
            jLabels.clear();
            myPanel.rePaint(0, 0, 0, 0, list);

            urlName = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();//获取选择框的第一列数据
            /*UrlServiceImpl urlService = (UrlServiceImpl) ac.getBean("urlService");
            List<Url> urls = urlService.findUrlByName(urlName);
            path = urls.get(0).getIphotoUrl();//获取选择框的路径
            loadDialog.dispose();*/

            try {
                conn = dbUtil.getCon();
                List<Url> urls = urlDao.findUrlByName(conn, urlName);
                if (urls.size() > 1) {
                    showMessageDialog(null, "数据重复,取第一条");
                }
                path = urls.get(0).getIphotoUrl();
                pageName = urlName;
                List<Data> datas = dataDao.findDataByUid(conn, urls.get(0).getId());
                for (Data data : datas) {
                    listData.add(data);
                }
                /*List<Route> routes = routeDao.findRouteByUid(conn, urls.get(0).getId());
                for (Route route : routes) {
                    dataList.add(route.getType());
                    if (route.getType().equals("@TEXT:")) {
                        textUrl.add(route.getUrl());
                    } else if (route.getType().equals("@PHOTO:")) {
                        photoUrl.add(route.getUrl());
                    }
                    uriList.add(route.getUrl());
                    list.add(route.getRoute());
                }

                List<Text> texts = textDao.findTextByUid(conn, urls.get(0).getId());
                for (Text text : texts) {
//                textUrl.add(text.getTextUrl());
                    textListUri.add(text.getTextListUrl());
                    listSpan.add(text.getText());
                }

                List<Photo> photos = photoDao.findPhotoByUid(conn, urls.get(0).getId());
                for (Photo photo : photos) {
//                photoUrl.add(photo.getPhotoUrl());
                    photoListUri.add(photo.getPhotoListUrl());
                    photoList.add(photo.getPhoto());
                }

                List<Audio> audios = audioDao.findAudioByUid(conn, urls.get(0).getId());
                for (Audio audio : audios) {
                    audioListUri.add(audio.getAudioUrl());
                    audioList.add(audio.getAudio());
                }

                List<Video> videos = videoDao.findVideoByUid(conn, urls.get(0).getId());
                for (Video video : videos) {
                    videoListUri.add(video.getVideoUrl());
                    videoList.add(video.getVideo());
                }*/
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }
            /*for (int i = 0; i < dataList.size(); i++) {
                System.out.println("加载坐标:" + list.size());
                String zuobiao = list.get(i);
                int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                if (!dataList.get(i).equals("@TEXT:")) {
                    if (dataList.size()>i){
                        jLabels.add(new JLabel(String.valueOf(dataList.get(i)), JLabel.CENTER));
                    }
                } else {
                    if (listSpan.size()>i){
                        jLabels.add(new JLabel(String.valueOf(listSpan.get(i)), JLabel.CENTER));
                    }
                }
                jLabels.get(jLabels.size() - 1).setBounds(x, y, xx - x, yy - y);
                myPanel.add(jLabels.get(jLabels.size() - 1));
            }*/
            for (Data data : listData) {
                String zuobiao = data.getCoord();
                int x = Integer.parseInt(zuobiao.substring(0, zuobiao.indexOf(",")));
                int y = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(",") + 1, zuobiao.indexOf(":")));
                int xx = Integer.parseInt(zuobiao.substring(zuobiao.indexOf(":") + 1, zuobiao.lastIndexOf(",")));
                int yy = Integer.parseInt(zuobiao.substring(zuobiao.lastIndexOf(",") + 1, zuobiao.length()));

                if (!data.getType().equals("@TEXT:")) {
                    jLabels.add(new JLabel(String.valueOf(data.getType()), JLabel.CENTER));
                } else {
                    jLabels.add(new JLabel(String.valueOf(data.getText()), JLabel.CENTER));
                }
                jLabels.get(jLabels.size() - 1).setBounds(x, y, xx - x, yy - y);
                myPanel.add(jLabels.get(jLabels.size() - 1));
                list.add(data.getCoord());
            }
            loadDialog.dispose();
        } else if (e.getSource() == btn2) {//加载弹出框取消按钮
            loadDialog.dispose();
        }

        //展示图片
        images = new ImageIcon(path);
        //缩放图片
//        images.setImage(images.getImage().getScaledInstance(myPanel.getWidth(), myPanel.getHeight(), Image.SCALE_DEFAULT));
        centerLabel.setIcon(images);
        myPanel.add(centerLabel, new Integer(Integer.MIN_VALUE));
        centerLabel.setBounds(0, 0, images.getIconWidth(), images.getIconHeight());
        myPanel.setBounds(9, 8, panelCenter.getWidth() - 18, panelCenter.getHeight() - 16);
        myPanel.setBounds((myPanel.getWidth() - centerLabel.getWidth()) / 2, (myPanel.getHeight() - centerLabel.getHeight()) / 2, centerLabel.getWidth(), centerLabel.getHeight());
        myPanel.rePaint(list);
    }

    /**
     * 当用户按下并松开鼠标按钮时发生. 用户在选择或双击图标的时候通常会点击鼠标按钮. 用户如果在松开鼠标之前移动鼠标,点击不会导致鼠标相应事件出现
     * 因为点击鼠标是按下鼠标和松开鼠标的结合, 在事件分配给 mouseClicked() 方法之前, mousePressed() 和 mouseReleased() 方法已同时被调用
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {//当用户按下鼠标按钮时发生.

    }

    @Override
    public void mouseReleased(MouseEvent e) {//当用户松开鼠标按钮时发生.

    }

    @Override
    public void mouseEntered(MouseEvent e) {//当鼠标离开当前组件并进入你所监听的组件时激活事件.
        if (e.getSource() == jb) {
            //设置按钮背景
            jb.setIcon(bgImg("img/hInput.jpg"));
        } else if (e.getSource() == jb2) {
            jb2.setIcon(bgImg("img/hOutput.jpg"));
        } else if (e.getSource() == jb3) {
            jb3.setIcon(bgImg("img/hSave.jpg"));
        } else if (e.getSource() == jb4) {
            jb4.setIcon(bgImg("img/hLoad.jpg"));
        }else if (e.getSource() == jb12){
            jb12.setIcon(bgImg("img/hupload.png"));
        }else if (e.getSource() == jb13){
            jb13.setIcon(bgImg("img/hqrcode.png"));
        }else if (e.getSource() == jb9) {
            jb9.setIcon(bgImg("img/hClose.jpg"));
        } else if (e.getSource() == jb10) {
            jb10.setIcon(bgImg("img/hBig.jpg"));
        } else if (e.getSource() == jb11) {
            jb11.setIcon(bgImg("img/hSmole.jpg"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {//当鼠标离开你所监听的组件时发生.
        if (e.getSource() == jb) {
            //设置按钮背景
            jb.setIcon(bgImg("img/input.jpg"));
        } else if (e.getSource() == jb2) {
            jb2.setIcon(bgImg("img/output.jpg"));
        } else if (e.getSource() == jb3) {
            jb3.setIcon(bgImg("img/save.jpg"));
        } else if (e.getSource() == jb4) {
            jb4.setIcon(bgImg("img/load.jpg"));
        }else if (e.getSource() == jb12){
            jb12.setIcon(bgImg("img/upload.png"));
        }else if (e.getSource() == jb13){
            jb13.setIcon(bgImg("img/qrcode.png"));
        }else if (e.getSource() == jb9) {
            jb9.setIcon(bgImg("img/close.jpg"));
        } else if (e.getSource() == jb10) {
            jb10.setIcon(bgImg("img/big.jpg"));
        } else if (e.getSource() == jb11) {
            jb11.setIcon(bgImg("img/smole.jpg"));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //判断上一个点击的哪个添加框  并消除颜色
    public void option() {
        switch (lastOptions) {
            case 1:
                jb5.setIcon(bgImg("img/text.jpg"));
                break;
            case 2:
                jb6.setIcon(bgImg("img/photo.jpg"));
                break;
            case 3:
                jb7.setIcon(bgImg("img/music.jpg"));
                break;
            case 4:
                jb8.setIcon(bgImg("img/video.jpg"));
                break;
        }
    }

    //保存生成的html文件到本地
    public void writeLocalStrTwo(String str, String path) {
        try {
            if (list.size() == 0) {
                showMessageDialog(this, "请先选择图片", "提示", WARNING_MESSAGE);
            } else {
                File file = new File(path);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                if (str != null && !"".equals(str)) {
                    FileOutputStream fos = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(str);
                    osw.flush();
                    osw.close();
                    System.out.println("执行完毕!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws IOException
     */
    public static boolean deleteFile1(String pathname) {
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    //二维码保存路径
    public void erweima() {
        try {
            fdopen = new FileDialog(jFrame, "保存", FileDialog.SAVE);
            fdopen.setVisible(true);
            BufferedReader in = null;
            in = new BufferedReader(new FileReader(fdopen.getDirectory() + fdopen.getFile()));

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            fdopen = new FileDialog(jFrame, "打开", FileDialog.LOAD);
            fdopen.setVisible(true);
            BufferedReader in = null;
            in = new BufferedReader(new FileReader(fdopen.getDirectory() + fdopen.getFile()));

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if (path != null) {
            myDialog = new MyDialog(jFrame, "选择所属", true);
            myDialog.setLocationRelativeTo(this);
            myDialog.setVisible(true);
            if (myDialog.getMessage() == 1) {
                o_id = myDialog.getO_id();
            }
            Jdlog2 = new JDialog(jFrame, "请输入");
            Jdlog2.setLayout(null);
            JLabel jLabel = new JLabel("输入原名字为替换,输入新名字为创建");

            JLabel jLabel1 = new JLabel("主图名称:");
            JLabel jLabel2 = new JLabel("网页注释:");


            jLabel.setBounds(150, 30, 300, 30);
            jLabel1.setBounds(50, 80, 70, 50);
            jLabel2.setBounds(50, 150, 70, 50);
            Jdlog2Field1.setBounds(130, 80, 300, 50);
            Jdlog2Field2.setBounds(130, 150, 300, 50);

            Jdlog2btn1.setBounds(100, 250, 100, 50);
            Jdlog2btn2.setBounds(300, 250, 100, 50);

            Jdlog2.add(jLabel);
            Jdlog2.add(jLabel1);
            Jdlog2.add(jLabel2);
            Jdlog2.add(Jdlog2Field1);
            Jdlog2.add(Jdlog2Field2);
            Jdlog2.add(Jdlog2btn1);
            Jdlog2.add(Jdlog2btn2);

            Jdlog2.setSize(500, 350);

            Jdlog2Field1.setText(urlName);
            pageName = urlName;
            if (urlName != null) {
                try {
                    conn = dbUtil.getCon();
                    List<Url> urls = urlDao.findUrlByName(conn, urlName);
                    Jdlog2Field2.setText(urls.get(0).getAnnotation());
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

            Jdlog2.setResizable(false);
            Jdlog2.setLocationRelativeTo(this);
            Jdlog2.setVisible(true);
        } else {
            //            JOptionPane.showMessageDialog(jFrame,"主图是空的");
            Jdlog3 = new JDialog(jFrame, "提示", false);
            JLabel jLabel = new JLabel("主图是空的", JLabel.CENTER);
            jLabel.setSize(200, 100);
            Jdlog3.setSize(jLabel.getSize());
            Jdlog3.add(jLabel);
            Jdlog3.setResizable(false);
            Jdlog3.setLocationRelativeTo(this);
            Jdlog3.setVisible(true);


        }
    }

    //读取后显示数据
    public void load() {
//        urlName = JOptionPane.showInputDialog(null, "请输入主图名称");
        if (path == null) {
            readAndLoad();
        } else {
            int ii = JOptionPane.showConfirmDialog(null, "确定要替换内容吗？");
            if (ii == 0) {
                pageName = null;
                urlName = null;
                path = null;
                list.clear();
                /*uriList.clear();
                dataList.clear();
                textListUri.clear();
                listSpan.clear();
                photoListUri.clear();
                photoList.clear();
                audioListUri.clear();
                audioList.clear();
                videoListUri.clear();
                videoList.clear();*/
                listData.clear();
                listText.clear();
                listPhoto.clear();
                listAudio.clear();
                listVideo.clear();
                listCoord.clear();
                listType.clear();
                listUrl.clear();
                listPath.clear();
                for (int i = 0; i < jLabels.size(); i++) {
                    jLabels.get(i).setBounds(0, 0, 0, 0);
                }
                jLabels.clear();
                //调用  读取文档并load  方法
                readAndLoad();
            }
        }
    }

    //读取文档并load
    public void readAndLoad() {
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        loadDialog = new JDialog(jFrame);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        jPanel.add(btn1);
        jPanel.add(btn2);
        try {
            conn = dbUtil.getCon();
            List<Url> urls = urlDao.findAllUrl(conn);
            photo = new String[urls.size()][2];
            for (int i = 0; i < urls.size(); i++) {
                photo[i][0] = urls.get(i).getIphoto();
                if (urls.get(i).getO_id() != 0) {
                    List<Url> urlLs = urlDao.findUrlById(conn, urls.get(i).getO_id());
                    photo[i][1] = urlLs.get(0).getIphoto();
                } else {
                    photo[i][1] = "无";
                }
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
        jTable = new JTable(photo, cum) {
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };
        jScrollPane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        loadDialog.add(jScrollPane);
        loadDialog.add(jPanel, BorderLayout.SOUTH);

        loadDialog.setSize(screenSize.width / 2, screenSize.height / 2);
        loadDialog.setLocationRelativeTo(null);
//            jFrame.setResizable(false);
        loadDialog.setVisible(true);

    }

    /**
     * 刷新右侧列表
     */
    public void reJsp() {
        //子级选择弹出框
        //右侧显示列表
        try {
            conn = dbUtil.getCon();
            List<Url> urls = urlDao.findAllUrl(conn);
            data = new String[urls.size()][1];
            for (int i = 0; i < urls.size(); i++) {
                data[i][0] = urls.get(i).getIphoto();
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


        model = new DefaultTableModel(data, column);
        table = new JTable(model);
        table.updateUI();//刷新table
        jsp.setViewportView(table);//刷新jsp
        table.setRowHeight(40);
//        table.setShowGrid(false);//去除间隔线
//        table.setShowVerticalLines(false);//隐藏左右间隔线
//        table.setShowHorizontalLines(false);//隐藏上下间隔线
//        table.setFont(new Font("黑体",0,14));
        renderer.setHorizontalAlignment(JLabel.CENTER);//内容剧中显示
        panelRightList.setLayout(null);

        panelRightList.add(jsp);

//        jsp.setBounds(9, jLabel.getHeight(), jFrame.getWidth() / 7-14, panelRight.getHeight() - panelRight.getHeight() / 10 - 50);
        jsp.setBounds(9, jLabel.getHeight(), jFrame.getWidth() / 7 + 14, panelRight.getHeight() - panelRight.getHeight() / 10 * 3 - 50);

        //分别设置水平和垂直滚动条自动出现
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //得到JScrollPane中的JScrollBar滚动块
        JScrollBar sBar = jsp.getVerticalScrollBar();
        //设置JScrollBar滚动块的位置在最后
        sBar.setValue(sBar.getMaximum());

        jsp.setOpaque(false);
        renderer.setOpaque(false);//render单元格的属性
        table.setEnabled(false);//不可点击

        //遍历表格中所有列，将其渲染器设置为renderer
        for (int i = 0; i < column.length; i++) {
            table.getColumn(column[i]).setCellRenderer(renderer);
        }
        //        renderer.setToolTipText("啊啊");//设置鼠标悬浮弹框
        table.setGridColor(new Color(238, 238, 238));//设置间隔线颜色
        jsp.setOpaque(false);
        jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
        table.getTableHeader().setVisible(false);

        //刷新滑动框内容
        jsp.getViewport().setOpaque(false);
        table.setOpaque(false);//将table设置为透明
        jsp.setOpaque(false);//将jsp根面板设置为透明
        jsp.getViewport().setOpaque(false);//将jsp的viewport设置为透明
    }


    public static void file(String fileName, String context) {

        File a = new File(fileName); //新建文件
        System.out.println(a.getPath());
        try {
            if (!a.exists()) {    //如果文件不存在则新建文件
                a.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //FileOutputStream in=new FileOutputStream(a);
            FileWriter fw = new FileWriter(a, false); //在文件末尾追加形式写文件
            fw.write(context);//写入字符串
            fw.flush();//刷新缓存
            fw.close();//关闭输入流
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fw = new FileReader(a);
            BufferedReader bf = new BufferedReader(fw);
            String str = null;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                System.out.println(str);//输出文件中的字符串
            }
            bf.close();
        } catch (IOException e) {
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
                if (tempString != null) {
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

/**
 * 画图panel
 */
class MyPanel extends JPanel {
    int x, y, x1, y1;
    java.util.List<String> list = new ArrayList<>();

    public MyPanel() {
//        this.setBackground(Color.CYAN);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.ORANGE);
        /*g.drawRect(, y+1, e.getX()-x+4, e.getY()-y+28);
        System.out.println("x:"+x+"y:"+ y+ "x:"+e.getX()+"y:"+ e.getY());*/
//        g.drawRect(x, y, x1 - x, y1 - y);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String s1 = String.valueOf(list.get(i));
                g.drawRect(x, y, x1 - x, y1 - y);
                int x4 = Integer.parseInt(s1.substring(0, s1.indexOf(",")));
                int y4 = Integer.parseInt(s1.substring(s1.indexOf(",") + 1, s1.indexOf(":")));
                int x5 = Integer.parseInt(s1.substring(s1.indexOf(":") + 1, s1.lastIndexOf(",")));
                int y5 = Integer.parseInt(s1.substring(s1.lastIndexOf(",") + 1, s1.length()));
                g.drawRect(x4, y4, x5 - x4, y5 - y4);
            }
        } else {
            g.drawRect(x, y, x1 - x, y1 - y);
        }
    }

    public void rePaint(int x, int y, int x1, int y1, List<String> list) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.list = list;
        repaint();
    }

    public void rePaint(List<String> list) {
        this.list = list;
        repaint();
    }
}

/**
 * 有背景图片的Panel类
 *
 * @author tntxia
 */
class BgPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -6352788025440244338L;

    private Image img = null;


    public BgPanel(String imgurl) {

        //添加背景图片
        URL imageUrl = ViewUtil.class.getClassLoader().getResource(imgurl);
        //ImageIcon icon = ImageIO.read(imageUrl);
        try {
            img = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

class MyDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;

    static final int YES = 1, NO = 0, CLOSE = -1;
    int message = 10;
    JButton yes, no;

    //访问数据库列表
    /*DbUtil dbUtil = new DbUtil();
    UrlDao urlDao = new UrlDao();
    Url url = new Url();*/

    Object[] cum = {"名称", "所属"};
    String[][] photo;
    JTable jTable;
    JScrollPane jScrollPane;
    JPanel jPanel = new JPanel();
    ApplicationContext ac;
    //返回值
    int o_id;

    private Connection conn = null;
    private DbUtil dbUtil = new DbUtil();
    private UrlDao urlDao = new UrlDao();

    public MyDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        yes = new JButton("确定");
        yes.addActionListener(this);
        no = new JButton("取消");
        no.addActionListener(this);
        jPanel.add(yes);
        jPanel.add(no);
        cont.add(jPanel, BorderLayout.SOUTH);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                message = CLOSE;
                setVisible(false);
            }
        });
        //访问数据库
        //加载配置文件
        try {
            conn = dbUtil.getCon();
            List<Url> urlList = urlDao.findAllUrl(conn);

            photo = new String[urlList.size()][2];
            for (int i = 0; i < urlList.size(); i++) {
                photo[i][0] = urlList.get(i).getIphoto();
                if (urlList.get(i).getO_id() != 0) {
                    List<Url> urls = urlDao.findUrlById(conn, urlList.get(i).getO_id());
                    photo[i][1] = urls.get(0).getIphoto();
                } else {
                    photo[i][1] = "无";
                }
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
        jTable = new JTable(photo, cum);
        jScrollPane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cont.add(jScrollPane, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {
            message = YES;
            String urlName = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();//读取你获取行号的某一列的值（也就是字段）
            try {
                conn = dbUtil.getCon();
                List<Url> urls = urlDao.findUrlByName(conn, urlName);
                o_id = urls.get(0).getId();
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }
//            System.out.println("Oid:" + o_id);
            setVisible(false);
        } else if (e.getSource() == no) {
            message = NO;
            setVisible(false);
        }
    }


    public int getMessage() {
        return message;
    }

    public int getO_id() {
        return o_id;
    }

}