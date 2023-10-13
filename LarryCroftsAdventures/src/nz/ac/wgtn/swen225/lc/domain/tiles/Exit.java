package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that completes the current level.
 *
 * @author Anthony Kendrew (300607402)
 */
public class Exit extends Free {
    /**
     * Create a new exit tile.
     */
    public Exit(int x, int y) {
        super(x, y);
    }

    /**
     * Sets the state of the player to 'completed'.
     */
    @Override
    public boolean performTileAction(Chap chap) {
        chap.setState(Chap.State.COMPLETED);
        return true;
    }

    @Override
    public Tile clone() throws CloneNotSupportedException {
        Exit tile = (Exit) super.clone();
        return tile;
    }
}
