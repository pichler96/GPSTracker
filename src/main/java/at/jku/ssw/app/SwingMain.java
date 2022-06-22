package at.jku.ssw.app;

import at.jku.ssw.app.diagram.Graphics;

import javax.swing.*;
import javax.swing.table.*;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;


/**
 * This class contains the SWING Components in order to create the GUI, as well as repaint it if any user-actions are performed.
 * @author Gruppe 3
 */
public class SwingMain extends JFrame {
    /**
     * pane represents the "lowest" level of the layout,
     * therefore every GUI Component has to be added to it, else it won't be visible.
     */
    protected final Container pane;
    /**
     * eastPanel represents the "eastern/right" part of the GUI.
     * eastPanel is partitioned in 2 Components:
     * 1. a Table which shows the Laps of one Track and is added to the northern/upper part of "eastPanel".
     * 2. a Diagram which shows a graphical representation of the tracks and is added south/underneath the table of Laps.
     */
    protected final JPanel eastPanel;
    /**
     * westPanel represents the "western/left" part of the GUI.
     * west contains only one table of tracks, so it is not partitioned any further.
     */
    protected final JPanel westPanel;
    /**
     * table the table of tracks, which is added to "westPanel", is saved in here.
     */
    protected JTable table;
    /**
     * is needed in order to check if the user selects one row in the table.
     */
    ListSelectionModel listModel;
    /**
     * graphicScroll the Diagram, which is added to the southern part of "eastPanel", is saved in here.
     */
    protected final JScrollPane graphicScroll;


    /**
     * This Object represents the running GUI.
     * All Structural Elements are added in here to the "pane"
     * @throws JAXBException is thrown by the TCX Parser, which reads the tcx - files.
     * @throws IOException is also thrown by the TCX Parser if there can't be found a tcx-file in the source folder.
     * @throws DatatypeConfigurationException *****.
     */
    public SwingMain() throws JAXBException, IOException, DatatypeConfigurationException {
        setTitle("GPSTracker");
        setSize(1200,585);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pane = getContentPane(); // "lowes" level of the layout

        westPanel = new JPanel(); //western part of the layout is saved here.
        westPanel.setPreferredSize(new Dimension(661,500));
        westPanel.setLayout(new GridLayout(1,0));
        westPanel.setBorder(BorderFactory.createTitledBorder("Tracks:"));

        //tablePanel the table of laps, which is added to the northern part of "eastPanel", is saved in here.
        JPanel tablePanel= getTablePanel();
        westPanel.add(tablePanel); //"West" contains the western (left) part of our GUI
        JScrollPane lapTableScroll= getLapScrollPane(0);
        lapTableScroll.setBorder(BorderFactory.createTitledBorder("Laps:"));

        //Graphics start (Part of the Diagram)
        Graphics graphics = new Graphics();
        Container container = graphics.getContainer();
        JPanel jPanelGraphic = new JPanel();
        jPanelGraphic.setLayout(new BorderLayout());
        jPanelGraphic.add(container, BorderLayout.CENTER);
        graphicScroll = new JScrollPane(jPanelGraphic);
        graphicScroll.setVisible(true);
        graphicScroll.setBorder(BorderFactory.createTitledBorder("Diagram:"));
        //Graphics end

        eastPanel = new JPanel(); //"EastPanel" contains the eastern part of our GUI
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(500,500));
        eastPanel.add(lapTableScroll, BorderLayout.NORTH);
        eastPanel.add(graphicScroll, BorderLayout.CENTER);

        //adding the eastern & the western part to the lower layer
        pane.add(westPanel, BorderLayout.WEST);
        pane.add(eastPanel, BorderLayout.CENTER);



         // menu represents the menuBar where all Menus are added.
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu sports = new JMenu("Sports");
        JMenu years = new JMenu("Years");

        JMenuItem twentyTwenty = new JMenuItem("2020");
        twentyTwenty.addActionListener(e -> {
            Main.data.filterStartYear(2020);
            repaintGUI();
        });

        JMenuItem twentyTwentyOne = new JMenuItem("2021");
        twentyTwentyOne.addActionListener(e -> {
            Main.data.filterStartYear(2021);
            repaintGUI();
        });

        JMenuItem twentyTwentyTwo = new JMenuItem("2022");
        twentyTwentyTwo.addActionListener(e -> {
            Main.data.filterStartYear(2022);
            repaintGUI();
        });

