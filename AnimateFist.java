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
    private JFrame frame;
    public AnimateFist(boolean R,JFrame f){
        fistCords = new int[4];
        mouseCords = new int[2];
        animating = false;
        isRightArm = R;
        frame=f;
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
    public boolean isRightArm() {
        return isRightArm;
    }
    public void animate(){
        animating = true;
        Point mp = MouseInfo.getPointerInfo().getLocation();
        Point loc = frame.getLocationOnScreen();
        mouseCords[0]=(int)mp.getX()-(int)loc.getX();
        mouseCords[1]=(int)mp.getY()-(int)loc.getY();
        double theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
        int inv=1;
        double offset=.8;
        //double slope = (fistCords[2]-fistCords[0])/(fistCords[3]-fistCords[1])*-1;
        double horzdist1=.2;
        double horzdist2=.2;
        double dist1=25;
        double dist2=25;
        for(int i = 0; i < 15; i+=1){
            if (mouseCords[0]<=920) {
                inv=-1;
            }
            else{
                inv=1;
            }
            theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
            theta=-theta;
            if(isRightArm){
                dist2+=1;
            }
            else {
                dist1+=1;
            }
            if (isRightArm) {
                horzdist2+=.015;
            } else {
                horzdist1+=.015;
            }
            double invdist1 = inv*dist1;
            double invdist2 = inv*dist2;
            double to = theta+offset;
            double to_p_h1 = to+horzdist1;
            double to_m_h2 = to-horzdist2;
            double fx1=invdist1*Math.sin(to_p_h1)+915;
            double fy1=invdist1*Math.cos(to_p_h1)+515;    
            double fx2=invdist2*Math.cos(to_m_h2)+915;
            double fy2=invdist2*-Math.sin(to_m_h2)+515;
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
        for(int i = 14; i >= 0; i-=1){
            if (mouseCords[0]<=920) {
                inv=-1;
            }
            else{
                inv=1;
            }
            theta = (Math.atan((520-(double)mouseCords[1])/(920-(double)mouseCords[0])));
            theta=-theta;
            if(isRightArm){
                dist2-=1;
            }
            else{
                dist1-=1;
            }
            if (isRightArm) {
                horzdist2-=.015;
            } else {
                horzdist1-=.015;
            }
            double fx1=inv*dist1*Math.sin(theta+offset+horzdist1)+915;
            double fy1=inv*dist1*Math.cos(theta+offset+horzdist1)+515;    
            double fx2=inv*dist2*Math.cos(theta+offset-horzdist2)+915;
            double fy2=inv*dist2*-Math.sin(theta+offset-horzdist2)+515;
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