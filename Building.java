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
    Building(String t, double x_loc, double y_loc, double w, double h, Color c,ArrayList<TempItem> TI) {
        type=t;
        x=x_loc;
        y=y_loc;
        width=w;
        height=h;
        color=c;
        buildItemsRequired = TI;
    }
    public void setRequired(ArrayList<TempItem> TI) {
        buildItemsRequired=TI;
    }
    public ArrayList<TempItem> getBuildItemsRequired(){
        return buildItemsRequired;
    }
    public int addResources(TempItem a){
        //returns the amount of item used
        int amountUsed = 0;
        for(int i = 0; i < buildItemsRequired.size();i++){
            if(a.getName().equals(buildItemsRequired.get(i).getName())){
                if(a.getQuantity() >= buildItemsRequired.get(i).getQuantity()){
                    amountUsed = buildItemsRequired.get(i).getQuantity();
                    buildItemsRequired.remove(buildItemsRequired.get(i));
                }
                else{
                    buildItemsRequired.get(i).changeQuantity(-1*a.getQuantity());
                    amountUsed = a.getQuantity();
                }
            }
        }
        return amountUsed;
    }
    public boolean isBuildable(){
        if(buildItemsRequired.size() < 1){
            return true;
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