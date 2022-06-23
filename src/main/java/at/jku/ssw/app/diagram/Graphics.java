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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Graphics extends javax.swing.JFrame {
    Container container;

    public Graphics() throws JAXBException, IOException, ParseException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Distance/Time", new Color(135, 245, 153));

        XMLGregorianCalendar date;
        double totalTime=0;
        double distance=0;
        double averageSpeed=0;
        int month;
        int year;
        HashMap<Object, Double> hashMap = new HashMap<>();
        ArrayList dateList = new ArrayList();
        ArrayList newDateList = new ArrayList();

        for(TrainingCenterDatabaseT trainingCenterDatabaseT : Main.getData()){
            for(ActivityT activityT : trainingCenterDatabaseT.getActivities().getActivity()){
                date = activityT.getLap().get(0).getStartTime();
                dateList.add(date);
                Collections.sort(dateList, new Comparator<XMLGregorianCalendar>() {
                    @Override
                    public int compare(XMLGregorianCalendar o1, XMLGregorianCalendar o2) {
                        if(o1.getYear() < o2.getYear()){
                            return -1;
                        }
                        else if(o1.getYear() == o2.getYear()){
                            if(o1.getMonth() < o2.getMonth()){
                                return -1;
                            }
                            else if(o1.getMonth() > o2.getMonth()){
                                return 1;
                            }
                        }
                        return 1;
                    }
                });
                month = date.getMonth();
                year = date.getYear();
                String dateObject = getDateTime(date, month + "." + year);
                newDateList.add(dateObject);
            }
        }


        for (TrainingCenterDatabaseT training : Main.getData()) {

            for (ActivityT activity : training.getActivities().getActivity()) {

                for (int i = 0; i < activity.getLap().size(); i++) {
                    totalTime += activity.getLap().get(i).getTotalTimeSeconds();
                    if(hashMap.containsKey(newDateList.get(i))){
                        hashMap.replace(newDateList.get(i),hashMap.get(newDateList.get(i)) + activity.getLap().get(i).getDistanceMeters());
                    }else{
                        hashMap.put(newDateList.get(i),  activity.getLap().get(i).getDistanceMeters());
                    }
                }
                totalTime = 0;
            }
        }

        chart.readInData();
        sort(hashMap);
        for (Map.Entry<Object, Double> entry : hashMap.entrySet()){
            chart.addData(new ModelChart(entry.getKey().toString(), new double[] {entry.getValue()}));
        }
        container.add(chart);
    }

    public static LinkedHashMap sort(HashMap<Object, Double> hashMap) {
        return (LinkedHashMap) hashMap;
    }

    public static String getDateTime(XMLGregorianCalendar gDate, String pattern){

        return Optional.ofNullable(gDate)
                .map(gdate -> {
                    Calendar calendar = gDate.toGregorianCalendar();
                    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                    formatter.setTimeZone(calendar.getTimeZone());
                    return formatter.format(calendar.getTime());
                })
                .orElse(null);
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