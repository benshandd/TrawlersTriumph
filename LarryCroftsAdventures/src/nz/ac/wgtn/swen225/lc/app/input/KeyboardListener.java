package nz.ac.wgtn.swen225.lc.app.input;

import nz.ac.wgtn.swen225.lc.domain.Chap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener implements KeyListener {




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeys(e, "Released");
    }

    private void handleKeys(KeyEvent e, String status){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W:
                //player.move("UP");

            case KeyEvent.VK_A:
                //player.move("LEFT");

            case KeyEvent.VK_S:
                //player.move("DOWN");

            case KeyEvent.VK_D:
                //player.move("RIGHT");

        }


    }
}
