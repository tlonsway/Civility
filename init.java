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
        Inventory i = new Inventory();
        Player_Inventory pi = new Player_Inventory();
        AIThread at = new AIThread();
        (new Thread(at)).start();
        Player p = new Player("Bob",100,Color.RED,pi);
        Display d = new Display(1800,1000,i,p,at);
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
        d.addBuilding(new GoldMine(500,500));
        d.addResource(new Tree(150,150));
        d.addResource(new Rock(-300,-300));
        at.addBot(new Human(100,-100,d));
        for(int i2=0;i2<100;i2++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            p.doDamage(1);
        }
    }
}