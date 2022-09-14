package dnd;

public class SpellBuilder {
    private String name;
    private int strength;
    private int rangeInMeters;
    private double hoursEffective;

    public SpellBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SpellBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public SpellBuilder setRangeInMeters(int rangeInMeters) {
        this.rangeInMeters = rangeInMeters;
        return this;
    }

    public SpellBuilder setHoursEffective(double hoursEffective) {
        this.hoursEffective = hoursEffective;
        return this;
    }

    public Spell createSpell() {
        return new Spell(name, strength, rangeInMeters);
    }
}