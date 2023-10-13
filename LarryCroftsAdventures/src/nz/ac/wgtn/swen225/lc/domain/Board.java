package nz.ac.wgtn.swen225.lc.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.AutoActor;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

/**
 * Represents the board for a given level.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class Board {
	private List<AutoActor> autoActors;
	private Tile[][] board;
	private final Chap chap;
	private int time;
	private final int level;
	private final int boardTreasureCount;
<<<<<<< HEAD
	private final AudioUnit audioUnit;
	private static Persistency persistency;
=======
>>>>>>> 13a61c953d1c0954777f4a22578518bddb3c99f9

	/**
	 * Create a new Board. Generates a 2D array of tiles using
	 * {@link Persistency#loadGame(File) loadGame}.
	 */
	public Board(File file) {
		this.autoActors = new ArrayList<>();
<<<<<<< HEAD
		this.audioUnit = audioUnit;
=======
		Persistency persistency = new Persistency();
>>>>>>> 13a61c953d1c0954777f4a22578518bddb3c99f9
		try {
			persistency = new Persistency();
			board = persistency.loadGame(file);
			autoActors = persistency.getActors();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("An error occurred while loading the game: " + e.getMessage());
		}
		time = persistency.timeLeft;
		boardTreasureCount = persistency.boardTreasureCount;
		level = persistency.level;
		int startX = persistency.playerX;
		int startY = persistency.playerY;
		Free playerTile = new Free(startX, startY);
		board[startX][startY] = playerTile;
		chap = new Chap(this, playerTile, persistency.playerTreasureCount);

	}

	/**
	 * Reset the given tile to a free tile.
	 * 
	 * @param tile the tile to reset
	 * @return the new tile that has been reset
	 */
	protected Free resetTile(Tile tile) {
		int x = tile.getX();
		int y = tile.getY();
		Free freeTile = new Free(x, y);
		board[x][y] = freeTile;
		return freeTile;
	}
	public List<AutoActor> getAutoActors() {
		return autoActors;
	}

	/**
	 * Get a tile at the specified coordinates.
	 * 
	 * @param x position horizontally
	 * @param y position vertically
	 * @return the tile
	 */
	public Tile getTile(int x, int y) {
		return board[x][y];
	}

	/**
	 * Get all tiles on the board.
	 * 
	 * @return 2D array of the tiles
	 */
	public Tile[][] getTiles() {
		return board;
	}
	/**
	 * Get the player.
	 * 
	 * @return the player
	 */
	public Chap getChap() {
		return chap;
	}

	/**
	 * Get the current level.
	 * 
	 * @return the current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Get the time counter of the board.
	 * 
	 * @return the current time left
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Get the number of treasures required to complete this level.
	 * 
	 * @return the number of treasures
	 */
	public int getBoardTreasureCount() {
		return boardTreasureCount;
	}
}
