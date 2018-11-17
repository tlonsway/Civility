import java.awt.*;
public class Inventory {
    double gold,wood,rock;
    public Inventory() {
        gold=wood=rock=0;
    }
    public double getGold() {
        return gold;
    }
    public void setGold(double g) {
        gold=g;
    }
    public void addGold(double g) {
        gold+=g;
    }
}