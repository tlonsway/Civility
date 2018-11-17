import java.util.*;
import javafx.geometry.*;
import java.awt.*;
public class Human implements AI {
    int x;
    int y;
    int width=30;
    int height=30;
    Display d;
    Color color = Color.GREEN;
    public Human(int x_loc, int y_loc, Display dis) {
        x=x_loc;
        y=y_loc;
        d=dis;
    }
    public void update() {
        ArrayList<Building> buildings = d.getBuildings();
        ArrayList<Resource> resources = d.getResources();
        int dir = (int)(Math.random()*4);
        double center_x=d.getCenterX();
        double center_y=d.getCenterY();
        int movx=0;
        int movy=0;
        if (dir==0) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x-10,y+center_y,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x-10,y+center_y,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movx=-10;
            }
        } else if (dir==1) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x+10,y+center_y,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x+10,y+center_y,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movx=10;
            }
        } else if (dir==2) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y-10,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y-10,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movy=-10;
            }
        } else if (dir==3) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y+10,width,height))) {
                    check = true;
                }
            }
            for (Resource r : resources) {
                if (r.getBoundingBox().intersects(new BoundingBox(x+center_x,y+center_y+10,width,height))) {
                    check = true;
                }
            }
            if (!check) {
                movy=10;
            }
        }
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
            x+=movx/2;
            y+=movy/2;
        }
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
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
    public Color getColor() {
        return color;
    }
}