public class WoodenAxe extends Item{
    public WoodenAxe(){
        super("wooden_axe",true,false);
        super.addStringToItemsRequired("wood");
        super.addIntegerToNumOfItem(50);
        super.setToolInfo("axe",10);
    }
}