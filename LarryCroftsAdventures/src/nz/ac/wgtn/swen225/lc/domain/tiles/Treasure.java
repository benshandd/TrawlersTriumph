package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;

/**
 * Represents a tile that has a collectible treasure.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class Treasure extends Free {
	/**
	 * Create a new treasure tile.
	 */
	public Treasure(int x, int y) {
		super(x, y);
	}

	/**
	 * Add a treasure to the treasure counter.
	 */
	@Override
	public boolean performTileAction(Chap chap) {
		chap.getBoard().getAudioUnit().playFishSFX();
		int treasure = chap.getPlayerTreasureCount();
		chap.addTreasure();
		assert treasure + 1 == chap.getPlayerTreasureCount();
		return true;
	}

	@Override
	public Tile clone() throws CloneNotSupportedException {
		Treasure tile = (Treasure) super.clone();
		return tile;
	}
}
