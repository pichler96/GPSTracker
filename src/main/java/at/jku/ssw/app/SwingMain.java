package at.jku.ssw.app;

import at.jku.ssw.app.diagram.Graphics;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SwingMain extends JFrame {
    private String [][] tableData;
    private String [] tableDataColumnNames;
    private String [][] lapData;
    private String [] lapTableColumnsNames;
    private JTable table;
    ListSelectionModel listModel;
    private Container pane;
    private  JPanel eastPanel;
    private JScrollPane graphicScroll;


    public SwingMain () throws JAXBException, IOException, DatatypeConfigurationException {
        setTitle("GPSTracker");
        setSize(1200,585); //schauen ob es Funktion gibt dass es die größe Fixieren tut Fix di ins Knie
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pane = getContentPane(); // "lowes" level of the layout

        JPanel west = new JPanel(); //western part of the layout is saved here.
        west.setPreferredSize(new Dimension(651,500));
        west.setLayout(new GridLayout(1,0));
        west.setBorder(BorderFactory.createTitledBorder("Tracks:"));



        JPanel tablePanel= getTablePanel();
        west.add(tablePanel); //"West" contains the western (left) part of our GUI


        JScrollPane lapTableScroll= getLapScrollPane(0);
        lapTableScroll.setBorder(BorderFactory.createTitledBorder("Laps:"));
        /*
        //Graphics start (Part of the Diagram) //neu vom Geri
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
        //west.add(graphicScroll); //hab i rausgemacht
        //Graphics end*/


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
        twentyTwenty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterStartYear(2020);
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                        west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });

        JMenuItem twentyTwentyOne = new JMenuItem("2021");
        twentyTwentyOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterStartYear(2021);
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });

        JMenuItem twentyTwentyTwo = new JMenuItem("2022");
        twentyTwentyTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterStartYear(2022);
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });




        JMenuItem biking = new JMenuItem("Biking");
        biking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterSports("Biking");
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });

        JMenuItem hiking = new JMenuItem("Hiking");
        hiking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterSports("Hiking");
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });

        JMenuItem running = new JMenuItem("Running");
        running.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterSports("Running");
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });

        JMenuItem skiing = new JMenuItem("Skiing");
        skiing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.filterSports("Skiing");
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();
            }
        });



        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        JMenuItem deleteFilters = new JMenuItem("delete Filters");
        deleteFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.data.deleteFilter();
                JPanel tablePanel1=getTablePanel();
                JScrollPane lapTableScroll1= getLapScrollPane(0);
                Component [] westComponents = west.getComponents();
                for(Component c: westComponents){
                    west.remove(c);
                }
                Component [] eastComponents = eastPanel.getComponents();
                for(Component c: eastComponents){
                    eastPanel.remove(c);
                }

                west.add(tablePanel1);
                west.revalidate();
                eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                eastPanel.revalidate();
                eastPanel.add(graphicScroll, BorderLayout.CENTER);
                eastPanel.revalidate();
                listModel= table.getSelectionModel();
                triggerListSelectionListener();
                west.repaint();eastPanel.repaint();
                pane.revalidate();pane.repaint();

            }
        });

        JMenuItem reloadData = new JMenuItem("reload Data");
        reloadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.data.load(); //lädt die TCX Files neu
                    JPanel tablePanel1=getTablePanel();
                    JScrollPane lapTableScroll1= getLapScrollPane(0);
                    Component [] westComponents = west.getComponents();
                    for(Component c: westComponents){
                        west.remove(c);
                    }
                    Component [] eastComponents = eastPanel.getComponents();
                    for(Component c: eastComponents){
                        eastPanel.remove(c);
                    }

                    west.add(tablePanel1);
                    west.revalidate();
                    eastPanel.add(lapTableScroll1, BorderLayout.NORTH);
                    eastPanel.revalidate();
                    eastPanel.add(graphicScroll, BorderLayout.CENTER);
                    eastPanel.revalidate();
                    listModel= table.getSelectionModel();
                    triggerListSelectionListener();
                    west.repaint();eastPanel.repaint();
                    pane.revalidate();pane.repaint();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenuItem search = new JMenuItem("search Track");
        search.addActionListener(e -> {
            // ...
        });



        //Menubar, adding the different "choice-options" to the menubar:
        file.add(deleteFilters);file.addSeparator();
        file.add(reloadData);file.addSeparator();
        file.add(exit);file.addSeparator();
        file.add(search);

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

    public void triggerListSelectionListener(){
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listModel.isSelectionEmpty()){
                    int selectedRow= listModel.getMinSelectionIndex();

                    JScrollPane lapTablePane= getLapScrollPane(selectedRow);
                    //String [] [] data= TableData.getTableOfLaps(selectedRow);
                    //String [] header= TableData.getTableOfLapsColumnNames();

                    Component [] componentArray = eastPanel.getComponents();
                    for(Component c: componentArray){
                        if(c instanceof JScrollPane){
                            eastPanel.remove(c);
                        }
                    }

                    //JScrollPane lapTablePane= getLapTable(header, data);
                    lapTablePane.setPreferredSize(new Dimension(500,150));

                    eastPanel.add(graphicScroll, BorderLayout.CENTER);
                    eastPanel.add(lapTablePane, BorderLayout.NORTH);

                    eastPanel.revalidate();
                    eastPanel.repaint();
                    pane.revalidate();
                    pane.repaint();
                }
            }

        });
    }

    public JScrollPane getLapTable(String[] header, String [][] data){
        //JTABLE -right side (east) contains LapTable and Diagram
        String [][] lapData = data;
        String [] lapTableColumnsNames=header;
        System.out.println(lapData[2][0]);
        //JTable right side:-----------
        DefaultTableModel model2 = new DefaultTableModel(lapData, lapTableColumnsNames);
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

    public JPanel getTablePanel(){
        // JTable -left side (west) - allData contains all data in "general form" (no lap-view) from TableData
        tableData= TableData.getTable();
        tableDataColumnNames=TableData.getTableColumnNames();


        //JTable -left side (west):
        DefaultTableModel model = new DefaultTableModel(tableData, tableDataColumnNames);
        table = new JTable(model){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
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
        //west.add(tablePanel); //"West" contains the western (left) part of our GUI
    }

    public JScrollPane getLapScrollPane(int row){
        //JTABLE -right side (east) contains LapTable and Diagram
        lapData = TableData.getTableOfLaps(row);
        lapTableColumnsNames=TableData.getTableOfLapsColumnNames();

        //JTable right side:-----------
        DefaultTableModel model2 = new DefaultTableModel(lapData, lapTableColumnsNames);
        JTable lapTable = new JTable(model2){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
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