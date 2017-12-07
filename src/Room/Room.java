package Room;

public abstract class Room {
    int roomNum;
    String name;
    int capability;

    public Room(int roomNum, String name, int capability) {
        this.roomNum = roomNum;
        this.name = name;
        this.capability = capability;
    }

    public boolean defend(){
        return true;
    }
}
