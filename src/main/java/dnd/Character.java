package dnd;

import java.util.ArrayList;
import java.util.Random;

public class Character {
    private boolean isNPC;
    private String forename;
    private String surname;
    private String race;
    private String adventurerClass;

    private int level;
    private int spellSlots;
    private String alignment;
    private int hitPoints;
    private int armourClass;
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    private ArrayList<Spell> spells = new ArrayList<Spell>();

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private boolean isCharmed;
    private boolean isIntimidated;

    public Character(String forename, String surname, String race, String adventurerClass, int hitPoints, int armourClass, int strength, int charisma) {
        this.forename = forename;
        this.surname = surname;
        this.race = race;
        this.adventurerClass = adventurerClass;
        this.hitPoints = hitPoints;
        this.armourClass = armourClass;
        this.strength = strength;
        this.charisma = charisma;
    }

    public Character(boolean isNPC, String forename, String surname, String race, String adventurerClass, int hitPoints, int armourClass, int strength, int charisma) {
        this.isNPC = isNPC;
        this.forename = forename;
        this.surname = surname;
        this.race = race;
        this.adventurerClass = adventurerClass;
        this.hitPoints = hitPoints;
        this.armourClass = armourClass;
        this.strength = strength;
        this.charisma = charisma;
    }

    public void speak(String dialogue, Character target) {
        System.out.println(this.forename + " turned to " + target.getForename() + " and said \"" + dialogue + "\"");
    }

    public void castSpellOnCharacter(Character target, Spell spell) {
        switch(spell.getType()) {
            case "Heal":
                target.regainHealth(spell.getStrength());
                break;
            case "Attack":
                attack(target, spell);
                break;
            case "Other":

                break;
        }

    }

    public void attack(Character target, ProficiencyItem attackItem) {

        Random dice = new Random();

        int roll = dice.nextInt(21);
        System.out.println("You rolled " + roll + " to attack, with strength modifier " + getStrength());
        int attackStrength = roll + getStrength();

        if (attackStrength >= target.armourClass) {
            System.out.println(target.forename + " is hit.");
            int damage = causeDamage(attackItem);
            target.takeDamage(damage);
        }
        else {
            System.out.println("Attack failed.");
        }
    }

    public int causeDamage(ProficiencyItem attackItem) {
        Random dice = new Random();

        int roll = dice.nextInt(7);
        int damage  = roll + attackItem.getStrength();
        System.out.println(this.getForename() + " rolled " + roll + " with an attack bonus of " + attackItem.getStrength());
        return damage;
    }

    public void takeDamage(int damage) {
        setHitPoints(getHitPoints()-damage);
        System.out.println(getForename() + " has taken " + damage + " points of damage, and now has " + getHitPoints() + " hit points remaining.");
    }

    public void regainHealth(int health) {
        setHitPoints(getHitPoints()+health);
        System.out.println(getForename() + " has gained " + health + " points of healing, and now has " + getHitPoints() + " hit points remaining.");
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

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void carryWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public boolean isNPC() {
        return isNPC;
    }

    public void setNPC(boolean NPC) {
        isNPC = NPC;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public void learnSpell(Spell spell) {
        spells.add(spell);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public boolean isCharmed() {
        return isCharmed;
    }

    public void setCharmed(boolean charmed) {
        isCharmed = charmed;
    }

    public boolean isIntimidated() {
        return isIntimidated;
    }

    public void setIntimidated(boolean intimidated) {
        isIntimidated = intimidated;
    }
}
