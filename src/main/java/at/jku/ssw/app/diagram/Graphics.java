package at.jku.ssw.app.diagram;
import at.jku.ssw.app.Main;
import at.jku.ssw.tcxparser.schema.ActivityLapT;
import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import java.awt.Color;


import javax.swing.*;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Graphics extends javax.swing.JFrame {
    Container container;
    private at.jku.ssw.app.diagram.Chart chart;

    public Graphics() throws JAXBException, IOException, ParseException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Distance/Time", new Color(135, 245, 137));

        Map<Date, Double> groupedData = new TreeMap<>();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                double distance_T = 0d;
                //date1 = activityT.getId(); - Funktioniert nicht immer
                Date date1 = null;
                for(ActivityLapT activityLapT : activityT.getLap()){
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    distance_T += activityLapT.getDistanceMeters();
                }
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if(groupedData.containsValue(date1)){
                    groupedData.put(date1, distance_T + groupedData.get(date1));
                }else{
                    groupedData.put(date1, distance_T);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for(Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[] {e.getValue()}));
        }
        container.add(chart);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Chart getChart() {
        return chart;
    }

    private void initComponents() {
        chart = new Chart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 6, 80)
                                .addComponent(chart, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addGap(0, 6, 80))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 8, 80)
                                .addComponent(chart, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addGap(0, 8, 80))
        );

        pack();
        setLocationRelativeTo(null);
    }
}