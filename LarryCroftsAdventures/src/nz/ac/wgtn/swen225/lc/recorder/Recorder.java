package nz.ac.wgtn.swen225.lc.recorder;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.io.IOException;
import java.util.ArrayList;


public class Recorder {
    private ArrayList<String> reversedMoves;
    private int x;
    private int y;
    int initLevel;

    public Recorder(ArrayList<String> reversedMoves, int x, int y, int initLevel) {
        this.reversedMoves = reversedMoves;
        this.x = x;
        this.y =y;
        this.initLevel = initLevel;
    }

    /**
     * saves the recording
     * @param count file count
     * @throws IOException for the file writer
     */
    public void saveRecorder(int count) throws IOException {
       new Persistency().saveGame(count,this.reversedMoves,this.x, this.y, this.initLevel);
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
