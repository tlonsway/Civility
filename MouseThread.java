import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MouseThread extends  MouseAdapter{
    private Display dis;
    public MouseThread(Display D){
        dis = D;
    }
    public void mousePressed(MouseEvent e){
        System.out.println(e.getButton());
        if(e.getButton() == 1){
            //should grab the resource that the player punched
            if(dis.view == "world"){
                dis.mouseLeftClick(e.getX(),e.getY());
            }
            else if(dis.view == "inventory"){
                dis.inventoryClick(e.getX(),e.getY());
            }
            else if(dis.view == "crafting"){
                dis.craftingClick(e.getX(),e.getY());
            }
        }
        else if(e.getButton() == 3){
            if(dis.view == "world"){
                dis.mouseRightClick(e.getX(),e.getY());
            }
        }
    }
    public void mouseWheelMoved(MouseWheelEvent e){
        System.out.println("wheel moved");
    }
}