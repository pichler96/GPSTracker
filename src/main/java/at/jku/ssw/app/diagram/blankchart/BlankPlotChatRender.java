package at.jku.ssw.app.diagram.blankchart;

import at.jku.ssw.app.diagram.Chart;

import java.awt.Graphics2D;

/**
 * Is an interface for blankplotchart
 * @author Gruppe 3.
 */
public abstract class BlankPlotChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);

    public abstract void renderSeries(Chart chart, Graphics2D g2, SeriesSize size, int index);
}
