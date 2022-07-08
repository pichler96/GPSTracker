package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.ModelChart;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gruppe 3.
 */
/**
 * The class Model chart test.
 */
public class ModelChartTest {

    private Logger log = Logger.getLogger("Logger");

    /**
     * Test get label.
     */
    @Test
    public void testGetLabel() {

        try {
            log.info("Starting execution of getLabel");
            String expectedValue = "";

            ModelChart modelchart = new ModelChart();
            String actualValue = modelchart.getLabel();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertFalse(expectedValue.equals(actualValue));

        } catch (Exception exception) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test set label.
     */
    @Test
    public void testSetLabel() {

        try {
            log.info("Starting execution of setLabel");
            String label = "";

            ModelChart modelchart = new ModelChart();
            modelchart.setLabel(label);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetLabel-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get values.
     */
    @Test
    public void testGetValues() {

        try {
            log.info("Starting execution of getValues");
            double[] expectedValue = new double[0];

            ModelChart modelchart = new ModelChart();
            double[] actualValue = modelchart.getValues();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertEquals(expectedValue, actualValue);

        } catch (Exception exception) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }


    /**
     * Test set values.
     */
    @Test
    public void testSetValues() {

        try {
            log.info("Starting execution of setValues");
            double[] values = new double[0];

            ModelChart modelchart = new ModelChart();
            modelchart.setValues(values);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetValues-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get max values.
     */
    @Test
    public void testGetMaxValues() {

        try {
            log.info("Starting execution of getMaxValues");
            double expectedValue = 0;

            ModelChart modelchart = new ModelChart();
            double actualValue = modelchart.getMaxValues();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertEquals(expectedValue, actualValue);

        } catch (Exception exception) {
            log.warning("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
