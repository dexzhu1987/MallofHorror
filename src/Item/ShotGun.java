package Item;

import Playable.*;
import Room.*;

public class ShotGun extends Item {
    public ShotGun() {
        super(4, "ShotGun");
    }

    public void effect(Playable player, Room room) {
        System.out.println("You have choosed ShortGun");
        System.out.println("ShortGun can kill two zomebie in the room");
        int orignalZombieNumber =room.getCurrentZombienumber();
        room.zombieKilled();
        room.zombieKilled();
        System.out.println("Zombies number has dropped from " + orignalZombieNumber + " to "  + room.getCurrentZombienumber());
    }
}
