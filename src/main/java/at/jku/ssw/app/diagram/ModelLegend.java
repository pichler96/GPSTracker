package at.jku.ssw.app.diagram;

import java.awt.*;

/**
 * Modellegend is used in chart and defines the description and the legend of the diagram.
 *
 * @author Gruppe 3.
 */
public class ModelLegend {
    /**
     * defines the name of the legend.
     */
    private String name;
    /**
     * defines the color of the model.
     */
    private Color color;

    /**
     * It is a constructor.
     *
     * @param name  the name.
     * @param color the color.
     */
    public ModelLegend(String name, Color color) {

        this.name = name;
        this.color = color;
    }


    /**
     * It is a constructor.
     */
    public ModelLegend() {

    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {

        return name;
    }


    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {

        this.name = name;
    }


    /**
     * Gets the color.
     *
     * @return the color.
     */
    public Color getColor() {

        return color;
    }


    /**
     * Sets the color.
     *
     * @param color the color.
     */
    public void setColor(Color color) {

        this.color = color;
    }

}
