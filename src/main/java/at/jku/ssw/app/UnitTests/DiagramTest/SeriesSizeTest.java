package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

/**
 * The class Series size test.
 * @author Gruppe 3.
 */
public class SeriesSizeTest {

    private Logger log = Logger.getLogger("Logger");

/**
 *
 * Test get X.
 *
 */
    @Test
    public void testGetX() {

        try {
            log.info("Starting execution of getX");
            double expectedValue = 1.3;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getX();
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
 *
 * Test set X.
 *
 */
    @Test
    public void testSetX() {

        try {
            log.info("Starting execution of setX");
            double x = 1.3;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setX(x);
            assertTrue(x == seriessize.getX());

        } catch (Exception exception) {
            log.warning("Exception in execution of setX-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

/**
 *
 * Test get Y.
 *
 */
    @Test
    public void testGetY() {

        try {
            log.info("Starting execution of getY");
            double expectedValue = 5.2;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getY();
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
 *
 * Test set Y.
 *
 */
    @Test
    public void testSetY() {

        try {
            log.info("Starting execution of setY");
            double y = 5.2;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setY(y);
            assertTrue(y == seriessize.getY());

        } catch (Exception exception) {
            log.warning("Exception in execution of setY-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

/**
 *
 * Test get width.
 *
 */
    @Test
    public void testGetWidth() {

        try {
            log.info("Starting execution of getWidth");
            double expectedValue = 5.1;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getWidth();
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
 *
 * Test set width.
 *
 */
    @Test
    public void testSetWidth() {

        try {
            log.info("Starting execution of setWidth");
            double width = 5.1;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setWidth(width);
            assertTrue(width == seriessize.getWidth());

        } catch (Exception exception) {
            log.warning("Exception in execution of setWidth-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

/**
 *
 * Test get height.
 *
 */
    @Test
    public void testGetHeight() {

        try {
            log.info("Starting execution of getHeight");
            double expectedValue = 12;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getHeight();
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
 *
 * Test set height.
 *
 */
    @Test
    public void testSetHeight() {

        try {
            log.info("Starting execution of setHeight");
            double height = 12;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setHeight(height);
            assertTrue(height == seriessize.getHeight());

        } catch (Exception exception) {
            log.warning("Exception in execution of setHeight-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
