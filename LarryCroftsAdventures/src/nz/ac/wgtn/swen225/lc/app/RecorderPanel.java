package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.input.KeyboardInputHandler;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    File file = null;
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
                    // need what level so we can just load the level and place the character where they were
                    Recorder r = new Recorder(new ArrayList<String>(reversedMoves),8,8,1); // random values for now
                    reversedMoves.clear();
                    try {
                        r.saveRecorder(count);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Add action listeners for the other buttons
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement load recorded game logic here
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Choose a saved game");
                int retVal = fc.showOpenDialog(null);

                if (retVal == JFileChooser.APPROVE_OPTION){
                    file = fc.getSelectedFile();
                }



            }
        });

        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement step-by-step logic here
                if (file == null){
                    JOptionPane.showMessageDialog(null,
                            "You need to load a file first!",
                            "File not chosen!",
                            JOptionPane.PLAIN_MESSAGE);
                } else{

                }


            }
        });

        autoReplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement auto replay logic here
                if (file == null){
                    JOptionPane.showMessageDialog(null,
                            "You need to load a file first!",
                            "File not chosen!",
                            JOptionPane.PLAIN_MESSAGE);
                } else{

                }
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
