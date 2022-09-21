package dnd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Character {
    private boolean isInactive;
    private boolean isNPC;
    private String forename;
    private String surname;
    private String race;
    private String adventurerClass;

    private int level;
    private int spellSlots;
    private String alignment;
    private int hitPoints;
    private int maxHitPoints;
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

    private boolean isUnconscious;

    private int succeededDeathSavingThrows;

    private int failedDeathSavingThrows;

    private int temporaryHitPoints;
    LinkedHashMap<String, Dice> diceMap = new LinkedHashMap<>();

    public void setDiceMap() {
        Dice d20 = new Dice(20);
        Dice d10 = new Dice(10);
        Dice d8 = new Dice(8);
        Dice d6 = new Dice(6);
        diceMap.put("d20", d20);
        diceMap.put("d10", d10);
        diceMap.put("d8", d8);
        diceMap.put("d6", d6);
    }

    public Character(String forename, String surname, String race, String adventurerClass, int hitPoints, int armourClass, int strength, int charisma, int wisdom) {
        this.forename = forename;
        this.surname = surname;
        this.race = race;
        this.adventurerClass = adventurerClass;
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.armourClass = armourClass;
        this.strength = strength;
        this.charisma = charisma;
        this.wisdom = wisdom;
        setDiceMap();
    }

    public Character(boolean isNPC, String forename, String surname, String race, String adventurerClass, int hitPoints, int armourClass, int strength, int charisma, int wisdom) {
        this.isNPC = isNPC;
        this.forename = forename;
        this.surname = surname;
        this.race = race;
        this.adventurerClass = adventurerClass;
        this.hitPoints = hitPoints;
        this.armourClass = armourClass;
        this.strength = strength;
        this.charisma = charisma;
        this.wisdom = wisdom;
        setDiceMap();
    }

    public void speak(String dialogue, Character target) {
        System.out.println(this.forename + " turned to " + target.getForename() + " and said \"" + dialogue + "\"");
    }

    public void castSpellOnCharacter(Character target, Spell spell) {
        switch(spell.getType()) {
            case "Heal":
                healACharacter(target, spell);
                break;
            case "Attack":
                attack(target, spell);
                break;
            case "Other":

                break;
        }

    }

    public void attack(Character target, ProficiencyItem attackItem) {

        int attackStrength = diceMap.get("d20").roll();
        attackStrength = addModifier(attackItem, attackStrength);

        if (hitSuccessful(target, attackStrength)) {
            causeDamage(attackItem, target, diceMap.get("d8"));
        }
        else {
            System.out.println("Attack failed.");
        }
    }

    private int addModifier(ProficiencyItem actionItem, int actionStrength) {
        if (actionItem instanceof Spell) {
            System.out.println("You rolled " + actionStrength + " to cast a spell, with wisdom modifier " + getWisdom());
            actionStrength += getWisdom();
        } else if (actionItem instanceof Weapon) {
            System.out.println("You rolled " + actionStrength + " to attack, with strength modifier " + getStrength());
             actionStrength += getStrength();
        }
        return actionStrength;
    }

    private boolean hitSuccessful(Character target, int attackStrength) {
        return attackStrength >= target.getArmourClass();
    }

    public int causeDamage(ProficiencyItem attackItem, Character target, Dice die) {
        System.out.println(target.getForename() + " is hit.");

        int roll = die.roll();
        int damage  =  roll + attackItem.getStrength();
        System.out.println(this.getForename() + " rolled " + roll + " with an attack bonus of " + attackItem.getStrength());
        target.takeDamage(damage);
        return damage;
    }

    public void takeDamage(int damage) {
        if (damage >= getHitPoints()) {
            setUnconscious(true);
        }
        setHitPoints(getHitPoints()-damage);
        System.out.println(getForename() + " has taken " + damage + " points of damage, and now has " + getHitPoints() + " hit points remaining.");
        if (isUnconscious) {
            System.out.println("Oh dear, it looks like " + getForename() + " has passed out, leaving all their belongings unguarded.");
        }
    }

    public void healACharacter(Character target, ProficiencyItem healingItem) {

        int healingStrength = diceMap.get("d20").roll();
        healingStrength = addModifier(healingItem, healingStrength);

        if (healingStrength >= 11) {
            target.regainHealth(healingItem.getStrength());
        }
        else {
            System.out.println("Spell failed.");
        }
    }

    public void regainHealth(int health) {

        setHitPoints(getHitPoints()+health);
        System.out.println(getForename() + " has gained " + health + " points of healing, and now has " + getHitPoints() + " hit points remaining.");
        if (isUnconscious && getHitPoints() >= 1) {
            setUnconscious(false);
            System.out.println(getForename() + " has regained consciousness.  Hopefully they are not concussed.");
        }
    }

    public void makeDeathSave() {

        int savingRoll = diceMap.get("d20").roll();
        int remainingThrows = 5 - (getFailedDeathSavingThrows() + getSucceededDeathSavingThrows());
        if (savingRoll <= 1) {
            setFailedDeathSavingThrows(getFailedDeathSavingThrows()+2);
            remainingThrows -= 2;
            System.out.println(getForename() + " has rolled " + savingRoll + " and the death saving throw has failed doubly. Oops. They have " + remainingThrows + " throws remaining, and a total of "
                    + getSucceededDeathSavingThrows() + " succeeded throws, and " + getFailedDeathSavingThrows() + " failed throws.");
            if(getFailedDeathSavingThrows() >= 3) {
                System.out.println(getForename() + " has failed 3 death saving throws and has died forever.");
                setInactive(true);
            }
        } else if (savingRoll > 1 && savingRoll < 10) {
            setFailedDeathSavingThrows(getFailedDeathSavingThrows()+1);
            remainingThrows -= 1;
            System.out.println(getForename() + " has rolled " + savingRoll + " and the death saving throw has failed. They have " + remainingThrows + " throws remaining, and a total of "
                    + getSucceededDeathSavingThrows() + " succeeded throws, and " + getFailedDeathSavingThrows() + " failed throws.");
            if(getFailedDeathSavingThrows() >= 3) {
                System.out.println(getForename() + " has failed 3 death saving throws and has died forever.");
                setInactive(true);
            }
        } else if (savingRoll > 9 && savingRoll < 20) {
            setSucceededDeathSavingThrows(getSucceededDeathSavingThrows()+1);
            remainingThrows -= 1;
            System.out.println(getForename() + " has rolled " + savingRoll + " and the death saving throw has succeeded. They have " + remainingThrows + " throws remaining, and a total of "
                    + getSucceededDeathSavingThrows() + " succeeded throws, and " + getFailedDeathSavingThrows() + " failed throws.");
            if(getSucceededDeathSavingThrows() >= 3) {
                setHitPoints(1);
                setUnconscious(false);
                System.out.println(getForename() + " has passed 3 death saving throws and regained consciousness.");
            }
        } else if (savingRoll >= 20) {
            setHitPoints(1);
            setUnconscious(false);
            System.out.println(getForename() + " has rolled " + savingRoll + " and has regained consciousness.");
        }
    }

    public void charm(Character target) {

    }

    public void intimidate(Character target) {

    }

    public void takeLongRest() {
        setHitPoints(maxHitPoints);
    }

    public void takeShortRest() {

    }

    public boolean isInactive() {
        return isInactive;
    }

    public void setInactive(boolean inactive) {
        isInactive = inactive;
        System.out.println(getForename() + " will leave the game at the end of the round.");
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

    public boolean isUnconscious() {
        return isUnconscious;
    }

    public void setUnconscious(boolean unconscious) {
        isUnconscious = unconscious;
    }

    public int getSucceededDeathSavingThrows() {
        return succeededDeathSavingThrows;
    }

    public void setSucceededDeathSavingThrows(int succeededDeathSavingThrows) {
        this.succeededDeathSavingThrows = succeededDeathSavingThrows;
    }

    public int getFailedDeathSavingThrows() {
        return failedDeathSavingThrows;
    }

    public void setFailedDeathSavingThrows(int failedDeathSavingThrows) {
        this.failedDeathSavingThrows = failedDeathSavingThrows;
    }

    public int getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(int temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }
}
