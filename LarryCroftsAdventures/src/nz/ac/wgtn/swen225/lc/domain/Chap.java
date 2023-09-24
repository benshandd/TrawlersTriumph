package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.exceptions.IllegalMove;
import nz.ac.wgtn.swen225.lc.domain.exceptions.InventoryFull;
import nz.ac.wgtn.swen225.lc.domain.items.Item;

public class Chap {
    private static final int MAX_INVENTORY = 8;
    private final Item[][] inventory = new Item[2][4];
    private int x;
    private int y;

    public Chap() {
    }

    public void move(Direction direction) throws IllegalMove {
        switch (direction) {
            case UP -> throw new IllegalMove("Temp");
            case DOWN -> throw new IllegalMove("Temp");
            case LEFT -> throw new IllegalMove("Temp");
            case RIGHT -> throw new IllegalMove("Temp");
            default -> throw new IllegalMove("Invalid move direction");
        }
    }

    public Item addItem(Item item) throws InventoryFull {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] == null) {
                    inventory[i][j] = item;
                    return inventory[i][j];
                }
            }
        }
        throw new InventoryFull();
    }

    public boolean removeItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] == item) {
                    inventory[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}
}
