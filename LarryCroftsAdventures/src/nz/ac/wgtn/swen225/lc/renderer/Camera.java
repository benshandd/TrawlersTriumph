package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.Chap;

/**
 * Represents the position of the focus area on the maze grid
 *
 * @author Alex Manning (300600549)
 */
public class Camera {
    private double x;
    private double y;
    private final double w;
    private final double h;
    private State state;

    public enum State {
        IDLE, UP, DOWN, LEFT, RIGHT
    }

    public Camera(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.state = State.IDLE;
    }

    /**
     * Update camera position depending on state
     *
     * @param chap Chap to base camera stopping condition from
     */
    public void updateCameraPosition(Chap chap) {
        double distance = 0.125;
        switch (state) {
            case IDLE -> {
            }
            case UP -> y -= distance;
            case DOWN -> y += distance;
            case LEFT -> x -= distance;
            case RIGHT -> x += distance;
            default -> {
            }
        }
        // Once camera reaches point where boat is in center of focus area, stop moving
        if ((getX() == chap.getTile().getX() - (int) (w / 2)) && (y == chap.getTile().getY() - (int) (w / 2))) {
            state = State.IDLE;
        }
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public State getState() {
        return state;
    }

    public void setState(State s) {
        state = s;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
