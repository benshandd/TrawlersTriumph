package nz.ac.wgtn.swen225.lc.domain.tiles;

import com.google.gson.JsonObject;

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
}