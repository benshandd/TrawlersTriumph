package nz.ac.wgtn.swen225.lc.recorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.Move;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Recorder {
    /**
     * loads the moves from the chosen file
     *
     * @param file chosen file
     * @return moves in that file
     */
    public ArrayList<Move> loadSave(File file, App app) {
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            ArrayList<Move> moves = new ArrayList<>();
            //getting the actions array out of the file to then be able to use the actions
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonMoves = jsonObject.getAsJsonArray("actions");

            for (int i = 0; i < jsonMoves.size(); i++) {
                moves.add(new Move(jsonMoves.get(i).getAsString(), 1));
            }
            //pass file name to load the level the player started on and position where they started from
            app.setup(file);
            return moves;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Logic of the step funtion
     *
     * @param app   app component to fetch the correct things
     * @param file  the loaded file
     * @param moves the list of moves we are reading from
     */
    public static void step(App app, File file, ArrayList<Move> moves) {
        if (file == null) {
            JOptionPane.showMessageDialog(null,
                    "You need to load a file first!",
                    "File not chosen!",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            app.repaint();
            if (!moves.isEmpty()) {
                app.moveAction(moves.remove(0).move());
            } else {
                JOptionPane.showMessageDialog(null,
                        "All moves have been shown!",
                        "Replay finished!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

    /**
     * auto replay logic
     *
     * @param app   app component to fetch the correct things
     * @param file  the loaded file
     * @param moves the list of moves we are reading from
     * @param speed the speed the user sets on the slider
     */
    public static void auto(App app, File file, ArrayList<Move> moves, int speed) {
        if (file == null) {
            JOptionPane.showMessageDialog(null,
                    "You need to load a file first!",
                    "File not chosen!",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            //timer to be able to go through each action at a certain speed (set by slider)
            Timer recordingIndicatorTimer = new Timer();
            recordingIndicatorTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!app.paused) {
                        if (!moves.isEmpty()) {
                            app.moveAction(moves.remove(0).move());
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "All moves have been shown!",
                                    "Replay finished!",
                                    JOptionPane.PLAIN_MESSAGE);
                            recordingIndicatorTimer.cancel();
                        }
                        app.repaint();
                    } else {
                        recordingIndicatorTimer.schedule(this, 2000);
                    }

                }
            }, 0, 600 / speed);

        }
    }
}
