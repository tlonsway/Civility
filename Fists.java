import java.awt.*;
public class Fists{
    private Color color;
    private int x1,x2,y1,y2 = 0;
    public Fists(Color C){
        color = C;
    }
    public int[] getFistsCords(int X, int Y){
        int[] ret = new int[4];
        //player middle at (920,520) always
        double theta = (Math.atan((520-(double)Y)/(920-(double)X)));
        int inv=1;
        if (X<=920) {
            inv=-1;
        }
        /*double fx1=inv*30*Math.cos(theta)+915;
        double fy1=inv*30*Math.sin(theta)+515;
        double fx2=inv*30*Math.cos(theta)+915;
        double fy2=inv*30*Math.sin(theta)+515;*/
        int dist=25;
        theta=-theta;
        double offset=.8;
        double horzdist=.2;
        double fx1=inv*dist*Math.sin(theta+offset+horzdist)+915;
        double fy1=inv*dist*Math.cos(theta+offset+horzdist)+515;    
        double fx2=inv*dist*Math.cos(theta+offset-horzdist)+915;
        double fy2=inv*dist*-Math.sin(theta+offset-horzdist)+515;
        //fx2=1;
        //fy2=1;
        ret[0]=(int)fx1;
        ret[1]=(int)fy1;
        ret[2]=(int)fx2;
        ret[3]=(int)fy2;
        return ret;
    }
    public Color getColor() {
        return color;
    }
}