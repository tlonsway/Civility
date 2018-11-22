import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import javafx.geometry.*;
public class AnimateFist implements Runnable{
    private int[] fistCords;
    private int mouseCords[];
    private boolean animating;
    private boolean isRightArm;
    public AnimateFist(boolean R){
        fistCords = new int[4];
        mouseCords = new int[2];
        animating = false;
        isRightArm = R;
    }
    public void setMouseCords(int[] cords){
        mouseCords = cords;
    }
    public void run(){
        animate();
    }
    public void setMouseCords(int X,int Y){
        mouseCords[0] = X;
        mouseCords[1] = Y;
    }
    public int getX(){
        return mouseCords[0];
    }
    public int getY(){
        return mouseCords[1];
    }
    public boolean isAnimating(){
        return animating;
    }
    public int[] getFistCords(){
        return fistCords;
    }
    public void animate(){
        animating = true;
        Point mp = MouseInfo.getPointerInfo().getLocation();
        mouseCords[0]=(int)mp.getX();
        mouseCords[1]=(int)mp.getY();
        double theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
        int inv=1;
        double offset=.8;
        double horzdist=.2;
        //double slope = (fistCords[2]-fistCords[0])/(fistCords[3]-fistCords[1])*-1;
        for(int i = 0; i < 10; i++){
            if (mouseCords[0]<=920) {
                inv=-1;
            }
            else{
                inv=1;
            }
            theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
            theta=-theta;
            int dist1=25;
            int dist2=25;
            if(isRightArm){
                dist2+=i;
            }
            else{
                dist1+=i;
            }
            double fx1=inv*dist1*Math.sin(theta+offset+horzdist)+915;
            double fy1=inv*dist1*Math.cos(theta+offset+horzdist)+515;    
            double fx2=inv*dist2*Math.cos(theta+offset-horzdist)+915;
            double fy2=inv*dist2*-Math.sin(theta+offset-horzdist)+515;
            fistCords[0] = (int)fx1;
            fistCords[1] = (int)fy1;
            fistCords[2] = (int)fx2;
            fistCords[3] = (int)fy2;
            try{
                Thread.sleep(10);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        for(int i = 10; i > 0; i--){
            if (mouseCords[0]<=920) {
                inv=-1;
            }
            else{
                inv=1;
            }
            theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
            theta=-theta;
            int dist1=25;
            int dist2=25;
            if(isRightArm){
                dist2+=i;
            }
            else{
                dist1+=i;
            }
            double fx1=inv*dist1*Math.sin(theta+offset+horzdist)+915;
            double fy1=inv*dist1*Math.cos(theta+offset+horzdist)+515;    
            double fx2=inv*dist2*Math.cos(theta+offset-horzdist)+915;
            double fy2=inv*dist2*-Math.sin(theta+offset-horzdist)+515;
            fistCords[0] = (int)fx1;
            fistCords[1] = (int)fy1;
            fistCords[2] = (int)fx2;
            fistCords[3] = (int)fy2;
            try{
                Thread.sleep(10);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        animating = false;
    }
}