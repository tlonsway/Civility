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
    private boolean isPlacable;
    private int cx;
    private int cy;
    private boolean isTool;
    private String toolName;
    private int toolDamage;
    public Item(String T,boolean IC,boolean IP){
        type = T;
        quantity = 1;
        if(IC){
            itemsRequired = new ArrayList<String>();
            numOfItem = new ArrayList<Integer>();
        }
        isCraftable = IC;
        isPlacable = IP;
    }
    public void setToolInfo(String TN, int TD){
        isTool = true;
        toolName = TN;
        toolDamage = TD;
    }
    public boolean isATool(){
        return isTool;
    }
    public int getToolDamage(){
        return toolDamage;
    }
    public String getToolName(){
        return toolName;
    }
    public boolean getIsPlacable(){
        return isPlacable;
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