        JMenuItem biking = new JMenuItem("Biking");
        biking.addActionListener(e -> {
            Main.data.filterSports("Biking");
            repaintGUI();
        });

        JMenuItem hiking = new JMenuItem("Hiking");
        hiking.addActionListener(e -> {
            Main.data.filterSports("Hiking");
            repaintGUI();
        });

        JMenuItem running = new JMenuItem("Running");
        running.addActionListener(e -> {
            Main.data.filterSports("Running");
            repaintGUI();
        });

        JMenuItem skiing = new JMenuItem("Skiing");
        skiing.addActionListener(e -> {
            Main.data.filterSports("Skiing");
            repaintGUI();
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        JMenuItem deleteFilters = new JMenuItem("Delete Filters");
        deleteFilters.addActionListener(e -> {
            Main.data.deleteFilter();
            repaintGUI();
        });

        JMenuItem reloadData = new JMenuItem("Reload Data");
        reloadData.addActionListener(e -> {
            try {
                Main.data.load(); //reloads the TCX-files
                repaintGUI();

            } catch (FileNotFoundException | JAXBException ex) {
                ex.printStackTrace();
            }
        });

        JMenuItem configureDataPath = new JMenuItem("Change Data Path");
        configureDataPath.addActionListener(e -> {
            JFileChooser fc=new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fc.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    Main.data.setPath(fc.getSelectedFile());
                    repaintGUI();
                } catch (JAXBException | FileNotFoundException jaxbException) {
                    jaxbException.printStackTrace();
                }
            }
        });

        //menu-bar, adding the different "choice-options" to the menu-bar:
        file.add(deleteFilters);file.addSeparator();
        file.add(reloadData);file.addSeparator();
        file.add(configureDataPath);file.addSeparator();
        file.add(exit);

        sports.add(biking); sports.addSeparator();
        sports.add(running);sports.addSeparator();
        sports.add(hiking);sports.addSeparator();
        sports.add(skiing);

        years.add(twentyTwenty); years.addSeparator();
        years.add(twentyTwentyOne); years.addSeparator();
        years.add(twentyTwentyTwo);

        menu.add(file);
        menu.add(sports);
        menu.add(years);

        setJMenuBar(menu);

