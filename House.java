import java.awt.*;
 public class House extends Building {
    String owner;
    public House(double x_loc, double y_loc) {
        super("house",x_loc,y_loc,75,75,Color.GREEN,null);
        owner="no owner";
    }
    public void click() {
        
    }
    public void setOwner(String name) {
        owner=name;
    }
    public String getOwner() {
        return owner;
    }
}