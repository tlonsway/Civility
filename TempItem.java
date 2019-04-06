public class TempItem{
    private String name;
    private int quantity;
    public TempItem(String n, int q){
        name = n;
        quantity = q;
    }
    public String toString(){
        return "Name: "+name+" Quantity: "+quantity;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public void changeQuantity(int q){
        quantity += q;
    }
}