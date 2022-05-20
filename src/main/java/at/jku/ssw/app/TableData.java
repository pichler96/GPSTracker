package at.jku.ssw.app;

import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TableData {


   public static String[][] getTable() {
       String id;
       String sport;
       XMLGregorianCalendar startTime;
       double totalTime=0;
       double distance=0;
       double averageSpeed=0;
       double maxSpeed=0;
       double sumHeartRate=0;
       double counterHeartRate=0;
       int maxHeartRate=0;
       double averageHeartRate= 0;


       String [][] table = new String[Main.getData().size()][9];
       int counter=0;

       for (TrainingCenterDatabaseT training : Main.getData()) {


           for (ActivityT activity : training.getActivities().getActivity()) {
               id= activity.getCreator().getName();
               sport= activity.getSport().value();
               startTime= activity.getLap().get(0).getStartTime();

               for(int i=0; i< activity.getLap().size(); i++){
                   totalTime+= activity.getLap().get(i).getTotalTimeSeconds();
                   distance+= activity.getLap().get(i).getDistanceMeters();
                   if(maxSpeed< activity.getLap().get(i).getMaximumSpeed()){
                       maxSpeed=activity.getLap().get(i).getMaximumSpeed();
                   }
                   if(activity.getLap().get(i).getAverageHeartRateBpm()!= null){
                       sumHeartRate += activity.getLap().get(i).getAverageHeartRateBpm().getValue();
                       counterHeartRate++;
                   }

                   if(activity.getLap().get(i).getMaximumHeartRateBpm()!=null){
                       if(maxHeartRate< activity.getLap().get(i).getMaximumHeartRateBpm().getValue()){
                           maxHeartRate=activity.getLap().get(i).getMaximumHeartRateBpm().getValue();
                       }
                   }
               }
               if(totalTime!=0){
                   averageSpeed = distance/totalTime; // in meters per second
               }

               if(counterHeartRate!=0){
                   averageHeartRate= sumHeartRate/counterHeartRate;
               }



               //fill Table with data:
               if(id == null){
                   table[counter][0]= "noName";
               }
               else{
                   table[counter][0]= id;
               }

               if(sport == null){
                   table[counter][1]="noSport";
               }
               else{
                   table[counter][1]=sport;
               }

               if(startTime==null){
                   table[counter][2]="noStartTime";
               }
               else{
                   table[counter][2]=startTime.toString();
               }

               table[counter][3]=Double.toString(totalTime);

               table[counter][4]=Double.toString(distance);

               table[counter][5]=Double.toString(averageSpeed);

               table[counter][6]=Double.toString(maxSpeed);

               table[counter][7]=Double.toString(averageHeartRate);

               table[counter][8]=Integer.toString(maxHeartRate);

               counter ++;

               //reset variables:
               id= null;
               sport=null;
               startTime= null;
               totalTime=0;
               distance=0;
               averageSpeed=0;
               maxSpeed=0;
               averageHeartRate=0;
               maxHeartRate=0;
               sumHeartRate=0;
               counterHeartRate=0;

           }



       }

       return table;
    }

   public static String[] getTableColumnNames(){
       return new String[]{"ID", "Sport", "Start Time", "Total Time", "Distance", "Avg Speed", "Max Speed", "Avg Heartrate", "Max Heartrate"};
   }

    public static String [][] getTableOfLaps() {
       int counter=0;
       int size= Main.getData().get(0).getActivities().getActivity().get(0).getLap().size();
       String[][] table = new String[size][7];


        for (TrainingCenterDatabaseT training : Main.getData()) {
            for (ActivityT activity : training.getActivities().getActivity()) {
                for(int i=0; i< activity.getLap().size(); i++){
                    //table[counter][0]= activity.getCreator().getName(); // we decided to not show id/name and sport in this Lap-table
                    //table[counter][1]= activity.getSport().toString();
                    table[counter][0]= activity.getLap().get(i).getStartTime().toString(); //Start Time
                    table[counter][1]= Double.toString(activity.getLap().get(i).getTotalTimeSeconds()); //TotalTime
                    table[counter][2]= Double.toString(activity.getLap().get(i).getMaximumSpeed());//Max Speed
                    table[counter][3]= Integer.toString(activity.getLap().get(i).getMaximumHeartRateBpm().getValue());//Max Heartrate
                    table[counter][4]= Double.toString(activity.getLap().get(i).getDistanceMeters());//Distance
                    table[counter][5]= Integer.toString(activity.getLap().get(i).getAverageHeartRateBpm().getValue());//Avg Heartrate
                    table[counter][6]= Integer.toString(activity.getLap().get(i).getCalories());//Calories
                    counter++;
                }
                return table;
            }


        }
        return table;
    }

    public static String[] getTableOfLapsColumnNames(){
        return new String[]{"Start Time", "Total Time", "Max Speed", "Max Heartrate", "Distance", "Avg Heartrate", "Calories"};
    }
}
