package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

import java.time.Duration;
import java.time.Instant;
import java.util.ServiceLoader;

/**
 * Represents an enemy in the game.
 */
public class AutoActor {
	private int x;
	private int y;
	private Direction direction;
	private Instant lastDirectionChange;
	private Chap chap;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public AutoActor(int x, int y, Direction direction, Instant lastDirectionChange, Chap chap) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.lastDirectionChange = lastDirectionChange;
		this.chap = chap;
	}

	public AutoActor() {
	}

	public AutoActor move(Tile[][] grid) {
		Instant now = Instant.now();
		Duration duration = Duration.between(lastDirectionChange, now);
		long secondsElapsed = duration.getSeconds();

		if (secondsElapsed >= 3) {
			Direction newDirection = calculateDirectionTowardsChap();

			int newX = x;
			int newY = y;

			switch (newDirection) {
				case UP:
					newY = y - 1;
					break;
				case DOWN:
					newY = y + 1;
					break;
				case LEFT:
					newX = x - 1;
					break;
				case RIGHT:
					newX = x + 1;
					break;
			}

			if (isValidMove(grid, newX, newY)) {
				return new AutoActor(newX, newY, newDirection, Instant.now(), chap);
			} else {
				return new AutoActor(x, y, getRandomDirection(), Instant.now(), chap);
			}
		} else {
			return new AutoActor(x, y, direction, lastDirectionChange, chap);
		}
	}

	private Direction calculateDirectionTowardsChap() {
		int chapX = chap.getX();
		int chapY = chap.getY();
		int xDiff = chapX - x;
		int yDiff = chapY - y;

		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			return xDiff > 0 ? Direction.RIGHT : Direction.LEFT;
		} else {
			return yDiff > 0 ? Direction.DOWN : Direction.UP;
		}
	}

	private boolean isValidMove(Tile[][] grid, int x, int y) {
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
			return grid[x][y] instanceof Free;
		}
		return false;
	}

	private Direction getRandomDirection() {
		Direction[] directions = Direction.values();
		int randomIndex = (int) (Math.random() * directions.length);
		return directions[randomIndex];
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * Loads custom actors using a plugin-based design.
	 */
	public static class PluginLoader {
		private static final String PLUGIN_PACKAGE = "nz.ac.wgtn.swen225.lc.plugins";
		private ServiceLoader<AutoActor> autoActorLoader;

		public PluginLoader() {
			autoActorLoader = ServiceLoader.load(AutoActor.class);
		}

		/**
		 * Get a list of loaded custom actors.
		 *
		 * @return Iterable of custom actors.
		 */
		public Iterable<AutoActor> getCustomActors() {
			return autoActorLoader;
		}
	}
}
