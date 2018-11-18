import javafx.geometry.*;
import java.awt.*;
public abstract class Resource{
    private double x;
    private double y;
    private double height;
    private double width;
    private String type;
    private Color color;
    private double Max_Health;
    private double health;
    //private String Item_type
    public Resource(double X, double Y, double H, double W, String T, Color C){
        x = X;
        y = Y;
        height = H;
        width = W;
        type = T;
        color = C;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    public String getType(){
        return type;
    }
    public Color getColor(){
        return color;
    }
    public double getHealth(){
        return health;
    }
    public void dealDamage(double damage){
        health -= damage;
    }
    public double getMax_Health(){
        return Max_Health;
    }
}