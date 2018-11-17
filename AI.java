import javafx.geometry.*;
import java.awt.*;
public interface AI {
    public void update();
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public BoundingBox getBoundingBox();
    public Color getColor();
}