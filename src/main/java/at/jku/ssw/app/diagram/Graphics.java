package at.jku.ssw.app.diagram;
import at.jku.ssw.app.diagram.ModelChart;

import java.awt.Color;


import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.IOException;

public class Graphics extends javax.swing.JFrame {
    Container container;

    public Graphics() throws JAXBException, IOException {
        initComponents();
        container = getContentPane();
        container.setBackground(new Color(250, 250, 250));
        chart.addLegend("Data", new Color(135, 189, 245));

        //Daten einlesen
        chart.readInData();

        chart.addData(new ModelChart("January", new double[]{500, 200, 80,89}));
        chart.addData(new ModelChart("February", new double[]{600, 750, 90,150}));
        chart.addData(new ModelChart("March", new double[]{200, 350, 460,900}));
        chart.addData(new ModelChart("April", new double[]{480, 150, 750,700}));
        chart.addData(new ModelChart("May", new double[]{350, 540, 300,150}));
        chart.addData(new ModelChart("June", new double[]{190, 280, 81,200}));
        container.add(chart);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    private void initComponents() {

        chart = new Chart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(119, 119, 119))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /*public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Graphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Graphics().setVisible(true);
                } catch (JAXBException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

     */
    private at.jku.ssw.app.diagram.Chart chart;
}
