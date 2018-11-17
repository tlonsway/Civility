import javafx.geometry.*;
import java.awt.*;
public abstract class Building {
    double x;
    double y;
    double width;
    double height;
    String type;
    Color color;
    Building(String t, double x_loc, double y_loc, double w, double h, Color c) {
        type=t;
        x=x_loc;
        y=y_loc;
        width=w;
        height=h;
        color=c;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
    public String getType() {
        return type;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public Color getColor() {
        return color;
    }
}