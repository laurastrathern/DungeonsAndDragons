package dnd;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    LinkedHashMap<Integer, Character> characterMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, String> actionMap = new LinkedHashMap<>();

    LinkedHashMap<Integer, Spell> spellMap = new LinkedHashMap<>();


    public void beginGame() {
        System.out.println("A new game has begun.");
        generateNPC();
        setActionMap();
        getCharacterAction();

    }

    public void setActionMap() {
        actionMap.put(1, "attack");
        actionMap.put(2, "cast a spell");
        actionMap.put(3, "charm");
        actionMap.put(4, "intimidate");
        actionMap.put(5, "investigate");
        actionMap.put(6, "take a short rest");
        actionMap.put(7, "take a long rest");
        actionMap.put(8, "move");
        actionMap.put(9, "exit game");
    }

    public void setSpellMap() {
        Spell spell1 = new Spell("Acid Splash", 5, 27, 5, "Attack", "You hurl a bubble of acid.");
        Spell spell2 = new Spell("Animal Friendship", 4,5, 60, "Other", "This spell lets you convince a beast that you mean it no harm.");
        Spell spell3 = new Spell("Anti-magic Field", 5,10, 60, "Other", "A 10-foot-radius invisible sphere of antimagic surrounds you.");
        Spell spell4 = new Spell("Black Tentacles", 4, 27, 1, "Attack", "Squirming, ebony tentacles fill a 20-foot square on ground");
        Spell spell5 = new Spell("Snilloc's Snowball Swarm", 6, 30, 5, "Attack", "A flurry of magic snowballs erupts.");
        Spell spell6 = new Spell("Aura of Vitality", 4, 20, 1, "Heal");
        Spell spell7 = new Spell("Acid Arrows", 4, 30, 10, "Attack", "A shimmering green arrow streaks toward a target within range and bursts in a spray of acid. ");
        Spell spell8 = new Spell("Cure Wounds", 3, 1, 1, "Heal", "At your touch, their wounds are healed.");
        spellMap.put(1, spell1);
        spellMap.put(2, spell2);
        spellMap.put(3, spell3);
        spellMap.put(4, spell4);
        spellMap.put(5, spell5);
        spellMap.put(6, spell6);
        spellMap.put(7, spell7);
        spellMap.put(8, spell8);
    }

    public void getCharacterAction() {
        while (activePlayerInGame()) {
            for (Character character: characterMap.values()) {
                System.out.println("\n");
                if (character.isUnconscious()) {
                    getDeathSave(character);
                }
                else {
                    switch (getChosenAction(character)) {
                        case 1:
                            character.attack(getChosenTarget(), getChosenWeapon(character));
                            break;
                        case 2:
                            character.castSpellOnCharacter(getChosenTarget(), getChosenSpell(character));
                            break;
                        case 3:
                            character.charm(getChosenTarget());
                            break;
                        case 4:
                            character.intimidate(getChosenTarget());
                            break;
                        case 5:

                            break;
                        case 6:
                            character.takeShortRest();
                            break;
                        case 7:
                            character.takeLongRest();
                            break;
                        case 9:
                            character.setInactive(true);
                            break;
                    }
                }
            }
            removeExitedCharacters();

        }
    }

    private boolean activePlayerInGame() {
        return characterMap.isEmpty() == false;
    }

    public void provideActionOptions(Character character) {
        StringBuilder actionOptions = new StringBuilder("");
        actionOptions.append("Hello " + character.getForename() + ". ");

        for (Integer key: actionMap.keySet()) {
            String action = actionMap.get(key);
            actionOptions.append("Press " + key + " to " + action  + ". ");
        }

        System.out.println(actionOptions);
    }

    public int getChosenAction(Character character) {
        this.provideActionOptions(character);
        int action = input.nextInt();
        System.out.println("You choose: " + actionMap.get(action));
        return action;
    }

    public void provideTargetOptions() {
        StringBuilder targetOptions = new StringBuilder("");

        for (Integer key: characterMap.keySet()) {
            Character target = characterMap.get(key);
            targetOptions.append("Press " + key + " to select " + target.getForename() + ". ");
        }

        System.out.println(targetOptions);
    }

    public Character getChosenTarget() {
        provideTargetOptions();
        int target = input.nextInt();
        Character chosenTarget = characterMap.get(target);
        return chosenTarget;
    }

    public void provideWeaponsOptions(Character character) {
        StringBuilder weaponsOptions = new StringBuilder("");

        for (Weapon weapon: character.getWeapons()) {
            int weaponKey = character.getWeapons().indexOf(weapon) + 1;
            weaponsOptions.append("Press " +  weaponKey + " to select " + weapon.getName() + ". ");
        }
        System.out.println(weaponsOptions);
    }

    public Weapon getChosenWeapon(Character character) {
        provideWeaponsOptions(character);
        int weapon = input.nextInt() - 1;
        Weapon chosenWeapon = character.getWeapons().get(weapon);
        return chosenWeapon;
    }

    public void provideSpellOptions(Character character) {
        StringBuilder spellOptions = new StringBuilder("");

        for (Spell spell: character.getSpells()) {
            int spellKey = character.getSpells().indexOf(spell) + 1;
            spellOptions.append("Press " + spellKey + " to select " + spell.getName() + ". ");
        }

        System.out.println(spellOptions);
    }

    public Spell getChosenSpell(Character character) {
        provideSpellOptions(character);
        int spell = input.nextInt() - 1;
        Spell chosenSpell = character.getSpells().get(spell);
        return chosenSpell;
    }

    public void getDeathSave(Character character) {
        System.out.println("Hello " + character.getForename() + ". Press 1 to try to save yourself from death. Alternatively, press 2 to do nothing or press 3 to exit the game.");
        int deathSave = input.nextInt();

        if (deathSave == 1) {
            character.makeDeathSave();
        }
        else if (deathSave == 2){
            System.out.println("You choose to do nothing." );
        }
        else {
            character.setInactive(true);
        }
    }
    public void addCharacterToGame(Character character) {
        characterMap.put(characterMap.size()+1, character);
    }

    public void removeExitedCharacters() {
        ArrayList<Integer> characterKey = new ArrayList<>();
        for (Integer key: characterMap.keySet()) {
            Character character = characterMap.get(key);
            if (character.isInactive()) {
                characterKey.add(key);
                System.out.println(character.getForename() + " has exited the game. Thank you for playing.");
            }
        }
        for (Integer key: characterKey) {
            characterMap.remove(key);
        }

    }


    public void beginCombat() {

    }

    public void generateNPC() {
        Character character1 = new Monk("Schokolade", "Kuhlschrank", "Dwarf", 16, 5, -3, 2, 6);
        Character character2 = new Fighter("Nelja", "Valkoinen", "Elf",  15, 2, 4, 3, 5);
        characterMap.put(characterMap.size()+1, character1);
        characterMap.put(characterMap.size()+1, character2);
        Weapon spear = new Weapon("Spear", 3, 90);
        Weapon sword = new Weapon("sword", 5, 7);
        Weapon axe = new Weapon("Huge Axe", 6, 20);
        Weapon grenade = new Weapon("Grenade", 100, 200);
        Weapon crossBow = new Weapon("Selina", 4, 100);

        character1.carryWeapon(axe);
        character1.carryWeapon(grenade);
        character2.carryWeapon(spear);
        character2.carryWeapon(sword);
        character2.carryWeapon(crossBow);

        character1.learnSpell(spellMap.get(4));
        character1.learnSpell(spellMap.get(5));
        character1.learnSpell(spellMap.get(8));
        character2.learnSpell(spellMap.get(1));
        character2.learnSpell(spellMap.get(6));
        character2.learnSpell(spellMap.get(7));
        character2.learnSpell(spellMap.get(8));
        for (Integer key: characterMap.keySet()) {
            Character character = characterMap.get(key);
            System.out.println(key + ": " + character.getForename() + " is added to the game.");
        }

    }
}
