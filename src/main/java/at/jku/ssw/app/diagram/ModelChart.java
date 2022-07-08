package at.jku.ssw.app.diagram;

/**
 * @author Gruppe 3.
 */
public class ModelChart {
    private String label;
    private double values[];

    /**
     * It is a constructor.
     *
     * @param label  the label.
     * @param values the values.
     */
    public ModelChart(String label, double[] values) {

        this.label = label;
        this.values = values;
    }


    /**
     * It is a constructor.
     */
    public ModelChart() {

    }

    /**
     * Gets the label.
     *
     * @return the label.
     */
    public String getLabel() {

        return label;
    }


    /**
     * Sets the label.
     *
     * @param label the label.
     */
    public void setLabel(String label) {

        this.label = label;
    }


    /**
     * Gets the values.
     *
     * @return the values.
     */
    public double[] getValues() {

        return values;
    }


    /**
     * Sets the values.
     *
     * @param values the values.
     */
    public void setValues(double[] values) {

        this.values = values;
    }

    /**
     * Gets the max values.
     *
     * @return the max values.
     */
    public double getMaxValues() {

        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
