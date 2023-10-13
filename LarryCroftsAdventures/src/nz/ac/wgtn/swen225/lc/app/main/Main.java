package nz.ac.wgtn.swen225.lc.app.main;

import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.app.input.KeyboardInputHandler;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for Larry Croft's Adventures game.
 * This class sets up the game's graphical user interface and main functionality.
 *
 * @author Matthew Kerr (300613741)
 */
public class Main extends JFrame {
    static JFrame f;
    static JLabel l;
    private final Recorder recorder;
    private static Persistency persistency;
    private static RecorderPanel recorderPanel;
    static App applicationWindow;

    private final MenuPanel menuPanel;


    /**
     * Constructor for the Main class. Initializes the game window and components.
     */
    public Main() {
        recorder = new Recorder();
        System.out.println("Hello world");

        String path = "LarryCroftsAdventures/assets/";
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(new File(path + "icon.png")));
            setIconImage(img.getImage());
        } catch (Exception e) {

        }


        menuPanel = new MenuPanel();
        applicationWindow = new App();
        persistency = new Persistency(applicationWindow);

        getContentPane().add(menuPanel);
        menuPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationWindow.setup(new File("LarryCroftsAdventures/levels/level1.json"));

                // Remove the menu panel
                getContentPane().remove(menuPanel);
                revalidate();

                // Create and add the application window
                getContentPane().add(applicationWindow);
                pack();
                setLocationRelativeTo(null);
                revalidate();
                repaint();

            }
        });

        menuPanel.getLevelSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a level has been selected in the MenuPanel

                if (menuPanel.levelSelected) {
                    getContentPane().remove(menuPanel);
                    revalidate();

                    // Get the selected level
                    int selectedLevel = menuPanel.getSelectedLevel();

                    // Create and add the application window with the selected level
                    getContentPane().add(applicationWindow);
                    applicationWindow.setup(new File("LarryCroftsAdventures/levels/level" + selectedLevel + ".json"));
                    pack();
                    setLocationRelativeTo(null);
                    revalidate();
                    repaint();
                }
                menuPanel.remove(menuPanel.getHelpButton());
                menuPanel.remove(menuPanel.getStartButton());
                menuPanel.getLevelSelectButton().setText("Start game");
            }
        });


        setTitle("Reel it in");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        recorderPanel = new RecorderPanel(applicationWindow);
        new KeyboardInputHandler(applicationWindow); // Initialize keyboard input handling.
        setJMenuBar(createMenuBar());  // Create and set the menu bar for the game
        addWindowListener(new WindowAdapter() {
            /**
             * Handles the window closing event.
             *
             * @param e The WindowEvent representing the window closing event.
             */
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } // Exit the application if the user confirms.
            }
        });
    }

    /**
     * Creates the menu bar for the game window.
     *
     * @return The JMenuBar containing the game's menu options.
     */
    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenu levelMenu = new JMenu("Level");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem helpMenuItem = new JMenuItem("Help");

        JMenuItem level1 = new JMenuItem("Level 1");
        JMenuItem level2 = new JMenuItem("Level 2");
        level1.setToolTipText("Load level 1");  // Tooltip for the Exit menu item.
        level2.setToolTipText("Load level 2");  // Tooltip for the Exit menu item.
        level1.addActionListener(new ActionListener() {
            /**
             * Handles the action when the level menu item is clicked.
             *
             * @param e The ActionEvent representing the menu item click event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the help dialog when the "Level1" menu item is clicked
                applicationWindow.setup(new File("LarryCroftsAdventures/levels/level1.json"));
            }
        });

        level2.addActionListener(new ActionListener() {
            /**
             * Handles the action when the level menu item is clicked.
             *
             * @param e The ActionEvent representing the menu item click event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the help dialog when the "Level2" menu item is clicked
                applicationWindow.setup(new File("LarryCroftsAdventures/levels/level2.json"));
            }
        });

        pauseMenuItem.addActionListener(new ActionListener() {
            /**
             * Handles the action when the Pause menu item is clicked.
             *
             * @param e The ActionEvent representing the menu item click event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the help dialog when the "Pause" menu item is clicked
                applicationWindow.setPaused(true);
            }
        });


        Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
                if (response == 0) {
                    System.exit(0);
                }
            }
        };
        exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        exitMenuItem.setAction(exitAction);
        exitMenuItem.setToolTipText("Exit the game");  // Tooltip for the Exit menu item.


        saveMenuItem.addActionListener((event) -> {
            try {
                // Get the current state of the game
                int newFileNum = recorderPanel.getFileCount();
                ArrayList<Move> actions = recorderPanel.getMovesList();
                int playerX = App.getBoard().getChap().getX();
                int playerY = App.getBoard().getChap().getY();
                int playerTreasureCount = App.getBoard().getChap().getPlayerTreasureCount();
                int boardTreasureCount = App.getBoard().getBoardTreasureCount() - playerTreasureCount;
                int level = App.getBoard().getLevel();
                int timeLeft = App.getBoard().getTime();
                Tile[][] board = App.getBoard().getTiles();
                Chap chap = App.getBoard().getChap();
                // Set the parameters for saving
                persistency.setSaveParameters(newFileNum, actions,
                        playerX, playerY, playerTreasureCount,
                        boardTreasureCount, level, timeLeft, board, chap);

                // Save the game
                persistency.saveGame("saved-game-");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        resumeMenuItem.setToolTipText("Resume from game save"); // Tooltip for the Resume menu item.
        resumeMenuItem.addActionListener((event) -> {
            persistency.resumeGame();
        });

        helpMenuItem.setToolTipText("Help page with game rules"); // Tooltip for the HelpMenu menu item.
        helpMenuItem.addActionListener(new ActionListener() {
            /**
             * Handles the action when the Help menu item is clicked.
             *
             * @param e The ActionEvent representing the menu item click event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the help dialog when the "Help" menu item is clicked
                HelpDialog helpDialog = new HelpDialog(f);
                helpDialog.setVisible(true);
            }
        });

        fileMenu.add(exitMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(resumeMenuItem);
        fileMenu.add(pauseMenuItem);

        helpMenu.add(helpMenuItem);

        levelMenu.add(level1);
        levelMenu.add(level2);

        menuBar.add(fileMenu);
        menuBar.add(levelMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Create an instance of the Main class to start the game.
        new Main();
    }

}
