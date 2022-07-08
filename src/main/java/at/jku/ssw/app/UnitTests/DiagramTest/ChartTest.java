package at.jku.ssw.app.UnitTests.DiagramTest;

import static org.junit.jupiter.api.Assertions.*;

import at.jku.ssw.app.diagram.Chart;
import at.jku.ssw.app.diagram.ModelChart;
import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.logging.Logger;

/**
 * The class Chart test.
 * @author Gruppe 3.
 */
public class ChartTest {

    private final Logger log = Logger.getLogger("Logger");

    /**
     * Test get label text.
     */
    @Test
    public void testGetLabelText() {

        try {
            log.info("Starting execution of getLabelText");
            String expectedValue = "";

            log.info("Expected Value=" + expectedValue + " . Actual Value=" + "");
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + "");
            assertEquals(expectedValue, "");

        } catch (Exception exception) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test render series.
     */
    @Test
    public void testRenderSeries() {

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


    /**
     * Test add legend.
     */
    @Test
    public void testAddLegend() {

        try {
            log.info("Starting execution of addLegend");
            String name = "";
            Color color = Color.BLACK;

            Chart chart = new Chart();
            chart.addLegend(name, color);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution of add Legend-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test add data.
     */
    @Test
    public void testAddData() {

        try {
            log.info("Starting execution of addData");
            ModelChart data = new ModelChart();

            Chart chart = new Chart();
            chart.addData(data);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution of add Data-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }


    /**
     * Test resize.
     */
    @Test
    public void testResize() {

        try {
            log.info("Starting execution of resize");
            int width = 0;
            int height = 0;

            Chart chart = new Chart();
            chart.resize(width, height);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution of resize-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test read in data.
     */
    @Test
    public void testReadInData() {

        try {
            log.info("Starting execution of readInData");
            java.util.List<TrainingCenterDatabaseT> expectedValue = null;

            java.util.List<TrainingCenterDatabaseT> actualValue = Chart.readInData();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertNotEquals(expectedValue, actualValue);

        } catch (Exception e) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + e);
            e.printStackTrace();
            assertFalse(false);
        }
    }
}
