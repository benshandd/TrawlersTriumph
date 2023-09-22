package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.*;

public class TestMaze {
    int gridDimension = 15;
    TestTile[][] grid = new TestTile[gridDimension][gridDimension];

    public TestMaze(){
        Color c;
        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid[x].length; y++){
                if ((x + y) % 2 == 0){ c = new Color(95+(x*5), 158+(x*5), 160+(x*5)); }
                else {c = new Color(9+(y*5), 121+(y*5), 105+(y*5)); }
                grid[x][y] = new TestTile(x, y, c);
            }
        }
    }
}