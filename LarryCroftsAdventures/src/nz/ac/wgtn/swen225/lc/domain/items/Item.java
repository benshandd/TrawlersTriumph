package nz.ac.wgtn.swen225.lc.domain.items;

import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * Represents an item that is stored in the player's inventory.
 *
 * @author Anthony Kendrew (300607402)
 */
public interface Item extends Cloneable {
    /**
     * Deep clone method.
     *
     * @return the deep copy of this object
     */
    Item clone() throws CloneNotSupportedException;
}
