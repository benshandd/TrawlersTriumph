package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton startButton;
    private JButton levelSelectButton;

    public MenuPanel() {
        setLayout(new GridLayout(2, 1));

        // Create and customize the "Start" button
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));

        // Create and customize the "Level Select" button
        levelSelectButton = new JButton("Level Select");
        levelSelectButton.setFont(new Font("Arial", Font.BOLD, 24));

        // Add the buttons to the menu panel
        add(startButton);
        add(levelSelectButton);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getLevelSelectButton() {
        return levelSelectButton;
    }
}
