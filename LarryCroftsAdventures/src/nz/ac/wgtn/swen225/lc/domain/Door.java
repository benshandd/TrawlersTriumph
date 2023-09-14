package nz.ac.wgtn.swen225.lc.domain;

public class Door extends Wall {
    private Key.Colour colour;
    public Door() {
        super();
    }

    public boolean canOpen(Key key) {
        return key.getColour().equals(colour);
    }
}
