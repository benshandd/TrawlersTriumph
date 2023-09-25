package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

public class InfoField extends Free {
    private String message;

    public InfoField(Chap chap, String message, String type, String item) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
