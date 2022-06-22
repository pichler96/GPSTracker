package at.jku.ssw.app;

import at.jku.ssw.tcxparser.schema.ActivityT;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * This class has methods for the two tables in the GUI.
 * These Methods get the data which should be displayed in the tables and
 * puts it in an appropriate datatype in order to be able to work with it in SwingMain.
 */
public class TableData {

    /**
     * This method defines the data(, of all tracks,) in the track-table on the west/left side of the GUI.
     * At first, it iterates over the data-files, some information can be accessed directly over its activity,
     * other information is reached by iteration over all laps from that certain activity and e.g. add the values up.
     * @return The data is returned as a String array (matrix), since there is a String array needed in order to create a JTable.
     */
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

       if(Main.getData().isEmpty()){
           return new String[0][0];
       }
       String [][] table = new String[Main.getData().size()][10];
       int counter=0;
       //iterate over the data-Files
       for (TrainingCenterDatabaseT training : Main.getData()) {
           //iterate over the activities/GPS-tracks
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
               //iterate over the laps
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
               table[counter][0] = Objects.requireNonNullElse(id, "---");
               table[counter][1] = Objects.requireNonNullElse(sport, "---");
               table[counter][2]= date;

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

    /**
     * This method defines the headers for each column in the track-table on the west/left side of the GUI.
     * @return The headers are returned as a String array, since there is a String array needed in order to create a JTable.
     */
   public static String[] getTableColumnNames(){
       return new String[]{"Device-ID", "Sport", "Date", "Total Time", "Distance", "Avg Speed", "Max Speed", "Avg Heartrate", "Max Heartrate", "Calories"};
   }

    /**
     * This method defines the data(, of all laps of a certain track,) in the lap-table on the east/right side of the GUI.
     * @param row is used to access the correct track, which laps should be returned.
     * @return The data is returned as a String array (matrix), since there is a String array needed in order to create a JTable.
     */
    public static String [][] getTableOfLaps(int row) {
       int counter=0;
        if (Main.getData().isEmpty()){
           return new String[0][0];
        }
       int size= Main.getData().get(row).getActivities().getActivity().get(0).getLap().size();
       String[][] table;
       size= Main.getData().get(row).getActivities().getActivity().get(0).getLap().size();
       table= new String[size][7];
       //activity: the track, which laps should be displayed
        ActivityT activity= Main.getData().get(row).getActivities().getActivity().get(0);
        //iterate over the laps of that certain track
        for(int i=0; i< activity.getLap().size(); i++){
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
        return table;
    }

    /**
     * This method defines the headers for each column in the lap-table on the east/right side of the GUI.
     * @return The headers are returned as a String array, since there is a String array needed in order to create a JTable.
     */
    public static String[] getTableOfLapsColumnNames(){
        return new String[]{"Start Time", "Total Time", "Max Speed", "Max Heartrate", "Distance", "Avg Heartrate", "Calories"};
    }

}
