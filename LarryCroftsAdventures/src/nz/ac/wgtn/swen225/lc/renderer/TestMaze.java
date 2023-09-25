package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Wall;

import java.awt.*;

public class TestMaze {
    int gridDimension = 15;
    Tile[][] grid = new Tile[gridDimension][gridDimension];

    public TestMaze(){
        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid[x].length; y++){
                if (x % 2 == 0){
                    grid[x][y] = new Free();
                }
                else {
                    grid[x][y] = new Wall();
                }
            }
        }
    }
}