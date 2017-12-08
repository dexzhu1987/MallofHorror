package Dice;

public class PairofDice {
    private int faceOne;
    private int faceTwo;
    final private int MAXFACE = 6;


    public PairofDice () {
        faceOne = 1;
        faceTwo = 1;
    }

    public int rollDieOne() {
        this.faceOne = ((int)(Math.random()*6))+1;
        return faceOne;
    }

    public int rollDieTwo() {
        this.faceTwo = ((int)(Math.random()*6))+1;
        return faceTwo;
    }

    public int getDieOneFace () {
        return faceOne;
    }

    public int getDieTwoFace () {
        return  faceTwo;
    }

    public int sumofTwo() {
        return faceOne + faceTwo;
    }

    @Override
    public String toString() {
        return "Dice One has a value of " + faceOne + "\t Dice Two has value of " + faceTwo;
    }
}
