import java.util.*;
import java.awt.*;
import javafx.geometry.*;
public class Biome{
    private ArrayList<Resource> resources;
    private int x,y,width,height;
    private String type;
    private Color color;
    long randseed;
    //possible types are: winter, desert, forest, rocky
    public Biome(String t, int x_loc, int y_loc, long rs) {
        resources = new ArrayList<Resource>();
        x=x_loc;
        y=y_loc;
        width=3000;
        height=3000;
        type = t;
        //randseed=123456789;
        randseed=rs;
        Random generator = new Random(randseed);
        if (t.equals("winter")) {
            int amt = 255-(int)(generator.nextDouble()*40)-20;
            color = new Color(amt,amt,amt);
            for(int i=0;i<20+(int)(generator.nextDouble()*80);i++) {
                resources.add(new PineTree(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<(int)(generator.nextDouble()*10);i++) {
                resources.add(new Rock(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<20+(int)(generator.nextDouble()*80);i++) {
                resources.add(new Glacier(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("desert")) {
            color = new Color(255,250,100+(int)(generator.nextDouble()*121));
            for(int i=0;i<(int)(generator.nextDouble()*10);i++) {
                resources.add(new Rock(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<30+(int)(generator.nextDouble()*120);i++) {
                resources.add(new Cactus(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("forest")) {
            color = new Color(0,100+(int)(generator.nextDouble()*101),5);
            for(int i=0;i<50+(int)(generator.nextDouble()*150);i++) {
                resources.add(new Tree(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<(int)(generator.nextDouble()*10);i++) {
                resources.add(new Rock(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<5+(int)(generator.nextDouble()*20);i++) {
                resources.add(new Bush(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("rocky")) {
            int amt = 50+(int)(generator.nextDouble()*100);
            color = new Color(amt,amt,amt);
            for(int i=0;i<2+(int)(generator.nextDouble()*8);i++) {
                resources.add(new Tree(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<20+(int)(generator.nextDouble()*80);i++) {
                resources.add(new Rock(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
            for(int i=0;i<(int)(generator.nextDouble()*10);i++) {
                resources.add(new Crystal(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("jungle")) {
            color = new Color(0,80+(int)(generator.nextDouble()*80),0);
            for(int i=0;i<20+(int)(generator.nextDouble()*100);i++) {
                resources.add(new RubberTree(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("plains")) {
            color = new Color(134,206,0);
            for(int i=0;i<30+(int)(generator.nextDouble()*120);i++) {
                resources.add(new Bush(x+(int)(width*((generator.nextDouble()*1))),y+(int)(height*((generator.nextDouble()*1)))));
            }
        } else if (t.equals("swamp")) {
            color = new Color(0,96,28);
            
            
        }
        
    }
    public String getType(){
        return type;
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