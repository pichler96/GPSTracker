package at.jku.ssw.app;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

public class MainFrame extends JFrame{
    JLabel gpsTracker;
    JPanel mainPanel;
    JButton jaButton;
    JButton neinButton;
    JTable trackTable;
    JScrollPane tableScroll;


    public MainFrame(){
        TableModel dataModel = new
                AbstractTableModel() {
                    //JTABLE -right side (east) -- we will change the formatting of this table (e.g. align the header to the left side)
                    String [][] data = TableData.getTableOfLaps();
                    String [] lapTableColumnsNames=TableData.getTableOfLapsColumnNames();

                    //JTable right side:-----------
                    DefaultTableModel model2 = new DefaultTableModel(data, lapTableColumnsNames);
                    JTable lapTable = resize(new JTable(model2));
                    //Columns are displayed in the correct width:

                    public JTable resize(JTable table)
                    {
                        for(int i=0;i<table.getColumnCount();i++)
                        {
                            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
                            TableColumn col = colModel.getColumn(i);
                            int width = 0;
                            TableCellRenderer renderer = col.getHeaderRenderer();
                            if (renderer == null)
                            {
                                renderer = table.getTableHeader().getDefaultRenderer();
                            }
                            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, i);
                            if(table.getRowCount()>0)
                            {
                                for(int r=0;r<table.getRowCount();r++)
                                {
                                    renderer=table.getCellRenderer(r,i);
                                    Component comp1=renderer.getTableCellRendererComponent(table,table.getValueAt(r, i),false,false, r, i);
                                    if(comp.getPreferredSize().width<comp1.getPreferredSize().width)
                                    {
                                        width=comp1.getPreferredSize().width;
                                    }
                                    else
                                    {
                                        width=comp.getPreferredSize().width;
                                    }
                                }
                            }
                            else
                            {
                                width=comp.getPreferredSize().width;
                            }
                            col.setPreferredWidth(width+4);
                        }
                        return table;
                    }

                    public int getColumnCount() {
                        return lapTable.getColumnCount();
                    }

                    public int getRowCount() {
                        return lapTable.getRowCount();
                    }

                    public Object getValueAt(int row, int col) {
                        return lapTable.getValueAt(row,col);
                    }
                };

        trackTable.setModel(dataModel);

        setContentPane(mainPanel);
        setTitle("GPSTracker");
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private JScrollPane trackTable() {
        //JTABLE -right side (east) -- we will change the formatting of this table (e.g. align the header to the left side)
        String [][] data = TableData.getTableOfLaps();
        String [] lapTableColumnsNames=TableData.getTableOfLapsColumnNames();

        //JTable right side:-----------
        DefaultTableModel model2 = new DefaultTableModel(data, lapTableColumnsNames);
        JTable lapTable = new JTable(model2);

        lapTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll2 = new JScrollPane(lapTable);
        tableScroll2.setVisible(true);

        //Columns are displayed in the correct width:
        TableColumnResize resizeLapTable = new TableColumnResize(lapTable);
        resizeLapTable.resize();
        return tableScroll2;
    }


    private void setTrackTable() {
        DefaultTableModel model = new DefaultTableModel();

    }

}
