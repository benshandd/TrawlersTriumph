package nz.ac.wgtn.swen225.lc.domain.tiles;

public interface Tile {
    /**
     * Checks whether this tile is traversable.
     * @return true if the tile can be stepped onto by the player at the given moment, false otherwise
     */
    boolean traversable();
}