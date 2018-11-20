import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
public class init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Window");
        frame.setVisible(true);
        frame.setSize(1800,1000);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0,140,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
        frame.getContentPane().setCursor(blankCursor);
        Inventory i = new Inventory();
        Player_Inventory pi = new Player_Inventory();
        AIThread at = new AIThread();
        (new Thread(at)).start();
        Fists fists = new Fists(new Color(252, 210, 126));
        Hotbar hotbar = new Hotbar();
        Player p = new Player("Bob",100,Color.BLUE,pi,fists,hotbar);
        Display d = new Display(1800,1000,i,p,at);
        at.setDisplay(d);
        frame.add(d);
        d.setVisible(true);
        (new Thread(new FrameThread(d,60))).start();
        (new Thread(new UpdateThread(d))).start();
        KeyboardThread kt = new KeyboardThread(d);
        MouseThread mt = new MouseThread(d);
        frame.addMouseListener(mt);
        frame.addKeyListener(kt);
        d.addBuilding(new Center(200,300));
        d.addBuilding(new House(0,0));
        d.addBuilding(new House(-400,400));
        d.addBuilding(new Factory(-600,-600));
        d.addBuilding(new GoldMine(500,500));
        d.addResource(new Tree(150,150));
        d.addResource(new Rock(-300,-300));
        for(int a = 0; a < 10; a++){
            p.addItem(new Item("Test"));
        }
        for(int x = 0; x < (int)(Math.random()*100);x++){
            //d.addResource(new Tree((int)(Math.random()*1000),(int)(Math.random()*1000)));
        }
        for(int i2=0;i2<20;i2++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}