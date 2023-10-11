package nz.ac.wgtn.swen225.lc.domain.items;

/**
 * Represents a key that is stored in the inventory.
 * @author Anthony Kendrew (300607402)
 */
public record Key(Key.Colour colour) implements Item {
    /**
     * Create a new key.
     * @param colour the colour of the key
     */
    public Key {
        if (colour == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Represents the colour of keys and doors.
     */
    public enum Colour {BLUE, GREEN, YELLOW, RED}
}
