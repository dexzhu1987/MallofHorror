package Room;

import java.util.*;
import Character.*;

public abstract class Room {
    protected int roomNum;
    protected String name;
    protected int capability;
    protected List<GameCharacter> roomCharaters;

    public Room(int roomNum, String name, int capability) {
        this.roomNum = roomNum;
        this.name = name;
        this.capability = capability;
        roomCharaters = new ArrayList<>();
    }

    public boolean defend(){
        return true;
    }


    public int getRoomNum() {
        return roomNum;
    }

    public String getName() {
        return name;
    }

    public int getCapability() {
        return capability;
    }

    public void enter(GameCharacter character){
        roomCharaters.add(character);
    }

    public void leave(GameCharacter character){
        roomCharaters.remove(character);
    }

    public boolean isFull(){
        if (roomCharaters.size()>=capability){
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return name + " has " + roomCharaters ;
    }
}
