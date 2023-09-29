package nz.ac.wgtn.swen225.lc.domain.tiles;

public interface Tile {
    /**
     * Checks whether this tile is traversable.
     * @return true if the tile can be stepped onto by the player at the given moment, false otherwise
     */
    boolean traversable();

    /**
     * Performs the action that is attributed to this tile.
     * This tile has no action, so this method does nothing
     */
    default void performTileAction() {

    }

    /**
     * Get the x position.
     * @return the x position
     */
    int getX();

    /**
     * Get the y position.
     * @return the y position
     */
    int getY();
}