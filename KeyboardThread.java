import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class KeyboardThread extends KeyAdapter {
    Display dis;    
    public KeyboardThread(Display d) {
        dis=d;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            dis.aPress();
        }
        if (key == KeyEvent.VK_D) {
            dis.dPress();
        }
        if (key == KeyEvent.VK_W) {
            dis.wPress();
        }
        if (key == KeyEvent.VK_S) {
            dis.sPress();
        } 
        if(key == KeyEvent.VK_I){
            dis.iPress();
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            dis.aRelease();
        }
        if (key == KeyEvent.VK_D) {
            dis.dRelease();
        }
        if (key == KeyEvent.VK_W) {
            dis.wRelease();
        }
        if (key == KeyEvent.VK_S) {
            dis.sRelease();
        }    
    }
}