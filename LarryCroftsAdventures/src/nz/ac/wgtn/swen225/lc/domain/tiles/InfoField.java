package nz.ac.wgtn.swen225.lc.domain.tiles;

/**
 * Represents a tile that has an information message pop up when the player steps onto it.
 */
public class InfoField extends Free {
    private String message;

    /**
     * Create a new information field tile.
     * @param message the information that pops up when the player steps onto this tile
     */
    public InfoField(String message) {
        super();
        this.message = message;
    }

    /**
     * Get the message contained within this tile.
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
