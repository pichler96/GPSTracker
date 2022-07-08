package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.ModelLegend;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class Model legend test.
 * @author Gruppe 3.
 */
public class ModelLegendTest {

    private final Logger log = Logger.getLogger("Logger");

    /**
     * Test get name.
     */
    @Test
    public void testGetName() {

        try {
            log.info("Starting execution of getName");
            String expectedValue = "";

            ModelLegend modellegend = new ModelLegend();
            String actualValue = modellegend.getName();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertFalse(expectedValue.equals(actualValue));

        } catch (Exception exception) {
            log.warning("Exception in execution of execute Get All Log From First Mov F-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test set name.
     */
    @Test
    public void testSetName() {

        try {
            log.info("Starting execution of setName");
            String name = "Karl";

            ModelLegend modellegend = new ModelLegend();
            modellegend.setName(name);
            assertTrue(name.equals(modellegend.getName()));

        } catch (Exception exception) {
            log.warning("Exception in execution of set Name-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get color.
     */
    @Test
    public void testGetColor() {

        try {
            log.info("Starting execution of getColor");
            Color expectedValue = Color.BLACK;

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

    /**
     * Test set color.
     */
    @Test
    public void testSetColor() {

        try {
            log.info("Starting execution of setColor");
            Color color = Color.BLACK;

            ModelLegend modellegend = new ModelLegend();
            modellegend.setColor(color);
            assertTrue(color.equals(modellegend.getColor()));

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetColor-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
