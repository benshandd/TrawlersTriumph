package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.input.KeyboardInputHandler;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

public class RecorderPanel extends JPanel {

    private JButton recordButton;
    private JButton loadButton;
    private JButton stepButton;
    private JButton autoReplayButton;
    private JSlider replaySpeedSlider;

    public static Stack<String> reversedMoves;
    public static boolean recording = false;
    private int count = 0;
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

            @Override
            public void actionPerformed(ActionEvent e) {
                recording = !recording;
                if (recording) {
                    recordButton.setText("Stop Recording");
                    // Implement recording logic here
                    count++;
                    reversedMoves = new Stack<>();
                } else {
                    recordButton.setText("Record");
                    // Implement stop recording logic here
                    Recorder r = new Recorder(reversedMoves,8,7); // random values for now
                    try {
                        r.saveRecorder(count);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    reversedMoves.clear();
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
