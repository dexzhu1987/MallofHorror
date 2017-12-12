package Item;

import Room.*;
import Playable.*;
import java.util.*;
import Character.*;
import Game.*;

public class Sprint extends Item {
    public Sprint() {
        super(7,"Sprint");
    }

    @Override
    public void effect(Playable player, Room room) {
        System.out.println("-------------------------------Sprint---------------------------------------");
        System.out.println("You have choosed Sprint");
        System.out.println("You can eacaped from this room to other any room");
        int roompicked = 0;
        boolean loop = false;
        do {
            loop=false;
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Please choose your room number that you want to go to");
                roompicked = input.nextInt();
                if (roompicked<0 || roompicked>6 ){
                    System.out.println("Please enter a valid room number");
                    loop=true;
                }
                if (roompicked == room.getRoomNum()){
                    System.out.println("You are already in the room, please select other number");
                    loop=true;
                }
            }
            catch (Exception e){
                System.out.println("Please enter a number");
                loop = true;
            }
        }
        while (loop);
               String charselect = "";
        boolean selectedCorrect = false;
        HashSet<GameCharacter> existedCharacters = room.existChracterForThatPlayer(player);
        do {
            System.out.println(player + " please choose your characters to Room " + roompicked);
            Scanner input = new Scanner(System.in);
            System.out.println("In the list: " + room.existChracterForThatPlayer(player));
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
        System.out.println(selectedCharacter + " has lefted " + room.getName());
        affectedRoomNumber = roompicked;
        affectedGameCharacter = selectedCharacter;

    }

    public void afterEffect(GameBroad gameBroad){
        System.out.println("--------------------------------Sprint-----------------------------------");
        System.out.println("Due to Sprint has been used, aftereffect(entering destination is triggerd)");
        if (gameBroad.matchRoom(affectedRoomNumber).isFull()){
            gameBroad.matchRoom(4).enter(affectedGameCharacter);
            System.out.println("Due to " + gameBroad.matchRoom(affectedRoomNumber).getName() + " is full, " + affectedGameCharacter + " will go to Parking instead.");
            System.out.println("------------------------------------------------------------------------------");
        }else {
            gameBroad.matchRoom(affectedRoomNumber).enter(affectedGameCharacter);
            System.out.println(affectedGameCharacter + " has entered " + gameBroad.matchRoom(affectedRoomNumber).getName());
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        GameBroad gb = new GameBroad(1);
        Room r1 = gb.matchRoom(4);
        Playable p1 = gb.getPlayers().get(0);

        Item item = new Sprint();
        p1.setColor("RED");
        r1.enter(p1.getGameCharacters().get(0));
        r1.enter(p1.getGameCharacters().get(1));

        item.effect(p1, r1);
        item.afterEffect(gb);

    }


}
