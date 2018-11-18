public class AIMoveThread implements Runnable {
    AI a;
    public AIMoveThread(AI ai) {
        a=ai;
    }
    public void run() {
        a.update();
    }
}
    