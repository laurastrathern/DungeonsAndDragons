package dnd;

public abstract class ProficiencyItem {
    private String name;

    private int strength;
    private int accuracy;


    private int rangeInMeters;
    private String effect;


    public ProficiencyItem(String name, int strength, int rangeInMeters) {
        this.name = name;
        this.strength = strength;
        this.rangeInMeters = rangeInMeters;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int causeDamage(Character target, Dice die) {
        System.out.println(target.getForename() + " is hit.");

        int roll = die.roll();
        int damage  =  roll + getStrength();
        System.out.println("You rolled " + roll + " with an attack bonus of " + getStrength());
        if (getEffect() != null) {
            System.out.println(getEffect());
        }

        return damage;
    }



}
