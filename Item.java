import java.util.*;
import java.awt.*;
public class Item{
    private String name;
    private int quantity;
    private boolean canBePlaced;
    private Color color;
    private String shape;
    private ArrayList<TempItem> required;
    private int toolDamage; 
    private int treeDamage;
    private int rockDamage;
    private int fragileDamage;
    private int specialDamage;
    private int techLevel;
    private int[] damages; //0:tool,1:tree,2:rock,3:fragile,4:special
    public Item(String n, int q, boolean cBP,Color c, String s, ArrayList<TempItem> r,int[] dam,int tl){
        name = n;
        quantity = q;
        canBePlaced = cBP;
        color = c;
        shape = s;
        required = r;
        damages = dam;
        techLevel = tl;
    }
    public String toString(){
        String ret = "Name: "+name+" Quantity: "+quantity;
        return ret;
    }
    public int getTechLevel(){
        return techLevel;
    }   
    public boolean canbeCrafteg(){
        if(required == null){
            return false;
        }
        return true;
    }
    public boolean isTool(){
        if(toolDamage > 0){
            return true;
        }
        return false;
    }
    public int[] getDamages() {
        return damages;
    }
    public int getToolDamage(){
        return damages[0];
    }
    public ArrayList<TempItem> getRequired(){
        return required;
    }
    public void changeQuantity(int x){
        quantity += x;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getName(){
        return name;
    }
    public boolean getCanBePlaced(){
        return canBePlaced;
    }
}