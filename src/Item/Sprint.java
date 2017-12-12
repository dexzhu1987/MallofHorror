package Item;

import Room.*;
import Playable.*;

import java.util.*;
import Character.*;

public class Sprint extends Item {
    public Sprint() {
        super(7,"Sprint");
    }

    @Override
    public void effect(Playable player, Room room) {
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
                }
                if (roompicked == room.getRoomNum()){
                    System.out.println("You are already in the room, please select other number");
                }
            }
            catch (Exception e){
                System.out.println("Please enter a number");
                loop = true;
            }
        }
        while (loop || (roompicked<0 || roompicked>6 || roompicked == room.getRoomNum()) );
        System.out.println(player + " please choose your characters to Room " + roompicked + ": " +
                room.getName() );
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
        Room leavingroom = room;
        leavingroom.leave(selectedCharacter);
        if (leavingroom.isFull()){
            .enter(selectedCharacter);
            System.out.println("Due to room is full, character is moved to Parking instead");
        }else {
            gameBroad.matchRoom(startplayerroomnumber).enter(selectedCharacter);
        }
        ;
        System.out.println(gameBroad.matchGameCharacter(gameBroad.getPlayers().get(startplayer),charselect) + " leave " +
                leavingroom.getName() + " enter " + gameBroad.matchRoom(startplayerroomnumber).getName());
        System.out.println();
    }




}
