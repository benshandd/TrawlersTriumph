package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

import java.time.Duration;
import java.time.Instant;

/**
 * Represents an enemy in the game.
 */
public class Enemy {
	private int x;
	private int y;
	private Direction direction;
	private Instant lastDirectionChange;
	private Chap chap;

	/**
	 * Enum representing possible movement directions for the enemy.
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	/**
	 * Constructs an enemy with the specified parameters.
	 *
	 * @param x                   The initial x-coordinate.
	 * @param y                   The initial y-coordinate.
	 * @param direction           The initial movement direction.
	 * @param lastDirectionChange The instant when the direction was last changed.
	 * @param chap                The player that the enemy is following.
	 */
	public Enemy(int x, int y, Direction direction, Instant lastDirectionChange, Chap chap) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.lastDirectionChange = lastDirectionChange;
		this.chap = chap;
	}

	public Enemy() {
	}

	/**
	 * Moves the enemy on the game grid based on its current state.
	 *
	 * @param grid The game grid.
	 * @return A new enemy object after the movement.
	 */
	public Enemy move(Tile[][] grid) {
		Instant now = Instant.now();
		Duration duration = Duration.between(lastDirectionChange, now);
		long secondsElapsed = duration.getSeconds();

		// Move every 3 seconds
		if (secondsElapsed >= 3) {
			// Calculate new direction towards Chap in which to go
			Direction newDirection = calculateDirectionTowardsChap();

			int newX = x;
			int newY = y;

			// Update the coords based on the new direction
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

			// Valid move? Then create a new Enemy object with the updated state
			if (isValidMove(grid, newX, newY)) {
				return new Enemy(newX, newY, newDirection, Instant.now(), chap);
			} else {
				// Not a valid move?, generate a different direction and create a new Enemy
				// object
				return new Enemy(x, y, getRandomDirection(), Instant.now(), chap);
			}
		} else {
			// Keep current state until, it's time to next move
			return new Enemy(x, y, direction, lastDirectionChange, chap);
		}
	}

	/**
	 * Calculates the movement direction for the enemy to approach the player
	 * character (Chap).
	 *
	 * @return The calculated direction.
	 */
	private Direction calculateDirectionTowardsChap() {
		int chapX = chap.getX();
		int chapY = chap.getY();
		int xDiff = chapX - x;
		int yDiff = chapY - y;

		// Compare the absolute differences in x and y to determine the direction
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			return xDiff > 0 ? Direction.RIGHT : Direction.LEFT;
		} else {
			return yDiff > 0 ? Direction.DOWN : Direction.UP;
		}
	}

	/**
	 * Checks if a move to the specified coordinates is valid on the game grid.
	 *
	 * @param grid The game grid.
	 * @param x    The x-coordinate of the potential move.
	 * @param y    The y-coordinate of the potential move.
	 * @return True if the move is valid, false otherwise.
	 */
	private boolean isValidMove(Tile[][] grid, int x, int y) {
		// Check if the coordinates are within the bounds of the grid and the
		// destination is a Free tile
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
			return grid[x][y] instanceof Free;
		}
		return false;
	}

	/**
	 * Generates a random movement direction for the enemy.
	 *
	 * @return A randomly chosen direction.
	 */
	private Direction getRandomDirection() {
		// Get all possible directions and choose a random one
		Direction[] directions = Direction.values();
		int randomIndex = (int) (Math.random() * directions.length);
		return directions[randomIndex];
	}

	/**
	 * Gets the x-coord of the enemy.
	 *
	 * @return The x-coord.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y-coord of the enemy.
	 *
	 * @return The y-coord.
	 */
	public double getY() {
		return y;
	}

}
