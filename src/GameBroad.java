import Dice.PairofDice;
import Item.*;
import Playable.*;
import Room.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameBroad {
  private List<Room> rooms;
  private List<Playable> players;
  private ItemDeck itemDeck;
  private Playable[] totalPlayerslist;


  public GameBroad(int numplayer) {
    rooms = new ArrayList<>();
    rooms.add(new RestRoom());
    rooms.add(new Cachou());
    rooms.add(new Megatoys());
    rooms.add(new Parking());
    rooms.add(new SecurityHQ());
    rooms.add(new Supermarket());

    totalPlayerslist = new Playable[6];
    for (int i=0; i<totalPlayerslist.length; i++){
        totalPlayerslist[i]=new Player();
      }
    totalPlayerslist[0].setColor("RED");
    totalPlayerslist[1].setColor("YELLOW");
    totalPlayerslist[2].setColor("BLUE");
    totalPlayerslist[3].setColor("GREEN");
    totalPlayerslist[4].setColor("BROWN");
    totalPlayerslist[5].setColor("BLACK");

    players = new ArrayList<>();
    for (int i=0; i<numplayer; i++){
        players.add(totalPlayerslist[i]);
    }


    itemDeck = new ItemDeck();

  }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Playable> getPlayers() {
        return players;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    public int totalCharatersRemain () {
      int sum=0;
      for (int i=0; i<players.size(); i++){
          sum += players.get(i).remaingCharacter();
      }
      return sum;
    }


}
