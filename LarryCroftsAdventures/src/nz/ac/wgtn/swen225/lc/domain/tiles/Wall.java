package nz.ac.wgtn.swen225.lc.domain.tiles;

/**
 * Represents a tile that cannot be stepped on by the player.
 */
public class Wall implements Tile {
    /**
     * Create a new wall tile.
     */
    public Wall() {
    }

    @Override
    public boolean traversable() {
        return false;
    }
}