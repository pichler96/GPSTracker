package at.jku.ssw.app.diagram.blankchart;

/**
 * SeriesSize is a class which supports the creation of the rectangles in the diagram.
 *
 * @author Gruppe 3.
 */
public class SeriesSize {
    /**
     * value to set the size of the series for the rectangle which is used in the graph.
     */
    private double x;
    /**
     * value to set the size of the series for the rectangle which is used in the graph.
     */
    private double y;
    /**
     * width to set the size of the series for the rectangle which is used in the graph.
     */
    private double width;
    /**
     * height to set the size of the series for the rectangle which is used in the graph.
     */
    private double height;

    /**
     * It is a constructor.
     *
     * @param x      the x.
     * @param y      the y.
     * @param width  the width.
     * @param height the height.
     */
    public SeriesSize(double x, double y, double width, double height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    /**
     * It is a constructor.
     */
    public SeriesSize() {

    }

    /**
     * Gets the X.
     *
     * @return the  X.
     */
    public double getX() {

        return x;
    }


    /**
     * Sets the X.
     *
     * @param x the x.
     */
    public void setX(double x) {

        this.x = x;
    }


    /**
     * Gets the Y.
     *
     * @return the  Y.
     */
    public double getY() {

        return y;
    }


    /**
     * Sets the Y.
     *
     * @param y the y.
     */
    public void setY(double y) {

        this.y = y;
    }


    /**
     * Gets the width.
     *
     * @return the width.
     */
    public double getWidth() {

        return width;
    }


    /**
     * Sets the width.
     *
     * @param width the width.
     */
    public void setWidth(double width) {

        this.width = width;
    }


    /**
     * Gets the height.
     *
     * @return the height.
     */
    public double getHeight() {

        return height;
    }


    /**
     * Sets the height.
     *
     * @param height the height.
     */
    public void setHeight(double height) {

        this.height = height;
    }
}
