package at.jku.ssw.app.diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * Labelcolor paints with one methode all the instance of the graphics.
 *
 * @author Gruppe 3.
 */
public class LabelColor extends JLabel {

    /**
     * paints the diagram, which is given in the methode and define a size for it.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) - 4;
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        g2.setColor(getBackground());
        g2.fillOval(x, y, size, size);
    }
}
