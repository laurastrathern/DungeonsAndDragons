package dnd;

public class Warlock extends Character {

//    Warlocks are seekers of the knowledge that lies hidden in the fabric of the multiverse.
//    Through pacts made with mysterious beings of supernatural power, warlocks unlock magical effects both subtle and spectacular.
//    Drawing on the ancient knowledge of beings such as fey nobles, demons, devils, hags, and alien entities of the Far Realm, warlocks piece together arcane secrets to bolster their own power.
//
//    Hit Points
//    Hit Dice: 1d8 per warlock level
//    Hit Points at 1st Level: 8 + your Constitution modifier
//    Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per warlock level after 1st
//
//            Proficiencies
//    Armor: Light armor
//    Weapons: Simple weapons
//    Tools: None
//    Saving Throws: Wisdom, Charisma
//    Skills: Choose two skills from Arcana, Deception, History, Intimidation, Investigation, Nature, and Religion
    public Warlock(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        super(forename, surname, race, armourClass, strength, charisma, wisdom, constitution);
        setBaseHitPoints(8);
        setMaxHitPoints(getBaseHitPoints()+getConstitution());
        setHitPoints(getMaxHitPoints());
        setHitDie(diceMap.get("d8"));
        setActionDie(diceMap.get("d20"));
    }
}
