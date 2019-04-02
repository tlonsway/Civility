import java.util.*;
public class Item{
    private String name;
    private int quantity;
    private boolean canBePlaced;
    private int[] color;
    private String shape;
    public Item(String n, int q, boolean cBP,int[] c, String s){
        name = n;
        quantity = q;
        canBePlaced = cBP;
        color = c;
        shape = s;
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