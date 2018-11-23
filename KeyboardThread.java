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
        if(key == KeyEvent.VK_1){
            dis.onePress();
        }
        if(key == KeyEvent.VK_2){
            dis.twoPress();
        }
        if(key == KeyEvent.VK_3){
            dis.threePress();
        }
        if(key == KeyEvent.VK_4){
            dis.fourPress();
        }
        if(key == KeyEvent.VK_5){
            dis.fivePress();
        }
        if(key == KeyEvent.VK_6){
            dis.sixPress();
        }
        if(key == KeyEvent.VK_7){
            dis.sevenPress();
        }
        if(key == KeyEvent.VK_8){
            dis.eightPress();
        }
        if(key == KeyEvent.VK_9){
            dis.ninePress();
        }
        if(key == KeyEvent.VK_0){
            dis.zeroPress();
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