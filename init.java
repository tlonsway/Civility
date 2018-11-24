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
        Fists fists = new Fists(new Color(252, 210, 126),frame);
        Hotbar hotbar = new Hotbar();
        Player p = new Player("Bob",100,new Color(252, 210, 126),pi,fists,hotbar);
        ArrayList<Item> craftableItems = new ArrayList<Item>();
        craftableItems.add(new WoodenAxe());
        craftableItems.add(new HouseBlueprint());
        int X = 150;
        int Y = 150;
        for(Item e: craftableItems){
            e.setCX(X);
            e.setCY(Y);
            X+=150;
            if(X >= 1650){
                X = 150;
                Y += 100;
            }
        }
        Display d = new Display(1800,1000,i,p,at,frame,craftableItems);
        at.setDisplay(d);
        frame.add(d);
        d.setVisible(true);
        KeyboardThread kt = new KeyboardThread(d);
        MouseThread mt = new MouseThread(d);
        frame.addMouseListener(mt);
        frame.addKeyListener(kt);
        d.addBuilding(new Center(200,300));
        d.addBuilding(new House(0,0));
        d.addBuilding(new House(-400,400));
        d.addBuilding(new Factory(-600,-600));
        d.addBuilding(new GoldMine(500,500));
        //d.addResource(new Tree(150,150));
        //d.addResource(new Rock(-300,-300));
        for(int x = 0; x < (int)(Math.random()*100);x++){
            //d.addResource(new Tree((int)(2000*((Math.random()*2)-1)),(int)(2000*((Math.random()*2)-1))));
            //d.addResource(new Rock((int)(2000*((Math.random()*2)-1)),(int)(2000*((Math.random()*2)-1))));
        }
        //Biome b = new Biome("rocky",-1000,-1000);
        //d.addBiome(b);
        for(int x=-20000;x<=20000;x+=3000) {
            for(int y=-20000;y<=20000;y+=3000) {
                int choice=(int)(Math.random()*5);
                String type="";
                if (choice==0) {
                    type="winter";
                } else if (choice==1) {
                    type="forest";
                } else if (choice==2) {
                    type="desert";
                } else if (choice==3) {
                    type="rocky";
                } else {
                    type="jungle";
                }
                   
                d.addBiome(new Biome(type,x,y));
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i2=0;i2<20;i2++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        (new Thread(new FrameThread(d,60))).start();
        (new Thread(new UpdateThread(d))).start();
    }
}