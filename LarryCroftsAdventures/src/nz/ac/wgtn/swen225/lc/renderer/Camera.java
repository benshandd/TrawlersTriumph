package nz.ac.wgtn.swen225.lc.renderer;

import java.util.Vector;

/**
 * Reprsents the position of the focus area on the maze grid
 */
public class Camera {
    private int x;
    private int y;
    private int w;
    private int h;

    public Camera(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return w; }
    public int getHeight() { return h; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // Converts world x coord to camera x coord
    public int worldXToCameraX(int worldX){
        return worldX - x;
    }

    // Converts world y coord to camera y coord
    public int worldYToCameraY(int worldY){
        return worldY - y;
    }
}
