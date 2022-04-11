package at.jku.ssw.app;

import at.jku.ssw.tcxparser.schema.ActivityT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingMain extends JFrame {

    public SwingMain () throws JAXBException, IOException {
        setTitle("TestSwingGUI");
        setSize(800,500);
        //1920*180
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane(); //unterste Ebene des Layouts

        JPanel west = new JPanel(); //westlicher Teil des Layouts sind hier gespeichert. (Besteht aus 1 oberer und 1 unterer Hälfte)
        west.setPreferredSize(new Dimension(200,400));
        west.setLayout(new GridLayout(2,0));


        JButton button = new JButton("Exit");
        button.setPreferredSize(new Dimension(200,400));
        button.addActionListener(e -> System.exit(0)); // e -> des is der Vorschlag vom IntelliJ "exchange with lambda

        JButton button2 = new JButton("button");
        JButton button3 = new JButton("button");

        String [][] allData= TableData.getTable();
        String [] allDataColumnNames={"Name", "Sport", "Start Time", "Total Time", "Distance", "Average Speed", "Max Speed", "Average Heartrate", "Max Heartrate"};

        //JTABLE links oben ---------------------------
        String [][] data = {
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Running","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "15", "35"},
                {"Jogging","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "33", "220",},
                {"Hiking","Max Muster", "1234 930240", "Linz", "4.36km", "00:30:24", "9034", "982"}
        };
        String [] columnNames={"Discipline","Name", "SvNr", "Place", "Distance", "Time", "speed", "altitude"};

        //String [] trackDataColumnNames = {"Sports", "Name", "Notes"}; // vom "Test"
        DefaultTableModel model = new DefaultTableModel(allData, allDataColumnNames);
        JTable table = new JTable(model);
        table.getTableHeader();

        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVisible(true);

        //online Code to resize columns dynamically
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
        //source: https://stackoverflow.com/questions/6447984/auto-resize-the-widths-of-jtables-columns-dynamically


        // JTable hinzufügen zum "TablePanel"
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScroll, BorderLayout.CENTER);


        west.add(tablePanel); //"West" ist der westliche (linke) Teil unserer GUI.
        west.add(button3); //hier gehört noch die Grafik stattdessen rein

        //JTable rechts oben:-----------
        DefaultTableModel model2 = new DefaultTableModel(data, columnNames);
        JTable table2 = new JTable(model2);
        table2.getTableHeader();

        table2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //Scrollbar for the Table of Data
        JScrollPane tableScroll2 = new JScrollPane(table2);
        tableScroll2.setVisible(true);

        //online Code to resize columns dynamically
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
        //source: https://stackoverflow.com/questions/6447984/auto-resize-the-widths-of-jtables-columns-dynamically



        JPanel eastPanel = new JPanel(); //"EastPanel" ist der östliche Teil (rechte) unserer GUI
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(tableScroll2, BorderLayout.CENTER);
        eastPanel.setPreferredSize(new Dimension(230,460));


        //Hinzufügen des östlichen & westlichen Teils zum Fenster
        pane.add(eastPanel, BorderLayout.EAST);
        pane.add(west, BorderLayout.CENTER);



        //Menubar ---------------------------------------------------------------
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu sports = new JMenu("Sports");
        JMenu years = new JMenu("Years");

        JToggleButton sportToggl = new JToggleButton(("sportart Nix"));


        JMenuItem twentyEighteen = new JMenuItem("2018");
        twentyEighteen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem twentyNineteen = new JMenuItem("2019");
        twentyNineteen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem twentyTwenty = new JMenuItem("2020");
        twentyTwenty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem twentyTwentyOne = new JMenuItem("2021");
        twentyTwentyOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem twentyTwentyTwo = new JMenuItem("2022");
        twentyTwentyTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });




        JMenuItem biking = new JMenuItem("Biking");
        biking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem driving = new JMenuItem("Driving");
        driving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem flying = new JMenuItem("Flying");
        flying.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem hiking = new JMenuItem("Hiking");
        hiking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem running = new JMenuItem("Running");
        running.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });

        JMenuItem skiing = new JMenuItem("Skiing");
        skiing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //...
            }
        });




        JMenuItem exit2 = new JMenuItem("Exit");
        exit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem search = new JMenuItem("search Track");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tut noch nichts
            }
        });



        //Menubar hinzufügen der Auswahlmöglichkeiten der einzelnen Reiter:
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

        //pane.add(menu, BorderLayout.NORTH); // ident zu darunter
        setJMenuBar(menu);
    }


}