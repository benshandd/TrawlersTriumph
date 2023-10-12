package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.app.RecorderPanel;
import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.IllegalMove;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RecorderTest {

    File testFile = new File("LarryCroftsAdventures/src/nz/ac/wgtn/swen225/lc/recorder/RecorderTestFile.json");

    Board board;
    Chap chap;
    App app;
    ArrayList<Move> testList = new ArrayList<>();
    @BeforeEach
    public void initialise(){
        AudioUnit au = new AudioUnit();
        board = new Board(testFile, au);
        chap = board.getChap();
        app = new App();
        testList.add(new Move("UP",1));
        testList.add(new Move("LEFT",1));
        testList.add(new Move("DOWN",1));
        testList.add(new Move("LEFT",1));
        testList.add(new Move("LEFT",1));
        testList.add(new Move("RIGHT",1));
        testList.add(new Move("UP",1));
        testList.add(new Move("UP",1));
    }


    @Test
    public void testStepAndAuto(){
        testList.forEach(m -> assertDoesNotThrow(() -> new Recorder().step(m)));
        assertEquals(6,app.getBoard().getChap().getY());
        assertEquals(6,app.getBoard().getChap().getX());
        assertDoesNotThrow(() -> new Recorder().step(new Move("UP",1)));
        assertDoesNotThrow(() -> new Recorder().step(new Move("UP",1)));
        assertThrows(IllegalMove.class, () -> new Recorder().step(new Move("UP",1)));
    }
    @Test
    public void testRecording(){
        assertDoesNotThrow(() -> new RecorderPanel(app).startRecording());
    }
    @Test
    public void testSaving() {
        Recorder r = new Recorder(testList,chap.getX(),chap.getY(),chap.getPlayerTreasureCount(),board.getBoardTreasureCount()
                , board.getLevel(), board.getTime(), board.getTiles(), chap);
    }
    @Test
    public void testLoading(){
        testList = assertDoesNotThrow(() -> new Recorder().loadSave(testFile,app));
    }
}
