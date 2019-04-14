import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Civility");
        
        //frame.setLayout(null);
        /*try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        //frame.setVisible(true);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
        frame.getContentPane().setCursor(blankCursor);
        JTextField chatBox = new JTextField("Type here to chat (press t)");
        
        TechTree techLvl = new TechTree();
        ResearchCenter research = new ResearchCenter();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        double xscale = screenSize.getWidth()/1800;
        double yscale = screenSize.getHeight()/1000;
        
        //double xscale = 1;
        //double yscale = 1;
        
        InputMap im = chatBox.getInputMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        
        chatBox.setBounds((int)(1450*(screenSize.getWidth()/1800)),(int)(430*(screenSize.getHeight()/1000)),(int)(300*(screenSize.getWidth()/1800)),20);
        chatBox.setEditable(true);
        chatBox.setVisible(true);
        frame.add(chatBox);
        Inventory i = new Inventory();
        InventoryTwo pi = new InventoryTwo();
        AIThread at = new AIThread();
        (new Thread(at)).start();
        Fists fists = new Fists(new Color(252, 210, 126),frame,xscale,yscale);
        Hotbar hotbar = new Hotbar();
        Player p = new Player(Human.getRandomName(),100,new Color(252, 210, 126),pi,fists,hotbar);
        //-----------------------------------------------------------
        
        
        p.addItem(new Item("wood",1000,false,new Color(163,68,0),"square",null,new int[]{0,0,0,0,0},0));
        p.addItem(new Item("stone",1000,false,new Color(163,68,0), "square",null,new int[]{0,0,0,0,0},0));
        
        //0:tool,1:tree,2:rock,3:fragile,4:special
        ArrayList<Item> craftableItems = new ArrayList<Item>();
        craftableItems.add(new Item("stick",2,false,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("wood",5))),new int[]{1,1,1,1,0},0));
        craftableItems.add(new Item("wood planks",1,false,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("wood",5))),new int[]{0,0,0,0,0},0));
        craftableItems.add(new Item("wooden axe",1,false,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("wood planks",5))),new int[]{1,10,3,2,0},0));
        craftableItems.add(new Item("wooden pickaxe",1,false,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("wood planks",7))),new int[]{1,3,10,2,0},0));
        craftableItems.add(new Item("stone axe",1,false,new Color(163,68,0),"square", new ArrayList<TempItem>(Arrays.asList(new TempItem("stone",10),new TempItem("stick",5))),new int[]{1,15,5,1,0},1));
        craftableItems.add(new Item("stone pickaxe",1,false,new Color(163,68,0),"square", new ArrayList<TempItem>(Arrays.asList(new TempItem("stone",12),new TempItem("stick",7))),new int[]{1,5,15,1,0},1));
        craftableItems.add(new Item("wall frame",1,true,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("stone",10))),new int[]{0,0,0,0,0},1));
        craftableItems.add(new Item("house frame",1,true,new Color(163,68,0),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("wood planks",20))),new int[]{0,0,0,0,0},1));
        craftableItems.add(new Item("stone brick",1,false,new Color(127,127,127),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("stone",5))),new int[]{0,0,0,0,0},1));
        craftableItems.add(new Item("Zuberrr Zpace Zip",1,false,new Color(127,127,127),"square",new ArrayList<TempItem>(Arrays.asList(new TempItem("stone",1))),new int[]{1,100,100,100,0},3));
        ClientDataHost cdh = new ClientDataHost();
        
        (new Thread(cdh)).start();
        
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("preparing to create display");
        
        Display d = new Display((int)(screenSize.getWidth()),(int)(screenSize.getHeight()),i,p,at,frame,craftableItems,cdh,chatBox,techLvl,xscale,yscale);
        
        //
        
        System.out.println("display created");
        
        //Display d = new Display(1800,1000,i,p,at,frame,craftableItems,cdh,null,research);
        at.setDisplay(d);
        frame.add(d);
        d.setVisible(true);
        d.setDoubleBuffered(true);
        
        //--------------------------------------
        MouseScrollWheelThread mouseWheel = new MouseScrollWheelThread(d);
        frame.addMouseWheelListener(mouseWheel);
        
        ArrayList<Item> Items = new ArrayList<Item>();
        Items.add(new Item("wood",0,false,new Color(163,68,0),"square",null,new int[]{0,0,0,0,0},0));
        Items.add(new Item("stone",0,false,Color.GRAY,"square",null,new int[]{0,0,0,0,0},0));
        KeyboardThread kt = new KeyboardThread(d);
        MouseThread mt = new MouseThread(d);
        frame.addMouseListener(mt);
        frame.addKeyListener(kt);
        d.addBuilding(new ResearchCenter());
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
        /*long randseed = (int)(Math.random()*100)*(int)(Math.random()*100)*(int)(Math.random()*100);
        Biome b = new Biome("rocky",-1000,-1000,randseed);
        d.addBiome(b);
        Random generator = new Random(randseed);
        for(int x=-40000;x<=40000;x+=3000) {
            for(int y=-40000;y<=40000;y+=3000) {
                int choice=(int)(generator.nextDouble()*6);
                String type="";
                if (choice==0) {
                    type="winter";
                } else if (choice==1) {
                    type="forest";
                } else if (choice==2) {
                    type="desert";
                } else if (choice==3) {
                    type="rocky";
                } else if (choice==4) {
                    type="jungle";
                } else if (choice==5) {
                    type="plains";
                }
                   
                d.addBiome(new Biome(type,x,y,randseed));
                try {
                    //Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i2=0;i2<20;i2++) {
            try {
                //Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        
        (new Thread(new FrameThread(d,100))).start();
        (new Thread(new UpdateThread(d))).start();
        
        //frame.setVisible(true);
        //frame.revalidate();
        try{
            frame.setIconImage(ImageIO.read(new File("images/icon.png")));
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0,140,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
        frame.setVisible(true);
        //d.setVisible(true);
        //d.revalidate();
        //frame.validate();
        //frame.requestFocusInWindow();
        
    }
}