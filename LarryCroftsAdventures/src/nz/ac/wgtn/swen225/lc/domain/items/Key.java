package nz.ac.wgtn.swen225.lc.domain.items;

public class Key {
    private Key.Colour colour;

    public Key(Key.Colour colour) {
        this.colour = colour;
    }

    public Key.Colour getColour() {
        return colour;
    }

    public enum Colour {BLUE, GREEN, YELLOW, RED}
}
