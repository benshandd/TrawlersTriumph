package nz.ac.wgtn.swen225.lc.domain.tiles;

/**
 * Represents a tile that cannot be stepped on by the player.
 */
public class Wall implements Tile {
    protected int x;
    protected int y;

    /**
     * Create a new wall tile.
     * @param x the x position of the tile
     * @param y the y position of the tile
     */
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean traversable() {
        return false;
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