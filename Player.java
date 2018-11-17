import javafx.geometry.*;
import java.awt.*;
public class Player{
    private String name;
    private double health;
    private Color color;
    //private Player_Inventory;
    private Player(String N, double H, Color C){
        name = N;
        health = H;
        color = C;
    }
}