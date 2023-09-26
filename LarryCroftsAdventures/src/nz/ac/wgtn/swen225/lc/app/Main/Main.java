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

public class Main extends JFrame {
    static JFrame f;
    static JLabel l;

    public Main() {
        System.out.println("Hello world");
        f = new JFrame("Larry Croft's Adventures");
        l = new JLabel("Welcome");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        App applicationWindow = new App();
        new KeyboardInputHandler(applicationWindow);
        f.setJMenuBar(createMenuBar());
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
                if (response == 0) { System.exit(0); }
            }
        });

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(applicationWindow, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);

        f.setVisible(true);
    }


    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem helpMenuItem = new JMenuItem("Help");



        exitMenuItem.setToolTipText("Exit the game");
        exitMenuItem.addActionListener((event) -> {int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the game?", "Closing game", JOptionPane.YES_NO_OPTION);
        if (response == 0) { System.exit(0); }});

        saveMenuItem.setToolTipText("Save the game state");
//        saveMenuItem.addActionListener((event) -> persistency.saveGame());

        resumeMenuItem.setToolTipText("Resume the game");
//        resumeMenuItem.addActionListener((event) -> persistency.resumeGame();

        helpMenuItem.setToolTipText("Help page with game rules");
        helpMenuItem.addActionListener(new ActionListener() {
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }

}
