package at.jku.ssw.app;
import javax.swing.*;
import java.awt.BorderLayout;


public class SwingMain {

    public static void main(String[] args) {
        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("GPSTracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define new buttons with different regions
        JButton jb1 = new JButton("NORTH");
        JButton jb2 = new JButton("SOUTH");
        JButton jb3 = new JButton("WEST");
        JButton jb4 = new JButton("EAST");
        JButton jb5 = new JButton("CENTER");

        // Define the panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jb1, BorderLayout.NORTH);
        panel.add(jb2, BorderLayout.SOUTH);
        panel.add(jb3, BorderLayout.WEST);
        panel.add(jb4, BorderLayout.EAST);
        panel.add(jb5, BorderLayout.CENTER);
        frame.add(panel);

        // Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create the Menu Entries
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);
        JMenu view = new JMenu("View");
        menuBar.add(view);
        JMenu years = new JMenu("Years");
        menuBar.add(years);
        JMenu columns = new JMenu("Columns");
        menuBar.add(columns);
        JMenu help = new JMenu("Help");
        menuBar.add(help);

        // Create the Menu Items
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(a -> frame.dispose());
        file.add(exit);


        // Set the window to be visible as the default to be false
        frame.pack();
        frame.setVisible(true);
    }

}