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

/**
 * @author Gerald Waldburger, K12005573
 */
public class Graphics extends javax.swing.JFrame {
    Container container;
    private at.jku.ssw.app.diagram.Chart chart;

    /**
     * @throws JAXBException
     * @throws IOException
     * @throws ParseException Constructor for Graphics which, creates a graphic for distance and time
     */
    public Graphics() throws JAXBException, IOException, ParseException {

        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Distance (km) / Time (mm-yy)", new Color(135, 245, 137));

        Map<Date, Double> groupedData = new TreeMap<>();

        for (TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()) {
            for (ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                double distance_T = 0d;
                Date date1 = null;
                for (ActivityLapT activityLapT : activityT.getLap()) {
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    distance_T += activityLapT.getDistanceMeters();
                }
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if (groupedData.containsValue(date1)) {
                    groupedData.put(date1, (distance_T + groupedData.get(date1)) / 1000);
                } else {
                    groupedData.put(date1, distance_T / 1000);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for (Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[]{e.getValue()}));
        }
        container.add(chart);
    }

    /**
     * @param avgCalories
     * @param placeholder
     * @throws JAXBException
     * @throws IOException
     * @throws ParseException Constructor for Graphics which, creates a graphic for calories and time
     */
    public Graphics(double avgCalories, int placeholder) throws JAXBException, IOException, ParseException {

        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Avg Calories (kcal) / Time (mm-yy)", new Color(245, 236, 135));

        Map<Date, Double> groupedData = new TreeMap<>();

        for (TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()) {
            for (ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                avgCalories = 0d;
                Date date1 = null;
                int counter = 0;
                for (ActivityLapT activityLapT : activityT.getLap()) {
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    avgCalories += activityLapT.getCalories();
                    counter += 1;
                }
                avgCalories = avgCalories / counter;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if (groupedData.containsValue(date1)) {
                    groupedData.put(date1, (avgCalories + groupedData.get(date1)) / 2);
                } else {
                    groupedData.put(date1, avgCalories);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for (Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[]{e.getValue()}));
        }
        container.add(chart);
    }

    /**
     * @param avgSpeed
     * @throws JAXBException
     * @throws IOException
     * @throws ParseException Constructor for Graphics which, creates a graphic for speed and time
     */
    public Graphics(double avgSpeed) throws JAXBException, IOException, ParseException {

        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Avg Speed (km/h) / Time (mm-yy)", new Color(245, 135, 236));

        Map<Date, Double> groupedData = new TreeMap<>();

        for (TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()) {
            for (ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                avgSpeed = 0d;
                Date date1 = null;
                int counter = 0;
                for (ActivityLapT activityLapT : activityT.getLap()) {
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();
                    avgSpeed += (activityLapT.getDistanceMeters() / activityLapT.getTotalTimeSeconds());
                    counter += 1;
                }
                avgSpeed = avgSpeed / counter;
                avgSpeed = avgSpeed * 3.6;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if (groupedData.containsValue(date1)) {
                    groupedData.put(date1, (avgSpeed + groupedData.get(date1)) / 2);
                } else {
                    groupedData.put(date1, avgSpeed);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for (Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[]{e.getValue()}));
        }
        container.add(chart);
    }

    /**
     * @param heartRate
     * @throws JAXBException
     * @throws IOException
     * @throws ParseException Constructor for Graphics which, creates a graphic for average heartrate and time
     */
    public Graphics(int heartRate) throws JAXBException, IOException, ParseException {

        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Avg Heartrate (bpm) / Time (mm-yy)", new Color(139, 135, 245));

        Map<Date, Double> groupedData = new TreeMap<>();

        for (TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()) {
            for (ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()) {
                heartRate = 0;
                Date date1 = null;
                int counter = 0;
                for (ActivityLapT activityLapT : activityT.getLap()) {
                    date1 = activityLapT.getStartTime().toGregorianCalendar().getTime();

                    if (activityLapT.getAverageHeartRateBpm() != null) {
                        heartRate = heartRate + (activityLapT.getAverageHeartRateBpm().getValue());
                    } else {
                        heartRate = 0;
                    }
                    counter += 1;
                }

                if (heartRate == 0) {
                    break;
                }

                heartRate = heartRate / counter;
                date1 = new Date(date1.getYear(), date1.getMonth(), 1);
                if (groupedData.containsValue(date1)) {
                    groupedData.put(date1, (heartRate + groupedData.get(date1)) / 2);
                } else {
                    groupedData.put(date1, (double) heartRate);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        for (Map.Entry<Date, Double> e : groupedData.entrySet()) {
            chart.addData(new ModelChart(simpleDateFormat.format(e.getKey()), new double[]{e.getValue()}));
        }
        container.add(chart);
    }

    /**
     * @return a container, so its a getter for a container
     */
    public Container getContainer() {

        return container;
    }

    /**
     * @param container its a setter for a container - so a container looks like the incoming one
     */
    public void setContainer(Container container) {

        this.container = container;
    }

    /**
     * @return a chart, so its a getter for a chart
     */
    public Chart getChart() {

        return chart;
    }

    /**
     * creating a chart, and use it the create an above layout and the grouping.
     * Within that, also the size is defined and the call to action for the application-diagram
     */
    public void initComponents() {

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
