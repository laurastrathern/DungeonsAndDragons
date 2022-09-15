package dnd;

import java.util.Random;

public class Dice {

    private int sides;

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int roll() {
        Random random = new Random();
        int maximumRoll = getSides()+1;
        int roll = random.nextInt(maximumRoll);
        return roll;
    }
}
