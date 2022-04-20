package at.jku.ssw.app;

import at.jku.ssw.tcxparser.Data;
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
    private static Data data;

    public static void main(String[] args) {

        try {
            data = new Data("data");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        for (TrainingCenterDatabaseT training : data.getTrainings()) {
            for (ActivityT activity : training.getActivities().getActivity()) {
                System.out.println(activity.getCreator().getName() + " start " + activity.getSport());
                activity.getLap().forEach(a -> System.out.println("LAP start: " + a.getStartTime() + " for TotalTime " + a.getTotalTimeSeconds() + " Distance: " +a.getDistanceMeters() + " Speed: " + a.getMaximumSpeed()));
                System.out.println();
            }
        }

        EventQueue.invokeLater(() -> {
            SwingMain m= null;
            try {
                m = new SwingMain();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
            assert m != null;
            m.setVisible(true);
       });
    }

    public static List<ActivityT> getListOfTracks(){
        return list;
    }

    public static List<TrainingCenterDatabaseT> getData() {
        return data.getTrainings();
    }
}
