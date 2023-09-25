package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class Renderer {

    TestMaze maze = new TestMaze();
    public Camera camera = new Camera(3, 3, 9, 9);
    public Renderer(){

    }

    /**
     * Draws each tile to form maze
     * @param mazePanel
     * @param g
     */
    public void draw(JPanel mazePanel, Graphics g){
        int mazePanelWidth = mazePanel.getWidth();
        int mazePanelHeight = mazePanel.getHeight();
        int tileWidth = mazePanelWidth/ camera.getWidth();
        int tileHeight = mazePanelHeight/camera.getHeight();

        // Set background colour
        g.setColor(new Color(232, 220, 202));
        g.fillRect(0, 0, mazePanelWidth, mazePanelHeight);

        int clampedValue = Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = mazePanelWidth/2 - (clampedValue* camera.getWidth()/2);
        int distanceFromTopBorder = mazePanelHeight/2 - (clampedValue* camera.getHeight()/2);

        for (int x = camera.getX(); x < camera.getX() + camera.getWidth(); x++){
            for (int y = camera.getY(); y < camera.getY() + camera.getHeight(); y++){
                int cameraX = camera.worldXToCameraX(x);
                int cameraY = camera.worldYToCameraY(y);
                //System.out.println(cameraX + " " + cameraY);
                g.setColor(Color.BLACK);
                g.drawRect(cameraX*clampedValue + distanceFromLeftBorder, cameraY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue);
                g.setColor(maze.grid[x][y].color);
                g.fillRect(cameraX*clampedValue + distanceFromLeftBorder, cameraY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue);
            }
        }
    }

    public void moveCameraLeft(){ camera.setX(camera.getX()-1); }
    public void moveCameraRight(){ camera.setX(camera.getX()+1); }
    public void moveCameraUp(){ camera.setY(camera.getY()-1); }
    public void moveCameraDown(){ camera.setY(camera.getY()+1); }

}
