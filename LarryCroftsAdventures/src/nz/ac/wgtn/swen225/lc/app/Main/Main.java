package nz.ac.wgtn.swen225.lc.app.Main;


import nz.ac.wgtn.swen225.lc.app.App;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    static JFrame f;
    static JLabel l;

    public static void main(String[] args) {
        System.out.println("Hello world");
        f = new JFrame("Larry Croft's Adventures");
        l = new JLabel("Welcome");

        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        App applicationWindow = new App();

        f.setJMenuBar(createMenuBar());


        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(applicationWindow, BorderLayout.CENTER);
        f.setLocationByPlatform(true);
        f.pack();
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
        exitMenuItem.addActionListener((event) -> System.exit(0));

        saveMenuItem.setToolTipText("Save the game state");
//        saveMenuItem.addActionListener((event) -> persistency.save());

        resumeMenuItem.setToolTipText("Resume the game");
//        resumeMenuItem.addActionListener((event) -> persistency.resume();

        helpMenuItem.setToolTipText("Help page with game rules");
//        helpMenuItem.addActionListener((event) -> displayHelpMenu));


        fileMenu.add(exitMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(resumeMenuItem);
        fileMenu.add(helpMenuItem);

        menuBar.add(fileMenu);

        return menuBar;
    }
}