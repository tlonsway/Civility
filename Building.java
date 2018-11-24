import javafx.geometry.*;
import java.awt.*;
import java.util.*;
public abstract class Building implements Clickable {
    private double x;
    private double y;
    private double width;
    private double height;
    private String type;
    private Color color;
    private ArrayList<TempItem> buildItemsRequired;
    private ArrayList<TempItem> buildItemsHas;
    Building(String t, double x_loc, double y_loc, double w, double h, Color c,ArrayList<TempItem> TI) {
        type=t;
        x=x_loc;
        y=y_loc;
        width=w;
        height=h;
        color=c;
        buildItemsRequired = TI;
        buildItemsHas = new ArrayList<TempItem>();
    }
    public int addResource(Item i){
        if(contains(i,buildItemsHas)){
            if(i.getQuantity() <= getRequiredQuantityOf(i.getType())-getHasQuantityOf(i.getType())){
                return i.getQuantity();
            }
            return getRequiredQuantityOf(i.getType())-getHasQuantityOf(i.getType());
        }
        if(i.getQuantity() <= getRequiredQuantityOf(i.getType())-getHasQuantityOf(i.getType())){
            buildItemsHas.add(new TempItem(i.getType(),i.getQuantity()));
            return i.getQuantity();
        }
        buildItemsHas.add(new TempItem(i.getType(),getRequiredQuantityOf(i.getType())-getHasQuantityOf(i.getType())));
        return getRequiredQuantityOf(i.getType())-getHasQuantityOf(i.getType());
    }
    public boolean isBuildable(){
        for(TempItem e: buildItemsRequired){
            boolean check = false;
            for(TempItem i: buildItemsHas){
                if(i.getType().equals(e.getType()) && i.getQuantity() >= e.getQuantity()){
                    check = true;
                }
            }
            if(!check){
                return false;
            }
        }
        return true;
    }
    public int getRequiredQuantityOf(String n){
        for(TempItem t: buildItemsRequired){
            if(t.getType().equals(n)){
                return t.getQuantity();
            }
        }
        return 0;
    }
    public int getHasQuantityOf(String n){
        for(TempItem t: buildItemsHas){
            if(t.getType().equals(n)){
                return t.getQuantity();
            }
        }
        return 0;
    }
    public boolean contains(Item i,ArrayList<TempItem> items){
        for(TempItem e: items){
            if(e.getType().equals(i.getType())){
                return true;
            }
        }
        return false;
    }
    public boolean requires(Item i){
        for(TempItem e: buildItemsRequired){
            if(e.getType().equals(i.getType())){
                return true;
            }
        }
        return false;
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