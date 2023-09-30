package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * Represents the player in Larry Croft's Adventures
 */
public class Chap {
    private final Item[][] inventory = new Item[2][4];
    private final Board board;
    private Free tile;

    /**
     * Create a new Chap character. A new character should be created per level.
     * @param board the board that Chap is placed on
     * @param tile the tile that the player is standing on
     */
    public Chap(Board board, Free tile) {
        this.board = board;
        this.tile = tile;
    }

    /**
     * Move the character on the board one square in the specified direction.
     * @param direction the direction to move the character
     * @throws IllegalMove if the tile to the given direction is not traversable or the edge of the board is encountered
     */
    public void move(Direction direction) throws IllegalMove {
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

        if (!next.traversable()) {
            throw new IllegalMove("Not traversable: " + direction);
        }

        next.performTileAction();
        tile = board.resetTile(next);
    }

    /**
     * Add an item to the player's inventory.
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
     * @param item the item to add
     * @return true if successful, false if the item was not found in the player's inventory
     */
    public boolean removeItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].equals(item)) {
                    inventory[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the player has an item in their inventory.
     * @param item the item to check
     * @return true if the player has the item, false otherwise
     */
    public boolean hasItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the tile the player is standing on.
     * @return the tile
     */
    public Free getTile() {
        return tile;
    }

    /**
     * Represents the four possible directions for movement of the player.
     */
    public enum Direction {UP, DOWN, LEFT, RIGHT}
}
