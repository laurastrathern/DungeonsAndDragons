package dnd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class Character {
    private boolean isInactive;
    private boolean isNPC;
    private String forename;
    private String surname;
    private String race;

    private int level;
    private int spellSlots;
    private String alignment;

    private int baseHitPoints;
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

    private final int maxDeathSavingThrows = 5;

    private int temporaryHitPoints;

    private int columnLocation;
    private int rowLocation;
    LinkedHashMap<String, Dice> diceMap = new LinkedHashMap<>();

    private Dice actionDie;
    private Dice hitDie;

    public void setDiceMap() {
        Dice d20 = new Dice(20);
        Dice d12 = new Dice(12);
        Dice d10 = new Dice(10);
        Dice d8 = new Dice(8);
        Dice d6 = new Dice(6);
        diceMap.put("d20", d20);
        diceMap.put("d12", d12);
        diceMap.put("d10", d10);
        diceMap.put("d8", d8);
        diceMap.put("d6", d6);
    }

    public Character(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        this.forename = forename;
        this.surname = surname;
        this.race = race;

        this.armourClass = armourClass;
        this.strength = strength;
        this.charisma = charisma;
        this.wisdom = wisdom;
        this.constitution = constitution;

        this.hitPoints = baseHitPoints + constitution;
        this.maxHitPoints = hitPoints;

        setDiceMap();
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
                if (spell.getEffect() != null) {
                    System.out.println(spell.getEffect());
                }

                break;
        }

    }

    public void attack(Character target, ProficiencyItem attackItem) {

        int attackRoll = getActionDie().roll();

        if (isIntimidated() && !target.isIntimidated()) {
            attackRoll = rollWithDisadvantage(attackRoll);
        }
        if((target.isIntimidated() || target.isCharmed()) && !isIntimidated()) {
            attackRoll = rollWithAdvantage(attackRoll);
        }
        if (attackRoll == getActionDie().getSides()) {
            System.out.println("You rolled a natural 20. Your attack causes double damage!");
            int doubleDamage = attackItem.causeDamage(target, getHitDie())*2;
            target.takeDamage(doubleDamage);
        } else if (attackRoll == 1) {
            System.out.println("You rolled a natural 1. You trip on a rock as you try to attack and fall on your face.");
            if((target.isIntimidated() || target.isCharmed())) {
                target.setIntimidated(false);
                target.setCharmed(false);
                System.out.println("If " + target.getForename() + " found you charming or intimidating, they sure don't anymore.");
            }
        } else {
            int attackStrength = addModifier(attackItem, attackRoll);

            if (target.hitSuccessful(attackStrength)) {
                int damage = attackItem.causeDamage(target, getHitDie());
                target.takeDamage(damage);
            }
            else {
                System.out.println("Attack failed.");
            }
        }
    }

    private int rollWithDisadvantage(int attackRoll) {
        int disadvantageRoll = getActionDie().roll();
        System.out.println("You roll with disadvantage. Your higher roll was " + Math.max(attackRoll, disadvantageRoll));
        return Math.min(attackRoll, disadvantageRoll);

    }

    private int rollWithAdvantage(int initialRoll) {
        int advantageRoll = getActionDie().roll();
        System.out.println("You roll with advantage. Your lower roll was " + Math.min(initialRoll, advantageRoll));
        return Math.max(initialRoll, advantageRoll);
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

    private boolean hitSuccessful(int attackStrength) {
        return attackStrength >= getArmourClass();
    }

    public void takeDamage(int damage) {
        if (isUnconscious()) {
            int automaticDeathSaveFail = 0;
            failDeathSave(automaticDeathSaveFail);
        }
        if (damage >= getHitPoints() && !isUnconscious()) {
            setUnconscious(true);
            setIntimidated(false);
            setCharmed(false);
            System.out.println("Oh dear, it looks like " + getForename() + " has passed out, leaving all their belongings unguarded.");
        }
        setHitPoints(getHitPoints()-damage);
        System.out.println(getForename() + " has taken " + damage + " points of damage, and now has " + getHitPoints() + " hit points remaining.");

        if (-getHitPoints() > getMaxHitPoints()) {
            System.out.println("Wow, that was brutal. " + getForename() + " is dead.");
            setInactive(true);
        }
    }

    public void healACharacter(Character target, ProficiencyItem healingItem) {

        int healingStrength = getActionDie().roll();
        healingStrength = addModifier(healingItem, healingStrength);

        if (healingStrength >= 11) {
            if (healingItem.getEffect() != null) {
                System.out.println(healingItem.getEffect());
            }
            target.regainHealth(healingItem.getStrength());
        }
        else {
            System.out.println("Spell failed.");
        }
    }

    public void regainHealth(int health) {

        setHitPoints(getHitPoints()+health);
        System.out.println(getForename() + " has gained " + health + " points of healing, and now has " + getHitPoints() + " hit points remaining.");
        if (isUnconscious() && getHitPoints() >= 1) {
            setUnconscious(false);
            System.out.println(getForename() + " has regained consciousness.  Hopefully they are not concussed.");
        }
        setIntimidated(false);
    }

    public void makeDeathSave() {
        int savingRoll = getActionDie().roll();

        if (savingRoll >= 1 && savingRoll < 10) {
            failDeathSave(savingRoll);
        } else {
            passDeathSave(savingRoll);
        }
    }

    private void passDeathSave(int savingRoll) {
        if (savingRoll > 9 && savingRoll < 20) {
            setSucceededDeathSavingThrows(getSucceededDeathSavingThrows()+1);

            printDeathThrowInfo(savingRoll, "succeeded");
            if(getSucceededDeathSavingThrows() >= 3) {
                regainConsciousness();
                System.out.println(getForename() + " has passed 3 death saving throws and regained consciousness.");
            }
        } else if (savingRoll >= 20) {
            regainConsciousness();
            System.out.println(getForename() + " has rolled " + savingRoll + " and has regained consciousness.");
        }
    }

    private void failDeathSave(int savingRoll) {
        if (savingRoll == 1) {
            setFailedDeathSavingThrows(getFailedDeathSavingThrows()+2);
            String throwSuccess = "failed doubly. Oops";
            printDeathThrowInfo(savingRoll, throwSuccess);
        }
        else if (savingRoll == 0) {
            setFailedDeathSavingThrows(getFailedDeathSavingThrows()+1);
            System.out.println(getForename() + " has lost 1 death saving throw due to being attacked while unconscious.");
        }
        else {
            setFailedDeathSavingThrows(getFailedDeathSavingThrows()+1);
            String throwSuccess = "failed";
            printDeathThrowInfo(savingRoll, throwSuccess);
        }

        if(getFailedDeathSavingThrows() >= 3) {
            die();
        }
    }

    public void regainConsciousness() {
        setHitPoints(1);
        setUnconscious(false);
        setSucceededDeathSavingThrows(0);
        setFailedDeathSavingThrows(0);
    }

    public void die() {
        System.out.println(getForename() + " has failed 3 death saving throws and has died forever.");
        setInactive(true);
    }

    public void printDeathThrowInfo(int savingRoll, String throwSuccess) {
        System.out.println(getForename() + " has rolled " + savingRoll + " and the death saving throw has " + throwSuccess + ". They have a total of "
                + getSucceededDeathSavingThrows() + " succeeded throws, and " + getFailedDeathSavingThrows() + " failed throws.");
    }

    public void charm(Character target) {
        int charmRoll = getActionDie().roll();
        System.out.println("You rolled " + charmRoll + " to charm someone, with charisma modifier " + getCharisma());
        int charmStrength = charmRoll + getCharisma();
        if (charmStrength > 11) {
            target.setCharmed(true);
            System.out.println(target.getForename() + " is charmed by you.");
        }
        else {
            System.out.println("You were not charming enough. " + target.getForename() + " is not interested.");
        }
    }

    public void intimidate(Character target) {
        int intimidationStrength = getActionDie().roll();
        System.out.println("You rolled " + intimidationStrength + " to intimidate someone, with charisma modifier " + getCharisma());
        intimidationStrength += getCharisma();
        if (intimidationStrength > 11) {
            target.setIntimidated(true);
            System.out.println("Wow, scary! " + target.getForename() + " is intimidated by you.");
        }
        else {
            System.out.println("That was boring.  " + target.getForename() + " is not intimidated by you.");
        }
    }

    public void takeLongRest() {
        setHitPoints(getMaxHitPoints());
        setIntimidated(false);
        setCharmed(false);
    }

    public void takeShortRest() {
        if (getHitPoints() < getMaxHitPoints()) {
            int halfLostHitPoints = (getMaxHitPoints()-getHitPoints())/2;
            setHitPoints(getHitPoints()+Math.round(halfLostHitPoints));
        }
        setIntimidated(false);
        setCharmed(false);
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

    public int getMaxDeathSavingThrows() {
        return maxDeathSavingThrows;
    }

    public int getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(int temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    public int getBaseHitPoints() {
        return baseHitPoints;
    }

    public void setBaseHitPoints(int baseHitPoints) {
        this.baseHitPoints = baseHitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getColumnLocation() {
        return columnLocation;
    }

    public void setColumnLocation(int columnLocation) {
        this.columnLocation = columnLocation;
    }

    public int getRowLocation() {
        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public Dice getHitDie() {
        return hitDie;
    }

    public void setHitDie(Dice hitDie) {
        this.hitDie = hitDie;
    }

    public Dice getActionDie() {
        return actionDie;
    }

    public void setActionDie(Dice actionDie) {
        this.actionDie = actionDie;
    }
}
