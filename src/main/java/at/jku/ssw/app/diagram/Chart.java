package at.jku.ssw.app.diagram;

import at.jku.ssw.app.diagram.blankchart.BlankPlotChart;
import at.jku.ssw.app.diagram.blankchart.BlankPlotChatRender;
import at.jku.ssw.app.diagram.blankchart.SeriesSize;
import at.jku.ssw.tcxparser.TcxParser;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Chart is a class, which provides a chart abstraction, and defines the base of a diagram
 *
 * @author Gruppe 3
 */
public class Chart extends javax.swing.JPanel {
    /**
     * instance of blankplotchart
     */
    private at.jku.ssw.app.diagram.blankchart.BlankPlotChart blankPlotChart;
    /**
     * instance of a panelLegend defined by jpanel
     */
    private javax.swing.JPanel panelLegend;
    /**
     * list of modellegend to add and remove or work with the legend
     */
    private final List<ModelLegend> legends = new ArrayList<>();
    /**
     * list of modelcharts to make a diagram with dynamic chartinstances
     */
    private final List<ModelChart> model = new ArrayList<>();
    /**
     * Is used in the methode renderSeries for the Modellegend and the rectangle building
     */
    private final int seriesSize = 12;
    /**
     * SeriesSpace is the space between all the sizes in the diagram definition.
     */
    private final int seriesSpace = 6;


    /**
     * Creates a new chart which defines the label text and render the series with a new chart, and a graphics and a size
     */
    public Chart() {

        initComponents();
        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRender() {
            public String getLabelText(int index) {

                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {

                double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    g2.setColor(legend.getColor());
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }

            @Override
            public void renderSeries(Chart chart, Graphics2D g2, SeriesSize size, int index) {

                System.out.println();
            }
        });
    }


    /**
     * Add legend
     *
     * @param name  the name
     * @param color the color
     */
    public void addLegend(String name, Color color) {

        ModelLegend data = new ModelLegend(name, color);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }


    /**
     * Add data
     *
     * @param data the data
     */
    public void addData(ModelChart data) {

        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
    }


    /**
     * Resize
     *
     * @param width  the width
     * @param height the height
     */
    public void resize(int width, int height) {

        this.setPreferredSize(new Dimension(width, height));
    }


    /**
     * Read in data
     *
     * @return java.util.List<TrainingCenterDatabaseT>
     * @throws JAXBException                  is thrown by the TCX Parser, which reads the tcx - files.
     * @throws IOException                    is also thrown by the TCX Parser if there can't be found a tcx-file in the source folder.
     */
    public static java.util.List<TrainingCenterDatabaseT> readInData() throws JAXBException, IOException {

        TcxParser parser = new TcxParser();
        List<TrainingCenterDatabaseT> trainings = new ArrayList<>();

        File directoryPath = new File("data");

        for (File training : Objects.requireNonNull(directoryPath.listFiles())) {
            if (FilenameUtils.getExtension(training.getName()).equals("tcx")) {
                trainings.add(parser.parseTCX(new FileInputStream(training.getPath())));
            }
        }
        return trainings;
    }

    /**
     * Insert components and define the vertical and horizontal layout
     */
    void initComponents() {

        blankPlotChart = new at.jku.ssw.app.diagram.blankchart.BlankPlotChart();
        panelLegend = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                        .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }
}
