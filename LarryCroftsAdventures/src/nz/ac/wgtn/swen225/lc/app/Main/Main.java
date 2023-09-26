package nz.ac.wgtn.swen225.lc.app.Main;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.HelpDialog;
import nz.ac.wgtn.swen225.lc.app.input.KeyboardInputHandler;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * The main class for Larry Croft's Adventures game.
 * This class sets up the game's graphical user interface and main functionality.
 */
public class Main extends JFrame {
    static JFrame f;
    static JLabel l;

    /**
     * Constructor for the Main class. Initializes the game window and components.
     */
    public Main() {
        System.out.println("Hello world");
        f = new JFrame("Larry Croft's Adventures");
        l = new JLabel("Welcome");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application.


        App applicationWindow = new App();
        new KeyboardInputHandler(applicationWindow); // Initialize keyboard input handling.
        f.setJMenuBar(createMenuBar());  // Create and set the menu bar for the game
        f.addWindowListener(new WindowAdapter() {
            /**
             * Handles the window closing event.
             *
             * @param e The WindowEvent representing the window closing event.
             */
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
                if (response == 0) {
                    System.exit(0);
                } // Exit the application if the user confirms.
            }
        });

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(applicationWindow, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null); // Center the game window on the screen.

        f.setVisible(true);  // Make the game window visible.
    }

    /**
     * Creates the menu bar for the game window.
     *
     * @return The JMenuBar containing the game's menu options.
     */
    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem helpMenuItem = new JMenuItem("Help");


        exitMenuItem.setToolTipText("Exit the game");  // Tooltip for the Exit menu item.
        exitMenuItem.addActionListener((event) -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
            if (response == 0) {
                System.exit(0);
            }
        });  // Exit the application if the user confirms.

        saveMenuItem.setToolTipText("Save the game state"); // Tooltip for the Save menu item.
//        saveMenuItem.addActionListener((event) -> persistency.saveGame());

        resumeMenuItem.setToolTipText("Resume the game"); // Tooltip for the Resume menu item.
//        resumeMenuItem.addActionListener((event) -> persistency.resumeGame();

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
        fileMenu.add(helpMenuItem);

        menuBar.add(fileMenu);

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
