import javafx.geometry.*;
import java.awt.*;
import java.util.*;
public class Player{
    private String name;
    private double health;
    private Color color;
    private final double maxHealth = 100;
    private Player_Inventory inventory;
    private Fists fists;
    public Player(String N, double H, Color C,Player_Inventory I,Fists F){
        name = N;
        health = H;
        color = C;
        inventory = I;
        fists = F;
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
    public boolean doDamage(double damage){
        //deals the damage to the player and returns true if player is dead
        health -= damage;
        if(health <= 0){
            return true;
        }
        return false;
    }
    public ArrayList<Item> getInventory(){
        return inventory.getItems();
    }
    public void addItem(Item i){
        inventory.addItem(i);
    }
    public int[] getFistCords(int x,int y){
        return fists.getFistsCords(x,y);
    }   
    public Color getFistColor(){
        return fists.getColor();
    }
}