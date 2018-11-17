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
            center_x-=10;
        }
        if (d) {
            center_x+=10;
        }
        if (w) {
            center_y-=10;
        }
        if (s) {
            center_y+=10;
        }
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(""+(int)(in.getGold()),40,40);
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        for (Building b : buildings) {
            if (b.getBoundingBox().intersects(screen)) {
                g.setColor(b.getColor());
                g.fillRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
            }
        }
        for (Resource r : resources) {
            if (r.getBoundingBox().intersects(screen)) {
                g.setColor(r.getColor());
                g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
            }
        }
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