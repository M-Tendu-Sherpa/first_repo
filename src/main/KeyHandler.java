package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        if(code == KeyEvent.VK_U){
            upPressed = true;
        }
        if(code == KeyEvent.VK_J){
            downPressed = true;
        }
        if(code == KeyEvent.VK_H){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_K){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        if(code == KeyEvent.VK_U){
            upPressed = false;
        }
        if(code == KeyEvent.VK_J){
            downPressed = false;
        }
        if(code == KeyEvent.VK_H){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_K){
            rightPressed = false;
        }
    }
}
