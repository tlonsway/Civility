import java.util.*;
public class Item{
    private String type;
    private int quantity;
    private int bx;
    private int by;
    private int bwidth;
    private int bheight;
    private boolean isCraftable;
    private ArrayList<String> itemsRequired;
    private ArrayList<Integer> numOfItem;
    private boolean isPlaceable;
    private int cx;
    private int cy;
    public Item(String T,boolean IC){
        type = T;
        quantity = 1;
        itemsRequired = new ArrayList<String>();
        numOfItem = new ArrayList<Integer>();
        isCraftable = IC;
    }
    public void setCX(int x){
        cx = x;
    }
    public int getCX(){
        return cx;
    }
    public int getCY(){
        return cy;
    }
    public void setCY(int y){
        cy = y;
    }
    public ArrayList<String> getItemsRequired(){
        return itemsRequired;
    }
    public void addStringToItemsRequired(String name){
        itemsRequired.add(name);
    }
    public ArrayList<Integer> getNumOfItem(){
        return numOfItem;
    }
    public void addIntegerToNumOfItem(int i){
        numOfItem.add(i);
    }
    public void setButtonInfo(int BX,int BY,int BW,int BH){
        bx = BX;
        by = BY;
        bwidth = BW;
        bheight = BH;
    }
    public boolean getIsCraftable(){
        return isCraftable;
    }
    public int getBX(){
        return bx;
    }
    public int getBY(){
        return by;
    }   
    public int getBWidth(){
        return bwidth;
    }
    public int getBHeight(){
        return bheight;
    }
    public Item(String T, int quant){
        type = T;
        quantity = quant;
    }
    public String getType(){
        return type;
    }
    public void changeQuantity(int num){
        //System.out.println("quantity changed by: " + num);
        quantity += num;
    }
    public int getQuantity(){
        return quantity;
    }
}