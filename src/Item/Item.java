package Item;

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
}
