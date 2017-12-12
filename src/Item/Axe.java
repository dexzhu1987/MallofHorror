package Item;

import Playable.*;
import Room.*;

public class Axe extends Item {
    public Axe() {
        super(3, "Axe");
    }


    @Override
    public void effect(Playable player, Room room) {
        System.out.println("-----------------------------Axe------------------------------------------");
        System.out.println("You have choosed Axe");
        System.out.println("Axe can kill one zomebie in the room");
        int orignalZombieNumber =room.getCurrentZombienumber();
        room.zombieKilled();
        System.out.println("Zombies number has dropped from " + orignalZombieNumber + " to "  + room.getCurrentZombienumber());
        System.out.println("------------------------------------------------------------------------------");

    }
}
