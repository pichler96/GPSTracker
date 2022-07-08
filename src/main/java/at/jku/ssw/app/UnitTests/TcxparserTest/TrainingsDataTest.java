package at.jku.ssw.app.UnitTests.TcxparserTest;

import at.jku.ssw.model.TrainingsData;
import at.jku.ssw.model.schema.ActivityLapT;
import at.jku.ssw.model.schema.ActivityT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainingsDataTest {

    private TrainingsData trainings;

    @BeforeEach
    void setUp() throws JAXBException, FileNotFoundException {
        trainings = new TrainingsData("data");
    }

    @Test
    void newTrainingsData() throws JAXBException, FileNotFoundException {
        assertThrows(NullPointerException.class, () -> new TrainingsData(""));
    }

    @Test
    void getTrainings() {
        assertEquals(trainings.getTrainings().size(), 26);
    }

    @Test
    void load() throws JAXBException, FileNotFoundException {
        TrainingsData tmp = new TrainingsData("data");
        tmp.load();
        assertEquals(trainings, trainings);
    }

    @Test
    void filterSportsHiking() {
        String sport = "Hiking";
        trainings.filterSports(sport);
        for (ActivityT activityT : trainings.getTrainings().get(0).getActivities().getActivity()) {
            assertEquals(activityT.getSport().value(), sport);
        }
    }

    @Test
    void filterSportsRunning() {
        String sport = "Running";
        trainings.filterSports(sport);
        for (ActivityT activityT : trainings.getTrainings().get(0).getActivities().getActivity()) {
            assertEquals(activityT.getSport().value(), sport);
        }
    }

    @Test
    void filterAnyLapDistance() {
        int distance = 4000;
        trainings.filterAnyLapDistance(distance);
        for (ActivityT activityT : trainings.getTrainings().get(0).getActivities().getActivity()) {
            for (ActivityLapT activityLapT : activityT.getLap()) {
                assert(activityLapT.getDistanceMeters() > distance);
            }
        }
    }

    @Test
    void filterStartYear() {
        int year = 2020;
        trainings.filterStartYear(year);
        for (ActivityT activityT : trainings.getTrainings().get(0).getActivities().getActivity()) {
            for (ActivityLapT activityLapT : activityT.getLap()) {
                assertEquals(activityLapT.getStartTime().getYear(), year);
            }
        }
    }

    @Test
    void deleteFilter() throws JAXBException, FileNotFoundException {
        TrainingsData tmp = new TrainingsData("data");
        tmp.filterStartYear(2020);
        tmp.filterAnyLapDistance(2000);
        tmp.filterSports("Running");
        tmp.deleteFilter();
        assertEquals(tmp.getTrainings().get(0).getActivities().getActivity(), tmp.getTrainings().get(0).getActivities().getActivity());
    }

    @Test
    void setPathError() {
        assertThrows(NullPointerException.class, () -> trainings.setPath(new File("x")));
    }
}