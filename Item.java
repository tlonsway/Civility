public class Item{
    private String type;
    private int quantity;
    private int bx;
    private int by;
    private int bwidth;
    private int bheight;
    public Item(String T){
        type = T;
        quantity = 1;
    }
    public void setButtonInfo(int BX,int BY,int BW,int BH){
        bx = BX;
        by = BY;
        bwidth = BW;
        bheight = BH;
    }
    public int getBX(){
        return bx;
    }
    public int getBY(){
        return by;
    }   
    public int getBWidth(){
        return bwidth;
    }
    public int getBHeight(){
        return bheight;
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