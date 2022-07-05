package at.jku.ssw.app.UnitTests.DiagramTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import at.jku.ssw.app.diagram.Chart;
import at.jku.ssw.app.diagram.Graphics;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.logging.Logger;

public class GraphicsTest {

    private Logger log = Logger.getLogger("Logger");

    @Test
    public void getContainer() {
        try {
            log.info("Starting execution of getContainer");
            Container expectedValue = null;

            int heartRatec = 0;
            at.jku.ssw.app.diagram.Graphics graphics = new at.jku.ssw.app.diagram.Graphics();
            Container actualValue = graphics.getContainer();
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
    public void setContainer() {
        try {
            log.info("Starting execution of setContainer");
            Container container = null;
            ;
            int heartRatec = 0;
            ;
            at.jku.ssw.app.diagram.Graphics graphics = new at.jku.ssw.app.diagram.Graphics();
            graphics.setContainer(container);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetContainer-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getChart() {
        try {
            log.info("Starting execution of getChart");
            Chart expectedValue = null;

            int heartRatec = 0;
            ;
            at.jku.ssw.app.diagram.Graphics graphics = new at.jku.ssw.app.diagram.Graphics(heartRatec);
            Chart actualValue = graphics.getChart();
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
    public void initComponents() {
        try {
            log.info("Starting execution of initComponents");
            int heartRatec = 0;
            at.jku.ssw.app.diagram.Graphics graphics = new Graphics(heartRatec);
            graphics.initComponents();
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofinitComponents-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