        //show the correct Laps for the chosen Track
        listModel= table.getSelectionModel();
        triggerListSelectionListener();
    }

    /**
     * This Method is called if there is activated or deactivated a Filter, but also if the data path has changed or the data is reloaded.
     * It changes the content shown by the GUI based on the current needed data.
     * At first, all components from "westPanel" and "eastPanel" are recreated.
     * All components added to "westPanel" and "eastPanel" are deleted.
     * Then it adds the just recreated components to "westPanel" and "eastPanel".
     * In order to make these changes visible in the GUI "westPanel","eastPanel" and "pane" are revalidated and repainted.
     */
    protected void repaintGUI() {
        JPanel tablePanel1 = getTablePanel();
        JScrollPane lapTableScroll1 = getLapScrollPane(0);
        Component[] westComponents = westPanel.getComponents();
        for (Component c : westComponents) {
            westPanel.remove(c);
        }
        Component[] eastComponents = eastPanel.getComponents();
        for (Component c : eastComponents) {
            eastPanel.remove(c);
        }

        westPanel.add(tablePanel1);
        westPanel.revalidate();
        eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
        eastPanel.revalidate();
        eastPanel.add(graphicScroll, BorderLayout.CENTER);
        eastPanel.revalidate();
        listModel = table.getSelectionModel();
        triggerListSelectionListener();
        westPanel.repaint();
        eastPanel.repaint();
        pane.revalidate();
        pane.repaint();
    }

    /**
     * This Method is called if the user selects one row in the table of tracks.
     * It then checks which row/Track is selected and calls "getLapScrollPane" in order to receive the correct laps which belong to the selected Track.
     * All components added to "eastPanel" are deleted.
     * Then it adds the just received table of Laps and the diagram to "eastPanel".
     * In order to make these changes visible in the GUI "eastPanel" and "pane" are revalidated and repainted.
     */
    protected void triggerListSelectionListener() {
        listModel.addListSelectionListener(e -> {
            if (!listModel.isSelectionEmpty()){
                int selectedRow= listModel.getMinSelectionIndex();

                JScrollPane lapTablePane= getLapScrollPane(selectedRow);
                Component [] componentArray = eastPanel.getComponents();
                for(Component c: componentArray){
                    if(c instanceof JScrollPane){
                        eastPanel.remove(c);
                    }
                }
                lapTablePane.setPreferredSize(new Dimension(500,150));
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.add(lapTablePane, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.repaint();
                pane.revalidate();
                pane.repaint();
            }
        });
    }

    /**
     *This Method is called to get the table of tracks as a Panel.
     * It calls the methods "getTable" and "getTableColumnNames" to receive the data as String-Arrays.
     * Then a JTable is created which contains this data.
     * It forbids a user-input in the table, calls "TableColumnResize" which formats the table,
     * puts it in a JScrollPane and further in a JPanel.
     * @return a Panel which contains the table of tracks.
     */
    protected JPanel getTablePanel(){
        // JTable -left side (west) - allData contains all data in "general form" (no lap-view) from TableData
        String[][] tableData = TableData.getTable();
        String[] tableDataColumnNames = TableData.getTableColumnNames();

        //JTable -left side (west):
        DefaultTableModel model = new DefaultTableModel(tableData, tableDataColumnNames);
        table = new JTable(model){
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVisible(true);

        //Columns are displayed in a better way/size:
        resize(table);

        // JTable(left) add it to the "TablePanel"
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScroll, BorderLayout.CENTER);
        return tablePanel;
    }

    /**
     *This Method is called to get the table of laps as a JScrollPane.
     * It calls the methods "getTableOfLaps(row)" and "getTableOfLapsColumnNames(row)" to receive the data as String-Arrays.
     * Then a JTable is created which contains this data.
     * It forbids a user-input in the table, calls "TableColumnResize" which formats the table and
     * puts it in a JScrollPane.
     * @param row is needed to find the correct Track, which Laps should be shown.
     * @return a JScrollPane which contains a table of Laps for a certain track.
     */
    protected JScrollPane getLapScrollPane(int row){
        //JTable -right side (east) contains LapTable and Diagram
        String[][] lapData = TableData.getTableOfLaps(row);
        String[] lapTableColumnsNames = TableData.getTableOfLapsColumnNames();

        //JTable right side:-----------
        DefaultTableModel model2 = new DefaultTableModel(lapData, lapTableColumnsNames);
        JTable lapTable = new JTable(model2){
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        lapTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll2 = new JScrollPane(lapTable);
        tableScroll2.setVisible(true);

        //Columns are displayed in a better way/size:
        resize(lapTable);

        tableScroll2.setPreferredSize(new Dimension(500, 150));
        tableScroll2.setBorder(BorderFactory.createTitledBorder("Laps:"));
        return tableScroll2;
    }

    /**
     * This method changes the column width for each cell in a JTable in order to have a better formatted table.
     * @param resize This is the JTable which column-widths are adapted.
     */
    protected void resize(JTable resize){

        for(int i=0;i<resize.getColumnCount();i++)
        {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) resize.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;
            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null)
            {
                renderer = resize.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(resize, col.getHeaderValue(), false, false, 0, i);
            if(resize.getRowCount()>0)
            {
                for(int r=0;r<resize.getRowCount();r++)
                {
                    renderer=resize.getCellRenderer(r,i);
                    Component comp1=renderer.getTableCellRendererComponent(resize,resize.getValueAt(r, i),false,false, r, i);
                    if(comp.getPreferredSize().width<comp1.getPreferredSize().width)
                    {
                        width=comp1.getPreferredSize().width;
                    }
                    else if(comp.getPreferredSize().width>width)
                    {
                        width=comp.getPreferredSize().width;
                    }
                }
            }
            else
            {
                width=comp.getPreferredSize().width;
            }
            col.setPreferredWidth(width+14);
        }

/*
        final TableColumnModel columnModel = resize.getColumnModel();
        for (int column = 0; column < resize.getColumnCount(); column++) {
            int width = 0; // Min width
            for (int row = 0; row < resize.getRowCount(); row++) {
                TableCellRenderer renderer = resize.getCellRenderer(row, column);
                Component comp = resize.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width , width);
            }
            if(width > 300)
                width=300;
            width = Math.max(width, table.getColumnModel().getColumn(column).getPreferredWidth());
            columnModel.getColumn(column).setPreferredWidth(width);
        }*/
    }
}