package Character;


import Playable.Playable;

public abstract class GameCharacter {
    private String name;
    private int points;
    private int strength;
    private int vote;
    private Playable owner;


    public GameCharacter(String name, int points, int strength, int vote) {
        this.name = name;
        this.points = points;
        this.strength = strength;
        this.vote = vote;
        owner = new Playable() ;
    }

//    public int vote (){
//        int votesum = 0;
//        return votesum += getVote();
//    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getStrength() {
        return strength;
    }

    public int getVote() {
        return vote;
    }

    @Override
    public String toString() {
        return name;
    }

    public Playable getOwner() {
        return owner;
    }

    public void setOwner(Playable owner) {
        this.owner = owner;
    }
}
