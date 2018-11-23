public class Item{
    private String type;
    private int quantity;
    public Item(String T){
        type = T;
        quantity = 1;
    }
    public Item(String T, int quant){
        type = T;
        quantity = quant;
    }
    public String getType(){
        return type;
    }
    public void changeQuantity(int num){
        System.out.println("quantity changed by: " + num);
        quantity += num;
    }
    public int getQuantity(){
        return quantity;
    }
}