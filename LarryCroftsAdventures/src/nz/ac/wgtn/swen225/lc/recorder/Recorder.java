package nz.ac.wgtn.swen225.lc.recorder;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.Game;
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
    Stack<Chap> actions;
    File newFile;

    int timeStamp;
    int level;
    Game game;
    int x;
    int y;

    public Recorder(int timeStamp, int level, Game game, int x, int y) {
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
            fW.write("{\n");
            fW.write(TAB.pattern() + "Time Stamp: " + timeStamp + "," + "\n");
            fW.write(TAB.pattern() + "Level: " + level + "," +"\n");
            fW.write(TAB.pattern() + "Game State: " + game.toString() + "," + "\n");
            fW.write(TAB.pattern() + "x position: " + x + "," + "\n");
            fW.write(TAB.pattern() + "y position: " + y + "," + "\n");
            fW.write(TAB.pattern() + "actions: [\n");
            while (!actions.empty()){
                fW.write(HALF_TAB.pattern() + actions.pop().toString() + ",\n");
            }
            fW.write(TAB.pattern() + "]");
            fW.write("}");
            fW.close();
        }
    }

    /**
     * adds an action to the stack only if the global variable "record" is true.
     * @param chap the action needing to be added to the stack
     */
    public void addAction(Chap chap){
        actions.push(chap);
    }


    /**
     * parses the file to be able to replay what the player has done.
     */
    public void initParse() throws FileNotFoundException {
        List<Chap> actionsList = new ArrayList<Chap>();
        JFileChooser fc = new JFileChooser();
        int retVal = fc.showOpenDialog(null);
        File file;

        if (retVal == JFileChooser.APPROVE_OPTION){
            System.out.println("File opened successfully called: " + fc.getSelectedFile().getName());
            file = fc.getSelectedFile();
        } else{
            throw new FileNotFoundException();
        }

        Scanner scan = new Scanner(file);
        while (scan.hasNext()){
            actionsList.add(runParse(scan));
        }
    }

    public Chap runParse(Scanner scan){

    }





}
