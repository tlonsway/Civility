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
    public String getName(){
        return name;
    }
    public double getHealth(){
        return health;
    }
    public Color getColor(){
        return color;
    }
    public boolean recieveDamage(double damage){
        //deals the damage to the player and returns true if player is dead
        health -= damage;
        if(health <= 0){
            return true;
        }
        return false;
    }
    
}