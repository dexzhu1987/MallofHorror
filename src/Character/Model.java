package Character;

public class Model extends GameCharacter {
    public Model() {
        super("Model", 7, 1, 1);
    }

    public boolean attrackZombie(){
       return true;
    }

    @Override
    public int getVote() {
        return super.getVote();
    }

    @Override
    public int getPoints() {
        return super.getPoints();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getStrength() {
        return super.getStrength();
    }
}
