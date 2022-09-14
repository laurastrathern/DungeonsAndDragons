package dnd;

public class Spell extends ProficiencyItem {

    private double durationMinutes;
    private String type;

    public Spell(String name, int strength, int rangeInMeters) {
        super(name, strength, rangeInMeters);
    }
    public Spell(String name, int strength, int rangeInMeters, double durationMinutes, String type) {
        super(name, strength, rangeInMeters);
        this.durationMinutes = durationMinutes;
        this.type = type;
    }


    public double getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(double durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
