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
    public Item(String n, int q, boolean cBP,Color c, String s, ArrayList<TempItem> r,int td){
        name = n;
        quantity = q;
        canBePlaced = cBP;
        color = c;
        shape = s;
        required = r;
        toolDamage = td;
    }
    public String ToString(){
        String ret = "Name: "+name+" Quantity: "+quantity;
        return ret;
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
    public int getToolDamage(){
        return toolDamage;
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