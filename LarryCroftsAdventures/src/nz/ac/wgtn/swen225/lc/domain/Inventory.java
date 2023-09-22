package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Item;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> items;

    /**
     * Create a new empty Inventory
     */
    public Inventory() {
        items = new ArrayList<>();
    }
}
