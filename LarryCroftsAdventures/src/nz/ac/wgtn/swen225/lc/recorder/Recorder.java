package nz.ac.wgtn.swen225.lc.recorder;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.io.IOException;
import java.util.ArrayList;


public class Recorder {
    private ArrayList<String> reversedMoves;
    private int x;
    private int y;

    public Recorder(ArrayList<String> reversedMoves, int x, int y) {
        this.reversedMoves = reversedMoves;
        this.x = x;
        this.y =y;
    }

    /**
     * saves the recording
     * @param count file count
     * @throws IOException for the file writer
     */
    public void saveRecorder(int count) throws IOException {
       // new Persistency().saveGame(count,this.reversedMoves,this.x, this.y);
    }

    /**
     * loads the saved game
     */
    public void loadSave(){

    }

    /**
     * each time the step button is pressed this method is called
     */
    public void step(){

    }
}
