package nz.ac.wgtn.swen225.lc.domain.exceptions;

/**
 * An exception to throw when an illegal move is attempted to be made by the player.
 */
public class IllegalMove extends Exception {
    /**
     * Create a new IllegalMove.
     * @param message the message to throw
     */
    public IllegalMove(String message) {
        super(message);
    }
}
