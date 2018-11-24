public class HouseBlueprint extends Item{
    public HouseBlueprint(){
        super("HouseBlueprint",true,true);
        super.addStringToItemsRequired("wood");
        super.addIntegerToNumOfItem(100);
    }
}