package nz.ac.wgtn.swen225.lc.domain;

import java.io.FileNotFoundException;

import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

public class Board {
    private Tile[][] board;
    private Chap chap;

    private int time;
    private int level;

    private int treasure;

    /**
     * Create a new Board. Generates a 2D array of tiles using {@link Persistency#loadGame(String) loadGame}.
     */
    public Board() {
        Persistency persistency = new Persistency();
        try {
            board = persistency.loadGame("LarryCroftsAdventures/levels/level1.json");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while loading the game: " + e.getMessage());
        }
        time = persistency.timeLeft;
        level = persistency.level;
        Free playerTile = new Free(chap, 7, 7);
        board[7][7] = playerTile;
        chap = new Chap(this, playerTile);
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
    public int getTime(){
        return time;
    }

    /**
     * Get the treasure counter of the board.
     * @return the count of treasures collected so far
     */
    public int getTreasure(){
        return treasure;
    }
}
