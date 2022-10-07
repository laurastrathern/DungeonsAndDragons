package dnd;

public class Monk extends Character {

//    Whatever their discipline, monks are united in their ability to magically harness the energy that flows in their bodies.
//    Whether channeled as a striking display of combat prowess or a subtler focus of defensive ability and speed, this energy infuses all that a monk does.

//    Hit Points
//    Hit Dice: 1d8 per monk level
//    Hit Points at 1st Level: 8 + your Constitution modifier
//    Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per monk level after 1st
//
//            Proficiencies
//    Armor: None
//    Weapons: Simple weapons, shortswords
//    Tools: Choose one type of artisan’s tools or one musical instrument
//    Saving Throws: Strength, Dexterity
//    Skills: Choose two from Acrobatics, Athletics, History, Insight, Religion, and Stealth


    public Monk(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        super(forename, surname, race, armourClass, strength, charisma, wisdom, constitution);
        setBaseHitPoints(8);
        setMaxHitPoints(getBaseHitPoints()+getConstitution());
        setHitPoints(getMaxHitPoints());
    }
}
