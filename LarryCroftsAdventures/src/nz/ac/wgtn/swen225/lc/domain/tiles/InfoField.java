package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents a tile that has an information message pop up when the player
 * steps onto it.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class InfoField extends Free {
	/**
	 * Create a new information field tile.
	 */
	public InfoField(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean performTileAction(Chap chap) {
		return false;
	}

	@Override
	public Tile clone() throws CloneNotSupportedException {
		InfoField tile = (InfoField) super.clone();
		return tile;
	}
}
