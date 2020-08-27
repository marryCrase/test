package cn.itcast.view;

import cn.itcast.dao.DataDao;
import cn.itcast.dao.QrcodeDao;
import cn.itcast.dao.UrlDao;
import cn.itcast.domain.Url;
import cn.itcast.util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cn.itcast.util.readProperties.readFileByLines;
import static javax.swing.JOptionPane.showMessageDialog;

public class DeleteView extends JDialog implements ActionListener {

    //读取文件内容并返回
    String ip = readFileByLines("D:\\IPConfig.txt");
    private ViewUtil frame = null;
    private JDialog dialog;
    private Connection conn = null;
    private DbUtil dbUtil = new DbUtil();
    private UrlDao urlDao = new UrlDao();
    private DataDao dataDao = new DataDao();
    private QrcodeDao qrcodeDao = new QrcodeDao();

    private String data[][] = new String[][]{};
    private String column[] = new String[]{"名称"};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane jsp = new JScrollPane(table);
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    private JButton btn1 = new JButton("删除");
    private JButton btn2 = new JButton("取消");

    private JTextField textField = new JTextField(20);
    private JButton btn3 = new JButton("搜索");

    private List row = new ArrayList<>();

    private JPanel jPanel = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();


    /*public static void main(String[] args) {
        new DeleteView();
    }*/

    public DeleteView(ViewUtil frame, String title, boolean modal) {
        super(frame,title,modal);
        this.frame = frame;
        //获取屏幕大小
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        //菜单栏大小
//        Insets scrInsets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

        dialog = new JDialog();
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        reJsp();
        jPanel.add(jsp);
        jPanel3.add(textField);
        jPanel3.add(btn3);
        jPanel4.add(btn1);
        jPanel4.add(btn2);
//        dialog.add(jPanel, BorderLayout.WEST);
        dialog.add(jsp, BorderLayout.CENTER);
        dialog.add(jPanel3, BorderLayout.NORTH);
        dialog.add(jPanel4, BorderLayout.SOUTH);

        table.setRowHeight(50);

        dialog.setSize(scrSize.width / 2, scrSize.height / 2);

//        label.setBorder(BorderFactory.createLineBorder(Color.cyan,1));
//        jPanel2.setBorder(BorderFactory.createLineBorder(Color.red,1));

//        label.setBounds(20,20,dialog.getWidth()/2,dialog.getHeight());

        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            TableModel tableModel = table.getModel();
            int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
            try {
                conn = dbUtil.getCon();
                if (selectRows > 0){
                    int[] selRowIndexs = table.getSelectedRows(); // 用户所选行的序列
                    List<String> names = new ArrayList<>();
                    for (int i = 0; i < selRowIndexs.length; i++) {
                        // 用tableModel.getValueAt(row, column)取单元格数据
                        String name = (String) tableModel.getValueAt(selRowIndexs[i], 0);
                        names.add(name);
                    }
                    boolean y = false;
                    for (int i = 0; i < names.size(); i++){
                        List<Url> urls = urlDao.findUrlByName(conn, names.get(i));
                        List<Url> urls2 = urlDao.seachQrcodeByOid(conn,urls.get(0).getId());
                        if (urls2.size()>0){
                            y=true;
                            break;
                        }
                    }
                    if (y){
                        int isDelete = JOptionPane.showConfirmDialog(null,"该目录包含子项，确定一起删除吗？");
                        if (isDelete == JOptionPane.YES_OPTION){
                            for (int i = 0; i < names.size(); i++){
                                List<Url> urls = urlDao.findUrlByName(conn, names.get(i));
                                List<Url> urls1 = urlDao.seachQrcodeByOid(conn,urls.get(0).getId());
                                for (int ii = 0; ii < urls1.size(); ii++){
                                    dataDao.delData(conn, urls1.get(ii).getId());
                                    qrcodeDao.delQRcode(conn,urls1.get(ii).getId());
                                    urlDao.delUrl(conn,urls1.get(ii).getId());
                                }
                                dataDao.delData(conn, urls.get(0).getId());
                                qrcodeDao.delQRcode(conn,urls.get(0).getId());
                                urlDao.delUrl(conn, names.get(i));
//                                model.removeRow(table.getSelectedRow());
                            }
                            showMessageDialog(null, "删除成功");
                        }
                    }else {
                        for (int i = 0; i < names.size(); i++){
                            List<Url> urls = urlDao.findUrlByName(conn, names.get(i));
                            dataDao.delData(conn, urls.get(0).getId());
                            qrcodeDao.delQRcode(conn,urls.get(0).getId());
                            urlDao.delUrl(conn, names.get(i));
//                                model.removeRow(table.getSelectedRow());
                        }
                        showMessageDialog(null, "删除成功");
                    }

                }else{
                    showMessageDialog(null,"请先选中行");
                    return;
                }


                List<Url> urls = urlDao.findAllUrl(conn);
                List<String> names = new ArrayList<>();

                for (Url url : urls){
                    names.add(url.getIphoto());
                }
                fillTable(names);
                try {
                    conn = dbUtil.getCon();
                    urls = urlDao.seachQrcodeByOid(conn,0);
                    names.clear();
                    for (Url url : urls){
                        List<Url> urls2 = urlDao.seachQrcodeByOid(conn,url.getId());
                        if (urls2.size()>0){
                            names.add("▷"+url.getIphoto());
                        }else {
                            names.add(url.getIphoto());
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
                frame.fillTable(names);


            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }

        } else if (e.getSource() == btn2) {
            dialog.dispose();
        } else if (e.getSource() == btn3) {//搜索
            List<String> names = new ArrayList<>();
            if (textField.getText().isEmpty()){
                try {
                    conn = dbUtil.getCon();
                    List<Url> urls = urlDao.findAllUrl(conn);
                    for (Url url : urls) {
                        names.add(url.getIphoto());
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
                    List<Url> urls = urlDao.seachQrcodeByName(conn,textField.getText());
                    for (Url url : urls) {
                        names.add(url.getIphoto());
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


//    刷新列表
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
        table.getTableHeader().setVisible(false);

    }
}
