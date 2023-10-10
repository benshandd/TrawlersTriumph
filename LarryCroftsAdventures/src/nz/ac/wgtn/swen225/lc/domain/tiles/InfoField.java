package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that has an information message pop up when the player steps onto it.
 *
 * @author Anthony Kendrew (300607402)
 */
public class InfoField extends Free {
    private final String message;

    /**
     * Create a new information field tile.
     * @param message the information that pops up when the player steps onto this tile
     */
    public InfoField(String message, int x, int y) {
        super(x, y);
        this.message = message;
    }

    /**
     * Get the message contained within this tile.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public boolean performTileAction(Chap chap) {
        return false;
    }
}
