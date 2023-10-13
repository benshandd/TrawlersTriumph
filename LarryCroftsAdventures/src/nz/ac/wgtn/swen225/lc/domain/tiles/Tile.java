package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a square on the board.
 *
 * @author Anthony Kendrew (300607402)
 */
public interface Tile extends Cloneable {
    /**
     * Checks whether this tile is traversable.
     *
     * @param chap the player
     * @return true if the tile can be stepped onto by the player at the given
     * moment, false otherwise
     */
    boolean traversable(Chap chap);

    /**
     * Performs the action that is attributed to this tile. This tile has no action,
     * so this method does nothing.
     *
     * @param chap the player object associated with the board this tile is on
     * @return true if the action was performed successfully, false if the action failed to be performed
     * @throws IllegalStateException if the action is not allowed to be performed
     */
    default boolean performTileAction(Chap chap) {
        return true;
    }

    /**
     * Get the x position.
     *
     * @return the x position
     */
    int getX();

    /**
     * Get the y position.
     *
     * @return the y position
     */
    int getY();

    /**
     * Deep clone method.
     *
     * @return the deep copy of this object
     */
    Tile clone() throws CloneNotSupportedException;
}