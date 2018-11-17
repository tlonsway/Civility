import java.util.*;
public class Player_Inventory{
    private ArrayList<Item> items;
    public Player_Inventory(){
        items = new ArrayList<Item>();
    }
    public void addItem(Item i){
        boolean found = false;
        for(Item a: items){
            if(a.getType().equals(i.getType())){
                found = true;
                a.changeQuantity(1);
            }
            if(!found){
                items.add(i);
            }
        }
    }
    public void removeItem(Item i){
        boolean found = false;
        for(Item a: items){
            if(a.getType().equals(i.getType())){
                found = true;
                a.changeQuantity(-1);
                if(a.getQuantity() <= 0){
                    items.remove(a);
                }
            }
        }
    }
    public ArrayList<Item> getItems(){
        return items;
    }
}