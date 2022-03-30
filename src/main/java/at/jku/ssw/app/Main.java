package at.jku.ssw.app;

import at.jku.ssw.tcxparser.TcxParser;
import at.jku.ssw.tcxparser.schema.ActivityT;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run(){
                SwingMain m= new SwingMain();
                m.setVisible(true);
            }
        });


        try {
            TcxParser parser = new TcxParser();
            List<ActivityT> activities = new ArrayList<ActivityT>();
            activities.add(parser.parseTCX(new FileInputStream("data/2020/barcelona.tcx")).getActivities().getActivity().get(0));
            activities.add(parser.parseTCX(new FileInputStream("data/2021/wolfgangsee.tcx")).getActivities().getActivity().get(0));
            activities.add(parser.parseTCX(new FileInputStream("data/2021/tucson.tcx")).getActivities().getActivity().get(0));
            activities.add(parser.parseTCX(new FileInputStream("data/2021/sabino.tcx")).getActivities().getActivity().get(0));

            for (ActivityT activity : activities) {
                System.out.println(activity.getCreator().getName() + " start " + activity.getSport());
                activity.getLap().forEach(a -> System.out.println("LAP start: " + a.getStartTime() + " for TotalTime " + a.getTotalTimeSeconds() + " Distance: " +a.getDistanceMeters() + " Speed: " + a.getMaximumSpeed()));
                System.out.println();
            }

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
