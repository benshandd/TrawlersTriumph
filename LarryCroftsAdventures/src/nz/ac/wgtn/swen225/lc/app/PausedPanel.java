package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class PausedPanel extends JPanel {
    public PausedPanel() {
        setOpaque(false); // Make it transparent
        setLayout(new BorderLayout());

        JLabel pausedLabel = new JLabel("Game is Paused");
        pausedLabel.setFont(new Font("Arial", Font.BOLD, 36));
        pausedLabel.setForeground(Color.RED);
        pausedLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(pausedLabel, BorderLayout.CENTER);
        this.setBounds(300,0,800,800);
    }
}
