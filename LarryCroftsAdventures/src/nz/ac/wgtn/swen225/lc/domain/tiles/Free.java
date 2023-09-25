package nz.ac.wgtn.swen225.lc.domain.tiles;

import com.google.gson.JsonObject;
import nz.ac.wgtn.swen225.lc.domain.Chap;

public class Free implements Tile {
    private Chap chap;
    private String tile;
    private String item;

    public Free() {

    }

    public Free(JsonObject json) {
        this.tile = json.get("tile").getAsString();
        this.item = json.get("item").getAsString();
    }

    @Override
    public boolean traversable() {
        return true;
    }
}