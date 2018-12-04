public class WoodenPlank extends Item{
    public WoodenPlank(){
        super("wooden plank",true,false);
        super.addStringToItemsRequired("wood");
        super.addIntegerToNumOfItem(10);
    }
}