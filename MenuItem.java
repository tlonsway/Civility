public class MenuItem{
    private Item item;
    private int x;
    private int y;
    public MenuItem(Item i, int X, int Y){
        item = i;
        x = X;
        y = Y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Item getItem(){
        return item;
    }
}