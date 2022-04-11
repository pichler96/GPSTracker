package at.jku.ssw.app;

import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;


public class TableData {


   public static String[][] getTable() throws JAXBException, IOException {
       String name = null;
       String sport = null;
       XMLGregorianCalendar startTime = null;
       double totalTime=0;
       double distance=0;
       double averageSpeed=0;
       double maxSpeed=0;
       int sumHeartRate=0;
       int counterHeartRate=0;
       int maxHeartRate=0;
       double averageHeartRate= 0;


       String [][] table = new String[Main.loadData().size()][9];
       int counter=0;

       for (TrainingCenterDatabaseT training : Main.loadData()) {


           for (ActivityT activity : training.getActivities().getActivity()) {
               name= activity.getCreator().getName();
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
               if(name == null){
                   table[counter][0]= "noName";
               }
               else{
                   table[counter][0]= name;
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
               name= null;
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

}
