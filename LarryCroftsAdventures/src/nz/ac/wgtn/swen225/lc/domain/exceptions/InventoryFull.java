package nz.ac.wgtn.swen225.lc.domain.exceptions;

/**
 * An exception to throw when the player's inventory is full.
 */
public class InventoryFull extends Exception {
    /**
     * Create a new InventoryFull with no message.
     */
    public InventoryFull() {
        super();
    }

    /**
     * Create a new InventoryFull with a specified message.
     * @param message the message to throw
     */
    public InventoryFull(String message) {
        super(message);
    }
}
