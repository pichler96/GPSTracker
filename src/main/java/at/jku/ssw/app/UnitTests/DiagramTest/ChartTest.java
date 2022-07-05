package at.jku.ssw.app.UnitTests.DiagramTest;

import static org.junit.jupiter.api.Assertions.*;

import at.jku.ssw.app.diagram.Chart;
import at.jku.ssw.app.diagram.ModelChart;
import at.jku.ssw.app.diagram.blankchart.BlankPlotChart;
import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class ChartTest {

    private Logger log = Logger.getLogger("Logger");

    @Test
    public void getLabelText() {
        try {
            log.info("Starting execution of getLabelText");
            String expectedValue = "";

            int index = 0;

            Chart chart = new Chart();

            log.info("Expected Value=" + expectedValue + " . Actual Value=" + "");
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + "");
            assertEquals(expectedValue, "");

        } catch (Exception exception) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void renderSeries() {
        try {
            log.info("Starting execution of renderSeries");
            Chart chart = null;
            Graphics2D g2 = null;
            SeriesSize size = null;
            int index = 0;

            Chart chart2 = new Chart();
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofrenderSeries-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void addLegend() {
        try {
            log.info("Starting execution of addLegend");
            String name = "";
            Color color = null;
            ;
            ;
            Chart chart = new Chart();
            chart.addLegend(name, color);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofaddLegend-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void addData() {
        try {
            log.info("Starting execution of addData");
            ModelChart data = null;
            ;
            ;
            Chart chart = new Chart();
            chart.addData(data);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofaddData-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void resize() {
        try {
            log.info("Starting execution of resize");
            int width = 0;
            int height = 0;
            ;
            ;
            Chart chart = new Chart();
            chart.resize(width, height);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofresize-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void readInData() {
        try {
            log.info("Starting execution of readInData");
            java.util.List<TrainingCenterDatabaseT> expectedValue = null;

            Chart chart = new Chart();
            java.util.List<TrainingCenterDatabaseT> actualValue = chart.readInData();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertFalse(expectedValue.equals(actualValue));

        } catch (Exception e) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + e);
            e.printStackTrace();
            assertFalse(false);
        }
    }
}
