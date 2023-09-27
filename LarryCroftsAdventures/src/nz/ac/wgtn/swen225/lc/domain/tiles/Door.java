package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Key;

/**
 * Represents a tile containing a door.
 */
public class Door extends Wall {
    private final Key.Colour colour;

    /**
     * Create a new door tile.
     * @param colour the colour key that is required to open this door
     */
    public Door(Key.Colour colour) {
        super();
        this.colour = colour;
    }

    /**
     * Checks whether the player can open this door with a given key.
     * @param key the key the player is attempting to open
     * @return true if the key is the same colour as this door, false otherwise
     */
    public boolean canOpen(Key key) {
        if (key == null) {
            return false;
        }
        return key.getColour().equals(colour);
    }

    /**
     * Get the colour.
     * @return the colour of the key
     */
    public Key.Colour getColour() {
        return colour;
    }
}
