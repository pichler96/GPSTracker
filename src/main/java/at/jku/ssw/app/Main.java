package at.jku.ssw.app;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import at.jku.ssw.tcxparser.TrainingsData;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;

/**
 * Main class of the Application in order to start it.
 */
public class Main {

    public static TrainingsData data;

    public static void main(String[] args) {

        try {
            data = new TrainingsData("data");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        // In SwingMain
        EventQueue.invokeLater(() -> {
            SwingMain m = null;
            try {
                m = new SwingMain();
            } catch (JAXBException | IOException | DatatypeConfigurationException | ParseException e) {
                e.printStackTrace();
            }
            if(m!=null) m.setVisible(true);
        });
    }

    /**
     * Method in order to get the Data which is saved as a public static variable in this class.
     * @return the data from the tcx parser.
     */
    public static List<TrainingCenterDatabaseT> getData() {
        return data.getTrainings();
    }
}
