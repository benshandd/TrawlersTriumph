package nz.ac.wgtn.swen225.lc.renderer;

import java.util.Vector;

/**
 * Reprsents the position of the focus area on the maze grid
 */
public class Camera {
    private double x;
    private double y;
    private double w;
    private double h;
    private double goalX;
    private double goalY;

    public Camera(double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public double getWidth() { return w; }
    public double getHeight() { return h; }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
