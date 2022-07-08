package at.jku.ssw.app.diagram.blankchart;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 * Creates a chart without any values and setup.
 *
 * @author Gruppe 3.
 */
public class BlankPlotChart extends JComponent {

    /**
     * Is a format defined for the blankplot
     */
    private final DecimalFormat format = new DecimalFormat("#,##0.##");
    /**
     * an instance of the niceScale
     */
    private NiceScale niceScale;
    /**
     * is a double value of a maximum value
     */
    private double maxValues;
    /**
     * is a double value of a minimum value
     */
    private double minValues;
    /**
     * a counter for all labels, to how many labels are accurate.
     */
    private int labelCount;
    /**
     * Format for the value to compare
     */
    private String valuesFormat = "#,##0.##";
    /**
     * an instance of the interface of blankblotchart
     */
    private BlankPlotChatRender blankPlotChatRender;


    /**
     * Gets the blank plot chat render.
     *
     * @return the blank plot chat render.
     */
    public BlankPlotChatRender getBlankPlotChatRender() {

        return blankPlotChatRender;
    }


    /**
     * Sets the blank plot chat render.
     *
     * @param blankPlotChatRender the blank plot chat render.
     */
    public void setBlankPlotChatRender(BlankPlotChatRender blankPlotChatRender) {

        this.blankPlotChatRender = blankPlotChatRender;
    }


    /**
     * Gets the max values.
     *
     * @return the max values.
     */
    public double getMaxValues() {

        return maxValues;
    }


    /**
     * Sets the max values.
     *
     * @param maxValues the max values.
     */
    public void setMaxValues(double maxValues) {

        this.maxValues = maxValues;
        niceScale.setMax(maxValues);
        repaint();
    }


    /**
     * Gets the min values.
     *
     * @return the min values.
     */
    public double getMinValues() {

        return minValues;
    }


    /**
     * Gets the label count.
     *
     * @return the label count.
     */
    public int getLabelCount() {

        return labelCount;
    }


    /**
     * Sets the label count.
     *
     * @param labelCount the label count.
     */
    public void setLabelCount(int labelCount) {

        this.labelCount = labelCount;
    }


    /**
     * Gets the values format.
     *
     * @return the values format.
     */
    public String getValuesFormat() {

        return valuesFormat;
    }


    /**
     * Sets the values format.
     *
     * @param valuesFormat the values format.
     */
    public void setValuesFormat(String valuesFormat) {

        this.valuesFormat = valuesFormat;
        format.applyPattern(valuesFormat);
    }

    /**
     * creates Blank plot chart.
     */
    public BlankPlotChart() {

        setBackground(Color.WHITE);
        setOpaque(false);
        setForeground(new Color(100, 100, 100));
        setBorder(new EmptyBorder(20, 10, 10, 10));
        init();
    }


    /**
     * Init some values.
     */
    private void init() {

        initValues(0, 10);
    }


    /**
     * Init values.
     *
     * @param minValues the min values.
     * @param maxValues the max values.
     */
    public void initValues(double minValues, double maxValues) {

        this.minValues = minValues;
        this.maxValues = maxValues;
        niceScale = new NiceScale(minValues, maxValues);
        repaint();
    }

