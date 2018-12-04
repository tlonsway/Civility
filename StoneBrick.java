public class StoneBrick extends Item{
    public StoneBrick(){
        super("stone brick",true,false);
        super.addStringToItemsRequired("stone");
        super.addIntegerToNumOfItem(10);
    }
}