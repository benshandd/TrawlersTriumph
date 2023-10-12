package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersistencyTest {

	@Test
	public void testLoadLevelOne() {
		Persistency persistency = new Persistency();
		File testFile = new File("LarryCroftsAdventures/levels/level1.json");

		try {
			Tile[][] loadedGame = persistency.loadGame(testFile);
			assertNotNull(loadedGame);
			assertEquals(8, persistency.playerX);
			assertEquals(8, persistency.playerY);
			assertEquals(60, persistency.timeLeft);
			assertEquals(10, persistency.boardTreasureCount);
			assertEquals(1, persistency.level);

			assertEquals(15, loadedGame.length);
			for (Tile[] row : loadedGame) {
				assertEquals(15, row.length);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Exception thrown when loading the game");
		}
	}

	@Test
	public void testLoadLevelTwo() {
		Persistency persistency = new Persistency();
		File testFile = new File("LarryCroftsAdventures/levels/level2.json");

		try {
			Tile[][] loadedGame = persistency.loadGame(testFile);
			assertNotNull(loadedGame);
			assertEquals(15, persistency.playerX);
			assertEquals(17, persistency.playerY);
			assertEquals(300, persistency.timeLeft);
			assertEquals(25, persistency.boardTreasureCount);
			assertEquals(2, persistency.level);
			assertEquals(30, loadedGame.length);
			for (Tile[] row : loadedGame) {
				assertEquals(30, row.length);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Exception thrown when loading the game");
		}
	}
	@Test
	public void loadGame() {
		Persistency persistency = new Persistency();
		try {
			Tile[][] loadedGame = persistency.loadGame(persistency.level1);
			assertNotNull(loadedGame);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown when saving or loading the game");
		}
	}

	@Test
	public void testResumeGame() {
		Persistency persistency = new Persistency();
		persistency.resumeGame();

	}
	@Test
	public void testGetItemByName() {
		Persistency persistency = new Persistency();
		Item item = persistency.getItemByName("Key_Yellow");
		assertNotNull(item);
	}
}
