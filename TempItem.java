public class TempItem{
    private String type;
    private int quantity;
    public TempItem(String name,int q){
        type = name;
        quantity = q;
    }
    public String getType(){
        return type;
    }
    public int getQuantity(){
        return quantity;
    }
    public void changeQuantity(int q){
        quantity += q;
    }
}