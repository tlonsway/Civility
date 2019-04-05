import java.util.*;
public class MenuManager{
    private ArrayList<MenuItem> craftingMenu;
    private ArrayList<MenuItem> inventoryMenu;
    private ArrayList<Item> craftingList;
    private ArrayList<Item> inventoryItems;
    public MenuManager(ArrayList<Item> cl, ArrayList<Item> inven){
        craftingList = cl;
        inventoryItems = inven;
        craftingUpdate();
        inventoryUpdate();
    }
    public void craftingUpdate(){
        craftingMenu = new ArrayList<MenuItem>();
        for(int i = 0; i < craftingList.size(); i++){
            craftingMenu.add(new MenuItem(craftingList.get(i),(i%5)*280, ((int)(i/5))*120));
        }
    }
    public void inventoryUpdate(){
        inventoryMenu = new ArrayList<MenuItem>();
        for(int i = 0; i < inventoryItems.size(); i++){
            inventoryMenu.add(new MenuItem(inventoryItems.get(i),(i%10)*140, (int)(i/10)*90));
        }
    }
    public ArrayList<MenuItem> getCraftingMenu(){
        return craftingMenu;
    }
    public ArrayList<MenuItem> getInventoryMenu(){
        return inventoryMenu;
    }
    public ArrayList<Item> getCraftingList(){
        return craftingList;
    }
}