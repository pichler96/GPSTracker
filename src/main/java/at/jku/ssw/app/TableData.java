package at.jku.ssw.app;

import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DecimalFormat;
import java.util.List;


public class TableData {


   public static String[][] getTable() {
       String id;
       String sport;
       String date;
       double totalTime=0;
       double distance=0;
       double averageSpeed=0;
       double maxSpeed=0;
       double sumHeartRate=0;
       double counterHeartRate=0;
       int maxHeartRate=0;
       double averageHeartRate= 0;
       int sumCalories=0;


       String [][] table = new String[Main.getData().size()][10];
       int counter=0;

       for (TrainingCenterDatabaseT training : Main.getData()) {


           for (ActivityT activity : training.getActivities().getActivity()) {
               String d= activity.getCreator().getName().substring(24,25);
               if(d.charAt(0)=='D'){
                   id= activity.getCreator().getName().substring(26, activity.getCreator().getName().length()-1);
               }
               else {
                   id = activity.getCreator().getName().substring(24, activity.getCreator().getName().length() - 1);
               }
               sport= activity.getSport().value();

               if(activity.getLap().get(0).getStartTime().getDay()<10){
                   if(activity.getLap().get(0).getStartTime().getMonth()<10){
                       date= activity.getLap().get(0).getStartTime().getYear()+"-0"+activity.getLap().get(0).getStartTime().getMonth()+"-0"+activity.getLap().get(0).getStartTime().getDay();
                   }else{
                       date= activity.getLap().get(0).getStartTime().getYear()+"-"+activity.getLap().get(0).getStartTime().getMonth()+"-0"+activity.getLap().get(0).getStartTime().getDay();
                   }
               }else if(activity.getLap().get(0).getStartTime().getMonth()<10){
                   date= activity.getLap().get(0).getStartTime().getYear()+"-0"+activity.getLap().get(0).getStartTime().getMonth()+"-"+activity.getLap().get(0).getStartTime().getDay();
               }else{
                   date= activity.getLap().get(0).getStartTime().getYear()+"-"+activity.getLap().get(0).getStartTime().getMonth()+"-"+activity.getLap().get(0).getStartTime().getDay();
               }

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
                   sumCalories+= activity.getLap().get(i).getCalories();

               }
               if(totalTime!=0){
                   averageSpeed = Math.round((distance/totalTime)*100.0)/100.0; // in meters per second
               }

               if(counterHeartRate!=0){
                   averageHeartRate= Math.round((sumHeartRate/counterHeartRate)*100.0)/100.0;
               }



               //fill Table with data:
               if(id == null){
                   table[counter][0]= "---";
               }
               else{
                   table[counter][0]= id;
               }

               if(sport == null){
                   table[counter][1]="---";
               }
               else{
                   table[counter][1]=sport;
               }

               if(date==null){
                   table[counter][2]="---";
               }
               else{
                   table[counter][2]=date.toString();
               }


               int input = (int) totalTime;
               final double scale3600 = 1.0/3600;
               final double scale60 = 1.0/60;
               int hh = (int) (input * scale3600);
               int mm = (int) ((input- hh*3600) * scale60);
               int ss = input - mm*60 - hh*3600;
               DecimalFormat format = new DecimalFormat("00");

               table[counter][3]=format.format(hh) + ":" + format.format(mm) + ":" + format.format(ss);

               table[counter][4]=Math.round((distance/1000)*100.0)/100.0+" km";

               table[counter][5]=Math.round((averageSpeed*3.6)*100.0)/100.0+" km/h";

               table[counter][6]=Math.round((maxSpeed*3.6)*100.0)/100.0+" km/h";

               if(averageHeartRate==0){
                   table[counter][7]="---";
               }else {
                   table[counter][7] = Math.round(averageHeartRate * 100.0) / 100.0+" bpm";
               }

               if(maxHeartRate==0){
                   table[counter][8]="---";
               }else{
                   table[counter][8]=maxHeartRate+" bpm";
               }

               if(sumCalories==0){
                   table[counter][9]="---";
               }else{
                   table[counter][9]=sumCalories+" kcal";
               }


               counter ++;

               //reset variables:
               id= null;
               sport=null;
               date= null;
               totalTime=0;
               distance=0;
               averageSpeed=0;
               maxSpeed=0;
               averageHeartRate=0;
               maxHeartRate=0;
               sumHeartRate=0;
               counterHeartRate=0;
               sumCalories=0;

           }



       }

       return table;
    }

   public static String[] getTableColumnNames(){
       return new String[]{"Device-ID", "Sport", "Date", "Total Time", "Distance", "Avg Speed", "Max Speed", "Avg Heartrate", "Max Heartrate", "Calories"};
   }


    public static String [][] getTableOfLaps(int row) {
       int counter=0;
       int sizeCounter=row;
       if (Main.getData().isEmpty()){
           return new String[0][0];
        }
       int size= Main.getData().get(row).getActivities().getActivity().get(0).getLap().size();
       String[][] table = new String[size][7];


        for (TrainingCenterDatabaseT training : Main.getData()) {

            size= Main.getData().get(sizeCounter).getActivities().getActivity().get(0).getLap().size();
            table= new String[size][7];
            //for (ActivityT activity : training.getActivities().getActivity()) {
                ActivityT activity= Main.getData().get(sizeCounter).getActivities().getActivity().get(0);
                for(int i=0; i< activity.getLap().size(); i++){
                    //table[counter][0]= activity.getCreator().getName(); // we decided to not show id/name and sport in this Lap-table
                    //table[counter][1]= activity.getSport().toString();
                    table[counter][0]= activity.getLap().get(i).getStartTime().toString(); //Start Time


                    int input = (int) activity.getLap().get(i).getTotalTimeSeconds();
                    final double scale3600 = 1.0/3600;
                    final double scale60 = 1.0/60;
                    int hh = (int) (input * scale3600);
                    int mm = (int) ((input- hh*3600) * scale60);
                    int ss = input - mm*60 - hh*3600;
                    DecimalFormat format = new DecimalFormat("00");
                    table[counter][1]= format.format(hh) + ":" + format.format(mm) + ":" + format.format(ss); //TotalTime


                    table[counter][2]= Math.round((activity.getLap().get(i).getMaximumSpeed()*3.6)*100.0)/100.0+" km/h";//Max Speed
                    if(activity.getLap().get(i).getMaximumHeartRateBpm()==null) table[counter][3]="---";
                    else if(activity.getLap().get(i).getMaximumHeartRateBpm().getValue()==0) table[counter][3]="---";
                    else table[counter][3]= Integer.toString(activity.getLap().get(i).getMaximumHeartRateBpm().getValue())+" bpm";//Max Heartrate
                    table[counter][4]= Math.round((activity.getLap().get(i).getDistanceMeters()/1000)*100.0)/100.0+" km";//Distance
                    if(activity.getLap().get(i).getAverageHeartRateBpm()==null) table[counter][5]="---";
                    else if(activity.getLap().get(i).getAverageHeartRateBpm().getValue()==0) table[counter][5]="---";
                    else table[counter][5]= Integer.toString(activity.getLap().get(i).getAverageHeartRateBpm().getValue())+" bpm";//Avg Heartrate
                    table[counter][6]= Integer.toString(activity.getLap().get(i).getCalories());//Calories
                    counter++;
                }
                counter=0;
                return table;
        }
        return table;
    }


    public static String[] getTableOfLapsColumnNames(){
        return new String[]{"Start Time", "Total Time", "Max Speed", "Max Heartrate", "Distance", "Avg Heartrate", "Calories"};
    }

}
