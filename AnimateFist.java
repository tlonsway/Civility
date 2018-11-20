public class AnimateFist{
    private int[] fistCords;
    private int mouseCords[];
    private boolean animating;
    public AnimateFist(){
        fistCords = new int[4];
        mouseCords = new int[2];
        animating = false;
    }
    public boolean isAnimating(){
        return animating;
    }
    public void setMouseCords(int[] MC){
         mouseCords = MC;
    }
    public int[] getFistCords(){
        return fistCords;
    }
    public void animate(int X,int Y){
        animating = true;
        double theta = (Math.atan((520-(double)Y)/(920-(double)X)));
        int inv=1;
        //double slope = (fistCords[2]-fistCords[0])/(fistCords[3]-fistCords[1])*-1;
        for(int i = 0; i < 10; i++){
            int dist=25+i;
            theta=-theta;
            double offset=.8;
            double horzdist=.2;
            double fx1=inv*dist*Math.sin(theta+offset+horzdist)+915;
            double fy1=inv*dist*Math.cos(theta+offset+horzdist)+515;    
            double fx2=inv*dist*Math.cos(theta+offset-horzdist)+915;
            double fy2=inv*dist*-Math.sin(theta+offset-horzdist)+515;
            fistCords[0] = (int)fx1;
            fistCords[1] = (int)fy1;
            fistCords[2] = (int)fx2;
            fistCords[3] = (int)fy2;
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        for(int i = 10; i > 0; i--){
            int dist=25+i;
            theta=-theta;
            double offset=.8;
            double horzdist=.2;
            double fx1=inv*dist*Math.sin(theta+offset+horzdist)+915;
            double fy1=inv*dist*Math.cos(theta+offset+horzdist)+515;    
            double fx2=inv*dist*Math.cos(theta+offset-horzdist)+915;
            double fy2=inv*dist*-Math.sin(theta+offset-horzdist)+515;
            fistCords[0] = (int)fx1;
            fistCords[1] = (int)fy1;
            fistCords[2] = (int)fx2;
            fistCords[3] = (int)fy2;
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        animating = false;
    }
}