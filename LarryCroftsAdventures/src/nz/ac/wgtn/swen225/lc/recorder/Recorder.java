package nz.ac.wgtn.swen225.lc.recorder;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.io.IOException;
import java.util.Stack;
import java.util.regex.Pattern;


public class Recorder {

    static final Pattern TAB = Pattern.compile("    "); // four white spaces
    static final Pattern HALF_TAB = Pattern.compile("  ");
    private Stack<String> reversedMoves;
    private int x;
    private int y;

    public Recorder(Stack<String> reversedMoves, int x, int y) {
        this.reversedMoves = reversedMoves;
        this.x = x;
        this.y =y;
    }

    public void saveRecorder(int count) throws IOException {
        new Persistency().saveGame(count,this.reversedMoves,this.x, this.y);
    }

}
