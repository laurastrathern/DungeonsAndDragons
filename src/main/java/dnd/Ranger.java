package dnd;

public class Ranger extends Character {

//    Warriors of the wilderness, rangers specialize in hunting the monsters that threaten the edges of civilization—humanoid raiders, rampaging beasts and monstrosities,
//    terrible giants, and deadly dragons.
//    They learn to track their quarry as a predator does, moving stealthily through the wilds and hiding themselves in brush and rubble.
//    Rangers focus their combat training on techniques that are particularly useful against their specific favored foes.
//
//    Thanks to their familiarity with the wilds, rangers acquire the ability to cast spells that harness nature’s power, much as a druid does.
//    Their spells, like their combat abilities, emphasize speed, stealth, and the hunt. A ranger’s talents and abilities are honed with deadly focus on the grim task of protecting the borderlands.
//
//    Hit Points
//    Hit Dice: 1d10 per ranger level
//    Hit Points at 1st Level: 10 + your Constitution modifier
//    Hit Points at Higher Levels: 1d10 (or 6) + your Constitution modifier per ranger level after 1st
//
//            Proficiencies
//    Armor: Light armor, medium armor, shields
//    Weapons: Simple weapons, martial weapons
//    Tools: None
//    Saving Throws: Strength, Dexterity
//    Skills: Choose three from Animal Handling, Athletics, Insight, Investigation, Nature, Perception, Stealth, and Survival


    public Ranger(String forename, String surname, String race, int armourClass, int strength, int charisma, int wisdom, int constitution) {
        super(forename, surname, race, armourClass, strength, charisma, wisdom, constitution);
        setBaseHitPoints(10);
        setMaxHitPoints(getBaseHitPoints()+getConstitution());
        setHitPoints(getMaxHitPoints());
        setHitDie(diceMap.get("d10"));
        setActionDie(diceMap.get("d20"));
        setActionDie(diceMap.get("d20"));
    }
}
