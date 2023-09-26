package nz.ac.wgtn.swen225.lc.domain;

import java.io.FileNotFoundException;

import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.Door;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Wall;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

public class Board {
    private static final int SIZE = 15;
    private Tile[][] board;
    private Chap chap;

    public Board() {
        Persistency persistency = new Persistency();
            Tile[][] mazeObject = new Tile[15][15];
            try {
                board = persistency.loadGame("LarryCroftsAdventures" + File.separator + "levels" + File.separator +"level1.json", mazeObject);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //board = testBoard(); should call Persistency class
    }

    private Tile[][] testBoard() {
        Tile[][] testBoard = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int rand = (int) (Math.random() * 5);
                testBoard[i][j] = switch (rand) {
                    case 0 -> new Wall();
                    case 1 -> new Door(Key.Colour.YELLOW);
                    default -> new Free();
                };
            }
        }
        int x = tempRand();
        int y = tempRand();
        while (!testBoard[x][y].getClass().equals(Free.class)) {
            x = tempRand();
            y = tempRand();
        }
        Free tile = (Free) testBoard[x][y];
        chap = new Chap(this);
        tile.addChap(chap);

        return testBoard;
    }

    private int tempRand() {
        return (int) (Math.random() * 10);
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public Tile[][] getTiles() {
        return board;
    }

    public Chap getChap() {
        return chap;
    }
}
