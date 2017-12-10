import Dice.PairofDice;
import Item.Item;
import Playable.*;;
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
                    if (gameBroad.matchRoom(preroomselect).isFull() &&
                            ((preroomselect == pairofDice.getDieOneFace()) || (preroomselect == pairofDice.getDieTwoFace()))) {
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
        //-------------------pregame -- players get starter item --------------------
        gameBroad.getItemDeck().shuffle();
        for (int i=0; i<gameBroad.getPlayers().size(); i++)
        {
            gameBroad.getPlayers().get(i).getItem(gameBroad.getItemDeck().deal());
        }

        //---------------------gamephase-----------------------------
//        while (gameBroad.totalCharatersRemain()>4){
        // -----------parking search--------------------------
        if (gameBroad.matchRoom(4).isEmpty() || gameBroad.getItemDeck().getItemsDeck().size()<3){
            System.out.println("Due to Parking is empty, no searching will be performed");
        }
        else {
            //voteing begins
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
                    if (!colorcorrectselect(searchteam,vote)){
                        System.out.println("Please select correct color.");
                    }
                    else {
                        votes.add(vote);
                    }
                }
                while (!colorcorrectselect(searchteam,vote));
            }
            gameBroad.matchRoom(4).resetVoteResult();
            gameBroad.matchRoom(4).voteResultAfterVote(votes);
            System.out.println("Voting results: " + gameBroad.matchRoom(4).getCurrentVoteResult());
            //using Threat to change result;
            if (teamHasThreat(searchteam)){
                String yes = "";
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Voting result can be changed by item THREAT, anyone want to change the result?(y/n)");
                    yes = input.nextLine();
                    if (!yes.equalsIgnoreCase("n") && !yes.equalsIgnoreCase("y")) {
                        System.out.println("Please enter y or n");
                    }
                }
                while (!yes.equalsIgnoreCase("n") && !yes.equalsIgnoreCase("y"));
                if (yes.equalsIgnoreCase("y")){
                    String quitThreatused = "";
                    do {
                        int numThreat = 0;
                        for (Playable teammember: searchteam){
                            boolean loop = false;
                            do {
                                loop=false;
                                try {
                                    Scanner input = new Scanner(System.in);
                                    System.out.println(teammember + " please select how many THREAT you want use? from 0 to " + teammember.threatNum() );
                                    numThreat = input.nextInt();
                                    if (numThreat<0 || numThreat>teammember.threatNum()){
                                        System.out.println("Please enter a valid amount");
                                    }
                                }
                                catch (Exception e){
                                    System.out.println("Please enter a number");
                                    loop = true;
                                }
                            }
                            while (loop || (numThreat<0 || numThreat>teammember.threatNum()) );
                            gameBroad.matchRoom(4).voteResultAfterItem(teammember.getColor(),numThreat);
                        }
                        System.out.println("Result after the THREAT used: " + gameBroad.matchRoom(4).getCurrentVoteResult());
                        do {
                            Scanner input = new Scanner(System.in);
                            System.out.println("Please confirm no more THREAT will be used (y/n)");
                            quitThreatused = input.nextLine();
                            if (!quitThreatused.equalsIgnoreCase("n") && !quitThreatused.equalsIgnoreCase("y")) {
                                System.out.println("Please enter y or n");
                            }
                        }
                       while (!quitThreatused.equalsIgnoreCase("n") && !quitThreatused.equalsIgnoreCase("y"));
                    }
                    while (quitThreatused.equalsIgnoreCase("y"));
                }
            }
            //result print
            if (gameBroad.matchRoom(4).winner().equals("TIE")){
                System.out.println("Result is TIE");
                System.out.println("No Searching will be performed");
            }
            else {
                String winnercolor = gameBroad.matchRoom(4).winner();
                System.out.println("Winner is " + gameBroad.matchPlayer(winnercolor));
                String ok="";
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.println(gameBroad.matchPlayer(winnercolor) + " searched the parking and " +
                            "found below items(only winning player can see the result and arrange items),please type OK to move to next step)");
                    ok = input.nextLine();
                }
                while (!ok.equalsIgnoreCase("ok"));

                gameBroad.getItemDeck().shuffle();
                Item item1 =  gameBroad.getItemDeck().deal();
                Item item2 =  gameBroad.getItemDeck().deal();
                Item item3 =  gameBroad.getItemDeck().deal();
                List<Item> itemtemplist = new ArrayList<>();
                itemtemplist.add(item1);
                itemtemplist.add(item2);
                itemtemplist.add(item3);
                System.out.println("You get 1."+ itemtemplist.get(0) +", 2." + itemtemplist.get(1) +" and 3." +
                        itemtemplist.get(2) );
                int itemselect = 0;
                boolean loop =false;
                do {
                    try {
                        loop =false;
                        Scanner input= new Scanner(System.in);
                        System.out.println("Please select the item you want to keep (1,2,or 3)" );
                        itemselect = input.nextInt();
                        if (itemselect!=1 && itemselect !=2 && itemselect!=3){
                            System.out.println("Please select (1, 2, or 3)");
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please enter a number");
                        loop = true;
                    }
                }
                while (loop || (itemselect!=1 && itemselect !=2 && itemselect!=3));
                System.out.println("You get " + itemtemplist.get(itemselect-1) );
                gameBroad.matchPlayer(winnercolor).getItem(itemtemplist.get(itemselect-1) );
                itemtemplist.remove(itemselect-1);
                System.out.println("Remaining items: " + " 1."+ itemtemplist.get(0) +", 2." + itemtemplist.get(1) );
                int itemgiveselect = 0;
                do {
                    try {
                        loop =false;
                        Scanner input= new Scanner(System.in);
                        System.out.println("Please select the item you want to give (1 or 2)" );
                        itemgiveselect = input.nextInt();
                        if (itemgiveselect!=1 && itemgiveselect !=2){
                            System.out.println("Please select (1 or 2");
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please enter a number");
                        loop = true;
                    }
                }
                while (loop || (itemgiveselect!=1 && itemgiveselect !=2));
                String givecolor="";
                HashSet<Playable> others = gameBroad.RemainPlayers(gameBroad.matchPlayer(winnercolor));
                do {
                        Scanner input= new Scanner(System.in);
                        System.out.println("Please select who you want give (type in color) in the List: " +  others );
                        givecolor = input.nextLine();
                        if (!colorcorrectselect(others,givecolor)) {
                            System.out.println("Please select correct color");
                        }
                }
                while (!colorcorrectselect(others,givecolor));
            gameBroad.matchPlayer(givecolor).getItem(itemtemplist.get(itemgiveselect-1));
            itemtemplist.remove(itemgiveselect-1);
            gameBroad.getItemDeck().addBackItem(itemtemplist.get(0));
                String ok1="";
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Other players will be joining the game now, type \"OK\" to continue");
                    ok1 = input.nextLine();
                }
                while (!ok1.equalsIgnoreCase("ok"));
                System.out.println("Player " + givecolor + " get an item from player " + winnercolor);
            }
        }
        //--------------------------parking searching ends ---------------------------------------







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

    public static boolean colorcorrectselect(HashSet<Playable> voteplayerlist, String votecolor){
        for (Playable player: voteplayerlist){
            if (votecolor.equalsIgnoreCase(player.getColor())){
                return true;
            }
        }
        return false;
    }

    public static boolean teamHasThreat(HashSet<Playable> players){
        for (Playable player: players){
           if (player.hasThreat())
               return true;
        }
        return false;
    }







}
