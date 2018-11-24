public class WoodenAxe extends Item{
    public WoodenAxe(){
        super("wooden_axe",true);
        super.addStringToItemsRequired("wood");
        super.addIntegerToNumOfItem(50);
    }
}