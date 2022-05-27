package at.jku.ssw.app;

import at.jku.ssw.app.diagram.Graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.IOException;

public class SwingMain extends JFrame {

    public SwingMain () throws JAXBException, IOException, DatatypeConfigurationException {
        setTitle("GPSTracker");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container pane = getContentPane(); // "lowes" level of the layout

        JPanel west = new JPanel(); //western part of the layout is saved here.(Contains a top and a bottom half)
        west.setPreferredSize(new Dimension(500,500));
        west.setLayout(new GridLayout(1,0));



        // JTable -left side (west) - allData contains all data in "general form" (no lap-view) from TableData
        String [][] allData= TableData.getTable();
        String [] allDataColumnNames=TableData.getTableColumnNames();


        //JTable -left side (west):
        DefaultTableModel model = new DefaultTableModel(allData, allDataColumnNames);
        JTable table = new JTable(model);
        //table.getTableHeader();
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

        west.add(tablePanel); //"West" contains the western (left) part of our GUI

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



        //Graphics start (Part of the Diagram)
        Graphics graphics = new Graphics();
        Container container = graphics.getContainer();
        JPanel jPanelGraphic = new JPanel();
        jPanelGraphic.setLayout(new BorderLayout());
        jPanelGraphic.add(container, BorderLayout.CENTER);
        JScrollPane graphicScroll = new JScrollPane(jPanelGraphic);
        graphicScroll.setVisible(true);
        //Graphics end


        JPanel eastPanel = new JPanel(); //"EastPanel" contains the eastern part of our GUI
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(500,500));
        tableScroll2.setPreferredSize(new Dimension(500, 150));
        eastPanel.add(tableScroll2, BorderLayout.NORTH);
        eastPanel.add(graphicScroll, BorderLayout.SOUTH);



        //adding the eastern & the western part to the lower layer
        pane.add(eastPanel, BorderLayout.EAST);
        pane.add(west, BorderLayout.CENTER);


        //Menubar ---------------------------------------------------------------
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu sports = new JMenu("Sports");
        JMenu years = new JMenu("Years");

        JToggleButton sportToggl = new JToggleButton(("TestTogglButton"));


        JMenuItem twentyEighteen = new JMenuItem("2018");
        twentyEighteen.addActionListener(e -> {
            //...
        });

        JMenuItem twentyNineteen = new JMenuItem("2019");
        twentyNineteen.addActionListener(e -> {
            //...
        });

        JMenuItem twentyTwenty = new JMenuItem("2020");
        twentyTwenty.addActionListener(e -> {
            //...
        });

        JMenuItem twentyTwentyOne = new JMenuItem("2021");
        twentyTwentyOne.addActionListener(e -> {
            //...
        });

        JMenuItem twentyTwentyTwo = new JMenuItem("2022");
        twentyTwentyTwo.addActionListener(e -> {
            //...
        });




        JMenuItem biking = new JMenuItem("Biking");
        biking.addActionListener(e -> {
            //...
        });

        JMenuItem driving = new JMenuItem("Driving");
        driving.addActionListener(e -> {
            //...
        });

        JMenuItem flying = new JMenuItem("Flying");
        flying.addActionListener(e -> {
            //...
        });

        JMenuItem hiking = new JMenuItem("Hiking");
        hiking.addActionListener(e -> {
            //...
        });

        JMenuItem running = new JMenuItem("Running");
        running.addActionListener(e -> {
            //...
        });

        JMenuItem skiing = new JMenuItem("Skiing");
        skiing.addActionListener(e -> {
            //...
        });



        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        JMenuItem search = new JMenuItem("search Track");
        search.addActionListener(e -> {
            // ...
        });



        //Menubar, adding the different "choice-options" to the menubar:
        file.add(exit);
        file.addSeparator();
        file.add(search);

        sports.add(biking); sports.addSeparator();
        sports.add(driving);sports.addSeparator();
        sports.add(flying);sports.addSeparator();
        sports.add(running);sports.addSeparator();
        sports.add(hiking);sports.addSeparator();
        sports.add(skiing);
        sports.add(sportToggl);

        years.add(twentyEighteen); years.addSeparator();
        years.add(twentyNineteen); years.addSeparator();
        years.add(twentyTwenty); years.addSeparator();
        years.add(twentyTwentyOne); years.addSeparator();
        years.add(twentyTwentyTwo);


        menu.add(file);
        menu.add(sports);
        menu.add(years);

        setJMenuBar(menu);
    }


}