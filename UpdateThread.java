public class UpdateThread implements Runnable {
    Display d;
    public UpdateThread(Display dis) {
        d=dis;
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000/120);
            } catch (Exception e) {
                e.printStackTrace();
            }
            d.update();
        }
    }
}