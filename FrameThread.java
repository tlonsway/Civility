public class FrameThread implements Runnable {
    Display d;
    int fps;
    public FrameThread(Display dis, int frames_per_second) {
        d=dis;
        fps=frames_per_second;
    }
    public void run() {
        while(true) {
            //long stime = System.nanoTime();
            try {
                Thread.sleep(1000/fps);
            } catch (Exception e) {
                e.printStackTrace();
            }
            d.update();
            d.draw();
            //System.out.println("frame took: " + (System.nanoTime()-stime));
            //d.update();
        }
    }
}