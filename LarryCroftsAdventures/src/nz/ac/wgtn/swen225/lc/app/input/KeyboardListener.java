package nz.ac.wgtn.swen225.lc.app.input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener {
    public KeyboardListener() {
        // Create an action to handle CTRL-X (Exit)
        Action exitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CTRL-X was pressed.");
                // Handle CTRL-X action here (exit the game)
            }
        };

        // Create an action to handle CTRL-S (Save)
        Action saveAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CTRL-S was pressed.");
                // Handle CTRL-S action here (save the game)
            }
        };

        // Create an action to handle CTRL-R (Resume)
        Action resumeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CTRL-R was pressed.");
                // Handle CTRL-R action here (resume a saved game)
            }
        };

        // Create a global key listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_X:
                                exitAction.actionPerformed(null);
                                break;
                            case KeyEvent.VK_S:
                                saveAction.actionPerformed(null);
                                break;
                            case KeyEvent.VK_R:
                                resumeAction.actionPerformed(null);
                                break;
                        }
                    }
                }
                return false;
            }
        });
    }
}
