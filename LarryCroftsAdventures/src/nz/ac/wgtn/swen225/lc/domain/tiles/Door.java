package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;

/**
 * Represents a tile containing a door.
 *
 * @author Anthony Kendrew (300607402)
 */
public class Door extends Wall {
    private final Key.Colour colour;

    /**
     * Create a new door tile.
     *
     * @param colour the colour key that is required to open this door
     */
    public Door(Key.Colour colour, int x, int y) {
        super(x, y);
        if (colour == null) {
            throw new IllegalArgumentException();
        }
        this.colour = colour;
    }

    /**
     * Attempt to open this door.
     */
    @Override
    public boolean performTileAction(Chap chap) {
        if (canOpen(chap)) {
            assert chap.removeItem(new Key(colour));
            return true;
        }
        return false;
    }

    /**
     * Checks whether the door can be opened or not.
     */
    @Override
    public boolean traversable(Chap chap) {
        return canOpen(chap);
    }

    /**
     * Checks whether the player can open this door with a given key.
     *
     * @param chap the player
     * @return true if the key is the same colour as this door, false otherwise
     */
    public boolean canOpen(Chap chap) {
        return chap.hasItem(new Key(colour));
    }

    /**
     * Get the colour.
     *
     * @return the colour of the door
     */
    public Key.Colour getColour() {
        return colour;
    }

    @Override
    public Tile clone() throws CloneNotSupportedException {
        Door tile = (Door) super.clone();
        return tile;
    }
}
