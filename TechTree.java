import java.util.*;
public class TechTree{
    private int techLevel;
    private ArrayList<String[]> techLevelUpRequirments;
    private ArrayList<TempItem> itemsRequired;
    public TechTree(){
        techLevel = -1;
        techLevelUpRequirments = new ArrayList<String[]>();
        techLevelUpRequirments.add(new String[]{"wood planks:1","stick:1"});
        techLevelUpRequirments.add(new String[]{"stone brick:1"});
        itemsRequired = new ArrayList<TempItem>();
        levelUp();
    }
    public int addComponents(Item i){
        int amountUsed = 0;
        for(int a = 0; a < itemsRequired.size();a++){
            if(itemsRequired.get(a).getName().equals(i.getName())){
                if(i.getQuantity() >= itemsRequired.get(a).getQuantity()){
                    amountUsed = itemsRequired.get(a).getQuantity();
                    itemsRequired.remove(a);
                    break;
                }
                else{
                    amountUsed = i.getQuantity();
                    itemsRequired.get(a).changeQuantity(i.getQuantity()*-1);
                    break;
                }
            }
        }
        if(itemsRequired.size() < 1){
            levelUp();
        }
        return amountUsed;
    }
    public int getTechLevel(){
        return techLevel;
    }
    public ArrayList<TempItem> getRequired(){
        return itemsRequired;
    }
    private void levelUp(){
        System.out.println("Level Up");
        techLevel ++;
        itemsRequired = new ArrayList<TempItem>();
        for(String e: techLevelUpRequirments.get(techLevel)){
            itemsRequired.add(new TempItem(e.substring(0,e.indexOf(":")),Integer.parseInt(e.substring(e.indexOf(":")+1,e.length()))));
        }
        for(TempItem e: itemsRequired){
            System.out.println(e);
        }
    }
    public boolean isRequired(String name){
        for(TempItem e: itemsRequired){
            if(e.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}