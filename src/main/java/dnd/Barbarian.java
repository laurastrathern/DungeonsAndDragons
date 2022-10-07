package dnd;

public class Barbarian extends Character {

//    For some, their rage springs from a communion with fierce animal spirits. Others draw from a roiling reservoir of anger at a world full of pain.
//    For every barbarian, rage is a power that fuels not just a battle frenzy but also uncanny reflexes, resilience, and feats of strength.

//    Hit Points
//    Hit Dice: 1d12 per barbarian level
//    Hit Points at 1st Level: 12 + your Constitution modifier
//    Hit Points at Higher Levels: 1d12 (or 7) + your Constitution modifier per barbarian level after 1st
//
//            Proficiencies
//    Armor: Light armor, medium armor, shields
//    Weapons: Simple weapons, martial weapons
//    Tools: None
//    Saving Throws: Strength, Constitution
//    Skills: Choose two from Animal Handling, Athletics, Intimidation, Nature, Perception, and Survival

    public Barbarian(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        super(forename, surname, race, armourClass, strength, charisma, wisdom, constitution);
        setBaseHitPoints(12);
        setMaxHitPoints(getBaseHitPoints()+getConstitution());
        setHitPoints(getMaxHitPoints());
    }
}
