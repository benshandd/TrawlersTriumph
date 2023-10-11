package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that is locked until all the treasures are collected.
 * @author Anthony Kendrew (300607402)
 */
public class ExitLock extends Wall {
    /**
     * Create a new exit lock tile.
     */
    public ExitLock(int x, int y) {
        super(x, y);
    }

    /**
     * Checks whether the player has collected all the treasures.
     */
    @Override
    public boolean traversable(Chap chap) {
        return chap.canUnlockExit();
    }

    @Override
    public Tile clone() throws CloneNotSupportedException {
        ExitLock tile = (ExitLock) super.clone();
        return tile;
    }
}
