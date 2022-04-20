package at.jku.ssw.tcxparser;

import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<TrainingCenterDatabaseT> trainings = new ArrayList<>();
    private TcxParser parser = new TcxParser();

    /**
     * Data Object to parse and save a list of TrainingCenterDatabaseT Objects.
     * @param path to parse the .tcx Files.
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public Data(String path) throws JAXBException, FileNotFoundException {
        load(path);
    }

    /**
     *
     * @return a list of TrainingCenterDatabaseT Objects
     */
    public List<TrainingCenterDatabaseT> getTrainings() {
        return trainings;
    }

    /**
     * Clear and Reload the List of TrainingCenterDatabaseT Objects.
     * @param path to parse the .tcx Files.
     * @throws FileNotFoundException
     * @throws JAXBException
     */
    public void load(String path) throws FileNotFoundException, JAXBException {
        // Reset trainings
        trainings.clear();

        //Parase all files at the path
        File directoryPath = new File("data");
        for(File training : directoryPath.listFiles()) {
            if (FilenameUtils.getExtension(training.getName()).equals("tcx")) {
                trainings.add(parser.parseTCX(new FileInputStream(training.getPath())));
            }
        }
    }
}
