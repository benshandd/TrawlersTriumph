package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.renderer.AudioUnit;

/**
 * Represents a tile that has a collectible key.
 * 
 * @author Anthony Kendrew (300607402)
 */
public class KeyTile extends Free {
	private final Key.Colour colour;

	/**
	 * Create a new key tile.
	 * 
	 * @param colour the colour of the collectible key
	 */
	public KeyTile(Key.Colour colour, int x, int y) {
		super(x, y);
		if (colour == null) {
			throw new IllegalArgumentException();
		}
		this.colour = colour;
	}

	/**
	 * Add a key to the player's inventory.
	 */
	@Override
	public boolean performTileAction(Chap chap) {
		chap.getBoard().getAudioUnit().playSound(AudioUnit.AudioClip.KEYCOLLECT);
		return chap.addItem(new Key(colour));
	}

	/**
	 * Get the colour.
	 * 
	 * @return the colour of the key
	 */
	public Key.Colour getColour() {
		return colour;
	}

	@Override
	public Tile clone() throws CloneNotSupportedException {
		KeyTile tile = (KeyTile) super.clone();
		return tile;
	}
}
