package dnd;

import java.util.ArrayList;

public class Character {

    public String forename;
    public String surname;
    public String race;
    public String adventurerClass;
    public int spellSlots;
    public String alignment;
    public int hitPoints;
    public int armourClass;
    public ArrayList<String> weapons;

    public int strength;
    public int dexterity;
    public int charisma;

    public void speak(String dialogue) {
        System.out.println(this.forename + " " + this.surname + " said \"" + dialogue + "\"");
    }

    public void castSpellOnCharacter(Spell spell, Character target) {

    }

    public boolean attack(Character target) {
        int roll = 0;
        if (roll >= target.armourClass) {
            System.out.println(target.forename + " is hit.");
            return true;
        }
        else {
            System.out.println("Attack failed.");
            return false;

        }
    }

    public int causeDamage(Character target) {
        int roll = 0;

        return roll;
    }

    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        return  this.hitPoints;
    }

    public void charm(Character target) {

    }

    public void intimidate(Character target) {

    }






    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAdventurerClass() {
        return adventurerClass;
    }

    public void setAdventurerClass(String adventurerClass) {
        this.adventurerClass = adventurerClass;
    }

    public int getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(int spellSlots) {
        this.spellSlots = spellSlots;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public ArrayList<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<String> weapons) {
        this.weapons = weapons;
    }
}
