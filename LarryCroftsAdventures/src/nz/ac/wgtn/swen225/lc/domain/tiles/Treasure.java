package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that has a collectible treasure.
 */
public class Treasure extends Free {
    /**
     * Create a new treasure tile.
     */
    public Treasure(int x, int y) {
        super(x, y);
    }

    /**
     * Add a treasure to the treasure counter.
     */
    @Override
    public boolean performTileAction(Chap chap) {
        chap.addTreasure();
        return true;
    }
}
