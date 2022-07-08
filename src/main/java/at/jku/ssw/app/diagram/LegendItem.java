package at.jku.ssw.app.diagram;

/**
 * Is used in chart in a list to define the legend as an item.
 *
 * @author Gruppe 3.
 */
public class LegendItem extends javax.swing.JPanel {
    /**
     * Is the color of the label.
     */
    private LabelColor lbColor;
    /**
     * defines the name of the jlabel.
     */
    private javax.swing.JLabel lbName;

    /**
     * Legend item constructor - paints the text and the background, and sets some components.
     *
     * @param data contains the data of the legend.
     */
    public LegendItem(ModelLegend data) {
        initComponents();
        setOpaque(false);
        lbColor.setBackground(data.getColor());
        lbName.setText(data.getName());
    }


    /**
     * Init components and defines the legend-item on the group layout of the chart.
     */
    private void initComponents() {


        lbColor = new LabelColor();
        lbName = new javax.swing.JLabel();

        lbColor.setText("labelColor1");

        lbName.setForeground(new java.awt.Color(100, 100, 100));
        lbName.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbName)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbName)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(lbColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }
}
