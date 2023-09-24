package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Item;

import java.util.ArrayList;

public class Chap {
    private static final int MAX_INVENTORY = 8;
    private final ArrayList<Item> inventory;
    private int x;
    private int y;

    public Chap() {
        inventory = new ArrayList<>();
    }

    ;

    public void move(Direction direction) throws IllegalMove {
        switch (direction) {
            case UP -> throw new IllegalMove("Temp");
            case DOWN -> throw new IllegalMove("Temp");
            case LEFT -> throw new IllegalMove("Temp");
            case RIGHT -> throw new IllegalMove("Temp");
            default -> throw new IllegalMove("Invalid move direction");
        }
    }

    public boolean addItem(Item item) throws InventoryFull {
        if (inventory.size() >= MAX_INVENTORY) {
            throw new InventoryFull();
        }
        return inventory.add(item);
    }

    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}
}
