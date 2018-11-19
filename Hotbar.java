public class Hotbar{
    private Item[] items;
    private int itemSelected;
    public Hotbar(){
        items = new Item[10];
        for(int i = 0; i < 10; i++){
            items[i] = new Item("test");
        }
        items[1] = new Item("Tristan");
    }
    public Item[] getHotbar(){
        return items;
    }
    public void selectItem(int S){
        itemSelected = S;
    }
    public int getItemSelected(){
        return itemSelected;
    }
    public void addItemToHotBar(Item i){
        items[itemSelected] = i;
    }
}