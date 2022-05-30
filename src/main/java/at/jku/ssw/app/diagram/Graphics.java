package at.jku.ssw.app.diagram;
import at.jku.ssw.app.Main;
import at.jku.ssw.app.diagram.ModelChart;
import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import java.awt.Color;


import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
        HashMap<Date, Double> hashMap = new HashMap<Date, Double>();

        for (TrainingCenterDatabaseT training : Main.getData()) {

            for (ActivityT activity : training.getActivities().getActivity()) {

                date = activity.getLap().get(0).getStartTime();
                month = date.getMonth();
                year = date.getYear();
                Date newDate = new Date(year, month, 1);

                for (int i = 0; i < activity.getLap().size(); i++) {
                    totalTime += activity.getLap().get(i).getTotalTimeSeconds();
                    if(hashMap.containsKey(newDate)){
                        hashMap.replace(newDate,hashMap.get(newDate) + activity.getLap().get(i).getDistanceMeters());
                    }else{
                        hashMap.put(newDate,  activity.getLap().get(i).getDistanceMeters());
                    }
                }
                if (totalTime != 0) {
                    averageSpeed = distance / totalTime;
                }

                date = null;
                totalTime = 0;
                distance = 0;
                averageSpeed = 0;
            }
        }
        //Daten einlesen
        chart.readInData();
        for (Map.Entry<Date, Double> entry : hashMap.entrySet()){
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
                                .addGap(100, 150, 200)
                                .addComponent(chart, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(100, 150, 200))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100, 150, 200)
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(100, 150, 200))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private at.jku.ssw.app.diagram.Chart chart;
}
