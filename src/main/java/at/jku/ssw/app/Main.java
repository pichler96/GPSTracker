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

        // In SwingMain
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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

    public static List<ActivityT> getListOfTracks() {
        return list;
    }

    public static List<TrainingCenterDatabaseT> getData() {
        return data.getTrainings();
    }
}
