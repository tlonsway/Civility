import java.awt.*;
import javax.swing.*;
import java.util.*;
public class init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Window");
        frame.setVisible(true);
        frame.setSize(1800,1000);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0,140,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Display d = new Display(1800,1000);
        frame.add(d);
        d.setVisible(true);
        (new Thread(new FrameThread(d,60))).start();
        (new Thread(new UpdateThread(d))).start();
        KeyboardThread kt = new KeyboardThread(d);
        frame.addKeyListener(kt);
        d.addBuilding(new Center(200,300));
        d.addBuilding(new House(0,0));
        d.addBuilding(new House(-400,400));
        d.addBuilding(new Factory(-600,-600));
    }
}