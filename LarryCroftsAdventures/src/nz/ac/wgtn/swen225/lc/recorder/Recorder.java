package nz.ac.wgtn.swen225.lc.recorder;
import com.google.gson.Gson;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;


public class Recorder {

    static final Pattern TAB = Pattern.compile("    "); // four white spaces
    static final Pattern HALF_TAB = Pattern.compile("  ");
    private Stack<Chap> actions;
    private File newFile;

    private int timeStamp;
    private int level;
    private String game;
    private int x;
    private int y;

    public Recorder(int timeStamp, int level, String game, int x, int y) {
        this.timeStamp = timeStamp;
        this.level = level;
        this.game = game;
        this.x = x;
        this.y =y;
    }

    /**
     * starts recording the gameplay when the button is pressed
     * @param newFileNum to add to the end of the file name for when making a new recorded gameplay
     */
    public void startRecordingGameplay(int newFileNum) {
        actions = new Stack<Chap>();
        newFile = new File("Recorded_Gameplay_" + newFileNum + ".json");
    }

    /**
     * when the button is pressed again will stop it recording and a json file will be made
     */
    public void stopRecordingGameplay() throws IOException {
        if (newFile.createNewFile()){
            FileWriter fW = new FileWriter(newFile);
            Gson gson = new Gson();
            Recorder r = new Recorder(11,1,"game",5,7);
            String jsonStr = gson.toJson(r);
            fW.write(jsonStr);
            fW.close();
        }
    }


    /**
     * adds an action to the stack only if the global variable "record" is true.
     * @param chap the action needing to be added to the stack
     */
    public void addAction(Chap chap){
        actions.push(new Chap());
    }
}
