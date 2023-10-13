package nz.ac.wgtn.swen225.lc.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The menu screen for the game
 * Contains start, level select and help
 *
 * @author Matthew Kerr (300613741)
 */
public class MenuPanel extends JPanel {
    private final JButton startButton;      //the start button
    private final JButton levelSelectButton;        //the level select button
    private final JButton helpButton;       //the help button
    private BufferedImage backgroundImage;  //the menu background image

    private final String selectedLevel = "1";   //the level select value

    public boolean levelSelected = false;   //the status of whether the level has been selected

    public MenuPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("LarryCroftsAdventures/assets/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add spacing between elements

        // Create and customize the title label
        JLabel titleLabel = new JLabel("Reel it in");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Create and customize the "Start" button
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 1;
        add(startButton, gbc);

        // Create and customize the "Level Select" button
        levelSelectButton = new JButton("Level Select");
        levelSelectButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 2;
        add(levelSelectButton, gbc);

        // Create and customize the "Help" button
        helpButton = new JButton("Help");
        helpButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 3;
        add(helpButton, gbc);

        // Create and customize the credits label
        JLabel creditsLabel = new JLabel("Created by Mathias, Matthew, Ben, Anthony, and Alex");
        creditsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        creditsLabel.setForeground(Color.white);
        gbc.gridy = 4;
        add(creditsLabel, gbc);

        this.setPreferredSize(new Dimension(600, 600));

        // Add action listeners for the buttons
        levelSelectButton.addActionListener(e -> showLevelSelectDialog());
        helpButton.addActionListener(e -> showHelpDialog());
    }

    /**
     * Pops up a level select dialog
     */
    private void showLevelSelectDialog() {
        if (!levelSelected) {
            String[] options = {"Level 1", "Level 2"};
            String selectedOption = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a level:",
                    "Level Select",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selectedOption != null) {
                if (selectedOption.equals("Level 1") || selectedOption.equals("Level 2")) {
                    // Handle the selected level
                    levelSelected = true;
                }
            }
        }
    }


    /**
     * Gets the help dialog and displays it on the menu
     */
    private void showHelpDialog() {
        Component parentComponent = this;
        while (!(parentComponent instanceof JFrame) && parentComponent != null) {
            parentComponent = parentComponent.getParent();
        }

        if (parentComponent instanceof JFrame parentFrame) {
            HelpDialog helpDialog = new HelpDialog(parentFrame);
            helpDialog.setVisible(true);
        } else {
            System.err.println("Parent JFrame not found.");
        }
    }

    /**
     * The paint component for drawing the background image on the menu
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Returns the start button
     *
     * @return
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Returns the level select button
     *
     * @return
     */
    public JButton getLevelSelectButton() {

        return levelSelectButton;
    }

    /**
     * returns the selected level number
     *
     * @return
     */
    public int getSelectedLevel() {
        if (selectedLevel.contains("1")) {
            return 1;
        } else {
            return 2;
        }
    }


    /**
     * returns the help button
     *
     * @return
     */
    public JButton getHelpButton() {
        return helpButton;
    }
}
