package nz.ac.wgtn.swen225.lc.domain.tiles;

/**
 * Represents a tile that is locked until all the treasures are collected.
 *
 * @author Anthony Kendrew (300607402)
 */
public class ExitLock extends Wall {
    /**
     * Create a new exit lock tile.
     */
    public ExitLock(int x, int y) {
        super(x, y);
    }
}
