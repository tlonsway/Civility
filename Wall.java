import java.awt.*;
 public class Wall extends Building {
    String owner;
    public Wall(double x_loc, double y_loc) {
        super("wall",x_loc,y_loc,25,25,Color.GRAY,null);
    }
    public void click() {
        
    }
}