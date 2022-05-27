package at.jku.ssw.app;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;


public class TableColumnResize {
    JTable table;

    public TableColumnResize(JTable table){
        this.table= table;
    }

    public void resize()
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
    }
}
