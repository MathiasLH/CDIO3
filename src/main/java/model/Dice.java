package model;

public class Dice {
    int currentRoll;
    int sides;

    public Dice(){
        sides = 6;
    }
    public Dice(int sides){
        this.sides = sides;
    }

    public int roll() {
        currentRoll = (int) (Math.random() * sides + 1);
        return currentRoll;
    }

    public int getCurrentRoll(){
        return currentRoll;
    }
}
