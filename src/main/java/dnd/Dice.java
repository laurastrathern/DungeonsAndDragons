package dnd;

import java.util.Random;

public class Dice {

    private final int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public int roll() {
        Random random = new Random();
        int maximumRoll = getSides()+1;
        int roll = random.nextInt(maximumRoll);
        return roll;
    }
}
