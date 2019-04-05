import javafx.geometry.*;
import java.awt.*;
public abstract class Resource implements Clickable {
    private double x;
    private double y;
    private double height;
    private double width;
    private String type;
    private int category; //0=woods, 1=rocks/metals, 2=fragiles, 3=specials
    private Color color;
    private double Max_Health;
    private double health;
    private Item yield;
    private boolean destroyed;
    public Resource(double X, double Y, double H, double W, String T, Color C, double MH,String YN, int maxquant, int cat){
        x = X;
        y = Y;
        height = H;
        width = W;
        type = T;
        color = C;
        health = MH;
        Max_Health = MH;
        yield = new Item(YN,(int)(Math.random()*maxquant)+1,false,null,null,null,new int[]{0,0,0,0,0});
        category=cat;
        destroyed=false;
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
    public Item getYield(){
        Item tempnew = new Item(yield.getName(),yield.getQuantity(),yield.getCanBePlaced(),null,"",null,new int[]{0,0,0,0});
        return tempnew;
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
    public int getCategory() {
        return category;
    }
    public void setDestroyed() {
        destroyed=true;
    }
    public boolean isDestroyed() {
        return destroyed;
    }
}