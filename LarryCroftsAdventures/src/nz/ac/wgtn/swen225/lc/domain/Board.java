package nz.ac.wgtn.swen225.lc.domain;

import java.io.File;
import java.io.FileNotFoundException;

import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

/**
 * Represents the board for a given level.
 */
public class Board {
    private Tile[][] board;
    private Chap chap;
    private int time;
    private int level;
    private int treasure;

    /**
     * Create a new Board. Generates a 2D array of tiles using {@link Persistency#loadGame(String) loadGame}.
     */
    public Board(int level) {
        Persistency persistency = new Persistency();
        this.level = level;

        try {
            File levelToLoad = new File("LarryCroftsAdventures/levels/level" + level + ".json");
            board = persistency.loadGame(levelToLoad);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while loading the game: " + e.getMessage());
        }
        time = persistency.timeLeft;
        int startX = persistency.x;
        int startY = persistency.y;
        Free playerTile = new Free(startX, startY);
        board[startX][startY] = playerTile;
        chap = new Chap(this, playerTile, treasure);
    }

    /**
     * Reset the given tile to a free tile.
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

    /**
     * Get a tile at the specified coordinates.
     * @param x position horizontally
     * @param y position vertically
     * @return the tile
     */
    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    /**
     * Get all tiles on the board.
     * @return 2D array of the tiles
     */
    public Tile[][] getTiles() {
        return board;
    }

    /**
     * Get the player.
     * @return the player
     */
    public Chap getChap() {
        return chap;
    }

    /**
     * Get the current level.
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get the time counter of the board.
     * @return the current time left
     */
    public int getTime() {
        return time;
    }
}
