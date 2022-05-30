package at.jku.ssw.app;

import at.jku.ssw.app.diagram.Graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class SwingMain extends JFrame implements ActionListener {

    public SwingMain () throws JAXBException, IOException {
        setTitle("TestSwingGUI");
        setSize(800,500);
        //1920*180
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane(); // "lowes" level of the layout

        JPanel west = new JPanel(); //western part of the layout is saved here.(Contains a top and a bottom half)
        west.setPreferredSize(new Dimension(200,400));
        west.setLayout(new GridLayout(2,0));

        JPanel nord = new JPanel(); //western part of the layout is saved here.(Contains a top and a bottom half)
        nord.setPreferredSize(new Dimension(200,400));
        nord.setLayout(new GridLayout(2,0));


        JButton button = new JButton("Exit"); //Exit Button to exit the program via menubar
        button.setPreferredSize(new Dimension(200,400));
        button.addActionListener(e -> System.exit(0));


        // JTable -left side (west) - allData contains all data in "general form" (no lap-view) from TableData
        String [][] allData= TableData.getTable();
        String [] allDataColumnNames={"ID", "Sport", "Start Time", "Total Time", "Distance", "Avg Speed", "Max Speed", "Avg Heartrate", "Max Heartrate"};

        //JTABLE -right side (east) -- we will change the formatting of this table (e.g. align the header to the left side)
        String [][] data = TableData.getLaps();
        String [] lapTableColumns={"Start Time", "Total Time", "Max Speed", "Max Heartrate", "Distance", "Avg Heartrate", "Calories"};

        //JTable -left side (west):
        DefaultTableModel model = new DefaultTableModel(allData, allDataColumnNames);
        JTable table = new JTable(model);
        //table.getTableHeader();
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVisible(true);

        //Code to resize columns dynamically
        for (int column = 0; column < table.getColumnCount(); column++)
        {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);


            int preferredWidth = tableColumn.getMinWidth();


            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }


        // JTable(left) add it to the "TablePanel"
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScroll, BorderLayout.CENTER);


        west.add(tablePanel); //"West" contains the western (left) part of our GUI

        //Graphics start (Part of the Diagram)
        Graphics graphics = new Graphics();
        Container container = graphics.getContainer();
        int westWith = west.getWidth();
        int westHeight = west.getHeight();

        graphics.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                graphics.getChart().resize(westWith, westHeight);

            }
        });
        JPanel jPanelGraphic = new JPanel();
        jPanelGraphic.setLayout(new BorderLayout());
        jPanelGraphic.add(container, BorderLayout.CENTER);
        JScrollPane graphicScroll = new JScrollPane(jPanelGraphic);
        graphicScroll.setVisible(true);
        west.add(graphicScroll);
        //Graphics end

        //JTable right side:-----------
        DefaultTableModel model2 = new DefaultTableModel(data, lapTableColumns);
        JTable table2 = new JTable(model2);

        table2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll2 = new JScrollPane(table2);
        tableScroll2.setVisible(true);

        //resize columns dynamically
        for (int column = 0; column < table2.getColumnCount(); column++)
        {
            TableColumn tableColumn = table2.getColumnModel().getColumn(column);


            int preferredWidth = tableColumn.getMinWidth();


            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table2.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table2.getCellRenderer(row, column);
                Component c = table2.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table2.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }


        JPanel eastPanel = new JPanel(); //"EastPanel" contains the eastern part of our GUI
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(tableScroll2, BorderLayout.CENTER);
        eastPanel.setPreferredSize(new Dimension(230,460));


        //adding the eastern & the western part to the lower layer
        pane.add(eastPanel, BorderLayout.EAST);
        pane.add(west, BorderLayout.CENTER);



        //Menubar ---------------------------------------------------------------
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu sports = new JMenu("Sports");
        JMenu years = new JMenu("Years");

        JToggleButton sportToggl = new JToggleButton(("sportart Nix"));


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




        JMenuItem exit2 = new JMenuItem("Exit");
        exit2.addActionListener(e -> System.exit(0));

        JMenuItem search = new JMenuItem("search Track");
        search.addActionListener(e -> {
            // ...
        });



        //Menubar, adding the different "choice-options" to the menubar:
        file.add(exit2);
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

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}