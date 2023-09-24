package nz.ac.wgtn.swen225.lc.domain;

public class Chap {
    private final Inventory inventory;
    public Chap() {
        inventory = new Inventory();
    }

    public boolean move(String direction) throws IllegalMove {
        throw new IllegalMove("Not working yet");
    }
}
