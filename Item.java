public class Item{
    private String type;
    private int quantity;
    public Item(String T){
        type = T;
        quantity = 1;
    }
    public String getType(){
        return type;
    }
    public void changeQuantity(int num){
        quantity += num;
    }
    public int getQuantity(){
        return quantity;
    }
}