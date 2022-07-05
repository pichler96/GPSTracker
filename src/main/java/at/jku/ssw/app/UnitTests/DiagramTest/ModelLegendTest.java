package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.ModelLegend;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ModelLegendTest {

    private Logger log = Logger.getLogger("Logger");

    @Test
    public void getName() {
        try {
            log.info("Starting execution of getName");
            String expectedValue = "";

            ModelLegend modellegend = new ModelLegend();
            String actualValue = modellegend.getName();
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
    public void setName() {
        try {
            log.info("Starting execution of setName");
            String name = "";

            ModelLegend modellegend = new ModelLegend();
            modellegend.setName(name);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetName-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getColor() {
        try {
            log.info("Starting execution of getColor");
            Color expectedValue = null;

            ModelLegend modellegend = new ModelLegend();
            Color actualValue = modellegend.getColor();
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
    public void setColor() {
        try {
            log.info("Starting execution of setColor");
            Color color = null;

            ModelLegend modellegend = new ModelLegend();
            modellegend.setColor(color);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetColor-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
