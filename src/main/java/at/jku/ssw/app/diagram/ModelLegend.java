package at.jku.ssw.app.diagram;

import java.awt.*;

/**
 * @author Gerald Waldburger, K12005573
 */


public class ModelLegend {

    private String name;
    private Color color;

    /**
     * It is a constructor.
     *
     * @param name  the name
     * @param color the color
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
     * Gets the name
     *
     * @return the name
     */
    public String getName() {

        return name;
    }


    /**
     * Sets the name
     *
     * @param name the name
     */
    public void setName(String name) {

        this.name = name;
    }


    /**
     * Gets the color
     *
     * @return the color
     */
    public Color getColor() {

        return color;
    }


    /**
     * Sets the color
     *
     * @param color the color
     */
    public void setColor(Color color) {

        this.color = color;
    }

}
