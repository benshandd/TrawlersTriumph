package nz.ac.wgtn.swen225.lc.domain;

public class Key extends Free {
    private Key.Colour colour;

    public Key(Chap chap, Key.Colour colour) {
        super(chap);
        this.colour = colour;
    }

    ;

    public Key.Colour getColour() {
        return colour;
    }

    enum Colour {BLUE, GREEN, YELLOW, RED}
}
