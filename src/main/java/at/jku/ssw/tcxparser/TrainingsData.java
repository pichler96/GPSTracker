package at.jku.ssw.tcxparser;

import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingsData {
    private String path;
    private List<TrainingCenterDatabaseT> trainings = new ArrayList<>();
    private List<TrainingCenterDatabaseT> trainingbackup = new ArrayList<>();
    private TcxParser parser = new TcxParser();

    /**
     * Data Object to parse and save a list of TrainingCenterDatabaseT Objects.
     * @param path to parse the .tcx Files.
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public TrainingsData(String path) throws JAXBException, FileNotFoundException {
        this.path = path;
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
     * @throws FileNotFoundException
     * @throws JAXBException
     */
    public void load() throws FileNotFoundException, JAXBException {
        // Reset trainings
        trainings.clear();

        //Parse all files at the path
        File directoryPath = new File(this.path);
        for(File training : directoryPath.listFiles()) {
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
        trainings = trainings.stream().filter(t -> t.getActivities().
                getActivity().
                get(0).
                getSport().
                value().
                equals(sport)).
                collect(Collectors.toList());
    }

    /**
     * Filter the list of TrainingCenterDatabaseT Objects based an the start year.
     * @param year start year
     */
    public void filterStartYear (int year) {
        trainings = trainings.stream().filter(t -> t.getActivities().
                getActivity().
                get(0).
                getLap().
                stream().
                anyMatch(d -> d.getStartTime().getYear() == year)).
                collect(Collectors.toList());
    }

    public void deleteFilter() {
        trainings = trainingbackup;
    }
}
