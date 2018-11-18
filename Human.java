import java.util.*;
import javafx.geometry.*;
import java.awt.*;
public class Human implements AI {
    double x;
    double y;
    int width=30;
    int height=30;
    Display d;
    Color color = Color.GREEN;
    String name;
    public Human(int x_loc, int y_loc, Display dis) {
        x=x_loc;
        y=y_loc;
        d=dis;
        String[] names = new String[]{"bob","brian","tristan","ethan","steve","bill","charles","oliver","harry","jack","george","noah","charlie","jacob","alfie","freddie","oscar","leo","logan","wes"};
        int namenum = (int)(Math.random()*names.length);
        name=names[namenum];
    }
    public void update() {
        ArrayList<Building> builds = d.getBuildings();
        ArrayList<Resource> resource = d.getResources();
        System.out.println("update start");
        int dir = (int)(Math.random()*4);
        double center_x=d.getCenterX();
        double center_y=d.getCenterY();
        int movx=0;
        int movy=0;
        int magnitude = 150;
        if (dir==0) {
            boolean check = false;
            for (Building b : builds) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x-magnitude,y+center_y,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resource) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x-magnitude,y+center_y,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movx=-magnitude;
            }
        } else if (dir==1) {
            boolean check = false;
            for (Building b : builds) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x+magnitude,y+center_y,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resource) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x+magnitude,y+center_y,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movx=magnitude;
            }
        } else if (dir==2) {
            boolean check = false;
            for (Building b : builds) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y-magnitude,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resource) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y-magnitude,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movy=-magnitude;
            }
        } else if (dir==3) {
            boolean check = false;
            for (Building b : builds) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y+magnitude,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resource) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y+magnitude,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movy=magnitude;
            }
        }
        System.out.println("collision check complete");
        for(int i=0;i<100;i++) {
            try {
                Thread.sleep(6);
            } catch (Exception e) {
                e.printStackTrace();
            }
            x+=(double)(movx)/100;
            y+=(double)(movy)/100;
        }
        System.out.println("completed update");
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
    public Color getColor() {
        return color;
    }
    public String getType() {
        return "human";
    }
    public String getName() {
        return name;
    }
}