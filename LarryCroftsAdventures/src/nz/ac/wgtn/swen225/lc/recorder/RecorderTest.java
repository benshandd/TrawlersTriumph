package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class RecorderTest {

	File testFile = new File("LarryCroftsAdventures/src/nz/ac/wgtn/swen225/lc/recorder/RecorderTestFile.json");

	Board board;
	Chap chap;
	boolean recording = false;

	@BeforeEach
	public void initialise() {
		AudioUnit au = new AudioUnit();
		board = new Board(testFile, au);
		chap = board.getChap();
	}

	@Test
	public void testAuto() {

	}

	@Test
	public void testStep() {

	}

	@Test
	public void testRecording() {
		assertEquals(8, chap.getX());
		assertEquals(8, chap.getY());
		assertEquals(2, chap.getPlayerTreasureCount());
		assertEquals(1, chap.getBoard().getLevel());
		assertEquals(10, chap.getBoard().getBoardTreasureCount());
		assertEquals(60, chap.getBoard().getTime());
		assertEquals(2, chap.getPlayerTreasureCount());
	}

	@Test
	public void testSaving() {

	}

	@Test
	public void testLoading() {

	}
}
