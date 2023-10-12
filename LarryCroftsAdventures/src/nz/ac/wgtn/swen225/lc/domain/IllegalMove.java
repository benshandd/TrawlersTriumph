package nz.ac.wgtn.swen225.lc.domain;

/**
 * An exception to throw when an illegal move is attempted by the player.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class IllegalMove extends Exception {
	/**
	 * Create a new IllegalMove.
	 * 
	 * @param message the message to throw
	 */
	public IllegalMove(String message) {
		super(message);
	}
}
