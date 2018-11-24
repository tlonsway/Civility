public class HouseFrame extends Item{
    public HouseFrame(){
        super("house_frame",true,true);
        super.addStringToItemsRequired("wood");
        super.addIntegerToNumOfItem(100);
    }
}