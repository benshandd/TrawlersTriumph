package nz.ac.wgtn.swen225.lc.recorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.IllegalMove;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecorderTest {

    File testFile = new File("LarryCroftsAdventures/src/nz/ac/wgtn/swen225/lc/recorder/RecorderTestFile.json");

    Board board;
    Chap chap;
    boolean recording = false;
    ArrayList<Move> moves;
    @BeforeEach
    public void initialise(){
        AudioUnit au = new AudioUnit();
        board = new Board(testFile, au);
        chap = board.getChap();

        try(JsonReader reader = new JsonReader(new FileReader(testFile))) {
            moves = new ArrayList<>();
            //getting the actions array out of the file to then be able to use the actions
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonMoves = jsonObject.getAsJsonArray("actions");

            for (int i = 0; i < jsonMoves.size(); i++) {
                moves.add(new Move(jsonMoves.get(i).getAsString(), 1));
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAuto(){

    }

    @Test
    public void testStep() {
//        int x = chap.getX();
//        int y = chap.getY();
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertEquals(y-1,chap.getY());
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertEquals(x-1,chap.getX());
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertEquals(y-1,chap.getY());
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertEquals(x+1,chap.getX());
//        assertDoesNotThrow(() -> new Recorder().step(moves.remove(0)));
//        assertEquals(y+1,chap.getY());

    }
    @Test
    public void testRecording(){
//        assertEquals(8,chap.getX());
//        assertEquals(8,chap.getY());
//        assertEquals(0,chap.getCurrentTreasure());
//        assertEquals(1,chap.getBoard().getLevel());
//        assertEquals(11,chap.getBoard().getTreasureLeft());
//        assertEquals(100,chap.getBoard().getTime());
    }
    @Test
    public void testSaving(){

    }
    @Test
    public void testLoading(){

    }
}
