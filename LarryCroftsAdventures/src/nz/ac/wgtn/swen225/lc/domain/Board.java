package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.Door;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Wall;

public class Board {
    private static final int SIZE = 15;
    private Tile[][] board;

    public Board() {
        board = testBoard();// should call Persistency class
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
        return testBoard;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public Tile[][] getTiles() {
        return board;
    }
}
