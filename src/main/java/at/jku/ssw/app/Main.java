package at.jku.ssw.app;

import at.jku.ssw.tcxparser.TcxParser;
import at.jku.ssw.tcxparser.schema.ActivityT;

import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.apache.commons.io.FilenameUtils;


public class Main {

    private static List<ActivityT> list;

    public static void main(String[] args) {

        try {
            for (TrainingCenterDatabaseT training : loadData()) {
                for (ActivityT activity : training.getActivities().getActivity()) {
                    System.out.println(activity.getCreator().getName() + " start " + activity.getSport());
                    activity.getLap().forEach(a -> System.out.println("LAP start: " + a.getStartTime() + " for TotalTime " + a.getTotalTimeSeconds() + " Distance: " +a.getDistanceMeters() + " Speed: " + a.getMaximumSpeed()));
                    System.out.println();
                }

            }

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // In SwingMain
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                SwingMain m = null;
                try {
                    m = new SwingMain();
                } catch (JAXBException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.setVisible(true);
           }
        });
    }

    public static List<ActivityT> getListOfTracks(){
        return list;
    }

    public static List<TrainingCenterDatabaseT> loadData() throws JAXBException, IOException {
        TcxParser parser = new TcxParser();
        List<TrainingCenterDatabaseT> trainings = new ArrayList<>();

        //Parase all files in data
        File directoryPath = new File("data");

        //List of all files and directories
        for(File training : directoryPath.listFiles()) {
            if (FilenameUtils.getExtension(training.getName()).equals("tcx")) {
                trainings.add(parser.parseTCX(new FileInputStream(training.getPath())));
            }
        }
        return trainings;
    }
}
