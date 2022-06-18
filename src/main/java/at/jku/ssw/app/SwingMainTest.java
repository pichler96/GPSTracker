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
    void repaintGUI() throws DatatypeConfigurationException, JAXBException, IOException {
        assertEquals(gui.getTitle(), "GPSTracker");
        //System.out.println(Arrays.toString(gui.getTablePanel().getComponents()));
        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
                System.out.println("gotScrollPane");
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                System.out.println("gotJTable");
            }
        }
        System.out.println( table.getModel().getValueAt(4,2));

        Container container= new Container();
        for (int i1 = 0; i1 < gui.getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container= (Container) gui.getComponents()[i1];
                System.out.println("got Container");
            }
        }

        JMenuBar menuBar= new JMenuBar();
        for (int i1 = 0; i1 < container.getComponents().length; i1++) {
            if (container.getComponents()[i1] instanceof JMenuBar) {
                menuBar= (JMenuBar) container.getComponents()[i1];
                System.out.println("got menuBar");
            }
        }

        JMenu menuYear = new JMenu();
        for (int i1 = 0; i1 < gui.getJMenuBar().getComponents().length; i1++) {
            if (gui.getJMenuBar().getComponents()[i1] instanceof JMenu) {
                System.out.println("got menu: "+gui.getJMenuBar().getComponents()[i1]);
                menuYear= (JMenu) gui.getJMenuBar().getComponents()[i1];
            }
        }

        JMenuItem menuItem = new JMenuItem();
        if(menuYear.getMenuComponent(0) instanceof JMenuItem){
            menuItem= (JMenuItem) menuYear.getMenuComponent(0);
            System.out.println(menuItem.toString());
        }

        menuItem.menuSelectionChanged(true);//triggers repaint GUI

        Main.data.filterStartYear(2021);
        SwingMain gui2=new SwingMain();

        for (int i1 = 0; i1 < gui2.getTablePanel().getComponents().length; i1++) {
            if (gui2.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui2.getTablePanel().getComponents()[i1];
                System.out.println("gotScrollPane");
            }
        }
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
                System.out.println("gotJTable");
            }
        }
        System.out.println( table.getModel().getValueAt(4,2));




    }

    @Test
    @Disabled
    void triggerListSelectionListener() {
    }

    @Test
    @Disabled
    void getTablePanel() {
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