package nz.ac.wgtn.swen225.lc.domain.tiles;

public class Wall implements Tile {
    public Wall() {
    }

    @Override
    public boolean traversable() {
        return false;
    }
}