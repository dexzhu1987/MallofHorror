import Item.*;
import Playable.*;
import Room.*;
import java.util.*;

public class GameBroad {
  private List<Room> rooms;
  private List<Playable> playables;
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


    playables = new ArrayList<>();
    for (int i=0; i<numplayer; i++){
        playables.add(totalPlayerslist[i]);
    }


    itemDeck = new ItemDeck();


  }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Playable> getPlayables() {
        return playables;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }
}
