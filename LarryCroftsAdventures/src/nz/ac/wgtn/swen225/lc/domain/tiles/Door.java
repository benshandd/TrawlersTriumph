package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Key;

public class Door extends Wall {
    private final Key.Colour colour;

    public Door(Key.Colour colour) {
        super();
        this.colour = colour;
    }

    public boolean canOpen(Key key) {
        if (key == null) {
            return false;
        }
        return key.getColour().equals(colour);
    }

    public Key.Colour getColour() {
        return colour;
    }
}
