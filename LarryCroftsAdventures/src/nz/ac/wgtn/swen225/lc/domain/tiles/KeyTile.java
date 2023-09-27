package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Key;

/**
 * Represents a tile that has a collectible key.
 */
public class KeyTile extends Free {
    private final Key.Colour colour;

    /**
     * Create a new key tile.
     * @param colour the colour of the collectible key
     */
    public KeyTile(Key.Colour colour) {
        super();
        this.colour = colour;
    }

    /**
     * Get the colour.
     * @return the colour of the key
     */
    public Key.Colour getColour() {
        return colour;
    }
}
