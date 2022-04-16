package at.jku.ssw.app;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


public class Diagram {

    class Diagramm extends JPanel {

        private double maxWert;
        private double monatswert[];

        private void setMonatswerte(double monatswert[]) {

            this.monatswert = monatswert;
            maxWert = monatswert[0];
            for (int i = 1; i < 12; i++) {
                if (monatswert[i] > maxWert) {
                    maxWert = monatswert[i];
                }
            }

        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g2);
            Dimension d = getSize(null);
//Skalieren auf 100x100 mit y-Invertierung
            g2.scale(d.getWidth() / 100.0, -d.getHeight() / 100.0);
// Ursprung verschieben nach (15,-90)
            g2.translate(15, -90);
// Linien etwas breiter
            g2.setStroke(new BasicStroke(1.5f));
// x-Achse zeichnen
            g2.draw(new Line2D.Double(0, 0, 85, 0));
// y-Achse zeichnen
            g2.draw(new Line2D.Double(0, 0, 0, 85));
// Linien etwas dünner
            g2.setStroke(new BasicStroke(0.5f));
// Farbverlauf von rot nach grün
            g2.setPaint(new GradientPaint(
                    0f, 0f, Color.black, 0f, 85f, Color.black));
// Säulen zeichnen
            for (int i = 0; i < 12; i++) {
                g2.fill(new Rectangle2D.Double(
                        0, 7 * i, monatswert[i] / maxWert * 85, 7));
            }
// konstante Farbe dunkelgrau
            g2.setPaint(Color.darkGray);
// Rahmen um Säulen zeichnen
            for (int i = 0; i < 12; i++) {
                g2.draw(new Rectangle2D.Double(
                        0, 7 * i, monatswert[i] / maxWert * 85, 7));
            }
// y-Invertierung rückgängig machen
            g2.scale(1, -1);
// Beschriftung x-Achse
            String xAchse = "JFMAMJJASOND";
// Fontgröße für Beschriftung ändern
            g2.setFont(g2.getFont().deriveFont(8f));
            for (int i = 0; i < 12; i++) {
                g2.drawString(xAchse.substring(i, i + 1),
                        i * 7, +10);
            }
// Fontgröße für Beschriftung ändern
            g2.setFont(g2.getFont().deriveFont(5f));
            for (int i = 0; i < maxWert; i = i + 10) {
                g2.drawString(" " + i, -15,
                        -Math.round(((double) i) / maxWert * 85));
            }
        }
    }
    private Diagramm dgramm;

    public Diagram(
            String titel, int x, int y, int w, int h) {
        super(titel);
        this.setSize(w, h);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        dgramm = new Diagramm();
        this.setContentPane(dgramm);
    }

    private double[] routeBerechnen() {
// zufälliges Erzeugen der Routen
        double route[] = new double[12];
        for (int i = 0; i < 12; i++) {
            route[i] = ((double) Math.round(
                    (Math.random() * 500))) / 100.0;
            System.out.println("Route " + (i + 1) + ": " + route[i]);
        }
        return route;
    }

    public static void main(String[] args) {
        String titel = "Routen";
        int x = 100;
        int y = 100;
        int w = 300;
        int h = 200;
        Diagram f = new Diagram(titel, x, y, w, h);
        f.dgramm.setMonatswerte(f.routeBerechnen());
        f.setVisible(true);
    }



    //Methode jahre und kilometer hauptübersicht
}
