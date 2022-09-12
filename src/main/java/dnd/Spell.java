package dnd;

public class Spell {

    public int strength;
    public int accuracy;
    public String effect;


    public int causeDamage(Character target) {
        return strength;
    }

    public int heal(Character target) {
        return strength;
    }

    public void takeEffect() {
        System.out.println(effect);
    }
}
