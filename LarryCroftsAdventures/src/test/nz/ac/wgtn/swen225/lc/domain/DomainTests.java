package test.nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.*;
import org.junit.jupiter.api.*;

public class DomainTests {
    private static Board board;
    private static Chap chap;

    /**
     * Initialise the board and player.
     */
    @BeforeAll
    public static void init() {
        board = new Board(1, null);
        chap = board.getChap();
    }
}
