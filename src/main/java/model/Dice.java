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
    public int roll(int forcedRoll){
        System.out.println("FORCING ROLL" + forcedRoll);
        return  forcedRoll;
    }

    public int getCurrentRoll(){
        return currentRoll;
    }
}
