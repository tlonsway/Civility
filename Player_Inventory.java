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
                a.changeQuantity(i.getQuantity());
            }
        }
        if(!found){
            int x = 150;
            int y = 150;
            if(items.size()>0){
                x = items.get(items.size()-1).getBX()+100;
                y = items.get(items.size()-1).getBY();
                if(x > 1650){
                    x = 150;
                    y += 100;
                }
            }
            i.setButtonInfo(x,y,50,50);
            items.add(i);
        }
    }
    public void removeItem(Item i){
        boolean found = false;
        for(Item a: items){
            if(a.getType().equals(i.getType())){
                found = true;
                a.changeQuantity(i.getQuantity()*-1);
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