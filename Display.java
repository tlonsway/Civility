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
    boolean w,a,s,d=false;
    Inventory in;
    public Display(int w, int h, Inventory inventory) {
        buildings = new ArrayList<Building>();
        resources = new ArrayList<Resource>();
        center_x=0;
        center_y=0;
        width=w;
        height=h;
        in=inventory;
    }
    public void update() {
        for(Building b : buildings) {
            if (b.getType().equals("goldmine")) {
                in.addGold(.005);
            }
        }
        if (a) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(890+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_x-=10;
        }
        if (d) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(910+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_x+=10;
        }
        if (w) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,490+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_y-=10;
        }
        if (s) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,510+center_y,40,40))) {
                    check = true;
                }
            }
            if (!check)
                center_y+=10;
        }
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        for (Building b : buildings) {
            if (b.getBoundingBox().intersects(screen)) {
                g.setColor(b.getColor());
                g.fillRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                g.setColor(Color.BLACK);
                Font f = new Font("Courier New",Font.PLAIN,18);
                g.setFont(f);
                g.drawString(b.getType(), (int)(b.getX()-center_x), (int)(b.getY()-center_y+b.getHeight()+20));
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
        g.setColor(Color.BLACK);
        Font f = new Font("Courier New", Font.BOLD, 30);
        g.setFont(f);
        g.drawString("Gold: "+(int)(in.getGold()),40,40);
        g.setColor(Color.RED);
        g.fillOval(900,500,40,40);
    }
    public void addBuilding(Building b) {
        buildings.add(b);
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
}