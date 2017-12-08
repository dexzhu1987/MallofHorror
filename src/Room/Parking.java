package Room;

import Item.Item;

public class Parking extends Room {
    public Parking() {
        super(4, "Parking", 99);
    }

    public Item search(){
        Item[] x=new Item[3];
        return x[0];
    }

    @Override
    public boolean defend(){
        return false;
    }
}
