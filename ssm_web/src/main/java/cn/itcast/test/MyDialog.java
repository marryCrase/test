package cn.itcast.test;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.List;

class MyDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;

    static final int YES = 1,NO = 0,CLOSE = -1;
    int message = 10;
    JButton yes,no;

    Object[] cum = {""};
    String[][] photo;
    JTable jTable;
    JScrollPane jScrollPane;
    JPanel jPanel = new JPanel();

    //返回值
    int o_id;

    public MyDialog(){
        super();
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        yes = new JButton("Yse");
        yes.addActionListener(this);
        no = new JButton("No");
        no.addActionListener(this);
        jPanel.add(yes);
        jPanel.add(no);
        cont.setLayout(null);
        cont.add(jPanel);



        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                message = CLOSE;
                setVisible(false);
            }
        });
        photo = new String[100][1];
            for (int i = 0;i<100;i++){
                photo[i][0] = "111";
            }
            jTable = new JTable(photo,cum);
            makeFace(jTable);
            jScrollPane = new JScrollPane(jTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            jScrollPane.setBounds(0,0,300,500);
            jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//垂直滚动
            jScrollPane.setOpaque(false);
            jScrollPane.getViewport().setOpaque(false);
            jScrollPane.setBackground(Color.black);
            cont.add(jScrollPane,BorderLayout.CENTER);

        setSize(800,800);
        setResizable(false);
        setDefaultCloseOperation(2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {
            message =  YES;

            o_id=Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());//读取你获取行号的某一列的值（也就是字段）
            System.out.println("Oid:"+o_id);

            setVisible(false);
        }else if (e.getSource() == no) {
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

    public static void main(String[] args) {
        new MyDialog();
    }
    /**
     * 表格face
     *
     * @param table
     */
    public void makeFace(JTable table) {
        table.setRowHeight(50);
        table.setOpaque(false);
        /*Border center = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        table.setBorder(center);*/
        //设置控件透明
        table.setOpaque(false);
        //设置按钮透明


        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (row % 2 == 0) {
                    setBackground(new Color(203, 203, 203));


                } else {
                    setBackground(Color.WHITE);
                }
                return super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
            }

        };
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
        }
    }
}
