import Dice.PairofDice;
import Playable.Playable;
import Playable.Player;
import Room.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        //deciding how many players phase;
        boolean keeploop;
        int numPlayers = 0;
        do {
            try {
                do {
                    keeploop = false;
                    Scanner input = new Scanner(System.in);
                    System.out.println("Welcome to Mall of Horror, Please select how many players (4 to 6)?");
                    numPlayers = input.nextInt();
                    if (numPlayers > 6 || numPlayers < 4) {
                        System.out.println("Please enter a number between 4 to 6");
                    }
                }
                while (numPlayers > 6 || numPlayers < 4);

            } catch (Exception e) {
                System.out.println("Please enter a number.");
                keeploop = true;
            }
        }
        while (keeploop);


        //lauching the a new game broad with decided players and set up their names
        GameBroad gameBroad = new GameBroad(numPlayers);
        System.out.println("There will be " + numPlayers + " players in this game.");
        for (int i = 0; i < numPlayers; i++) {
            Scanner input = new Scanner(System.in);
            System.out.println("Player " + gameBroad.getPlayers().get(i).getColor() + " please set up your name: ");
            String name = input.nextLine();
            gameBroad.getPlayers().get(i).setName(name);
        }
        System.out.println();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(gameBroad.getPlayers().get(i));
        }
        System.out.println();


        //Pre-game phase, setting up charater locations
        PairofDice pairofDice = new PairofDice();
        String precharselect = "";
        int preroomselect = 0;
        for (int i = 0; i < gameBroad.totalCharatersRemain(); i += 4) {
            for (int q = 0; q < gameBroad.getPlayers().size(); q++) {
                pairofDice.rollDieOne();
                pairofDice.rollDieTwo();
                System.out.println(gameBroad.getPlayers().get(q) + " get " + pairofDice.getDieOneFace() + " and "
                        + pairofDice.getDieTwoFace());
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Please select your charater (" + gameBroad.getPlayers().get(q).getCharactersselectstr()
                            + ")");
                    precharselect = input.nextLine();

                }
                while (!charactercorrectselect(gameBroad.getPlayers().get(q), precharselect));

                do {
                    try {
                        Scanner input = new Scanner(System.in);
                        System.out.println(precharselect + " will go to Room number? (Either " + pairofDice.getDieOneFace()
                                + " or " + pairofDice.getDieTwoFace() + ")");
                        preroomselect = input.nextInt();
                        if (preroomselect != pairofDice.getDieOneFace() && preroomselect != pairofDice.getDieTwoFace()) {
                            System.out.println("Please select either " + pairofDice.getDieOneFace() +
                                    " or " + pairofDice.getDieTwoFace());
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter a number");
                    }
                    String yes = "";
                    if (gameBroad.matchRoom(preroomselect).isFull()) {
                        do {
                            do {
                                Scanner input = new Scanner(System.in);
                                System.out.println(gameBroad.matchRoom(preroomselect).getName() +
                                        " is Full, are you sure? (Your character will be asigned to Parking instead)(choose y/n)");
                                yes = input.nextLine();
                                if (!yes.equalsIgnoreCase("n") && !yes.equalsIgnoreCase("y")) {
                                    System.out.println("Please enter y or n");
                                }
                            }
                            while (!yes.equalsIgnoreCase("n") && !yes.equalsIgnoreCase("y"));
                            if (yes.equalsIgnoreCase("n")) {
                                try {
                                    Scanner input = new Scanner(System.in);
                                    System.out.println(precharselect + " will go to Room number? (Either " + pairofDice.getDieOneFace()
                                            + " or " + pairofDice.getDieTwoFace() + ")");
                                    preroomselect = input.nextInt();
                                    if (preroomselect != pairofDice.getDieOneFace() && preroomselect != pairofDice.getDieTwoFace()) {
                                        System.out.println("Please select either " + pairofDice.getDieOneFace() +
                                                " or " + pairofDice.getDieTwoFace());
                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter a number");
                                }
                            }
                        }
                        while (yes.equalsIgnoreCase("n"));
                    }
                }
                while (preroomselect != pairofDice.getDieOneFace() && preroomselect != pairofDice.getDieTwoFace());

            if (gameBroad.matchRoom(preroomselect).isFull()){
                gameBroad.matchRoom(4).enter(gameBroad.getPlayers().get(q).selectchoose(precharselect));
                gameBroad.getPlayers().get(q).selectchooseremove(precharselect);
            }
            else{
                gameBroad.matchRoom(preroomselect).enter(gameBroad.getPlayers().get(q).selectchoose(precharselect));     ;
                gameBroad.getPlayers().get(q).selectchooseremove(precharselect);
            }
            }
        }

        gameBroad.printRooms();
        System.out.println();
        //-------------------pregame --select room phase ends---------------------------------
        //---------------------gamephase-----------------------------
//        while (gameBroad.totalCharatersRemain()>4){
        // -----------parking serch--------------------------
        if (gameBroad.matchRoom(4).isEmpty()){
            System.out.println("Due to Parking is empty, no searching will be performed");
        }
        else {
            HashSet<Playable> searchteam = new HashSet<>();
            searchteam = gameBroad.WhoCan(gameBroad.matchRoom(4).existCharacterColor());
            for (Playable teammember: searchteam){
                System.out.print(teammember + ", ");
            }
            System.out.println(" please vote who can search");
            List<String> votes = new ArrayList<>();
            String vote = "";
            for (Playable teammember: searchteam){
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.println(teammember + " please vote color in the list:" + searchteam );
                    vote=input.nextLine();
                    if (!votecorrectselect(searchteam,vote)){
                        System.out.println("Please select correct color.");
                    }
                    else {
                        votes.add(vote);
                    }
                }
                while (!votecorrectselect(searchteam,vote));
            }


        }







        }




//    }

    public static boolean charactercorrectselect(Playable player, String selectchar){
        for (int i=0; i<player.getCharactersselect().size(); i++){
            if (selectchar.equalsIgnoreCase(player.getCharactersselect().get(i).getName())){
                return true;
            }
        }
      return false;
    }

    public static boolean votecorrectselect(HashSet<Playable> voteplayerlist, String votecolor){
        for (Playable player: voteplayerlist){
            if (votecolor.equalsIgnoreCase(player.getColor())){
                return true;
            }
        }
        return false;
    }







}
