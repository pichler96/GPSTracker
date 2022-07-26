package at.jku.ssw.model;

import at.jku.ssw.model.schema.TrainingCenterDatabaseT;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data model of the Application, includes a list of TrainingCenterDatabaseT Objects.
 *
 * @author Gruppe 3
 */

public class TrainingsData {
    private File directoryPath;
    private List<TrainingCenterDatabaseT> trainings = new ArrayList<>();
    private List<TrainingCenterDatabaseT> trainingbackup = new ArrayList<>();
    private final TcxParser parser = new TcxParser();

    /**
     * Data Object to parse and save a list of TrainingCenterDatabaseT Objects.
     * @param path to parse the .tcx Files.
     */
    public TrainingsData(String path) throws JAXBException, FileNotFoundException {
        this.directoryPath = new File("C:\\UE04\\GPSTracker\\data");
        load();
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
     */
    public void load() throws FileNotFoundException, JAXBException {
        // Reset trainings
        trainings.clear();

        //Parse all files at the path
        for(File training : Objects.requireNonNull(directoryPath.listFiles())) {
            if (FilenameUtils.getExtension(training.getName()).equals("tcx")) {
                trainings.add(parser.parseTCX(new FileInputStream(training.getPath())));
            }
        }
        trainingbackup = trainings;
    }

    /**
     * Filter the list of TrainingCenterDatabaseT Objects based an the sport type.
     * @param sport sport type
     */
    public void filterSports (String sport) {
        trainings = trainingbackup.stream().filter(t -> t.getActivities().
                getActivity().
                get(0).
                getSport().
                value().
                equals(sport)).
                collect(Collectors.toList());
    }


    /**
     * Filter the list of TrainingCenterDatabaseT Objects based an the Distance of the Laps.
     * @param distance of the Laps
     */
    public void filterAnyLapDistance (double distance) {
        trainings = trainingbackup.stream().filter(t -> t.getActivities().
                getActivity().
                get(0).
                getLap().
                stream().
                allMatch(d -> d.getDistanceMeters() > distance)).
                collect(Collectors.toList());
    }

    /**
     * Filter the list of TrainingCenterDatabaseT Objects based an the start year.
     * @param year start year
     */
    public void filterStartYear (int year) {
        trainings = trainingbackup.stream().filter(t -> t.getActivities().
                getActivity().
                get(0).
                getLap().
                stream().
                allMatch(d -> d.getStartTime().getYear() == year)).
                collect(Collectors.toList());
    }

    public void deleteFilter() {
        trainings = trainingbackup;
    }

    public void setPath(File path) throws JAXBException, FileNotFoundException {
        this.directoryPath = path;
        load();
    }
}
