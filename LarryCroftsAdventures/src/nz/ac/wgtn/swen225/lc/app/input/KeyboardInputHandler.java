package nz.ac.wgtn.swen225.lc.app.input;

import nz.ac.wgtn.swen225.lc.app.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class KeyboardInputHandler {

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

    private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String MOVE_LEFT = "move left";
    private static final String MOVE_RIGHT = "move right";

    private static final String EXIT_GAME = "exit game";
    private static final String SAVE_GAME = "save game";
    private static final String RESUME_GAME = "resume game";
    private static final String START_LEVEL_1 = "start level 1";
    private static final String START_LEVEL_2 = "start level 2";
    private static final String PAUSE_GAME = "pause game";
    private static final String CLOSE_PAUSE_DIALOG = "close pause dialog";



    private App component;

    public KeyboardInputHandler(App component) {
        this.component = component;
        setupKeyBindings();
    }

    private void setupKeyBindings() {
        // Arrow keys for moving Chap within the maze
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), MOVE_UP);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), MOVE_DOWN);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), MOVE_LEFT);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), MOVE_RIGHT);

        // WASD keys for moving Chap within the maze
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), MOVE_UP);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), MOVE_DOWN);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), MOVE_LEFT);
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), MOVE_RIGHT);

        // CTRL-X to exit the game
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), EXIT_GAME);

        // CTRL-S to save the game state
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), SAVE_GAME);

        // CTRL-R to resume a saved game
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), RESUME_GAME);

        // CTRL-1 to start a new game at level 1
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), START_LEVEL_1);

        // CTRL-2 to start a new game at level 2
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), START_LEVEL_2);

        // SPACE to pause the game
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), PAUSE_GAME);

        // ESC to close the "game is paused" dialog and resume the game
        component.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CLOSE_PAUSE_DIALOG);

        // Define actions for each keybinding
        component.getActionMap().put(MOVE_UP, new MoveAction("UP"));
        component.getActionMap().put(MOVE_DOWN, new MoveAction("DOWN"));
        component.getActionMap().put(MOVE_LEFT, new MoveAction("LEFT"));
        component.getActionMap().put(MOVE_RIGHT, new MoveAction("RIGHT"));
        component.getActionMap().put(EXIT_GAME, new ExitAction());
        component.getActionMap().put(SAVE_GAME, new SaveGameAction());
        component.getActionMap().put(RESUME_GAME, new ResumeGameAction());
        component.getActionMap().put(START_LEVEL_1, new StartGameAction(1));
        component.getActionMap().put(START_LEVEL_2, new StartGameAction(2));
        component.getActionMap().put(PAUSE_GAME, new PauseGameAction());
        component.getActionMap().put(CLOSE_PAUSE_DIALOG, new ClosePauseDialogAction());
    }



    private class MoveAction extends AbstractAction {
        private String direction;

        public MoveAction(String direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle movement based on 'direction'
            // Implement your move logic here
            System.out.println(direction);
            switch (direction) {
                case "UP":
                    component.getRenderer().moveCameraUp();
                    break;
                case "DOWN":
                    component.getRenderer().moveCameraDown();
                    break;
                case "LEFT":
                    component.getRenderer().moveCameraLeft();
                    break;
                case "RIGHT":
                    component.getRenderer().moveCameraRight();
                    break;
                default:
                    break;
            }
            component.repaint();
            System.out.println("X: " + component.getRenderer().camera.getX() + " Y: " + component.getRenderer().camera.getY());


        }
    }

    private class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle CTRL-X action to exit the game and lose the current state
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
            if (response == 0) { System.exit(0); }
        }
    }

    private class SaveGameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle CTRL-S action to save the game state
            System.out.println("Saving game");
        }
    }

    private class ResumeGameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle CTRL-R action to resume a saved game
            System.out.println("Resuming game");
        }
    }

    private class StartGameAction extends AbstractAction {
        private int level;

        public StartGameAction(int level) {
            this.level = level;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle CTRL-1 and CTRL-2 to start a new game at the specified level
            System.out.println("Loading level " + level);
        }
    }

    private class PauseGameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle SPACE action to pause the game and display a "game is paused" dialog
            System.out.println("Game paused");
        }
    }

    private class ClosePauseDialogAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle ESC action to close the "game is paused" dialog and resume the game
            System.out.println("Exiting pause");
        }
    }



}
