package at.jku.ssw.app.UnitTests;

import at.jku.ssw.app.Main;
import at.jku.ssw.app.SwingMain;
import at.jku.ssw.app.diagram.Graphics;
import at.jku.ssw.tcxparser.TrainingsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a Test class for the GUI class - SwingMain but also for its "helper class" TableData
 * It surely is not very easy to test a Swing GUI with unit tests, however our approach was still to
 * try to have an appropriate coverage and check the class generally if its methods works basically.
 */
class SwingMainTest{
    public SwingMain gui;

    /**
     * This Method creates a new SwingMain/GUI, which will later be tested
     * @throws DatatypeConfigurationException Exeption from getting Main.data
     * @throws JAXBException Exeption from getting Main.data
     * @throws IOException Exeption from getting Main.data
     */
    @BeforeEach
    public void setUp() throws DatatypeConfigurationException, JAXBException, IOException, ParseException {
        try {
            Main.data = new TrainingsData("data");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        gui = new SwingMain();
    }

    /**
     * This method tests more or less the "running GUI" == "SwingMain" Constructor
     * It gets some values from the Table (on the east side)
     * Then it also activates a filter (year) and therefore tests the method "repaintGUI"
     * After that it looks at the values from the Table (on the east side) again and checks whether the filter
     * and the repaintGUI method have worked.
     * The method "resize" is also covered, as it is called as soon as the GUI is created but also when it's repainted
     */
    @Test
    void ConstructorAndRepaintGUIAndResize() throws JAXBException, IOException, ParseException {
        assertEquals(gui.getTitle(), "GPSTracker");

        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
            }
        }

        String[] expectedYears = {"2020","2021","2022"};
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
            }
        }


        JMenu menuYear = new JMenu();
        for (int i1 = 0; i1 < gui.getJMenuBar().getComponents().length; i1++) {
            if (gui.getJMenuBar().getComponents()[i1] instanceof JMenu) {
                menuYear= (JMenu) gui.getJMenuBar().getComponents()[i1];
            }
        }

        JMenuItem menuItem = new JMenuItem();
        if(menuYear.getMenuComponent(0) instanceof JMenuItem){
            menuItem= (JMenuItem) menuYear.getMenuComponent(0);
        }

        menuItem.menuSelectionChanged(true);//triggers repaint GUI

        Main.data.filterStartYear(2021);
        gui.repaintGUI();

        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
            }
        }
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
            }
        }

        assertEquals("2021",table.getModel().getValueAt(0,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(1,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(2,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(3,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(4,2).toString().substring(0,4));
        assertEquals("2021",table.getModel().getValueAt(5,2).toString().substring(0,4));
    }

    /**
     * This method uses the methods "getTablePanel" and "getLapScrollPane" in order to get their content.
     * Then it checks whether the Track-Table and the Lap-Table represent the same Track.
     */
    @Test
    void getTablePanelAndGetLapScrollPane() {
        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
            }
        }

        JScrollPane LapscrollPane= new JScrollPane();
        LapscrollPane= gui.getLapScrollPane(0);

        JTable LapTable = new JTable();
        for (int i1 = 0; i1 < LapscrollPane.getViewport().getComponents().length; i1++) {
            if (LapscrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                LapTable= (JTable) LapscrollPane.getViewport().getComponents()[i1];
            }
        }

        double distance=0;
        int km=3;
        for(int i=0; i<LapTable.getModel().getRowCount();i++){
            String d=LapTable.getModel().getValueAt(i,4).toString().substring(0,(LapTable.getModel().getValueAt(i,4).toString().length()-km));
            distance+=Double.parseDouble(d);
        }
        int l=table.getModel().getValueAt(0,4).toString().length();

        assertEquals(Double.valueOf(table.getModel().getValueAt(0,4).toString().substring(0,(l-km))),distance);
    }

    /**
     * This method tests the method "triggerListSelectionListener",
     * which is called when a Lap in the Track-Table is selected by the user.
     * It checks if the triggerListSelectionListener has worked, as it looks if the number of shown
     * laps in the lap Table are coherent to the selected track.
     */
    @Test
    void triggerListSelectionListener() {
        JScrollPane scrollPane= new JScrollPane();
        for (int i1 = 0; i1 < gui.getTablePanel().getComponents().length; i1++) {
            if (gui.getTablePanel().getComponents()[i1] instanceof JScrollPane) {
                scrollPane= (JScrollPane) gui.getTablePanel().getComponents()[i1];
            }
        }
        JTable table = new JTable();
        for (int i1 = 0; i1 < scrollPane.getViewport().getComponents().length; i1++) {
            if (scrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                table= (JTable) scrollPane.getViewport().getComponents()[i1];
            }
        }

        Container container= new Container();
        for (int i1 = 0; i1 < gui.getContentPane().getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container= (Container) gui.getContentPane().getComponents()[i1];
            }
        }

        JScrollPane lapScrollPane= new JScrollPane();
        for (int i1 = 0; i1 < container.getComponents().length; i1++) {
            if (container.getComponents()[i1] instanceof JScrollPane) {
                lapScrollPane= (JScrollPane) container.getComponents()[i1];
                break;
            }
        }

        JTable lapTable= new JTable();
        for (int i1 = 0; i1 < lapScrollPane.getViewport().getComponents().length; i1++) {
            if (lapScrollPane.getViewport().getComponents()[i1] instanceof JTable) {
                lapTable= (JTable) lapScrollPane.getViewport().getComponents()[i1];
            }
        }

        int size= Main.getData().get(0).getActivities().getActivity().get(0).getLap().size();
        assertEquals(lapTable.getModel().getRowCount(), size);
        //Number of Rows from lapTable is 5/size, because when the GUI starts it always shows the laps of the first Track.

        gui.table.setRowSelectionInterval(2,2);
        gui.listModel.setSelectionInterval(2,2);
        gui.triggerListSelectionListener();
        //System.out.println(Arrays.toString(gui.listModel.getSelectedIndices()));


         Container container2= new Container();
        for (int i1 = 0; i1 < gui.getContentPane().getComponents().length; i1++) {
            if (gui.getContentPane().getComponents()[i1] instanceof Container) {
                container2= (Container) gui.getContentPane().getComponents()[i1];
            }
        }

         JScrollPane lapScrollPane2= new JScrollPane();
        for (int i1 = 0; i1 < container2.getComponents().length; i1++) {
            if (container2.getComponents()[i1] instanceof JScrollPane) {
                lapScrollPane2= (JScrollPane) container2.getComponents()[i1];
            }
        }

         JTable lapTable2= new JTable();
        for (int i1 = 0; i1 < lapScrollPane2.getViewport().getComponents().length; i1++) {
            if (lapScrollPane2.getViewport().getComponents()[i1] instanceof JTable) {
                lapTable2= (JTable) lapScrollPane2.getViewport().getComponents()[i1];
            }
        }

        size= Main.getData().get(2).getActivities().getActivity().get(0).getLap().size();
        assertEquals(lapTable2.getModel().getRowCount(), size);
        //Number of Rows from the current LapTable, which shows the laps of the 3rd track (SelectionInterval(2,2)
    }

}