package cn.itcast.util;

import cn.itcast.dao.UrlDao;
import cn.itcast.domain.Url;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IPhotoList extends JDialog implements ActionListener {

    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    JDialog jFrame;

    Object[] cum = {"名称", "所属"};
    String[][] photo;
    JTable jTable;
    JScrollPane jScrollPane;

    JButton btn1 = new JButton("确定");
    JButton btn2 = new JButton("取消");

    JPanel jPanel = new JPanel();
    String getname;
    String urlPath;
    private Connection conn = null;
    private DbUtil dbUtil = new DbUtil();
    private UrlDao urlDao = new UrlDao();

    public IPhotoList(JFrame jf) {
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame = new JDialog(jf);
        btn1.setActionCommand("ok");
        btn2.setActionCommand("back");
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
                photo[i][1] = String.valueOf(urls.get(i).getO_id());
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
        jFrame.add(jScrollPane);
        jFrame.add(jPanel, BorderLayout.SOUTH);

        jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
        jFrame.setLocationRelativeTo(null);
//            jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            getname = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();//获取选择框的第一列数据
            urlPath = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();//获取选择框的第二列数据
            jFrame.dispose();
//            new Demo1().newDemo1(getname, urlPath);

        } else if (e.getActionCommand().equals("back")) {
            jFrame.dispose();
        }
    }
}
