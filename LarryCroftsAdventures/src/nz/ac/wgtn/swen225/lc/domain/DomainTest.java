<<<<<<< HEAD
package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.domain.items.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the domain package.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class DomainTest {
	private Chap chap;

	/**
	 * Initialise the board and player.
	 */
	@BeforeEach
	public void init() {
		AudioUnit au = new AudioUnit();
		Board board = new Board(Persistency.level1, au);
		chap = board.getChap();
	}

	@Test
	public void testBoard() {

	}

	@Test
	public void testMoveLeft() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertEquals(x - 1, chap.getX());
		assertEquals(y, chap.getY());
	}

	@Test
	public void testMoveLeftIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.LEFT));
	}

	@Test
	public void testMoveRight() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertEquals(x + 1, chap.getX());
		assertEquals(y, chap.getY());
	}

	@Test
	public void testMoveRightIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testMoveDown() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertEquals(x, chap.getX());
		assertEquals(y, chap.getY());
	}

	@Test
	public void testMoveDownIllegal() {
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.DOWN));
	}

	@Test
	public void testMoveUp() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertEquals(x, chap.getX());
		assertEquals(y - 1, chap.getY());
	}

	@Test
	public void testMoveUpIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.UP));
	}

	@Test
	public void testPickUpKey() {
		assertFalse(chap.hasItem(new Key(Key.Colour.RED)));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertTrue(chap.hasItem(new Key(Key.Colour.RED)));
	}

	@Test
	public void testCanOpenDoor() {
		testPickUpKey();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		Tile tile = chap.getBoard().getTile(chap.getX() + 1, chap.getY());
		assertTrue(tile instanceof Door);
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		tile = chap.getTile();
		assertTrue(tile instanceof Free);
		assertFalse(chap.hasItem(new Key(Key.Colour.RED)));
	}

	@Test
	public void testCannotOpenDoor() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testCannotOpenDoorWrongKey() {
		assertFalse(chap.hasItem(new Key(Key.Colour.BLUE)));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertTrue(chap.hasItem(new Key(Key.Colour.RED)));
		assertFalse(chap.hasItem(new Key(Key.Colour.BLUE)));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testPickUpTreasure() {
		assertEquals(0, chap.getPlayerTreasureCount());
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertEquals(1, chap.getPlayerTreasureCount());
	}
	
	@Test
	public void testFullInventory() {
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		assertFalse(chap.addItem(new Key(Key.Colour.RED)));
	}
	
	@Test
	public void testEmptyInventory() {
		assertFalse(chap.removeItem(new Key(Key.Colour.RED)));
	}
	
	@Test
	public void testMoveWhilePaused() {
		chap.setState(Chap.State.PAUSED);
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.UP));
	}
	
	@Test
	public void testCloneWall() throws CloneNotSupportedException {
		Wall tile1 = new Wall(0, 0);
		Wall tile2 = (Wall) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneFree() throws CloneNotSupportedException {
		Free tile1 = new Free(0, 0);
		Free tile2 = (Free) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneDoor() throws CloneNotSupportedException {
		Door tile1 = new Door(Key.Colour.RED, 0, 0);
		Door tile2 = (Door) tile1.clone();
        assertNotSame(tile1, tile2);
        assertSame(tile1.getColour(), tile2.getColour());
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneKeyTile() throws CloneNotSupportedException {
		KeyTile tile1 = new KeyTile(Key.Colour.RED, 0, 0);
		KeyTile tile2 = (KeyTile) tile1.clone();
        assertNotSame(tile1, tile2);
        assertSame(tile1.getColour(), tile2.getColour());
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneExit() throws CloneNotSupportedException {
		Exit tile1 = new Exit(0, 0);
		Exit tile2 = (Exit) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneExitLock() throws CloneNotSupportedException {
		ExitLock tile1 = new ExitLock(0, 0);
		ExitLock tile2 = (ExitLock) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneInfoField() throws CloneNotSupportedException {
		InfoField tile1 = new InfoField(0, 0);
		InfoField tile2 = (InfoField) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneTreasure() throws CloneNotSupportedException {
		Treasure tile1 = new Treasure(0, 0);
		Treasure tile2 = (Treasure) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testIllegalArgumentKeyTile() {
		assertThrows(IllegalArgumentException.class, () -> new KeyTile(null, 0, 0));
	}
	
	@Test
	public void testIllegalArgumentDoor() {
		assertThrows(IllegalArgumentException.class, () -> new Door(null, 0, 0));
	}

	@Test
	public void testInfoFieldNotReplacedWithFree() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertTrue(chap.getTile() instanceof InfoField);
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertTrue(chap.getTile() instanceof InfoField);
	}
}
=======
package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the domain package.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class DomainTest {
	private Chap chap;

	/**
	 * Initialise the board and player.
	 */
	@BeforeEach
	public void init() {
		AudioUnit au = new AudioUnit();
		Board board = new Board(Persistency.level1, au);
		chap = board.getChap();
	}

	@Test
	public void testBoard() {

	}

	@Test
	public void testMoveLeft() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertEquals(x - 1, chap.getX());
		assertEquals(y, chap.getY());
	}

	@Test
	public void testMoveLeftIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.LEFT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.LEFT));
	}

	@Test
	public void testMoveRight() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertEquals(x + 1, chap.getX());
		assertEquals(y, chap.getY());
	}

	@Test
	public void testMoveRightIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testMoveDown() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertEquals(x, chap.getX());
		assertEquals(y + 1, chap.getY());
	}

	@Test
	public void testMoveDownIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.DOWN));
	}

	@Test
	public void testMoveUp() {
		int x = chap.getX();
		int y = chap.getY();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertEquals(x, chap.getX());
		assertEquals(y - 1, chap.getY());
	}

	@Test
	public void testMoveUpIllegal() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.UP));
	}

	@Test
	public void testPickUpKey() {
		assertFalse(chap.hasItem(new Key(Key.Colour.RED)));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertTrue(chap.hasItem(new Key(Key.Colour.RED)));
	}

	@Test
	public void testCanOpenDoor() {
		testPickUpKey();
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		Tile tile = chap.getBoard().getTile(chap.getX() + 1, chap.getY());
		assertTrue(tile instanceof Door);
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		tile = chap.getBoard().getTile(chap.getX(), chap.getY());
		assertTrue(tile instanceof Free);
		assertFalse(chap.hasItem(new Key(Key.Colour.RED)));
	}

	@Test
	public void testCannotOpenDoor() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testCannotOpenDoorWrongKey() {
		assertFalse(chap.hasItem(new Key(Key.Colour.BLUE)));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertTrue(chap.hasItem(new Key(Key.Colour.RED)));
		assertFalse(chap.hasItem(new Key(Key.Colour.BLUE)));
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.RIGHT));
	}

	@Test
	public void testPickUpTreasure() {
		assertEquals(0, chap.getPlayerTreasureCount());
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.RIGHT));
		assertEquals(1, chap.getPlayerTreasureCount());
	}
	
	@Test
	public void testFullInventory() {
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		chap.addItem(new Key(Key.Colour.RED));
		assertFalse(chap.addItem(new Key(Key.Colour.RED)));
	}
	
	@Test
	public void testEmptyInventory() {
		assertFalse(chap.removeItem(new Key(Key.Colour.RED)));
	}
	
	@Test
	public void testMoveWhilePaused() {
		chap.setState(Chap.State.PAUSED);
		assertThrows(IllegalMove.class, () -> chap.move(Chap.Direction.UP));
	}
	
	@Test
	public void testCloneWall() throws CloneNotSupportedException {
		Wall tile1 = new Wall(0, 0);
		Wall tile2 = (Wall) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneFree() throws CloneNotSupportedException {
		Free tile1 = new Free(0, 0);
		Free tile2 = (Free) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneDoor() throws CloneNotSupportedException {
		Door tile1 = new Door(Key.Colour.RED, 0, 0);
		Door tile2 = (Door) tile1.clone();
        assertNotSame(tile1, tile2);
        assertSame(tile1.getColour(), tile2.getColour());
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneKeyTile() throws CloneNotSupportedException {
		KeyTile tile1 = new KeyTile(Key.Colour.RED, 0, 0);
		KeyTile tile2 = (KeyTile) tile1.clone();
        assertNotSame(tile1, tile2);
        assertSame(tile1.getColour(), tile2.getColour());
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneExit() throws CloneNotSupportedException {
		Exit tile1 = new Exit(0, 0);
		Exit tile2 = (Exit) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneExitLock() throws CloneNotSupportedException {
		ExitLock tile1 = new ExitLock(0, 0);
		ExitLock tile2 = (ExitLock) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneInfoField() throws CloneNotSupportedException {
		InfoField tile1 = new InfoField(0, 0);
		InfoField tile2 = (InfoField) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testCloneTreasure() throws CloneNotSupportedException {
		Treasure tile1 = new Treasure(0, 0);
		Treasure tile2 = (Treasure) tile1.clone();
        assertNotSame(tile1, tile2);
        assertEquals(tile1.getX(), tile2.getX());
        assertEquals(tile1.getY(), tile2.getY());
	}
	
	@Test
	public void testIllegalArgumentKeyTile() {
		assertThrows(IllegalArgumentException.class, () -> new KeyTile(null, 0, 0));
	}
	
	@Test
	public void testIllegalArgumentDoor() {
		assertThrows(IllegalArgumentException.class, () -> new Door(null, 0, 0));
	}

	@Test
	public void testInfoFieldNotReplacedWithFree() {
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertTrue(chap.getTile() instanceof InfoField);
		assertDoesNotThrow(() -> chap.move(Chap.Direction.DOWN));
		assertDoesNotThrow(() -> chap.move(Chap.Direction.UP));
		assertTrue(chap.getTile() instanceof InfoField);
	}
}
>>>>>>> 8edf04667853545a787c11713f2ed2b39c26035f
