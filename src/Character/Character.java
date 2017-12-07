package Character;

public abstract class Character {
    String name;
    int points;
    int strength;
    int vote;

    public Character(String name, int points, int strength, int vote) {
        this.name = name;
        this.points = points;
        this.strength = strength;
        this.vote = vote;
    }

    public int vote (){
        int votesum=0;
        return votesum;
    }

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


}
