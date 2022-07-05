package at.jku.ssw.app.diagram;

import java.awt.*;

/**
 * @author DJ-Raven
 */


public class ModelLegend {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegend(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegend() {
    }

    private String name;
    private Color color;
}
