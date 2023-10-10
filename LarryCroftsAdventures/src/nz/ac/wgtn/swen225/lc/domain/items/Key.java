package nz.ac.wgtn.swen225.lc.domain.items;

/**
 * Represents a key that is stored in the inventory.
 *
 * @author Anthony Kendrew (300607402)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof Key otherKey) return colour == otherKey.colour;
        return false;
    }

    /**
     * Get the colour.
     * @return the colour of the key
     */
    public Key.Colour getColour() {
        return colour;
    }

    /**
     * Represents the colour of keys and doors.
     */
    public enum Colour { BLUE, GREEN, YELLOW, RED }
}
