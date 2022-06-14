package at.jku.ssw.app.diagram;
import at.jku.ssw.app.Main;
import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import java.awt.Color;


import javax.swing.*;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Graphics extends javax.swing.JFrame {
    Container container;

    public Graphics() throws JAXBException, IOException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Distance/Time", new Color(245, 135, 236));

        XMLGregorianCalendar date;
        double totalTime=0;
        double distance=0;
        double averageSpeed=0;
        int month;
        int year;
        HashMap<Object, Double> hashMap = new HashMap<>();
        ArrayList dateList = new ArrayList();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()){
                date = activityT.getLap().get(0).getStartTime();
                month = date.getMonth();
                year = date.getYear();
                String dateListObject = month + "." + year;
                dateList.add(dateListObject);
                Comparator <String> comparator;
                dateList.stream().sorted();
                /*dateList.sort(comparator = new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });*/
            }
        }

        for (TrainingCenterDatabaseT training : Main.getData()) {

            for (ActivityT activity : training.getActivities().getActivity()) {

                for (int i = 0; i < activity.getLap().size(); i++) {
                    totalTime += activity.getLap().get(i).getTotalTimeSeconds();
                    if(hashMap.containsKey(dateList.get(i))){
                        hashMap.replace(dateList.get(i),hashMap.get(dateList.get(i)) + activity.getLap().get(i).getDistanceMeters());
                    }else{
                        hashMap.put(dateList.get(i),  activity.getLap().get(i).getDistanceMeters());
                    }
                }
                totalTime = 0;
            }
        }
        //Daten einlesen
        chart.readInData();
        for (Map.Entry<Object, Double> entry : hashMap.entrySet()){
            chart.addData(new ModelChart(entry.getKey().toString(), new double[] {entry.getValue()}));
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

    private at.jku.ssw.app.diagram.Chart chart;
}