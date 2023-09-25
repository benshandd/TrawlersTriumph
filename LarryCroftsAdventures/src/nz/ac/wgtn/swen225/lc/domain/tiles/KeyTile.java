package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;

public class KeyTile extends Free {
    private final Key.Colour colour;

    public KeyTile(Chap chap, Key.Colour colour, String type, String item) {
        super();
        this.colour = colour;
    }

    public boolean canOpen(Door door) {
        if (door == null) {
            return false;
        }
        return door.getColour().equals(colour);
    }

    public Key.Colour getColour() {
        return colour;
    }


}
