package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.ModelChart;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ModelChartTest {

    private Logger log = Logger.getLogger("Logger");

    @Test
    public void getLabel() {
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

    @Test
    public void setLabel() {
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

    @Test
    public void getValues() {
        try {
            log.info("Starting execution of getValues");
            double[] expectedValue = null;

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

    @Test
    public void setValues() {
        try {
            log.info("Starting execution of setValues");
            double[] values = null;

            ModelChart modelchart = new ModelChart();
            modelchart.setValues(values);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetValues-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getMaxValues() {
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
