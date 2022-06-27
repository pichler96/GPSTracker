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
                Date date1 = null;
                for(ActivityLapT activityLapT : activityT.getLap()){
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    distance_T += activityLapT.getDistanceMeters();
                }
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if(groupedData.containsValue(date1)){
                    groupedData.put(date1, (distance_T + groupedData.get(date1))/1000);
                }else{
                    groupedData.put(date1, distance_T/1000);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for(Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[] {e.getValue()}));
        }
        container.add(chart);
    }

    public Graphics(double avgCalories, int placeholder) throws JAXBException, IOException, ParseException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Calories/Time", new Color(245, 236, 135));

        Map<Date, Double> groupedData = new TreeMap<>();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                avgCalories = 0d;
                Date date1 = null;
                int counter = 0;
                for(ActivityLapT activityLapT : activityT.getLap()){
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    avgCalories += activityLapT.getCalories();
                    counter += 1;
                }
                avgCalories = avgCalories/counter;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if(groupedData.containsValue(date1)){
                    groupedData.put(date1, (avgCalories + groupedData.get(date1))/2);
                }else{
                    groupedData.put(date1, avgCalories);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for(Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[] {e.getValue()}));
        }
        container.add(chart);
    }

    public Graphics(double avgSpeed) throws JAXBException, IOException, ParseException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Speed/Time", new Color(245, 135, 236));

        Map<Date, Double> groupedData = new TreeMap<>();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                avgSpeed = 0d;
                Date date1 = null;
                int counter = 0;
                for(ActivityLapT activityLapT : activityT.getLap()){
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    avgSpeed += (activityLapT.getDistanceMeters()/activityLapT.getTotalTimeSeconds());
                    counter += 1;
                }
                avgSpeed = avgSpeed/counter;
                avgSpeed = avgSpeed* 3.6;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if(groupedData.containsValue(date1)){
                    groupedData.put(date1, (avgSpeed + groupedData.get(date1))/2);
                }else{
                    groupedData.put(date1, avgSpeed);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for(Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[] {e.getValue()}));
        }
        container.add(chart);
    }

    public Graphics(int heartRate) throws JAXBException, IOException, ParseException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Avg Heartrate/Time", new Color(139, 135, 245));

        Map<Date, Double> groupedData = new TreeMap<>();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                heartRate = 0;
                Date date1 = null;
                int counter = 0;
                for(ActivityLapT activityLapT : activityT.getLap()){
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();

                    if(activityLapT.getAverageHeartRateBpm() != null){
                        heartRate = heartRate + (activityLapT.getAverageHeartRateBpm().getValue());
                    }else{
                        heartRate = 0;
                    }
                    counter += 1;
                }

                if(heartRate == 0) {
                    break;
                }

                heartRate = heartRate/counter;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if(groupedData.containsValue(date1)){
                    groupedData.put(date1, (heartRate + groupedData.get(date1))/2);
                }else{
                    groupedData.put(date1, (double) heartRate);
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