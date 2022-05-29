package at.jku.ssw.app;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import at.jku.ssw.tcxparser.TrainingsData;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

public class Main {

    public static TrainingsData data;

    public static void main(String[] args) {

        try {
            data = new TrainingsData("data");
            // TODO: Add in UI:
            // File: Reload -> data.load()
            //       Delete Filter -> data.deleteFilter()

            // Sports: Running, Biking, Hiking, Skiing, -> data.filterSports("Running"); ...

            // Years: 2020, 2021,...  data.filterStartYear(2020) ...;

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        // In SwingMain
        EventQueue.invokeLater(() -> {
            SwingMain m = null;
            try {
                m = new SwingMain();
            } catch (JAXBException | IOException | DatatypeConfigurationException e) {
                e.printStackTrace();
            }
            if(m!=null) m.setVisible(true);
        });
    }


    public static List<TrainingCenterDatabaseT> getData() {
        return data.getTrainings();
    }
}
