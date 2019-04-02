public class Player{
    private InventoryTwo inventory;
    private int maxHealth;
    private int health;
    private int[] position;
    private String name;
    private int speed;
    public Player(String n, int mH,int h, int[] p, InventoryTwo i,int s){
        name = n;
        maxHealth = mH;
        health = h;
        position = p;
        inventory = i;
        speed = s;
    }
    public void craft(String name,int quantity){
        //get the requirments for crafting in the txt file
        TempItem[] required = new TempItem[1];
        for(TempItem a: required){
            inventory.removeItem(a.getName(),a.getQuantity());
        }
        //get the required info about the item from the txt file
        Item temp = new Item(name,quantity,false,null,null);
        inventory.addItem(temp);
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getHealth(){
        return health;
    }
    public int[] getPosition(){
        return position;
    }
    public String getName(){
        return name;
    }
    public void changeHealth(int h){
        health += h;
    }
    public void changeMaxHealth(int mh){
        maxHealth += mh;
    }
    public int getSpeed(){
        return speed;
    }
    public void changeSpeed(int s){
        speed += s;
    }
    public int[] changePosition(int xc,int yc){
        position[0] += xc;
        position[1] += yc;
        return position;
    }
}