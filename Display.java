import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import javafx.geometry.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Display extends JComponent {
    ArrayList<Building> buildings;
    ArrayList<Resource> resources;
    ArrayList<Biome> biomes;
    double center_x;
    double center_y;
    int width;
    int height; 
    boolean w,a,s,d,i=false;
    Inventory in;
    Player player;
    AIThread aithread;
    ArrayList<Item> craftableItems;
    String view;
    JFrame frame;
    int updatetime;
    int frametime;
    MenuManager menu;
    ClientDataHost chost;
    JTextField messageBox;
    ArrayList<String> itemsUsedCrafting;
    ResearchCenter researchCenter;
    private BufferedImage woodAxeImage;
    //private ArrayList
    ResourceImage rims;
    BiomeImage bims;
    BuildingImage buims;
    IconImage iims;
    BufferedImage forestTexture;
    int spamlim=0;
    Map map;
    double xScale;
    double yScale;
    public Display(int w, int h, Inventory inventory,Player p, AIThread at, JFrame f,ArrayList<Item> CI, ClientDataHost cdh, JTextField mb,ResearchCenter rc,double xS,double yS) {
        buildings = new ArrayList<Building>();
        resources = new ArrayList<Resource>();
        biomes = new ArrayList<Biome>();
        center_x=0;
        center_y=0;
        width=w;
        height=h;
        in=inventory;
        player = p;
        aithread=at;
        view = "world";
        frame=f;
        craftableItems = CI;
        updatetime=0;
        frametime=0;
        menu = new MenuManager(CI,player.getInventory(),rc.tree);
        chost=cdh;
        /*try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //-----------------------------------------------
        //map = new Map(""+cdh.getMapSeed());
        //biomes=map.getBiomes();
        //-----------------------------------------------
        messageBox = mb;
        ActionMap am = messageBox.getActionMap();
        itemsUsedCrafting = new ArrayList<String>();
        researchCenter = rc;
        rims = new ResourceImage();
        bims = new BiomeImage();
        buims = new BuildingImage();
        iims = new IconImage();
        xScale = xS;
        yScale = yS;
        System.out.println("X: "+xScale+" Y: "+yScale);
        try{
            forestTexture = ImageIO.read(new File("images/forest texture.png"));
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        am.put("enter", new AbstractAction() {
                public void actionPerformed(ActionEvent ae) {
                    chost.sendMessage(player.getName(),messageBox.getText());
                    messageBox.setText("");
                    frame.requestFocusInWindow();
                }
        });        
        am.put("escape", new AbstractAction() {
                public void actionPerformed(ActionEvent ae) {
                    frame.requestFocusInWindow();
                }
        });
        frame.requestFocusInWindow();
        //frame.setVisible(true);
        //this.setVisible(true);
        //frame.revalidate();
    }
    public void update() {
        long stime = System.nanoTime();
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        if (player.getHealth()<=0) {
            view="dead";
        }
        for(Building b : buildings) {
            if (b.getType().equals("goldmine")) {
                in.addGold(.005);
            }
        }
        for(Biome b : biomes) {
            for (int ri=0;ri<b.getResources().size();ri++) {
                Resource r = b.getResource(ri);
                if (r.isDestroyed()) {
                    b.removeResource(ri);
                    ri++;
                    continue;
                }
                if (r.getType().equals("cactus")&&r.getBoundingBox().intersects(new BoundingBox(890+center_x,490+center_y,60,60))) {
                    player.doDamage(.05);
                }
            }
        }
        int moveamt = 3;
        if (a) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_x-=moveamt;
        } else 
        if (d) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_x+=moveamt;
        }
        if (w) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,500-moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500-moveamt+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_y-=moveamt;
        } else 
        if (s) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_y+=moveamt;
        }
        //System.out.println((System.nanoTime()-stime)/1000);
        if (spamlim==1) {
            chost.sendPlayerLocation(player.getName(), (int)((center_x+width/2)), (int)((center_y+height/2)));
            spamlim=0;
        } else {
            spamlim++;
        }
        updatetime=(int)((System.nanoTime()-stime));
    }
    public void draw() {
        this.repaint();
    }
    public String sendMessage(){
        String ret = messageBox.getText();
        messageBox.setText("");
        return ret;
    }
    public void paintComponent(Graphics g) {
        long stime = System.nanoTime();
        super.paintComponent(g);
        //this.setDoubleBuffered(true);
        if(view == "inventory"){
            messageBox.setVisible(false);
            menu.inventoryUpdate();
            Font f = new Font("Courier New",Font.PLAIN,60);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Inventory",(int)(52*xScale),(int)(52*yScale));
            g.setColor(Color.WHITE);
            g.drawString("Inventory",(int)(50*xScale),(int)(50*yScale));
            g.setColor(Color.GRAY);
            g.fillRect((int)(100*xScale),(int)(100*yScale),(int)(1600*xScale),(int)(800*yScale));
            g.setColor(Color.BLACK);
            g.drawRect((int)(100*xScale),(int)(100*yScale),(int)(1600*xScale),(int)(800*yScale));
            int x = (int)(150*xScale);
            int y = (int)(150*yScale);
            f = new Font("Courier New",Font.PLAIN,18);
            g.setFont(f);
            for(MenuItem e: menu.getInventoryMenu()){
                g.setColor(Color.WHITE);
                g.fillRect((int)(e.getX()*xScale)+(int)(150*xScale),(int)(e.getY()*yScale)+(int)(150*yScale),(int)(80*xScale),(int)(80*yScale));
                g.setColor(Color.BLACK);
                g.drawRect((int)(e.getX()*xScale)+(int)(150*xScale),(int)(e.getY()*yScale)+(int)(150*yScale),(int)(80*xScale),(int)(80*yScale));
                try {
                    g.drawImage(iims.getImage(e.getItem().getName()),(int)(e.getX()*xScale)+(int)(155*xScale),(int)(e.getY()*yScale)+(int)(150*yScale),(int)(70*xScale),(int)(70*yScale),this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //g.drawString(e.getItem().getName(),e.getX()+170,e.getY()+220);
                g.drawString("x"+e.getItem().getQuantity(),(int)(e.getX()*xScale)+(int)(170*xScale),(int)(e.getY()*yScale)+(int)(228*yScale));
            }
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*yScale),(int)(10*xScale),(int)(10*yScale)); 
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*yScale),(int)(10*xScale),(int)(10*yScale));
            g.setColor(Color.GRAY);
            g.fillRect((int)(595*xScale),(int)(820*yScale),(int)(610*xScale),(int)(85*yScale));
            g.setColor(Color.BLACK);
            g.drawRect((int)(595*xScale),(int)(820*yScale),(int)(610*xScale),(int)(85*yScale));
            Item[] hotbar = player.getHotbar();
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            //System.out.println("elapsed remainder: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            for(int i = 0; i < 10; i++){
                if(player.getHotBarItemSelected() == i){
                    g.setColor(new Color(120,120,120));
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect((int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*yScale));
                if(hotbar[i] != null){
                    try{
                        g.drawImage(iims.getImage(hotbar[i].getName()),(int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*yScale),this);
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                    g.setColor(Color.WHITE);
                    g.drawString("x" + hotbar[i].getQuantity(),(int)(610*xScale)+(i*(int)(60*xScale)),(int)(895*yScale));
                }
                g.setColor(Color.BLACK);
                g.drawRect((int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*yScale));
            }
            //g.drawImage(woodAxeImage,100,100,this);
        }
        else if(view == "world"){
            messageBox.setVisible(true);
            //long stime = System.nanoTime();
            //System.out.println("elapsed initial: " + (System.nanoTime()-stime));
            BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
            /*for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    g.setColor(b.getColor());
                    g.fillRect(-(int)center_x+b.getX(),-(int)center_y+b.getY(),b.getWidth(),b.getHeight());
                }
            }*/
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    g.setColor(b.getColor());
                    /*int xloc = -(int)center_x+b.getX();
                    int yloc = -(int)center_y+b.getY();
                    int width = b.getWidth();
                    int height = b.getHeight();
                    if (xloc+width>1800) {
                        width=1800;
                    }
                    if (yloc+height>1000) {
                        height=1000;
                    }
                    if (xloc<=0) {
                        xloc=0;
                    }
                    if (yloc<=0) {
                        yloc=0;
                    }
                    g.fillRect(xloc,yloc,width,height);*/
                    //b.getX()-(b.getX()-center_x)
                    //if(b.getType().equals("forest")){
                    screen = new BoundingBox(0,0,width,height);
                    //g.setColor(Color.RED);
                    //g.drawRect((int)center_x,(int)center_y,width,height);
                    for(int c = (int)(b.getY()-center_y); c < (int)(b.getY()-center_y)+3000; c += 300){
                        for(int d = (int)(b.getX()-center_x); d < (int)(b.getX()-center_x)+3000; d += 300){
                            if (new BoundingBox(d,c,300,300).intersects(screen)){
                                g.drawImage(bims.getImage(b.getType()),d,c,300,300,this);
                            }
                        }
                    }
                    screen = new BoundingBox(center_x,center_y,width,height);
                    //}
                    
                    resources=b.getResources();
                    for (int ri=0;ri<resources.size();ri++) {
                        Resource r = resources.get(ri);
                        if (r.getBoundingBox().intersects(screen)) {
                            //g.setColor(r.getColor());
                            //g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
                            //g.setColor(Color.BLACK);
                            //g.drawRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
                            try{
                                g.drawImage(rims.getImage(r.getType()),(int)((r.getX()-center_x)*xScale),(int)((r.getY()-center_y)*yScale),(int)((r.getWidth())*xScale),(int)((r.getHeight())*yScale),this);
                                //g.drawImage(ImageIO.read(new File("images/"+r.getType()+".png")),(int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()),this);
                            }
                            catch(Exception ex){
                                System.out.println("error drawing image");
                                System.out.println(ex);
                            }
                            Font f = new Font("Courier New",Font.PLAIN,18);
                            g.setFont(f);
                            //g.drawString(r.getType(), (int)(r.getX()-center_x), (int)(r.getY()-center_y+r.getHeight()+20));
                            if (r.getHealth()<r.getMax_Health()) {
                                g.setColor(Color.BLACK);
                                g.drawRect((int)((r.getX()-center_x)*xScale)-1,(int)((r.getY()-center_y)*yScale)-(int)(16*yScale),(int)(r.getWidth()*yScale)+1,(int)(11*yScale));
                                g.setColor(Color.RED);
                                g.fillRect((int)((r.getX()-center_x)*xScale),(int)((r.getY()-center_y)*yScale)-(int)(15*yScale),(int)(r.getWidth()*xScale),(int)(10*yScale));
                                g.setColor(Color.GREEN);
                                g.fillRect((int)((r.getX()-center_x)*xScale),(int)((r.getY()-center_y)*yScale)-(int)(15*yScale),(int)((r.getHealth()/r.getMax_Health()*r.getWidth())*yScale),(int)(10*yScale));
                            }
                        }
                    }
                }
            }
            //System.out.println("elapsed biomes: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            g.setColor(researchCenter.getColor());
            g.fillRect((int)(researchCenter.getX()-center_x),(int)(researchCenter.getY()-center_y),(int)researchCenter.getWidth(),(int)researchCenter.getHeight());
            g.setColor(Color.BLACK);
            g.drawRect((int)(researchCenter.getX()-center_x),(int)(researchCenter.getY()-center_y),(int)researchCenter.getWidth(),(int)researchCenter.getHeight());
            g.drawString("Research Center",(int)(researchCenter.getX()-center_x),(int)(researchCenter.getY()-center_y+researchCenter.getHeight()+(int)(15*yScale)));   
            if(researchCenter.getBoundingBox().intersects(screen)){
                for(int a  = 0; a < researchCenter.tree.getRequired().size();a++){
                    g.drawString(researchCenter.tree.getRequired().get(a).getName()+" x"+researchCenter.tree.getRequired().get(a).getQuantity(),(int)(researchCenter.getX()-center_x+(int)(10*xScale)),(int)(researchCenter.getY()-center_y+(a*15))+(int)(20*yScale));
                }
            }
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(screen)) {
                    //g.setColor(b.getColor());
                    //g.fillRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                    //g.setColor(Color.BLACK);
                    //g.drawRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                    g.drawImage(buims.getImage(b.getType()),(int)((b.getX()-center_x)*xScale),(int)((b.getY()-center_y)*yScale),(int)(b.getWidth()*xScale),(int)(b.getHeight()*yScale),this);
                    Font f = new Font("Courier New",Font.PLAIN,15);
                    g.setFont(f);
                    String btitle="";
                    if (b.getType().equals("house")) {
                        btitle+=((House)(b)).getOwner()+"'s ";
                    }
                    g.drawString(btitle+b.getType(), (int)((b.getX()-center_x)*xScale), (int)((int)((b.getY()-center_y+b.getHeight())*yScale)+(int)(20*yScale)));
                    if(b.getType().contains("Frame")){
                        g.setColor(Color.BLACK);
                        for(int a = 0; a < b.getBuildItemsRequired().size();a++){
                            g.drawString(b.getBuildItemsRequired().get(a).getName() + " x" + b.getBuildItemsRequired().get(a).getQuantity(),(int)((b.getX()-center_x)*xScale),(int)((int)((b.getY()+b.getHeight()+30)*yScale)+(a*(int)(15*yScale))-(int)(center_y*yScale)));
                        }
                    }
                }
            }
            //System.out.println("elapsed buildings: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            for(AI a : aithread.getBots()) {
                if (a.getBoundingBox().intersects(screen)) {
                    g.setColor(a.getColor());
                    g.fillRect((int)(a.getX()-center_x),(int)(a.getY()-center_y),(int)(a.getWidth()),(int)(a.getHeight()));
                    g.setColor(Color.BLACK);
                    g.drawRect((int)(a.getX()-center_x),(int)(a.getY()-center_y),(int)(a.getWidth()),(int)(a.getHeight()));
                    Font f = new Font("Courier New",Font.PLAIN,18);
                    g.setFont(f);
                    g.drawString(a.getName(),(int)(a.getX()-center_x),(int)(a.getY()-center_y+a.getHeight()+(int)(20*yScale)));
                }                
            }
            //System.out.println("elapsed bots: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            g.setColor(Color.BLACK);
            Font f = new Font("Courier New", Font.BOLD, 30);
            g.setFont(f);
            g.drawString("Technology Level: "+researchCenter.tree.getTechLevel(),(int)(40*xScale),(int)(220*yScale));
            g.drawString("update time: " + updatetime,(int)(40*xScale),(int)(140*yScale));
            g.drawString("frame time: " + frametime,(int)(40*xScale),(int)(180*yScale));
            g.drawString("Gold: "+(int)(in.getGold()),(int)(40*xScale),(int)(40*yScale));
            g.drawString("center_x: " + center_x + "   center_y: " + center_y,(int)(300*xScale),(int)(40*yScale));
            g.setColor(player.getColor());
            g.fillOval((int)(900*xScale),(int)(500*yScale),(int)(40*xScale),(int)(40*yScale));
            for(PlayerLocation pl : chost.getPlayers()) {
                //System.out.println("other player location at " + pl.x + "," + pl.y);
                g.fillOval((int)(pl.x-center_x),(int)(pl.y-center_y),(int)(40*xScale),(int)(40*yScale));
                g.setColor(Color.BLACK);
                g.drawOval((int)(pl.x-center_x),(int)(pl.y-center_y),(int)(40*xScale),(int)(40*yScale));
                g.setFont(new Font("Courier New",Font.PLAIN,15));
                g.drawString(pl.n, (int)(pl.x-center_x), (int)(pl.y-center_y)+(int)(50*yScale));
            }
            
            g.setColor(Color.BLACK);
            g.drawOval((int)(900*xScale),(int)(500*yScale),(int)(40*xScale),(int)(40*yScale));
            //System.out.println("elapsed remainder_beforefist: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            int[] fistCords = player.getFistCords((int)mp.getX()-(int)loc.getX(),(int)mp.getY()-(int)loc.getY());
            //System.out.println("elapsed remainder_afterfistcoords: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*yScale),(int)(10*xScale),(int)(10*yScale));
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*xScale),(int)(10*xScale),(int)(10*xScale));
            g.setColor(player.getFistColor());
            g.fillOval((int)(fistCords[0]*xScale),(int)(fistCords[1]*yScale),(int)(10*xScale),(int)(10*yScale));
            g.fillOval((int)(fistCords[2]*xScale),(int)(fistCords[3]*yScale),(int)(10*xScale),(int)(10*yScale));
            g.setColor(Color.BLACK);
            g.drawOval((int)(fistCords[0]*xScale),(int)(fistCords[1]*yScale),(int)(10*xScale),(int)(10*yScale));
            g.drawOval((int)(fistCords[2]*xScale),(int)(fistCords[3]*yScale),(int)(10*xScale),(int)(10*yScale));
            //System.out.println("elapsed remainder_afterfist: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            g.drawRect((int)(899*xScale),(int)(479*yScale),(int)(41*xScale),(int)(11*yScale));
            g.setColor(Color.RED);
            g.fillRect((int)(900*xScale),(int)(480*yScale),(int)(40*xScale),(int)(10*yScale));
            g.setColor(Color.GREEN);
            g.fillRect((int)(900*xScale),(int)(480*yScale),(int)(player.getHealth()/(int)(100*xScale)*(int)(40*yScale)),(int)(10*yScale));
            g.setColor(Color.GRAY);
            g.fillRect((int)(595*xScale),(int)(820*yScale),(int)(610*xScale),(int)(85*yScale));
            g.setColor(Color.BLACK);
            g.drawRect((int)(595*xScale),(int)(820*yScale),(int)(610*xScale),(int)(85*yScale));
            Item[] hotbar = player.getHotbar();
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            if(player.getHotbar()[player.getHotBarItemSelected()] != null){
                try{
                    g.drawImage(iims.getImage(player.getHotbar()[player.getHotBarItemSelected()].getName()),fistCords[0]-(int)(8*xScale),fistCords[1]-(int)(8*yScale),(int)(24*xScale),(int)(24*yScale),this);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            }
            //System.out.println("elapsed remainder: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            for(int i = 0; i < 10; i++){
                if(player.getHotBarItemSelected() == i){
                    g.setColor(new Color(120,120,120));
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect((int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*xScale));
                if(hotbar[i] != null){
                    try{
                        g.drawImage(iims.getImage(hotbar[i].getName()),(int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*yScale),this);
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                    g.setColor(Color.WHITE);
                    g.drawString("x" + hotbar[i].getQuantity(),(int)(610*xScale)+(i*(int)(60*xScale)),(int)(895*yScale));
                }
                g.setColor(Color.BLACK);
                g.drawRect((int)(605*xScale)+(i*(int)(60*xScale)),(int)(830*yScale),(int)(50*xScale),(int)(50*yScale));
            }
            g.setColor(new Color(200,200,200,125));
            g.fillRect((int)(1450*xScale),(int)(50*yScale),(int)(300*xScale),(int)(400*yScale));
            g.setColor(Color.BLACK);
            int count = 0;
            for(String s : chost.getRecent()){
                g.drawString(s,(int)(1470*xScale),(int)(100*yScale)+(count*15));
                count ++; 
            }
            
            //System.out.println("elapsed hotbar: " + (System.nanoTime()-stime));
            //stime = System.nanoTime();
            int side=(int)Math.sqrt(biomes.size());
            int tcnt=0;
            int mbsize=12;
            g.setColor(Color.BLACK);
            g.fillRect((int)((1400-25)*xScale),(int)((600-25)*yScale),((int)(mbsize*xScale)*side)+(int)(50*xScale),((int)(mbsize*yScale)*side)+(int)(50*yScale));
            for(int r=0;r<side;r++) {
                for(int c=0;c<side;c++) {
                    Biome tb = biomes.get(tcnt);
                    Color btrans = new Color(tb.getColor().getRed(),tb.getColor().getGreen(),tb.getColor().getBlue(),120);
                    g.setColor(tb.getColor());
                    g.fillRect((int)(1400*xScale)+((int)(mbsize*xScale)*r), (int)(600*yScale)+((int)(mbsize*yScale)*c), (int)(mbsize*xScale), (int)(mbsize*yScale));
                    tcnt++;
                }
            }
            g.setColor(Color.RED);
            g.fillOval((int)((int)(1556*xScale)+(((center_x+(int)(900*xScale))/3000)*(int)(12*xScale))), (int)((int)(756*yScale)+(((center_y+(int)(500*yScale))/3000)*(int)(12*yScale))), (int)(10*xScale), (int)(10*yScale));
            g.setColor(Color.ORANGE);
            for(PlayerLocation pl : chost.getPlayers()) {
                g.fillOval((int)((int)(1556*xScale)+((((double)pl.x)/3000)*(int)(12*xScale))), (int)((int)(756*yScale)+((((double)pl.y)/3000)*(int)(12*yScale))), (int)(10*xScale), (int)(10*yScale));
            }
        }
        else if(view.equals("crafting")){
            messageBox.setVisible(false);
            menu.craftingUpdate();
            Font f = new Font("Courier New",Font.PLAIN,60);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Crafting",(int)(52*xScale),(int)(52*yScale));
            g.setColor(Color.WHITE);
            g.drawString("Crafting",(int)(50*yScale),(int)(50*yScale));
            g.setColor(Color.GRAY);
            g.fillRect((int)(100*xScale),(int)(100*yScale),(int)(1600*xScale),(int)(800*yScale));
            g.setColor(Color.BLACK);
            g.drawRect((int)(100*xScale),(int)(100*yScale),(int)(1600*xScale),(int)(800*yScale));
            f = new Font("Courier New",Font.PLAIN,13);
            g.setFont(f);
            for(MenuItem e: menu.getCraftingMenu()){
                g.setColor(Color.WHITE);
                g.fillRect((int)(e.getX()*xScale)+(int)(150*xScale),(int)(e.getY()*yScale)+(int)(150*yScale),(int)(130*xScale),(int)(110*yScale));
                if(hasResources(e.getItem())){
                    g.setColor(new Color(0, 183, 39));
                }
                else{
                    g.setColor(Color.RED);
                }
                g.drawRect((int)(e.getX()*xScale)+(int)(150*xScale),(int)(e.getY()*yScale)+(int)(150*yScale),(int)(130*xScale),(int)(110*yScale));
                g.setColor(Color.BLACK);
                g.drawString(e.getItem().getName(),(int)((e.getX()+160)*xScale),(int)((e.getY()+163)*yScale));
                g.drawImage(iims.getImage(e.getItem().getName()),(int)((e.getX()+163)*xScale),(int)((e.getY()+160)*yScale),(int)(65*xScale),(int)(65*yScale),this);
                for(int i = 0; i < e.getItem().getRequired().size(); i++){
                    g.drawString(e.getItem().getRequired().get(i).getQuantity()+" "+e.getItem().getRequired().get(i).getName(),(int)((e.getX()+160)*xScale),(int)((e.getY()+230)*yScale)+((int)(13*yScale)*i));
                }
            }
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*yScale),(int)(10*xScale),(int)(10*yScale)); 
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-(int)(5*xScale),(int)mp.getY()-(int)loc.getY()-(int)(5*yScale),(int)(10*xScale),(int)(10*yScale));
            for(int a = 0; a < itemsUsedCrafting.size();a++){
               int x = Integer.parseInt(itemsUsedCrafting.get(a).substring(itemsUsedCrafting.get(a).indexOf(":")+1,itemsUsedCrafting.get(a).indexOf(",")));
               int y = Integer.parseInt(itemsUsedCrafting.get(a).substring(itemsUsedCrafting.get(a).indexOf(",")+1,itemsUsedCrafting.get(a).length()));
               g.setColor(new Color(0,0,0,255-(y-50)));
               g.drawString(itemsUsedCrafting.get(a).substring(0,itemsUsedCrafting.get(a).indexOf(":")),(int)(x*xScale),(int)(y*yScale));
               itemsUsedCrafting.set(a,itemsUsedCrafting.get(a).substring(0,itemsUsedCrafting.get(a).indexOf(",")+1)+(y+1));
               if(y > 304){
                   itemsUsedCrafting.remove(itemsUsedCrafting.get(a));
               }
            }
        } else if (view.equals("dead")) {
            messageBox.setVisible(false);
            g.setColor(Color.RED);
            g.fillRect(0,0,(int)(1900*xScale),(int)(1000*yScale));
            Font f = new Font("Courier New",Font.BOLD,100);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("YOU DIED",(int)(650*xScale),(int)(400*yScale));
        }   
        frametime=(int)(((System.nanoTime()-stime)));
    }
    public void addBuilding(Building b) {
        buildings.add(b);
        if (b.getType().equals("house")) {
            House h = (House)(b);
            Human bot = new Human((int)(b.getX()),(int)(b.getY())+150,this,h);
            aithread.addBot(bot);
            h.setOwner(bot.getName());
            (new Thread(bot)).start();
        }
    }
    public void addResource(Resource r) {
        resources.add(r);
    }
    public void setCenterX(double x) {
        center_x=x;
    }
    public void setCenterY(double y) {
        center_y=y;
    }
    public double getCenterX() {
        return center_x;
    }
    public double getCenterY() {
        return center_y;
    }
    public void aPress() {
        a=true;
    }
    public void aRelease() {
        a=false;
    }
    public void dPress() {
        d=true;
    }
    public void dRelease() {
        d=false;
    }
    public void wPress() {
        w=true;
    }
    public void wRelease() {
        w=false;
    }
    public void sPress() {
        s=true;
    }
    public void sRelease() {
        s=false;
    }
    public void cPress(){
        if(view != "crafting"){
            view = "crafting";
        }
        else{
            view = "world";
        }
    }
    public void scroll(int x){
        int temp = (player.getHotBarItemSelected()+x)%10;
        if(temp < 0){
            temp = 9;
        }
        player.setHotBarItemSelected(temp);
    }
    public void onePress(){
        player.setHotBarItemSelected(0);
    }
    public void twoPress(){
        player.setHotBarItemSelected(1);
    }
    public void threePress(){
        player.setHotBarItemSelected(2);
    }
    public void fourPress(){
        player.setHotBarItemSelected(3);
    }
    public void fivePress(){
        player.setHotBarItemSelected(4);
    }
    public void sixPress(){
        player.setHotBarItemSelected(5);
    }
    public void sevenPress(){
        player.setHotBarItemSelected(6);
    }
    public void eightPress(){
        player.setHotBarItemSelected(7);
    }
    public void ninePress(){
        player.setHotBarItemSelected(8);
    }
    public void zeroPress(){
        player.setHotBarItemSelected(9);
    }
    public void iPress(){
        if(view != "inventory"){
            view = "inventory";
        }
        else{
            view = "world";
        }
    }
    public void inventoryClick(int x,int y){
        System.out.print("inventory click");
        for(MenuItem e: menu.getInventoryMenu()){
            if(new BoundingBox(e.getX()+150,e.getY()+150,130,80).intersects(new BoundingBox(x,y,1,1))){
                player.setHotBarItem(e.getItem());
            }
        }
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }
    public void mouseRightClick(double x, double y){
        System.out.println("Right click");
       for(Building b: buildings){
           if(b.getBoundingBox().intersects(new BoundingBox(x,y,1,1))){
               
           }
       }
    }
    public void mouseLeftClick(double x, double y) {
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        //System.out.println("Mouse clicked at x:" + x + " and y:" + y);
        Resource objectHit = null;
        Building buildinghit = null;
        Point mp = MouseInfo.getPointerInfo().getLocation();
        Point loc = frame.getLocationOnScreen();
        int[] fistcords = player.getFistCords((int)mp.getX()-(int)loc.getX(),(int)mp.getY()-(int)loc.getY());
        int fistx=fistcords[2];
        int fisty=fistcords[3];
        if (player.getFists().getAnimate().isRightArm()) {
            fistx=fistcords[0];
            fisty=fistcords[1];
        }
        Item temp = player.getHotbar()[player.getHotBarItemSelected()];
        if(temp != null && temp.getCanBePlaced()){
            if(temp.getName().equals("house frame")){
                buildings.add(new HouseFrame((int)x+(int)center_x,(int)y+(int)center_y));
                player.removeItem(temp.getName(),1);
                boolean check = false;
                for(Item a: player.getInventory()){
                    if(a.getName().equals(temp.getName())){
                        check = true;
                    }
                }
                if(!check){
                    player.getHotbar()[player.getHotBarItemSelected()] = null;
                }
            } else if(temp.getName().equals("wall frame")){
                buildings.add(new WallFrame((int)x+(int)center_x,(int)y+(int)center_y));
                player.removeItem(temp.getName(),1);
                boolean check = false;
                for(Item a: player.getInventory()){
                    if(a.getName().equals(temp.getName())){
                        check = true;
                    }
                }
                if(!check){
                    player.getHotbar()[player.getHotBarItemSelected()] = null;
                }
            }
        }
        if(temp != null && researchCenter.getBoundingBox().intersects(new BoundingBox(fistx+center_x,fisty+center_y,10,10)) && researchCenter.tree.isRequired(temp.getName())){
            researchCenter.tree.addComponents(temp);
        }
        for(Biome b : biomes) {
            if (b.getBoundingBox().intersects(screen)) {
                resources=b.getResources();
                for (Resource r : resources) {
                    if (r.getBoundingBox().intersects(new BoundingBox(fistx+center_x,fisty+center_y,10,10))) {
                        objectHit = r;
                        //System.out.println("Punched object: " + r.getType());
                        //System.out.println("Display: " + r.getYield().getQuantity());
                        if (r.getHealth()<=0) {
                            player.addItem(r.getYield());
                            r.setDestroyed();
                        }
                        //System.out.println("adding quantity of type " + r.getType() + " amount: " + r.getYield().getQuantity());
                    }
                }
            }
        }
        for (Building b : buildings) {
            if (b.getBoundingBox().intersects(new BoundingBox(fistx+center_x,fisty+center_y,10,10)) && player.getHotbar()[player.getHotBarItemSelected()] != null) {
                buildinghit = b;
                TempItem inFist = new TempItem(player.getHotbar()[player.getHotBarItemSelected()].getName(),player.getHotbar()[player.getHotBarItemSelected()].getQuantity());
                if(b.getType().contains("Frame")) {
                    player.removeItem(inFist.getName(),b.addResources(inFist));
                    System.out.println("hit " + b.getType());
                    if(b.isBuildable()){
                        if(b.getType().equals("House Frame")) {
                            this.addBuilding(new House(b.getX(),b.getY()));
                            buildings.remove(b);
                        }
                        if(b.getType().equals("Wall Frame")) {
                            this.addBuilding(new Wall(b.getX(),b.getY()));
                            buildings.remove(b);
                        }
                    }
                }
            }
        }
        player.punch(objectHit,(int)x,(int)y);        
        player.checkHotBar();
    }
    public void stopMotion(){
        w = false;
        a = false;
        s = false;
        d = false;
    }
    public void craftingClick(int x,int y){
        for(MenuItem i: menu.getCraftingMenu()){
            if(hasResources(i.getItem()) && new BoundingBox(i.getX()+150,i.getY()+150,270,110).intersects(new BoundingBox(x,y,1,1))){
                for(int a = 0;a < i.getItem().getRequired().size();a++){
                    itemsUsedCrafting.add(i.getItem().getRequired().get(a).getQuantity()+" "+i.getItem().getRequired().get(a).getName()+" removed:1650,"+(50+(a*15)));
                }
                player.craft(i.getItem());
            }
        }
    }
    public boolean hasResources(Item i){
        for(TempItem a: i.getRequired()){
            if(player.getInventoryQuantityOf(a.getName()) < a.getQuantity()){
                return false;
            }
        }
        return true;
    }
    public void addBiome(Biome b) {
        biomes.add(b);
        ArrayList<Resource> biomeResources = b.getResources();
        for(Resource r : biomeResources) {
            resources.add(r);
        }
    }
}