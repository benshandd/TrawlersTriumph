package nz.ac.wgtn.swen225.lc.domain.exceptions;

public class InventoryFull extends Exception {
    public InventoryFull() {
        super();
    }

    public InventoryFull(String message) {
        super(message);
    }
}
