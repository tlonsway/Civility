import javafx.geometry.*;
import java.awt.*;
import java.util.*;
public class Player{
    private String name;
    private double health;
    private Color color;
    private final double maxHealth = 100;
    private InventoryTwo inventory;
    private Fists fists;
    private Hotbar hotbar;
    public Player(String N, double H, Color C,InventoryTwo I,Fists F,Hotbar Hot){
        name = N;
        health = H;
        color = C;
        inventory = I;
        fists = F;
        hotbar = Hot;
    }
    public void setHotBarItem(Item i){
        hotbar.setHotbarItem(i);
    }   
    public Item[] getHotbar(){
        return hotbar.getHotbar();
    }
    public int getHotBarItemSelected(){
        return hotbar.getItemSelected();
    }
    public void setHotBarItemSelected(int i){
        hotbar.selectItem(i);
    }
    public void craft(Item i){
        inventory.craft(i);
    }
    public String getName(){
        return name;
    }
    public void removeItem(String name, int quantity){
        inventory.removeItem(name,quantity);
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
        return inventory.getInventory();
    }
    public void addItem(Item i){
        //System.out.println("Player: " + i.getQuantity());
        inventory.addItem(i);
    }
    public int[] getFistCords(int x,int y){
        return fists.getFistsCords(x,y);
    }   
    public Color getFistColor(){
        return fists.getColor();
    }
    public void punch(Resource resource,int x,int y){
        fists.animate(x,y);
        int playerWeaponDamage = 0;
        if(getHotbar()[getHotBarItemSelected()] != null){
            playerWeaponDamage = getHotbar()[getHotBarItemSelected()].getToolDamage();
        }
        if(resource != null){
            resource.dealDamage(5+playerWeaponDamage);
        }
    }
    public Fists getFists() {
        return fists;
    }
    public void checkHotBar(){
       hotbar.check();
    }
}