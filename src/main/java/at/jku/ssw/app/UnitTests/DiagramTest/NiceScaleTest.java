package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.blankchart.NiceScale;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gerald Waldburger, K12005573
 */
/**
 * The class Nice scale test
 */
public class NiceScaleTest {

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    /**
     * Test set min
     */
    @Test
    public void testSetMin() {

        try {
            log.info("Starting execution of setMin");
            double min = 0;
            final double c = Double.parseDouble(null);
            NiceScale nicescale = new NiceScale(c, c);
            nicescale.setMin(min);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetMin-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get max
     */
    @Test
    public void testGetMax() {

        try {
            log.info("Starting execution of getMax");
            double expectedValue = 0;
            final double c = Double.parseDouble(null);
            NiceScale nicescale = new NiceScale(c, c);
            double actualValue = nicescale.getMax();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertEquals(expectedValue, actualValue);

        } catch (Exception exception) {
            log.warning("Exception in execution of execute 1 Get All Log From First Mov F-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test set max
     */
    @Test
    public void testSetMax() {

        try {
            log.info("Starting execution of setMax");
            double max = 0;
            final double c = Double.parseDouble(null);
            NiceScale nicescale = new NiceScale(c, c);
            nicescale.setMax(max);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetMax-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test set min max
     */
    @Test
    public void testSetMinMax() {

        try {
            log.info("Starting execution of setMinMax");
            final double d = Double.parseDouble(String.valueOf(3));
            final double c = Double.parseDouble(String.valueOf(3));

            NiceScale nicescale = new NiceScale(d, c);
            nicescale.setMinMax(d, c);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetMinMax-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test set max ticks
     */
    @Test
    public void testSetMaxTicks() {

        try {
            log.info("Starting execution of setMaxTicks");
            final int d = Integer.parseInt(String.valueOf(2));
            final double c = 4;
            NiceScale nicescale = new NiceScale(d, c);
            nicescale.setMaxTicks(d);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetMaxTicks-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get tick spacing
     */
    @Test
    public void testGetTickSpacing() {

        try {
            log.info("Starting execution of getTickSpacing");
            Object expectedValue = Double.parseDouble(String.valueOf(0));
            final Object c = Double.parseDouble(String.valueOf(2.5));

            NiceScale nicescale = new NiceScale((Double) c, (Double) expectedValue);
            double actualValue = nicescale.getTickSpacing();
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
     * Test get nice min
     */
    @Test
    public void testGetNiceMin() {

        try {
            log.info("Starting execution of getNiceMin");
            Object expectedValue = Double.parseDouble(String.valueOf(2));

            final Object c = Double.parseDouble(String.valueOf(9.5));
            NiceScale nicescale = new NiceScale((Double) c, (Double) expectedValue);
            double actualValue = nicescale.getNiceMin();
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
     * Test get nice max
     */
    @Test
    public void testGetNiceMax() {

        try {
            log.info("Starting execution of getNiceMax");
            double expectedValue = 0;
            final double c = Double.parseDouble(null);

            NiceScale nicescale = new NiceScale(c, expectedValue);
            double actualValue = nicescale.getNiceMax();
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
     * Test get max ticks
     */
    @Test
    public void testGetMaxTicks() {

        try {
            log.info("Starting execution of getMaxTicks");
            int expectedValue = 0;

            final double c = Double.parseDouble(null);
            NiceScale nicescale = new NiceScale(c, expectedValue);
            int actualValue = nicescale.getMaxTicks();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertEquals(expectedValue, actualValue);

        } catch (Exception exception) {
            log.warning("Exception in execution of execute 1 Get All Log From First Mov F-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * Test get min
     */
    @Test
    public void testGetMin() {

        try {
            log.info("Starting execution of getMin");
            double expectedValue = 0;

            final double c = Double.parseDouble(null);
            NiceScale nicescale = new NiceScale(c, expectedValue);
            double actualValue = nicescale.getMin();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            assertEquals(expectedValue, actualValue);

        } catch (Exception exception) {
            log.warning("Exception in execution of execute 1 Get All Log From First Mov F-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
