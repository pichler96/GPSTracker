package at.jku.ssw.app;

import at.jku.ssw.tcxparser.TrainingsData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwingMainTest{
    public SwingMain gui;

    @BeforeEach
    public void setUp() throws DatatypeConfigurationException, JAXBException, IOException {
        try {
            Main.data = new TrainingsData("data");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        gui = new SwingMain();
    }


    @Test
    @Disabled
    void ConstructorAndRepaintGUI() throws DatatypeConfigurationException, JAXBException, IOException {
        assertEquals(gui.getTitle(), "GPSTracker");

        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
                //System.out.println("gotScrollPane");
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                //System.out.println("gotJTable");
            }
        }

        //table.getSelectionModel().addSelectionInterval(2,3);

        String expectedYears[] = {"2020","2021","2022"};
        List <String> expectedYearsList = Arrays.asList(expectedYears);
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(0,2).toString().substring(0,4)));
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(1,2).toString().substring(0,4)));
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(2,2).toString().substring(0,4)));
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(3,2).toString().substring(0,4)));
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(4,2).toString().substring(0,4)));
        assertTrue(expectedYearsList.contains(table.getModel().getValueAt(5,2).toString().substring(0,4)));


        Container container= new Container();
        for (int i1 = 0; i1 < gui.getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container= (Container) gui.getComponents()[i1];
                //System.out.println("got Container");
            }
        }

        /*
        JMenuBar menuBar= new JMenuBar();
        for (int i1 = 0; i1 < container.getComponents().length; i1++) {
            if (container.getComponents()[i1] instanceof JMenuBar) {
                menuBar= (JMenuBar) container.getComponents()[i1];
                //System.out.println("got menuBar");
            }
        }*/

        JMenu menuYear = new JMenu();
        for (int i1 = 0; i1 < gui.getJMenuBar().getComponents().length; i1++) {
            if (gui.getJMenuBar().getComponents()[i1] instanceof JMenu) {
                //System.out.println("got menu: "+gui.getJMenuBar().getComponents()[i1]);
                menuYear= (JMenu) gui.getJMenuBar().getComponents()[i1];
            }
        }

        JMenuItem menuItem = new JMenuItem();
        if(menuYear.getMenuComponent(0) instanceof JMenuItem){
            menuItem= (JMenuItem) menuYear.getMenuComponent(0);
            //System.out.println(menuItem.toString());
        }

        menuItem.menuSelectionChanged(true);//triggers repaint GUI

        Main.data.filterStartYear(2021);
        gui.repaintGUI();

        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
                //System.out.println("gotScrollPane");
            }
        }
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                //System.out.println("gotJTable");
            }
        }


        assertEquals("2021",table.getModel().getValueAt(0,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(1,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(2,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(3,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(4,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(5,2).toString().substring(0,4));
    }

    @Test
    @Disabled
    void getTablePanel() {
        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
                //System.out.println("gotScrollPane");
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                //System.out.println("gotJTable");
            }
        }

        JScrollPane LapscrollPane= new JScrollPane();
        LapscrollPane= gui.getLapScrollPane(0);

        JTable LapTable = new JTable();
        for (int i1 = 0; i1 < LapscrollPane.getViewport().getComponents().length; i1++) {
            if (LapscrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                LapTable= (JTable) LapscrollPane.getViewport().getComponents()[i1];
                //System.out.println("gotJTable");
            }
        }

        double distance=0;
        int km=3;
        for(int i=0; i<LapTable.getModel().getRowCount();i++){
            String d=LapTable.getModel().getValueAt(i,4).toString().substring(0,(LapTable.getModel().getValueAt(i,4).toString().length()-km));
            distance+=Double.valueOf(d);
        }
        int l=table.getModel().getValueAt(0,4).toString().length();
        //System.out.println(table.getModel().getValueAt(0,4).toString().substring(0,(l-km)));
        //System.out.println(distance);

        assertEquals(Double.valueOf(table.getModel().getValueAt(0,4).toString().substring(0,(l-km))),distance);
    }

    @Test
    void triggerListSelectionListener() {
        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
                //System.out.println("gotScrollPane");
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                //System.out.println("gotJTable");
            }
        }

        Container container= new Container();
        for (int i1 = 0; i1 < gui.getContentPane().getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container= (Container) gui.getContentPane().getComponents()[i1];
                //System.out.println("got Container");
            }
        }

        JScrollPane lapScrollPane= new JScrollPane();
        for (int i1 = 0; i1 < container.getComponents().length; i1++) {
            if (container.getComponents()[i1] instanceof JScrollPane) {
                lapScrollPane= (JScrollPane) container.getComponents()[i1];
                //System.out.println("got JScrollPane");
                break;
            }
        }

        JTable lapTable= new JTable();
        for (int i1 = 0; i1 < lapScrollPane.getViewport().getComponents().length; i1++) {
            if (lapScrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                lapTable= (JTable) lapScrollPane.getViewport().getComponents()[i1];
                //System.out.println("got JTable");
            }
        }

        System.out.println(lapTable.getModel().getRowCount());
        //gui.table.setRowSelectionInterval(2,2);
        gui.listModel.setSelectionInterval(2,2);
        gui.triggerListSelectionListener();
        System.out.println(Arrays.toString(gui.listModel.getSelectedIndices()));




         container= new Container();
        for (int i1 = 0; i1 < gui.getContentPane().getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container= (Container) gui.getContentPane().getComponents()[i1];
                //System.out.println("got Container");
            }
        }

         lapScrollPane= new JScrollPane();
        for (int i1 = 0; i1 < container.getComponents().length; i1++) {
            if (container.getComponents()[i1] instanceof JScrollPane) {
                lapScrollPane= (JScrollPane) container.getComponents()[i1];
                //System.out.println("got JScrollPane");
                break;
            }
        }

         lapTable= new JTable();
        for (int i1 = 0; i1 < lapScrollPane.getViewport().getComponents().length; i1++) {
            if (lapScrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                lapTable= (JTable) lapScrollPane.getViewport().getComponents()[i1];
                //System.out.println("got JTable");
            }
        }

        System.out.println(lapTable.getModel().getRowCount());

    }

    @Test
    @Disabled
    void getLapScrollPane() {
    }

    @Test
    @Disabled
    void resize() {
    }
}