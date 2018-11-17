import javafx.geometry.*;
import java.awt.*;
public interface AI {
    public void update();
    public double getX();
    public double getY();
    public int getWidth();
    public int getHeight();
    public BoundingBox getBoundingBox();
    public Color getColor();
    public String getType();
    public String getName();
}