package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class Renderer {

    TestMaze maze = new TestMaze();
    Camera camera = new Camera();
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
        int tileWidth = mazePanelWidth/maze.grid.length;
        int tileHeight = mazePanelHeight/maze.grid[0].length;

        // Set background colour
        g.setColor(new Color(232, 220, 202));
        g.fillRect(0, 0, mazePanelWidth, mazePanelHeight);

        int clampedValue = Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = mazePanelWidth/2 - (clampedValue*maze.grid.length/2);
        int distanceFromTopBorder = mazePanelHeight/2 - (clampedValue*maze.grid[0].length/2);

        for (int x = 0; x < maze.grid.length; x++){
            for (int y = 0; y < maze.grid[x].length; y++){
                g.setColor(Color.BLACK);
                g.drawRect(x*clampedValue + distanceFromLeftBorder, y*clampedValue + distanceFromTopBorder, clampedValue, clampedValue);
                g.setColor(maze.grid[x][y].color);
                g.fillRect(x*clampedValue + distanceFromLeftBorder, y*clampedValue + distanceFromTopBorder, clampedValue, clampedValue);
            }
        }
    }
}
