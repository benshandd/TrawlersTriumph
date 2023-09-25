package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

public class Free implements Tile {
    private Chap chap;

    public Free(Chap chap) {
        this.chap = chap;
    }

    @Override
    public boolean traversable() {
        return true;
    }

    public void addChap(Chap chap) {
        this.chap = chap;
    }

    public void removeChap() {
        this.chap = null;
    }

    public Chap getChap() {
        return chap;
    }
}