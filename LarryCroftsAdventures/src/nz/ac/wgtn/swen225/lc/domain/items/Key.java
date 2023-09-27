package nz.ac.wgtn.swen225.lc.domain.items;

import nz.ac.wgtn.swen225.lc.domain.tiles.Door;

/**
 * Represents a key that is stored in the inventory.
 */
public class Key implements Item {
    private Key.Colour colour;

    /**
     * Create a new key.
     * @param colour the colour of the key
     */
    public Key(Key.Colour colour) {
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

    /**
     * Checks whether the player can open a door with this key.
     * @param door the door the player is attempting to open
     * @return true if the door is the same colour as this key, false otherwise
     */
    public boolean canOpen(Door door) {
        if (door == null) {
            return false;
        }
        return door.getColour().equals(colour);
    }

    /**
     * Represents the colour of keys and doors.
     */
    public enum Colour { BLUE, GREEN, YELLOW, RED }
}
