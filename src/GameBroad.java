import Dice.PairofDice;
import Item.*;
import Playable.*;
import Room.*;
import java.util.*;


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
        for (int q=0; q<players.get(i).getGameCharacters().size(); q++){
            players.get(i).getGameCharacters().get(q).setOwnercolor(players.get(i).getColor());
            players.get(i).getCharactersselect().get(q).setOwnercolor(players.get(i).getColor());
        }
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

    public Room matchRoom(int roomNum){
      int q = 0;
      for (int i=0; i<rooms.size(); i++){
          if (roomNum==rooms.get(i).getRoomNum()){
              q = i;
          }
      }
      return rooms.get(q);
    }

    public void printRooms(){
        for (int i=0; i<rooms.size(); i++){
            System.out.println(rooms.get(i));
        }
    }

    public HashSet<Playable> WhoCan(HashSet<String> existCharacterColors){
        HashSet<Playable> voteplayers = new HashSet<Playable>();
        for (String existCharacterColor: existCharacterColors) {
            for (int q = 0; q < players.size(); q++) {
                if (existCharacterColor.contains(players.get(q).getColor())) {
                    voteplayers.add(players.get(q));
                }
            }
        }
        return  voteplayers;
    }



}
