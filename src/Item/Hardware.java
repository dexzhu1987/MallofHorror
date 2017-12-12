package Item;

import Playable.*;
import Room.*;
import Game.*;

public class Hardware extends Item {
    public Hardware() {
        super(5, "Hareware");
    }

    public void effect(Playable player, Room room) {
        System.out.println("You have choosed Hareware");
        System.out.println("The defend of this room has temporarily increased by 1");
        room.zombieKilled();
        System.out.println("A zomie has temporarly moved to somewhere else");
        affectedRoomNumber = room.getRoomNum();

    }

    public void afterEffect(GameBroad gameBroad){
        System.out.println("Due to Hareware has been used, aftereffect is triggerd");
        gameBroad.matchRoom(affectedRoomNumber).zombieApproached();
        System.out.println("The zombie left has returned");
        System.out.println("------------------------------------------------------------------------------");
    }


}
