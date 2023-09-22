package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.*;

public class TestMaze {
    TestTile[][] grid = new TestTile[9][9];

    public TestMaze(){
        Color c;
        for (int x = 0; x < 9; x++){
            for (int y = 0; y < 9; y++){
                if ((x + y) % 2 == 0){ c = new Color(95+(x*10), 158+(x*10), 160+(x*10)); }
                else {c = new Color(9+(y*10), 121+(y*10), 105+(y*10)); }
                grid[x][y] = new TestTile(x, y, c);
            }
        }
    }
}