    /**
     * A paint methode for a graphic.
     *
     * @param grphcs is a graphics, which needs to be painted.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (niceScale != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            createLine(g2);
            createValues(g2);
            createLabelText(g2);
            renderSeries(g2);
        }
    }


    /**
     * Create line.
     *
     * @param g2 the g2.
     */
    private void createLine(Graphics2D g2) {

        g2.setColor(new Color(220, 220, 220));
        Insets insets = getInsets();
        double textHeight = getLabelTextHeight(g2);
        double height = getHeight() - (insets.top + insets.bottom) - textHeight;
        double space = height / niceScale.getMaxTicks();
        double locationY = insets.bottom + textHeight;
        double textWidth = getMaxValuesTextWidth(g2);
        double spaceText = 5;
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            int y = (int) (getHeight() - locationY);
            g2.drawLine((int) (insets.left + textWidth + spaceText), y, getWidth() - insets.right, y);
            locationY += space;
        }

    }


    /**
     * Create values.
     *
     * @param g2 the g2.
     */
    private void createValues(Graphics2D g2) {

        g2.setColor(getForeground());
        Insets insets = getInsets();
        double textHeight = getLabelTextHeight(g2);
        double height = getHeight() - (insets.top + insets.bottom) - textHeight;
        double space = height / niceScale.getMaxTicks();
        double valuesCount = niceScale.getNiceMin();
        double locationY = insets.bottom + textHeight;
        FontMetrics ft = g2.getFontMetrics();
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            String text = format.format(valuesCount);
            Rectangle2D r2 = ft.getStringBounds(text, g2);
            double stringY = r2.getCenterY() * -1;
            double y = getHeight() - locationY + stringY;
            g2.drawString(text, insets.left, (int) y);
            locationY += space;
            valuesCount += niceScale.getTickSpacing();
        }
    }


    /**
     * Create label text.
     *
     * @param g2 the g2.
     */
    private void createLabelText(Graphics2D g2) {

        if (labelCount > 0) {
            Insets insets = getInsets();
            double textWidth = getMaxValuesTextWidth(g2);
            double spaceText = 5;
            double width = getWidth() - insets.left - insets.right - textWidth - spaceText;
            double space = width / labelCount;
            double locationX = insets.left + textWidth + spaceText;
            double locationText = getHeight() - insets.bottom;
            FontMetrics ft = g2.getFontMetrics();
            for (int i = 0; i < labelCount; i++) {
                double centerX = ((locationX + space / 2));
                g2.setColor(getForeground());
                String text = getChartText(i);
                Rectangle2D r2 = ft.getStringBounds(text, g2);
                double textX = centerX - r2.getWidth() / 2;
                g2.drawString(text, (int) textX, (int) locationText);
                locationX += space;
            }
        }
    }


    /**
     * Render series.
     *
     * @param g2 the g2.
     */
    private void renderSeries(Graphics2D g2) {

        if (blankPlotChatRender != null) {
            Insets insets = getInsets();
            double textWidth = getMaxValuesTextWidth(g2);
            double textHeight = getLabelTextHeight(g2);
            double spaceText = 5;
            double width = getWidth() - insets.left - insets.right - textWidth - spaceText;
            double height = getHeight() - insets.top - insets.bottom - textHeight;
            double space = width / labelCount;
            double locationX = insets.left + textWidth + spaceText;
            for (int i = 0; i < labelCount; i++) {
                blankPlotChatRender.renderSeries(this, g2, getRectangle(i, height, space, locationX, insets.top), i);
            }
        }
    }


    /**
     * Gets the max values text width.
     *
     * @param g2 the g2.
     * @return the max values text width.
     */
    private double getMaxValuesTextWidth(Graphics2D g2) {

        double width = 0;
        FontMetrics ft = g2.getFontMetrics();
        double valuesCount = niceScale.getNiceMin();
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            String text = format.format(valuesCount);
            Rectangle2D r2 = ft.getStringBounds(text, g2);
            double w = r2.getWidth();
            if (w > width) {
                width = w;
            }
            valuesCount += niceScale.getTickSpacing();
        }
        return width;
    }


    /**
     * Gets the label text height.
     *
     * @param g2 the g2.
     * @return the label text height.
     */
    private int getLabelTextHeight(Graphics2D g2) {

        FontMetrics ft = g2.getFontMetrics();
        return ft.getHeight();
    }


    /**
     * Gets the chart text.
     *
     * @param index the index.
     * @return the chart text.
     */
    private String getChartText(int index) {

        if (blankPlotChatRender != null) {
            return blankPlotChatRender.getLabelText(index);
        } else {
            return "Label";
        }
    }


    /**
     * Gets the rectangle.
     *
     * @param index  the index.
     * @param height the height.
     * @param space  the space.
     * @param startX the start X.
     * @param startY the start Y.
     * @return the rectangle.
     */
    public SeriesSize getRectangle(int index, double height, double space, double startX, double startY) {
        double x = startX + space * index;
        return new SeriesSize(x, startY + 1, space, height);
    }


    /**
     * Gets the series values of.
     *
     * @param values the values.
     * @param height the height.
     * @return the series values of.
     */
    public double getSeriesValuesOf(double values, double height) {

        double max = niceScale.getTickSpacing() * niceScale.getMaxTicks();
        double percentValues = values * 100d / max;
        return height * percentValues / 100d;
    }
}
