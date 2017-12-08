package Character;

public abstract class Character {
    private String name;
    private int points;
    private int strength;
    private int vote;

    public Character(String name, int points, int strength, int vote) {
        this.name = name;
        this.points = points;
        this.strength = strength;
        this.vote = vote;
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


}
