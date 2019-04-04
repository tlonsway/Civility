import java.util.*;
public class MenuManger{
    ArrayList<MenuItem> craftingMenu;
    ArrayList<MenuItem> inventoryMenu;
    ArrayList<Item> craftingList;
    ArrayList<Item> inventoryItems;
    public MenuManger(ArrayList<Item> cl, ArrayList<Item> inven){
        craftingList = cl;
        inventoryItems = inven;
        craftingUpdate();
    }
    public void craftingUpdate(){
        craftingMenu = new ArrayList<MenuItem>();
        for(int i = 0; i < craftingList.size(); i++){
            craftingMenu.add(new MenuItem(craftingList.get(i),(i%4)*350, (int)(i/3)*200));
        }
    }
    public void inventoryUpdate(){
        inventoryMenu = new ArrayList<MenuItem>();
        for(int i = 0; i < craftingList.size(); i++){
            inventoryMenu.add(new MenuItem(inventoryItems.get(i),(i%4)*350, (int)(i/3)*200));
        }
    }
    public ArrayList<MenuItem> getCraftingMenu(){
        return craftingMenu;
    }
    public ArrayList<MenuItem> getInventoryItem(){
        return inventoryMenu;
    }
}