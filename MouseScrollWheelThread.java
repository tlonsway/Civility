import java.awt.event.*;
import java.awt.*;
public class MouseScrollWheelThread implements MouseWheelListener{
    Display dis;
    public MouseScrollWheelThread(Display d){
        dis = d;
    }
    public void mouseWheelMoved(MouseWheelEvent e){
        dis.scroll(e.getWheelRotation());
    }
}