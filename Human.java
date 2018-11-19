import java.util.*;
import javafx.geometry.*;
import java.awt.*;
public class Human implements AI,Runnable {
    double x;
    double y;
    int width=30;
    int height=30;
    Display d;
    Color color = new Color(255,226,140);
    String name;
    House home;
    public Human(int x_loc, int y_loc, Display dis, House h) {
        x=x_loc;
        y=y_loc;
        d=dis;
        String[] names = new String[]{"Bob","Brian","Tristan","Ethan","Steve","Bill","Charles","Oliver","Harry","Jack","George","Noah","Charlie","Jacob","Alfie","Freddie","Oscar","Leo","Logan","Wes", "Roger","Nicholas","Rich","Korey","Renaldo","Donte","Rodolfo","Gerald","Rigoberto","Jamar","Doyle","Thad","Edgardo","Trey","Sylvester","Abel","Markus","Bryan","Odell","Oswaldo","Albert","Hai","Robby","Parker","Kermit","Darin","Willy","Juan","Jamie","Junior","Faustino","Harvey","Gayle","Eric","Otha","Sam","Roscoe","Stacey","Gerardo","Karl","Titus","Jerrod","Minh","Young","Danial","Emery","Felipe","Gustavo","Elisha","Malcolm"};
        int namenum = (int)(Math.random()*names.length);
        name=names[namenum];
        home=h;
    }
    public void update() {
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep((int)((Math.random()*2000)+500));
                //Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<Building> builds = d.getBuildings();
            ArrayList<Resource> resource = d.getResources();
            int dir = (int)(Math.random()*4);
            double center_x=d.getCenterX();
            double center_y=d.getCenterY();
            int movx=0;
            int movy=0;
            int magnitude = 150;
            int maxhomedist = 500;
            if (dir==0) {
                boolean check = false;
                for (Building b : builds) {
                    if (b.getBoundingBox().intersects(new BoundingBox(x-magnitude,y,width,height))) {
                        check = true;
                    }
                }
                for (Resource r : resource) {
                    if (r.getBoundingBox().intersects(new BoundingBox(x-magnitude,y,width,height))) {
                        check = true;
                    }
                }
                if (!check&&homeDistance(x-magnitude,y)<maxhomedist) {
                    movx=-magnitude;
                }
            } else if (dir==1) {
                boolean check = false;
                for (Building b : builds) {
                    if (b.getBoundingBox().intersects(new BoundingBox(x+magnitude,y,width,height))) {
                        check = true;
                    }
                }
                for (Resource r : resource) {
                    if (r.getBoundingBox().intersects(new BoundingBox(x+magnitude,y,width,height))) {
                        check = true;
                    }
                }
                if (!check&&homeDistance(x+magnitude,y)<maxhomedist) {
                    movx=magnitude;
                }
            } else if (dir==2) {
                boolean check = false;
                for (Building b : builds) {
                    if (b.getBoundingBox().intersects(new BoundingBox(x,y-magnitude,width,height))) {
                        check = true;
                    }
                }
                for (Resource r : resource) {
                    if (r.getBoundingBox().intersects(new BoundingBox(x,y-magnitude,width,height))) {
                        check = true;
                    }
                }
                if (!check&&homeDistance(x,y-magnitude)<maxhomedist) {
                    movy=-magnitude;
                }
            } else if (dir==3) {
                boolean check = false;
                for (Building b : builds) {
                    if (b.getBoundingBox().intersects(new BoundingBox(x,y+magnitude,width,height))) {
                        check = true;
                    }
                }
                for (Resource r : resource) {
                    if (r.getBoundingBox().intersects(new BoundingBox(x,y+magnitude,width,height))) {
                        check = true;
                    }
                }
                if (!check&&homeDistance(x,y+magnitude)<maxhomedist) {
                    movy=magnitude;
                }
            }
            for(int i=0;i<100;i++) {
                try {
                    Thread.sleep(6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                x+=(double)(movx)/100;
                y+=(double)(movy)/100;
            }
        }
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
    private double homeDistance(double x2, double y2) {
        return Math.sqrt((x2-home.getX())*(x2-home.getX())+(y2-home.getY())*(y2-home.getY()));
    }
}