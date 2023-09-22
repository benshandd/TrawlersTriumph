package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JPanel implements ActionListener {

    private JLabel timeLabel;
    JLabel[][] inventorySlots;
    private int time = 10;
    public App(){
        setup();
    }

    public void setup(){
        JPanel leftPanel = new JPanel(new GridLayout(2, 0, 0, 10));
        JPanel rightPanel = new JPanel(new GridLayout(9, 0, 0, 10));
        JPanel centrePanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                Renderer.draw(this, g);
            }
        };
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        createTimer();

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1400,800));
        leftPanel.setPreferredSize(new Dimension(300, 700));
        leftPanel.setBackground(Color.red);
        leftPanel.add(new RecorderPanel());

        rightPanel.setPreferredSize(new Dimension(300, 700));
        rightPanel.setBackground(Color.lightGray);
        Font font = new Font("Sans-Serif", Font.BOLD, 40);



        rightPanel.add(createLabel("LEVEL", font, Color.lightGray, Color.red));
        rightPanel.add(createLabel("1", font, Color.black, Color.green)); // get the level to display eventually
        rightPanel.add(createLabel("TIME", font, Color.lightGray, Color.red));
        rightPanel.add(timeLabel);
        rightPanel.add(createLabel("CHIPS LEFT", font, Color.lightGray, Color.red));
        rightPanel.add(createLabel("3", font, Color.black, Color.green)); //get the chips left
        rightPanel.add(createInventory(0));
        rightPanel.add(createInventory(1));


        this.add(centrePanel, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public JPanel createInventory(int index) {
        JPanel grid = new JPanel(new GridLayout(1, 4, 5, 5));
        grid.setPreferredSize(new Dimension(200, 100)); // Increase the width to accommodate square slots

        inventorySlots = new JLabel[2][4];

            for (int j = 0; j < 4; j++) {
                inventorySlots[index][j] = new JLabel();
                inventorySlots[index][j].setPreferredSize(new Dimension(100, 100)); // Make each slot a square
                inventorySlots[index][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid.add(inventorySlots[index][j]);
            }

        return grid;
    }


    public void updateInventory(boolean[][] items) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (items[i][j]) {
                    // Set an icon for an item (you can replace "yourItemIcon.png" with your actual icon file)
                    ImageIcon icon = new ImageIcon("yourItemIcon.png");
                    inventorySlots[i][j].setIcon(icon);
                } else {
                    // Set an empty square icon (you can replace "emptySquareIcon.png" with your empty square icon file)
                    ImageIcon emptyIcon = new ImageIcon("emptySquareIcon.png");
                    inventorySlots[i][j].setIcon(emptyIcon);
                }
            }
        }
    }



    public JLabel createLabel(String title, Font font, Color bg, Color fontColour){
        JLabel label = new JLabel(title);
        label.setFont(font);
        label.setForeground(fontColour);
        label.setBackground(bg);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setPreferredSize(new Dimension(200, 100));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        return label;
    }

    private void createTimer(){
        Font font = new Font("Sans-Serif", Font.BOLD, 50);
        timeLabel = createLabel("0", font, Color.black, Color.green);
        add(timeLabel);
        Timer timer = new Timer(1000, this);
        timer.setInitialDelay(1);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(time > 0) {
            time--;
        } else {
            //end the game

            JOptionPane.showMessageDialog(null,
                    "Time's up! Do you want to replay the current level?",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE);
            time = 50;

            //replay level
        }
        timeLabel.setText("" + time);
    }
}
