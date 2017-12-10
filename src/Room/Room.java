package Room;

import java.util.*;
import Character.*;


public abstract class Room {
    protected int roomNum;
    protected String name;
    protected int capability;
    protected List<GameCharacter> roomCharaters;

    public Room(int roomNum, String name, int capability) {
        this.roomNum = roomNum;
        this.name = name;
        this.capability = capability;
        roomCharaters = new ArrayList<>();
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

    private HashMap<String,Integer> potentialVoteForEachColor(){
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
        return potentialVoteForEachColor;
    }

    public HashMap<String,Integer> actualVoteForEachColor(List<String> votes){
        HashMap<String, Integer> actualVoteForEachColor = new HashMap<>();

        for (String color: existCharacterColor()){
            int votesum=0;
         for (int q=0; q<votes.size(); q++)   {
            if (color.equals(votes.get(q))){
              votesum+= potentialVoteForEachColor().get(color);
            }
         }
         actualVoteForEachColor.put(color, votesum);
        }
        return actualVoteForEachColor;
    }

    public String winner(List<String> votes){
        List<String> keys = new ArrayList<>(actualVoteForEachColor(votes).keySet());
        List<Integer> values= new ArrayList<>(actualVoteForEachColor(votes).values());
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
        System.out.println(r1.potentialVoteForEachColor());
        List<String> votes = new ArrayList<>();
        votes.add("RED");
        votes.add("RED");
        votes.add("YELLOW");
        votes.add("RED");
        votes.add("RED");
        votes.add("RED");
        votes.add("RED");
        System.out.println(r1.actualVoteForEachColor(votes));
        System.out.println(r1.winner(votes));
    }
}
