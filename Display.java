import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import javafx.geometry.*;
public class Display extends JComponent {
    ArrayList<Building> buildings;
    ArrayList<Resource> resources;
    double center_x;
    double center_y;
    int width;
    int height; 
    boolean w,a,s,d,i=false;
    Inventory in;
    Player player;
    AIThread aithread;
    public Display(int w, int h, Inventory inventory,Player p, AIThread at) {
        buildings = new ArrayList<Building>();
        resources = new ArrayList<Resource>();
        center_x=0;
        center_y=0;
        width=w;
        height=h;
        in=inventory;
        player = p;
        aithread=at;
    }
    public void update() {
        for(Building b : buildings) {
            if (b.getType().equals("goldmine")) {
                in.addGold(.005);
            }
        }
        int moveamt = 5;
        if (a) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_x-=moveamt;
        }
        if (d) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                    check = true;
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
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500-moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_y-=moveamt;
        }
        if (s) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_y+=moveamt;
        }
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(i){
            //g.setColor(Color.GREEN);
            //g.fillRect(0,0,1800,1000);
            g.setColor(Color.GRAY);
            g.fillRect(100,100,1600,800);
            g.setColor(Color.BLACK);
            g.drawRect(100,100,1600,800);
            int x = 150;
            int y = 150;
            ArrayList<Item> Items = player.getInventory();
            for(Item i: player.getInventory()){
                g.setColor(Color.WHITE);
                g.fillRect(x,y,50,50);
                g.setColor(Color.BLACK);
                g.drawRect(x,y,50,50);
                g.drawString(i.getType(),x+5,y+20);
                g.drawString("x"+i.getQuantity(),x+15, y+40);
                x+=100;
                if(x>=1650){
                    x = 150;
                    y += 50;
                }
            }
            Point mp = MouseInfo.getPointerInfo().getLocation();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-5,(int)mp.getY()-5,10,10);            
        }
        else{
            BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(screen)) {
                    g.setColor(b.getColor());
                    g.fillRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                    g.setColor(Color.BLACK);
                    Font f = new Font("Courier New",Font.PLAIN,15);
                    g.setFont(f);
                    String btitle="";
                    if (b.getType().equals("house")) {
                        btitle+=((House)(b)).getOwner()+"'s ";
                    }
                    g.drawString(btitle+b.getType(), (int)(b.getX()-center_x), (int)(b.getY()-center_y+b.getHeight()+20));
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(screen)) {
                    g.setColor(r.getColor());
                    g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
                    g.setColor(Color.BLACK);
                    Font f = new Font("Courier New",Font.PLAIN,18);
                    g.setFont(f);
                    g.drawString(r.getType(), (int)(r.getX()-center_x), (int)(r.getY()-center_y+r.getHeight()+20));
                }
            }
            for(AI a : aithread.getBots()) {
                if (a.getBoundingBox().intersects(screen)) {
                    g.setColor(a.getColor());
                    g.fillRect((int)(a.getX()-center_x),(int)(a.getY()-center_y),(int)(a.getWidth()),(int)(a.getHeight()));
                    g.setColor(Color.BLACK);
                    Font f = new Font("Courier New",Font.PLAIN,18);
                    g.setFont(f);
                    g.drawString(a.getName(),(int)(a.getX()-center_x),(int)(a.getY()-center_y+a.getHeight()+20));
                }                
            }
            g.setColor(Color.BLACK);
            Font f = new Font("Courier New", Font.BOLD, 30);
            g.setFont(f);
            g.drawString("Gold: "+(int)(in.getGold()),40,40);
            g.drawString("center_x: " + center_x + "   center_y: " + center_y,300,40);
            g.setColor(player.getColor());
            g.fillOval(900,500,40,40);
            Point mp = MouseInfo.getPointerInfo().getLocation();
            int[] fistCords = player.getFistCords((int)mp.getX(),(int)mp.getY());
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-5,(int)mp.getY()-5,10,10);
            g.setColor(player.getFistColor());
            g.fillOval(fistCords[0],fistCords[1],10,10);
            g.fillOval(fistCords[2],fistCords[3],10,10);
            g.setColor(Color.BLACK);
            g.drawRect(899,479,41,11);
            g.setColor(Color.RED);
            g.fillRect(900,480,40,10);
            g.setColor(Color.GREEN);
            g.fillRect(900,480,(int)(player.getHealth()/100*40),10);
            g.setColor(Color.GRAY);
            g.fillRect(595,820,610,70);
            g.setColor(Color.BLACK);
            g.drawRect(595,820,610,70);
            Item[] hotbar = player.getHotbar();
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            for(int i = 0; i < 10; i++){
                g.setColor(Color.WHITE);
                g.fillRect(605+i*60,830,50,50);
                g.setColor(Color.BLACK);
                g.drawRect(605+i*60,830,50,50);
                g.drawString(hotbar[i].getType(),610+i*60,850);
                g.drawString("x" + hotbar[i].getQuantity(),610+i*60,865);
            }
        }
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
    public void iPress(){
        if(i){
            i = false;
        }
        else{
            i = true;
        }
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }
    
}