package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.InfoField;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * Represents the player in Larry Croft's Adventures.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class Chap {
	private Item[][] inventory = new Item[2][4];
	private final Board board;
	private Free tile;
	private int playerTreasureCount;
	private State state;

	/**
	 * Stores the state of the game.
	 */
	public enum State {
		ONGOING, PAUSED, COMPLETED, DEAD
	};

	/**
	 * Create a new Chap character. A new character should be created per level.
	 * 
	 * @param board               the board that Chap is placed on
	 * @param tile                the tile that the player is standing on
	 * @param playerTreasureCount the number of treasures the player has collected
	 *                            so far
	 */
	public Chap(Board board, Free tile, int playerTreasureCount) {
		this.board = board;
		this.tile = tile;
		this.playerTreasureCount = playerTreasureCount;
		this.state = State.ONGOING;
	}

	/**
	 * Move the character on the board one square in the specified direction.
	 * 
	 * @param direction the direction to move the character
	 * @throws IllegalMove if the tile to the given direction is not traversable or
	 *                     the edge of the board is encountered
	 */
	public void move(Direction direction) throws IllegalMove {
		if (!state.equals(State.ONGOING)) {
			throw new IllegalMove("Cannot move when the game is not ongoing");
		}
		Tile next;
		int x = tile.getX();
		int y = tile.getY();
		try {
			next = switch (direction) {
			case UP -> board.getTile(x, y - 1);
			case DOWN -> board.getTile(x, y + 1);
			case LEFT -> board.getTile(x - 1, y);
			case RIGHT -> board.getTile(x + 1, y);
			};
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalMove("Encountered the edge of the board: " + direction);
		}

		if (!next.traversable(this)) {
			throw new IllegalMove("Not traversable: " + direction);
		}

		if (next.performTileAction(this)) {
			tile = board.resetTile(next);
		} else if (next instanceof InfoField infoField) {
			tile = infoField;
		}
	}

	/**
	 * Add an item to the player's inventory.
	 * 
	 * @param item the item to add
	 * @return true if successful, false if the player's inventory is full
	 */
	public boolean addItem(Item item) {
		for (int i = 0; i < inventory.length; i++) {
			for (int j = 0; j < inventory[i].length; j++) {
				if (inventory[i][j] == null) {
					inventory[i][j] = item;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Remove an item from the player's inventory.
	 * 
	 * @param item the item to add
	 * @return true if successful, false if the item was not found in the player's
	 *         inventory
	 */
	public boolean removeItem(Item item) {
		for (int i = 0; i < inventory.length; i++) {
			for (int j = 0; j < inventory[i].length; j++) {
				if (inventory[i][j] != null && inventory[i][j].equals(item)) {
					inventory[i][j] = null;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if the player has an item in their inventory.
	 * 
	 * @param item the item to check
	 * @return true if the player has the item, false otherwise
	 */
	public boolean hasItem(Item item) {
		for (int i = 0; i < inventory.length; i++) {
			for (int j = 0; j < inventory[i].length; j++) {
				if (inventory[i][j] != null && inventory[i][j].equals(item)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check whether the player can unlock the exit lock.
	 * 
	 * @return true if the player has collected all the treasures on the board,
	 *         false otherwise
	 */
	public boolean canUnlockExit() {
		return playerTreasureCount >= board.getBoardTreasureCount();
	}

	/**
	 * Update the state of the player.
	 * 
	 * @param state the new state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Get the player state.
	 * 
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Increment the treasure counter by one.
	 */
	public void addTreasure() {
		++playerTreasureCount;
	}

	/**
	 * Get the tile the player is standing on.
	 * 
	 * @return the tile
	 */
	public Free getTile() {
		return tile;
	}

	/**
	 * Get x position.
	 * 
	 * @return x position
	 */
	public int getX() {
		return tile.getX();
	}

	/**
	 * Get y position.
	 * 
	 * @return y position
	 */
	public int getY() {
		return tile.getY();
	}

	/**
	 * Get the player's inventory.
	 * @return the player's inventory
	 */
	public Item[][] getInventory() {
		return inventory;
	}

	public void setInventory(Item[][] inventory) {
		this.inventory = inventory;
	}
	/**
	 * Get the number of treasures collected by the player so far.
	 * 
	 * @return the number of treasures collected so far
	 */
	public int getPlayerTreasureCount() {
		return playerTreasureCount;
	}

	/**
	 * Represents the four possible directions for movement of the player.
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	/**
	 * Get board that Chap is on.
	 */
	public Board getBoard() {
		return board;
	}
}
