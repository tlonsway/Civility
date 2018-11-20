import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MouseThread extends  MouseAdapter{
    private Display dis;
    public MouseThread(Display D){
        dis = D;
    }
    public void mousePressed(MouseEvent e){
        if(e.getButton() == 1){
            //should grab the resource that the player punched
            dis.mouseClick(e.getX(),e.getY());
        }
        else if(e.getButton() == 2){
            //display the information about the object clicked on
        }
    }
}