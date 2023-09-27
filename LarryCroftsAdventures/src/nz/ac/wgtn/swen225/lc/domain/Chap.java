package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.exceptions.IllegalMove;
import nz.ac.wgtn.swen225.lc.domain.exceptions.InventoryFull;
import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * Represents the player in Larry Croft's Adventures
 */
public class Chap {
    private static final int MAX_INVENTORY = 8;
    private final Item[][] inventory = new Item[2][4];
    private final Board board;
    private int x = 7;
    private int y = 7;

    /**
     * Create a new Chap character. A new character should be created per level.
     * @param board the board that Chap is placed on
     */
    public Chap(Board board) {
        this.board = board;
    }

    /**
     * Move the character on the board one square in the specified direction.
     * @param direction the direction to move the character
     * @throws IllegalMove if the tile to the given direction is not traversable or the edge of the board is encountered
     */
    public boolean move(Direction direction) throws IllegalMove {
        Tile next;
        try {
            next = switch (direction) {
                case UP -> board.getTile(x, y - 1);
                case DOWN -> board.getTile(x, y + 1);
                case LEFT -> board.getTile(x - 1, y);
                case RIGHT -> board.getTile(x + 1, y);
            };
        } catch (IndexOutOfBoundsException e) {
            //throw new IllegalMove("Edge of the board to the " + direction.toString());
            return false;
        }

        if (!next.traversable()) {
            //throw new IllegalMove("Not traversable to the " + direction.toString());
            return false;
        }

        Free currentTile = (Free) board.getTile(x, y);
        Free nextTile = (Free) next;
        currentTile.removeChap();
        nextTile.addChap(this);
        x = switch (direction) {
            case LEFT -> x - 1;
            case RIGHT -> x + 1;
            default -> x;
        };
        y = switch (direction) {
            case UP -> y - 1;
            case DOWN -> y + 1;
            default -> y;
        };
        return true;
    }

    /**
     * Add an item to the player's inventory.
     * @param item the item to add
     * @throws InventoryFull if the player's inventory is full
     */
    public void addItem(Item item) throws InventoryFull {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] == null) {
                    inventory[i][j] = item;
                }
            }
        }
        throw new InventoryFull();
    }

    /**
     * Remove an item from the player's inventory.
     * @param item the item to add
     * @return true if successful, false if the item was not found in the player's inventory
     */
    public boolean removeItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] == item) {
                    inventory[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Represents the four possible directions for movement of the player
     */
    public enum Direction {UP, DOWN, LEFT, RIGHT}

    /**
     * Get the player's x position on the board.
     * @return the player's x position
     */
    public int getX(){ return x; }

    /**
     * Get the player's y position on the board.
     * @return the player's y position
     */
    public int getY(){ return y; }


}
