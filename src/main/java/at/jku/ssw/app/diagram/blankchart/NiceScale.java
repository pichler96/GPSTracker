package at.jku.ssw.app.diagram.blankchart;

/**
 * @author Gruppe 3.
 */
public class NiceScale {

    private double min;
    private double max;
    private int maxTicks = 10;
    private double tickSpacing;
    private double range;
    private double niceMin;
    private double niceMax;


    /**
     * It is a constructor.
     */
    public NiceScale(final double MIN, final double MAX) {
        min = MIN;
        max = MAX;
        calculate();
    }


    /**
     * Calculate a range, tickSpacing, niceMin and niceMax.
     */
    private void calculate() {
        range = niceNum(max - min, false);
        tickSpacing = niceNum(range / (maxTicks - 1), true);
        niceMin = Math.floor(min / tickSpacing) * tickSpacing;
        niceMax = Math.ceil(max / tickSpacing) * tickSpacing;
    }


    /**
     * Round based on the range of fraction.
     *
     * @return double.
     */
    private double niceNum(final double RANGE, final boolean ROUND) {

        double exponent;
        double fraction;
        double niceFraction;

        exponent = Math.floor(Math.log10(RANGE));
        fraction = RANGE / Math.pow(10, exponent);

        if (ROUND) {
            if (fraction < 1.5) {
                niceFraction = 1;
            } else if (fraction < 3) {
                niceFraction = 2;
            } else if (fraction < 7) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (fraction <= 1) {
                niceFraction = 1;
            } else if (fraction <= 2) {
                niceFraction = 2;
            } else if (fraction <= 5) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        return niceFraction * Math.pow(10, exponent);
    }


    /**
     * Sets the min max.
     */
    public void setMinMax(final double MIN, final double MAX) {

        min = MIN;
        max = MAX;
        calculate();
    }


    /**
     * Sets the max ticks.
     */
    public void setMaxTicks(final int MAX_TICKS) {

        maxTicks = MAX_TICKS;
        calculate();
    }


    /**
     * Gets the tick spacing.
     *
     * @return the tick spacing.
     */
    public double getTickSpacing() {

        return tickSpacing;
    }


    /**
     * Gets the nice min.
     *
     * @return the nice min.
     */
    public double getNiceMin() {

        return niceMin;
    }


    /**
     * Gets the nice max.
     *
     * @return the nice max.
     */
    public double getNiceMax() {

        return niceMax;
    }


    /**
     * Gets the max ticks.
     *
     * @return the max ticks.
     */
    public int getMaxTicks() {

        return maxTicks;
    }


    /**
     * Gets the min.
     *
     * @return the min.
     */
    public double getMin() {

        return min;
    }


    /**
     * Sets the min.
     *
     * @param min the min.
     */
    public void setMin(double min) {

        this.min = min;
        calculate();
    }


    /**
     * Gets the max.
     *
     * @return the max.
     */
    public double getMax() {

        return max;
    }


    /**
     * Sets the max.
     *
     * @param max the max.
     */
    public void setMax(double max) {

        this.max = max;
        calculate();
    }

}
