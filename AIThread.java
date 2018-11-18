import java.util.*;
public class AIThread implements Runnable {
    ArrayList<AI> bots;
    Display dis;
    public AIThread() {
        bots = new ArrayList<AI>();
    }
    public void run() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<AI> getBots() {
        return bots;
    }
    public void addBot(AI a) {
        bots.add(a);
    }
    public void setDisplay(Display d) {
        dis=d;
    }
}