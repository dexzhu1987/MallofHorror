package Room;

import java.util.*;
import Character.*;
import Playable.Player;


public abstract class Room {
    protected int roomNum;
    protected String name;
    protected int capability;
    protected List<GameCharacter> roomCharaters;
    protected HashMap<String, Integer> currentVoteResult;

    public Room(int roomNum, String name, int capability) {
        this.roomNum = roomNum;
        this.name = name;
        this.capability = capability;
        roomCharaters = new ArrayList<>();
        currentVoteResult = new HashMap<>();
    }

    public boolean defend(){
        return true;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public String getName() {
        return name;
    }

    public int getCapability() {
        return capability;
    }

    public void enter(GameCharacter character){
        roomCharaters.add(character);
    }

    public void leave(GameCharacter character){
        roomCharaters.remove(character);
    }

    public HashMap<String, Integer> getCurrentVoteResult() {
        return currentVoteResult;
    }

    public void setCurrentVoteResult(HashMap<String, Integer> currentVoteResult) {
        this.currentVoteResult = currentVoteResult;
    }

    public boolean isFull(){
        if (roomCharaters.size()>=capability){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEmpty(){
        return roomCharaters.size()==0;
    }

    public HashSet<String> existCharacterColor(){
        HashSet<String> existCharacterColor = new HashSet<>();
        for (int i=0; i<roomCharaters.size(); i++){
            existCharacterColor.add(roomCharaters.get(i).getOwnercolor());
        }
        return existCharacterColor;
    }

    /**
     * method set the vote result using the potential votes in the room
     */
    public void  resetVoteResult(){
        HashMap<String, Integer> potentialVoteForEachColor = new HashMap<>();
        int votesum=0;
        for (int i=0; i<roomCharaters.size(); i++){
                    votesum=roomCharaters.get(i).getVote();
                    for (int q = i+1; q<roomCharaters.size(); q++){
                        if (roomCharaters.get(q).getOwnercolor().equals(roomCharaters.get(i).getOwnercolor())){
                            votesum+=roomCharaters.get(q).getVote();
                        }
                    }
                    if (!potentialVoteForEachColor.containsKey(roomCharaters.get(i).getOwnercolor())){
                        potentialVoteForEachColor.put(roomCharaters.get(i).getOwnercolor(),votesum);
                    }

        }
        setCurrentVoteResult(potentialVoteForEachColor);
    }

    /**
     * set the currentresult to result after vote
     * @param votes
     */
    public void voteResultAfterVote(List<String> votes){
        HashMap<String, Integer> actualVoteForEachColor = new HashMap<>();

        for (String color: existCharacterColor()){
            int votesum=0;
         for (int q=0; q<votes.size(); q++)   {
            if (color.equalsIgnoreCase(votes.get(q))){
              votesum+= currentVoteResult.get(color);
            }
         }
         actualVoteForEachColor.put(color, votesum);
        }
        setCurrentVoteResult(actualVoteForEachColor);
    }

    /**
     * set the currentvoteresult to a new result after items used
     * @param color
     * @param increaseNum
     */
    public void voteResultAfterItem(String color, int increaseNum){
        int originalvote = currentVoteResult.get(color);
        int newvote=originalvote + increaseNum;
        currentVoteResult.replace(color,newvote);

    }

    public String winner(){
        List<String> keys = new ArrayList<>(currentVoteResult.keySet());
        List<Integer> values= new ArrayList<>(currentVoteResult.values());
        int max=values.get(0);
        for (int i=0; i<values.size(); i++){
            if (max<values.get(i)){
                max=values.get(i);
            }
        }
        int q=0;
        int count=0;
        for (int i=0; i<values.size();i++){
            if (max==values.get(i)){
                q=i;
                count++;
            }
        }
        if (count>1){
            return "TIE";
        }
        else {
            return keys.get(q);
        }


    }

    @Override
    public String toString() {
        return name + " has " + roomCharaters ;
    }


    public static void main(String[] args) {
        Room r1=new Parking();
        GameCharacter c1=new Model();
        GameCharacter c2=new GunMan();
        GameCharacter c3=new ToughGuy();
        GameCharacter c4=new GunMan();
        GameCharacter c5=new Model();
        c1.setOwnercolor("RED");
        c2.setOwnercolor("YELLOW");
        c3.setOwnercolor("YELLOW");
        c4.setOwnercolor("YELLOW");
        c5.setOwnercolor("YELLOW");
        r1.enter(c1);
        r1.enter(c2);
        r1.enter(c3);
        r1.enter(c4);
        r1.enter(c5);
        System.out.println(r1);
        r1.resetVoteResult();
        System.out.println(r1.getCurrentVoteResult());
        List<String> votes = new ArrayList<>();
        votes.add("RED");
        votes.add("RED");
        votes.add("YELLOW");
        votes.add("RED");
        votes.add("RED");
        votes.add("RED");
        r1.voteResultAfterVote(votes);
        System.out.println(r1.getCurrentVoteResult());
        System.out.println(r1.winner());
        r1.voteResultAfterItem("RED",3);
        r1.voteResultAfterItem("YELLOW",2);
        System.out.println(r1.getCurrentVoteResult());
        System.out.println(r1.winner());
    }
}
