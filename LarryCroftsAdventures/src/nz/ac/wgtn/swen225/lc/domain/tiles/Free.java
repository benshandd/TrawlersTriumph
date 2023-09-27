package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that the player can stand on or move in the direction of.
 */
public class Free implements Tile {
    private Chap chap;

    protected int x;
    protected int y;

    /**
     * Create a new free tile.
     * @param x the x position of the tile
     * @param y the y position of the tile
     */
    public Free(int x, int y) {
        this.x = x;
        this.y = y;
        chap = null;
    }

    /**
     * Create a new free tile with a player standing on it.
     * @param chap the player
     * @param x the x position of the tile
     * @param y the y position of the tile
     */
    public Free(Chap chap, int x, int y) {
        this.x = x;
        this.y = y;
        this.chap = chap;
    }

    @Override
    public boolean traversable() {
        return true;
    }

    /**
     * Add the player to this tile.
     * @param chap the player
     */
    public void addChap(Chap chap) {
        this.chap = chap;
    }

    /**
     * Remove the player from this tile.
     */
    public void removeChap() {
        this.chap = null;
    }

    /**
     * Get the player standing on this tile.
     * @return the player standing on this tile, or null if no player is standing on this tile
     */
    public Chap getChap() {
        return chap;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}