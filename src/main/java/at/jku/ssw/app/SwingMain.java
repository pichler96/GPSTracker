package at.jku.ssw.app;

import at.jku.ssw.app.diagram.Graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;

public class SwingMain extends JFrame {
    private JTable table;
    ListSelectionModel listModel;
    private final Container pane;
    private final JPanel eastPanel;
    private final JScrollPane graphicScroll;
    private final JPanel west;

    public SwingMain () throws JAXBException, IOException, DatatypeConfigurationException {
        setTitle("GPSTracker");
        setSize(1200,585);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pane = getContentPane(); // "lowes" level of the layout

        west = new JPanel(); //western part of the layout is saved here.
        west.setPreferredSize(new Dimension(651,500));
        west.setLayout(new GridLayout(1,0));
        west.setBorder(BorderFactory.createTitledBorder("Tracks:"));

        JPanel tablePanel= getTablePanel();
        west.add(tablePanel); //"West" contains the western (left) part of our GUI
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
        pane.add(eastPanel, BorderLayout.CENTER);
        pane.add(west, BorderLayout.WEST);

        //Menubar ---------------------------------------------------------------
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu sports = new JMenu("Sports");
        JMenu years = new JMenu("Years");

        JMenuItem twentyTwenty = new JMenuItem("2020");
        twentyTwenty.addActionListener(e -> {
            Main.data.filterStartYear(2020);
            repaintTable();
        });

        JMenuItem twentyTwentyOne = new JMenuItem("2021");
        twentyTwentyOne.addActionListener(e -> {
            Main.data.filterStartYear(2021);
            repaintTable();
        });

        JMenuItem twentyTwentyTwo = new JMenuItem("2022");
        twentyTwentyTwo.addActionListener(e -> {
            Main.data.filterStartYear(2022);
            repaintTable();
        });

        JMenuItem biking = new JMenuItem("Biking");
        biking.addActionListener(e -> {
            Main.data.filterSports("Biking");
            repaintTable();
        });

        JMenuItem hiking = new JMenuItem("Hiking");
        hiking.addActionListener(e -> {
            Main.data.filterSports("Hiking");
            repaintTable();
        });

        JMenuItem running = new JMenuItem("Running");
        running.addActionListener(e -> {
            Main.data.filterSports("Running");
            repaintTable();
        });

        JMenuItem skiing = new JMenuItem("Skiing");
        skiing.addActionListener(e -> {
            Main.data.filterSports("Skiing");
            repaintTable();
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        JMenuItem deleteFilters = new JMenuItem("Delete Filters");
        deleteFilters.addActionListener(e -> {
            Main.data.deleteFilter();
            repaintTable();
        });

        JMenuItem reloadData = new JMenuItem("Reload Data");
        reloadData.addActionListener(e -> {
            try {
                Main.data.load(); //lädt die TCX Files neu
                repaintTable();

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
                    repaintTable();
                } catch (JAXBException | FileNotFoundException jaxbException) {
                    jaxbException.printStackTrace();
                }
            }
        });

        //Menubar, adding the different "choice-options" to the menubar:
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

    private void repaintTable() {
        JPanel tablePanel1 = getTablePanel();
        JScrollPane lapTableScroll1 = getLapScrollPane(0);
        Component[] westComponents = west.getComponents();
        for (Component c : westComponents) {
            west.remove(c);
        }
        Component[] eastComponents = eastPanel.getComponents();
        for (Component c : eastComponents) {
            eastPanel.remove(c);
        }

        west.add(tablePanel1);
        west.revalidate();
        eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
        eastPanel.revalidate();
        eastPanel.add(graphicScroll, BorderLayout.CENTER);
        eastPanel.revalidate();
        listModel = table.getSelectionModel();
        triggerListSelectionListener();
        west.repaint();
        eastPanel.repaint();
        pane.revalidate();
        pane.repaint();
    }

    private void triggerListSelectionListener() {
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

    private JPanel getTablePanel(){
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

        TableColumnResize resizeTable = new TableColumnResize(table);
        resizeTable.resize();

        // JTable(left) add it to the "TablePanel"
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScroll, BorderLayout.CENTER);
        return tablePanel;
    }

    private JScrollPane getLapScrollPane(int row){
        //JTABLE -right side (east) contains LapTable and Diagram
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

        //Columns are displayed in the correct width:
        TableColumnResize resizeLapTable = new TableColumnResize(lapTable);
        resizeLapTable.resize();
        tableScroll2.setPreferredSize(new Dimension(500, 150));
        tableScroll2.setBorder(BorderFactory.createTitledBorder("Laps:"));
        return tableScroll2;
    }
}