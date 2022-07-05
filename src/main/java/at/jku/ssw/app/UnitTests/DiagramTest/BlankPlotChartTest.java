package at.jku.ssw.app.UnitTests.DiagramTest;

import at.jku.ssw.app.diagram.blankchart.BlankPlotChart;
import at.jku.ssw.app.diagram.blankchart.BlankPlotChatRender;
import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class BlankPlotChartTest {

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    @Test
    public void getBlankPlotChatRender() {
        try {
            log.info("Starting execution of getBlankPlotChatRender");
            BlankPlotChatRender expectedValue = null;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            BlankPlotChatRender actualValue = blankplotchart.getBlankPlotChatRender();
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
    public void setBlankPlotChatRender() {
        try {
            log.info("Starting execution of setBlankPlotChatRender");
            BlankPlotChatRender blankPlotChatRender = null;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            blankplotchart.setBlankPlotChatRender(blankPlotChatRender);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetBlankPlotChatRender-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void initValues() {
        try {
            log.info("Starting execution of initValues");
            double minValues = 0;
            double maxValues = 0;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            blankplotchart.initValues(minValues, maxValues);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofinitValues-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getRectangle() {
        try {
            log.info("Starting execution of getRectangle");
            SeriesSize expectedValue = null;

            int index = Integer.valueOf(String.valueOf(0));
            double height = Double.valueOf(String.valueOf(0));
            double space = Double.valueOf(String.valueOf(0));
            double startX = Double.valueOf(String.valueOf(0));
            double startY = Double.valueOf(String.valueOf(0));


            BlankPlotChart blankplotchart = new BlankPlotChart();
            SeriesSize actualValue = blankplotchart.getRectangle(index, height, space, startX, startY);
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
    public void getMaxValues() {
        try {
            log.info("Starting execution of getMaxValues");
            Object expectedValue = Double.valueOf(String.valueOf(0));

            BlankPlotChart blankplotchart = new BlankPlotChart();
            double actualValue = blankplotchart.getMaxValues();
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
    public void getSeriesValuesOf() {
        try {
            log.info("Starting execution of getSeriesValuesOf");
            double expectedValue = 0;

            double values = 0;
            double height = 0;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            double actualValue = blankplotchart.getSeriesValuesOf(values, height);
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
    public void setMaxValues() {
        try {
            log.info("Starting execution of setMaxValues");
            double maxValues = 0;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            blankplotchart.setMaxValues(maxValues);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetMaxValues-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getMinValues() {
        try {
            log.info("Starting execution of getMinValues");
            double expectedValue = 0;

            BlankPlotChart blankplotchart = new BlankPlotChart();
            double actualValue = blankplotchart.getMinValues();
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
    public void getLabelCount() {
        try {
            log.info("Starting execution of getLabelCount");
            int expectedValue = 0;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            int actualValue = blankplotchart.getLabelCount();
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
    public void setLabelCount() {
        try {
            log.info("Starting execution of setLabelCount");
            int labelCount = 0;
            BlankPlotChart blankplotchart = new BlankPlotChart();
            blankplotchart.setLabelCount(labelCount);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetLabelCount-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getValuesFormat() {
        try {
            log.info("Starting execution of getValuesFormat");
            String expectedValue = "";

            BlankPlotChart blankplotchart = new BlankPlotChart();
            String actualValue = blankplotchart.getValuesFormat();
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
    public void setValuesFormat() {
        try {
            log.info("Starting execution of setValuesFormat");
            String valuesFormat = "";
            BlankPlotChart blankplotchart = new BlankPlotChart();
            blankplotchart.setValuesFormat(valuesFormat);
            assertTrue(true);

        } catch (Exception exception) {
            log.warning("Exception in execution ofsetValuesFormat-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
