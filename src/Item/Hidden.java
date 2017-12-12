package Item;

import java.util.*;
import Character.*;
import Room.*;
import Game.*;
import Playable.*;

public class Hidden extends Item {
    public Hidden() {
        super(6, "Hidden");
    }

    public void effect(Playable player, Room room) {
        System.out.println("-------------------------------Hidden------------------------------------------");
        System.out.println("You have choosed Hidden");
        System.out.println("You will be hiding during the whole process, (you will not be eaten nor can join the voting)");
        System.out.println("Cosider your character temporaly leaving the room");
        int roompicked = 0;
        System.out.println(player + "pleaese choose the character you want to hide. " );
        String charselect = "";
        boolean selectedCorrect = false;
        HashSet<GameCharacter> existedCharacters = room.existChracterForThatPlayer(player);
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("In the list: " + existedCharacters);
            charselect = input.nextLine();
            for (GameCharacter character: existedCharacters){
                if (charselect.equalsIgnoreCase(character.getName())){
                    selectedCorrect = true;
                }
            }

            if (!selectedCorrect){
                System.out.println("Please select correct character");
            }
        }
        while (!selectedCorrect);
        GameCharacter selectedCharacter = new ToughGuy();
        for (GameCharacter character: existedCharacters){
            if (charselect.equalsIgnoreCase(character.getName())){
                selectedCharacter = character;
            }
        }
        room.leave(selectedCharacter);
        System.out.println(selectedCharacter + " temporarily lefted " + room.getName());
        affectedRoomNumber = room.getRoomNum();
        affectedGameCharacter = selectedCharacter;
    }

    public void afterEffect(GameBroad gameBroad){
        System.out.println("--------------------------------Hidden-----------------------------------------");
        System.out.println("Due to Hidden has been used, aftereffect(entering back to the room is triggerd)");
        gameBroad.matchRoom(affectedRoomNumber).enter(affectedGameCharacter);
        System.out.println(affectedGameCharacter + " has entered back to " + gameBroad.matchRoom(affectedRoomNumber).getName());
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        GameBroad gb = new GameBroad(1);
        Room r1 = gb.matchRoom(4);
        Playable p1 = gb.getPlayers().get(0);

        Item item = new Hidden();
        p1.setColor("RED");
        r1.enter(p1.getGameCharacters().get(0));
        r1.enter(p1.getGameCharacters().get(1));

        item.effect(p1, r1);
        item.afterEffect(gb);

    }

}
