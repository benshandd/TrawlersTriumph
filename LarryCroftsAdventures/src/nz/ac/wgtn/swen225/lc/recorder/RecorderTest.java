package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.app.RecorderPanel;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecorderTest {
    File testFile = new File("LarryCroftsAdventures/src/nz/ac/wgtn/swen225/lc/recorder/RecorderTestFile.json");
    App app;
    ArrayList<Move> testList = new ArrayList<>();
    RecorderPanel rp;
    @BeforeEach
    public void initialise(){
        app = new App();
        rp = new RecorderPanel(app);
        testList.add(new Move("UP",1));
        testList.add(new Move("LEFT",1));
        testList.add(new Move("DOWN",1));
        testList.add(new Move("LEFT",1));
        testList.add(new Move("RIGHT",1));
        testList.add(new Move("UP",1));
    }


    @Test
    public void testStepAndAuto() {
        rp.startRecording();
        testList.forEach(m -> assertDoesNotThrow(() -> app.moveAction(m.move())));
        rp.stopRecording();
    }
    @Test
    public void testRecording(){
        assertDoesNotThrow(() -> rp.startRecording());
    }
    @Test
    public void testSaving() {
        rp.saveEndingInfo();
        assertDoesNotThrow(() -> rp.stopRecording());

    }
    @Test
    public void testLoading(){
        testList = assertDoesNotThrow(() -> new Recorder().loadSave(testFile,app));
    }
}
