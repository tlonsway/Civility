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
    public int getInventoryQuantityOf(String s){
        return inventory.getQuantityOf(s);
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
            System.out.println("current weapon damage: " + playerWeaponDamage);
        }
        if(resource != null){
            int treedam = 4;
            int rockdam = 2;
            int fragiledam = 5;
            int specdam = 0;
            if (playerWeaponDamage!=0) {
                Item weapon = getHotbar()[getHotBarItemSelected()];
                int[] dams = weapon.getDamages();
                treedam=dams[1];
                rockdam=dams[2];
                fragiledam=dams[3];
                specdam=dams[4];
            }
            if (resource.getCategory()==0) {
                resource.dealDamage(treedam);
            }
            if (resource.getCategory()==1) {
                resource.dealDamage(rockdam);
            }
            if (resource.getCategory()==2) {
                resource.dealDamage(fragiledam);
            }
            if (resource.getCategory()==3) {
                resource.dealDamage(specdam);
            }
        }
    }
    public Fists getFists() {
        return fists;
    }
    public void checkHotBar(){
       hotbar.check();
    }
}