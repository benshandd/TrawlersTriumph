package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecorderPanel extends JPanel {

    private JButton recordButton;
    private JButton loadButton;
    private JButton stepButton;
    private JButton autoReplayButton;
    private JSlider replaySpeedSlider;

    public RecorderPanel() {
        initializeComponents();
        addComponentsToPanel();
    }

    private void initializeComponents() {
        this.setPreferredSize(new Dimension(300, 300));
        recordButton = new JButton("Record");
        loadButton = new JButton("Load Recorded Game");
        stepButton = new JButton("Step by Step");
        autoReplayButton = new JButton("Auto Replay");

        // Add action listeners to buttons
        recordButton.addActionListener(new ActionListener() {
            private boolean recording = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                recording = !recording;
                if (recording) {
                    recordButton.setText("Stop Recording");
                    // Implement recording logic here
                } else {
                    recordButton.setText("Record");
                    // Implement stop recording logic here
                }
            }
        });

        // Add action listeners for the other buttons
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement load recorded game logic here
            }
        });

        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement step-by-step logic here
            }
        });

        autoReplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement auto replay logic here
            }
        });

        replaySpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5); // Initial value set to 5
        replaySpeedSlider.setMajorTickSpacing(1);
        replaySpeedSlider.setPaintTicks(true);
        replaySpeedSlider.setPaintLabels(true);

        replaySpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = replaySpeedSlider.getValue();
                // Use the 'speed' value to adjust your game's replay speed
                // Implement your replay speed adjustment logic here
            }
        });
    }

    private void addComponentsToPanel() {
        setLayout(new GridLayout(5, 1));
        add(recordButton);
        add(loadButton);
        add(stepButton);
        add(autoReplayButton);
        add(replaySpeedSlider);    }

}
