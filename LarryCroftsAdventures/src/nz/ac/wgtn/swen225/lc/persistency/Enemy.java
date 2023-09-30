package nz.ac.wgtn.swen225.lc.persistency;

import java.time.*;

public record Enemy(int x, int y, Direction direction, Instant lastDirectionChange) {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    
    public Enemy {
        if (lastDirectionChange == null) {
            lastDirectionChange = Instant.now();
        }
    }

    public Enemy move() {
        Instant now = Instant.now();
        long secondsElapsed = java.time.Duration.between(lastDirectionChange, now).getSeconds();

        if (secondsElapsed >= 5) {
            Direction newDirection = getRandomDirection();
            return new Enemy(x, y, newDirection, now);
        } else {
            switch (direction) {
                case UP:
                    return new Enemy(x, y - 1, direction, lastDirectionChange);
                case DOWN:
                    return new Enemy(x, y + 1, direction, lastDirectionChange);
                case LEFT:
                    return new Enemy(x - 1, y, direction, lastDirectionChange);
                case RIGHT:
                    return new Enemy(x + 1, y, direction, lastDirectionChange);
                default:
                    return this;
            }
        }
    }

    private Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        int randomIndex = (int) (Math.random() * directions.length);
        return directions[randomIndex];
    }
}
