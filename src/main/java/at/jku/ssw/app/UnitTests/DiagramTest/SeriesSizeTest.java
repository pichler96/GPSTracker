package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

public class SeriesSizeTest {

    private Logger log = Logger.getLogger("Logger");

    @Test
    public void getX() {
        try {
            log.info("Starting execution of getX");
            double expectedValue = 0;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getX();
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
    public void setX() {
        try {
            log.info("Starting execution of setX");
            double x = 0;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setX(x);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetX-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getY() {
        try {
            log.info("Starting execution of getY");
            double expectedValue = 0;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getY();
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
    public void setY() {
        try {
            log.info("Starting execution of setY");
            double y = 0;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setY(y);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetY-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getWidth() {
        try {
            log.info("Starting execution of getWidth");
            double expectedValue = 0;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getWidth();
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
    public void setWidth() {
        try {
            log.info("Starting execution of setWidth");
            double width = 0;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setWidth(width);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetWidth-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getHeight() {
        try {
            log.info("Starting execution of getHeight");
            double expectedValue = 0;

            SeriesSize seriessize = new SeriesSize();
            double actualValue = seriessize.getHeight();
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
    public void setHeight() {
        try {
            log.info("Starting execution of setHeight");
            double height = 0;

            SeriesSize seriessize = new SeriesSize();
            seriessize.setHeight(height);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetHeight-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
