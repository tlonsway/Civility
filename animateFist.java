public class animateFist{
    private int[] fistCords;
    private int mouseCords[];
    public animateFist(){
        fistCords = new int[4];
        mouseCords = new int[2];
    }
    public int[] getMouseCords(){
        return mouseCords;
    }
    public int[] getFistCords(){
        return fistCords;
    }
    public void animate(int[] MC, int[] FC){
        mouseCords = MC;
        fistCords = FC;
        double slope = (fistCords[2]-fistCords[0])/(fistCords[3]-fistCords[1])*-1;
        for(int i = 0; i < 10; i++){
            
            try{
                Thread.sleep(100);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        for(int i = 10; i > 0; i--){
            
            try{
                Thread.sleep(100);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}