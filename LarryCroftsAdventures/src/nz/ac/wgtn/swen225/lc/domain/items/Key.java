package nz.ac.wgtn.swen225.lc.domain.items;

import nz.ac.wgtn.swen225.lc.domain.tiles.Door;

/**
 * Represents a key that is stored in the inventory.
 *
 * @author Anthony Kendrew (300607402)
 */
public record Key(Key.Colour colour) implements Item {
    /**
     * Create a new key.
     *
     * @param colour the colour of the key
     */
    public Key {
        if (colour == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Deep clone a key.
     * @return the cloned key
     */
    public Item clone() throws CloneNotSupportedException {
        Key item = (Key) super.clone();
        return item;
    }

    /**
     * Represents the colour of keys and doors.
     */
    public enum Colour {
        BLUE, GREEN, YELLOW, RED
    }
}
