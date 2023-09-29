package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.IllegalMove;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * RecorderPanel is a JPanel that provides recording and playback controls for the Larry Croft's Adventures game.
 * It allows the player to record and replay their actions in the game.
 */
public class RecorderPanel extends JPanel {

    private JButton recordButton;
    private JButton loadButton;
    private JButton stepButton;
    private JButton autoReplayButton;
    private JSlider replaySpeedSlider;

    /**
     * ArrayList to store recorded game moves.
     */
    public static ArrayList<String> moves;

    /**
     * A boolean flag to indicate whether recording is in progress.
     */
    public static boolean recording = false;
    private int count = 0;
    File file = null;
    App app;

    /**
     * Constructs a RecorderPanel and initializes its components.
     */
    public RecorderPanel(App app) {
        this.app = app;
        initializeComponents();
        addComponentsToPanel();
    }

    /**
     * Initializes the UI components of the RecorderPanel.
     */
    private void initializeComponents() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setBackground(Color.lightGray);
        recordButton = createSimpleButton("Record");
        loadButton = createSimpleButton("Load Recorded Game");
        stepButton = createSimpleButton("Step by Step");
        autoReplayButton = createSimpleButton("Auto Replay");

        // Add action listeners to buttons
        recordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                recording = !recording;
                if (recording) {
                    recordButton.setText("Stop Recording");
                    // Implement recording logic here
                    //for file names
                    count++;
                    moves = new ArrayList<>();
                } else {
                    recordButton.setText("Record");
                    // Implement stop recording logic here

                    Recorder r = new Recorder(new ArrayList<String>(moves),App.getBoard().getChap().getTile().getX(),App.getBoard().getChap().getTile().getY(),
                            App.getBoard().getLevel());
                    //clearing the moves after recording has been finished
                    moves.clear();
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
                // sets up an interface to choose a load file
                JFileChooser fc = new JFileChooser("LarryCroftsAdventures/Saves");
                fc.setDialogTitle("Choose a saved game");
                int retVal = fc.showOpenDialog(null);

                if (retVal == JFileChooser.APPROVE_OPTION){
                    file = fc.getSelectedFile();
                }
                //getting the moves out of the loaded file
                moves = new Recorder().loadSave(file);
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
                } else {
                    app.repaint();
                    if (!moves.isEmpty()) {
                        try {
                            new Recorder().step(moves.remove(0));
                        } catch (IllegalMove ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else{
                        //TODO implement a pop up saying been through all moves @Mr Kerr
                    }

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

        addSlider();

    }

    /**
     * adds JSlider to panel
     */
    private void addSlider(){
        replaySpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5); // Initial value set to 5
        replaySpeedSlider.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        replaySpeedSlider.setBorder(compound);
        replaySpeedSlider.setMajorTickSpacing(1);
        replaySpeedSlider.setPaintTicks(true);
        replaySpeedSlider.setPaintLabels(true);

        replaySpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = replaySpeedSlider.getValue();
                // Use the 'speed' value to adjust the game's replay speed
                // Implement your replay speed adjustment logic here

            }
        });
    }

    /**
     * Adds UI components to the panel.
     */
    private void addComponentsToPanel() {
        setLayout(new GridLayout(5, 1, 10, 10));
        add(recordButton);
        add(loadButton);
        add(stepButton);
        add(autoReplayButton);
        add(replaySpeedSlider);    }


    /**
     * Creates a simple JButton with specified text and appearance.
     *
     * @param text The text to display on the button.
     * @return A JButton with the specified text and appearance.
     */
    private static JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }
}
