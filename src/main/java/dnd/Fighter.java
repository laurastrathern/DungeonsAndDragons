package dnd;

public class Fighter extends Character {

//    Questing knights, conquering overlords, royal champions, elite foot soldiers, hardened mercenaries,
//    and bandit kings—as fighters, they all share an unparalleled mastery with weapons and armor, and a thorough knowledge of the skills of combat.
//    And they are well acquainted with death, both meting it out and staring it defiantly in the face.

//    Hit Points
//    Hit Dice: 1d10 per fighter level
//    Hit Points at 1st Level: 10 + your Constitution modifier
//    Hit Points at Higher Levels: 1d10 (or 6) + your Constitution modifier per fighter level after 1st
//
//            Proficiencies
//    Armor: All armor, shields
//    Weapons: Simple weapons, martial weapons
//    Tools: None
//    Saving Throws: Strength, Constitution
//    Skills: Choose two skills from Acrobatics, Animal Handling, Athletics, History, Insight, Intimidation, Perception, and Survival


    public Fighter(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        super(forename, surname, race, armourClass, strength, charisma, wisdom, constitution);
        setBaseHitPoints(10);
        setMaxHitPoints(getBaseHitPoints()+getConstitution());
        setHitPoints(getMaxHitPoints());
        setHitDie(diceMap.get("d10"));
        setActionDie(diceMap.get("d20"));
    }
}
