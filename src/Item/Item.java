package Item;

import Room.*;
import Playable.*;
import Character.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    protected int itemNum;
    protected String name;

    public Item(int itemNum, String name) {
        this.itemNum = itemNum;
        this.name = name;

    }

    public int getItemNum() {
        return itemNum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name ;
    }

    /**
     * the effect that once the item is used. It takes two param. One is the player, who is using the item and the room, in which the item is used
     * @param player who is using it
     * @param room where it is used
     */
    public void effect(Playable player, Room room){

    }
}
