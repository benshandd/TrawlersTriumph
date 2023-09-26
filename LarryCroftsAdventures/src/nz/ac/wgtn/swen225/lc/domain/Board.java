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
        try {
            board = persistency.loadGame("LarryCroftsAdventures/levels/level1.json");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while loading the game: " + e.getMessage());
        }
        chap = new Chap(this);
        board[7][7] = new Free(chap);
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

        chap = new Chap(this);
        testBoard[7][7] = new Free(chap);

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
