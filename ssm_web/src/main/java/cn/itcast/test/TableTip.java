package cn.itcast.test;
import java.awt.Point;

import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.TableModel;



public class TableTip {



    private JFrame jFrame;



    private JTable jTable;



    private JScrollPane jScrollPane;



    private int row;



    private int column;



    /**
     * @param args
     */

    public static void main(String[] args) {

        TableTip tableTip = new TableTip();

        tableTip.show();

    }



    private void show() {

        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setSize(300, 128);

        final Object jtText[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 },

                { 9, 10, 11, 12 }, { 13, 14, 15, 16 }, { 17, 18, 19, 20 } };

        final String jtHead[] = { "Column1", "Column2", "Column3", "Column4" };

        TableModel dataModel = new AbstractTableModel() {



            private static final long serialVersionUID = 0L;



            public int getColumnCount() {

                return jtHead.length;

            }



            public int getRowCount() {

                return jtText.length;

            }



            public Object getValueAt(int rowIndex, int columnIndex) {

                return jtText[rowIndex][columnIndex];

            }



            public String getColumnName(int column) {

                return jtHead[column];

            }



            public boolean isCellEditable(int row, int col) {

                return false;

            }



            public void setValueAt(Object aValue, int row, int column) {

                jtText[row][column] = aValue;

            }

        };

        jTable = new JTable(dataModel);

        jTable.setCellSelectionEnabled(false);

        // 设置不能重新调整列宽

        jTable.getTableHeader().setResizingAllowed(false);

        // 设置不能重新排序各列

        jTable.getTableHeader().setReorderingAllowed(false);

        // 监听鼠标移动，修改TIP

        jTable.addMouseMotionListener(new MouseMotionListener() {

            @Override

            public void mouseDragged(MouseEvent e) {

            }



            public void mouseMoved(MouseEvent e) {

                Point point = e.getPoint();

                int x = jTable.rowAtPoint(point);

                int y = jTable.columnAtPoint(point);

                if (x != row || y != column) {

                    row = x;

                    column = y;

                }

                Object tip = jTable.getValueAt(row, column);

                if (tip != null) {

                    jTable.setToolTipText("Row:"+(row+1)+" Column:"+(column+1)+":"+tip.toString());

                }

            }

        });

        jScrollPane = new JScrollPane(jTable);

        jScrollPane.setBounds(10, 40, 280, 90);

        jFrame.add(jScrollPane);

        jFrame.setVisible(true);

    }

}