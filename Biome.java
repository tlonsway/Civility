import java.util.*;
import java.awt.*;
import javafx.geometry.*;
public class Biome{
    private ArrayList<Resource> resources;
    private int x,y,width,height;
    private String type;
    private Color color;
    //possible types are: winter, desert, forest, rocky
    public Biome(String t, int x_loc, int y_loc) {
        resources = new ArrayList<Resource>();
        x=x_loc;
        y=y_loc;
        width=3000;
        height=3000;
        if (t.equals("winter")) {
            int amt = 255-(int)(Math.random()*40)-20;
            color = new Color(amt,amt,amt);
            for(int i=0;i<20+(int)(Math.random()*80);i++) {
                resources.add(new PineTree(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<(int)(Math.random()*10);i++) {
                resources.add(new Rock(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<20+(int)(Math.random()*80);i++) {
                resources.add(new Glacier(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
        } else if (t.equals("desert")) {
            color = new Color(255,250,100+(int)(Math.random()*121));
            for(int i=0;i<(int)(Math.random()*10);i++) {
                resources.add(new Rock(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<30+(int)(Math.random()*120);i++) {
                resources.add(new Cactus(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
        } else if (t.equals("forest")) {
            color = new Color(0,100+(int)(Math.random()*101),5);
            for(int i=0;i<50+(int)(Math.random()*150);i++) {
                resources.add(new Tree(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<(int)(Math.random()*10);i++) {
                resources.add(new Rock(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
        } else if (t.equals("rocky")) {
            int amt = 50+(int)(Math.random()*100);
            color = new Color(amt,amt,amt);
            for(int i=0;i<2+(int)(Math.random()*8);i++) {
                resources.add(new Tree(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<20+(int)(Math.random()*80);i++) {
                resources.add(new Rock(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
            for(int i=0;i<(int)(Math.random()*10);i++) {
                resources.add(new Crystal(x+(int)(width*((Math.random()*1))),y+(int)(height*((Math.random()*1)))));
            }
        }
    }
    public Color getColor() {
        return color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }
    public void removeResource(int i) {
        resources.remove(i);
    }
    public Resource getResource(int i) {
        return resources.get(i);
    }
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
}