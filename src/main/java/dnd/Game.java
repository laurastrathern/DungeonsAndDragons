package dnd;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    LinkedHashMap<Integer, Character> characterMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, String> actionMap = new LinkedHashMap<>();
    LinkedHashMap<String, Dice> diceMap = new LinkedHashMap<>();

    public void beginGame() {
        System.out.println("A new game has begun.");
        generateNPC();
        setActionMap();
        setDiceMap();
        getCharacterAction();


    }

    public void setActionMap() {
        actionMap.put(1, "speak");
        actionMap.put(2, "attack");
        actionMap.put(3, "cast a spell");
        actionMap.put(4, "charm");
        actionMap.put(5, "intimidate");
        actionMap.put(6, "investigate");
        actionMap.put(7, "take a short rest");
        actionMap.put(8, "take a long rest");
        actionMap.put(9, "move");
        actionMap.put(10, "exit game");
    }


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

    public void getCharacterAction() {
        while (activePlayerInGame()) {
            for (Character character: characterMap.values()) {
                System.out.println("\n");
                if (character.isUnconscious()) {
                    getDeathSave(character, diceMap.get("d20"));
                }
                else {
                    switch (getChosenAction(character)) {
                        case 1:
                            character.speak(getDialogue(), getChosenTarget());
                            break;
                        case 2:
                            character.attack(getChosenTarget(), getChosenWeapon(character));
                            break;
                        case 3:
                            character.castSpellOnCharacter(getChosenTarget(), getChosenSpell(character));
                            break;
                        case 10:
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
            weaponsOptions.append("Press " + character.getWeapons().indexOf(weapon) + " to select " + weapon.getName() + ". ");
        }

        System.out.println(weaponsOptions);
    }

    public Weapon getChosenWeapon(Character character) {
        provideWeaponsOptions(character);
        int weapon = input.nextInt();
        Weapon chosenWeapon = character.getWeapons().get(weapon);
        return chosenWeapon;
    }

    public void provideSpellOptions(Character character) {
        StringBuilder spellOptions = new StringBuilder("");

        for (Spell spell: character.getSpells()) {
            spellOptions.append("Press " + character.getSpells().indexOf(spell) + " to select " + spell.getName() + ". ");
        }

        System.out.println(spellOptions);
    }

    public Spell getChosenSpell(Character character) {
        provideSpellOptions(character);
        int spell = input.nextInt();
        Spell chosenSpell = character.getSpells().get(spell);
        return chosenSpell;
    }

    public String getDialogue() {
        System.out.println("Type dialogue here:");
        String dialogue = input.nextLine();
        return dialogue;
    }


    public void getDeathSave(Character character, Dice die) {
        System.out.println("Hello " + character.getForename() + ". Press 1 to try to save yourself from death. Alternatively, press 2 to do nothing.");
        int deathSave = input.nextInt();

        if (deathSave == 1) {
            character.makeDeathSave(die);
        }
        else {
            System.out.println("You choose to do nothing." );
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
        Character character1 = new Character(true, "Schokolade", "Kuhlschrank", "Dwarf", "Monk", 25, 16, 5, -3, 2);
        Character character2 = new Character(true, "Nelja", "Valkoinen", "Elf", "Fighter", 20, 15, 3, 1, 4);
        characterMap.put(characterMap.size()+1, character1);
        characterMap.put(characterMap.size()+1, character2);
        Weapon spear = new Weapon("Spear", 3, 90);
        Weapon sword = new Weapon("sword", 5, 7);
        Weapon axe = new Weapon("Huge Axe", 6, 20);
        Weapon grenade = new Weapon("Grenade", 100, 200);
        Weapon crossBow = new Weapon("Selina", 4, 100);

        Spell spell1 = new Spell("Acid Splash", 5, 27, 5, "Attack");
        Spell spell2 = new Spell("Animal Friendship", 4,5);
        Spell spell3 = new Spell("Anti-magic Field", 5,50);
        Spell spell4 = new Spell("Black Tentacles", 4, 27, 1, "Attack");
        Spell spell5 = new Spell("Snilloc's Snowball Swarm", 6, 30, 5, "Attack");
        Spell spell6 = new Spell("Aura of Vitality", 4, 20, 1, "Heal");

        character1.carryWeapon(axe);
        character1.carryWeapon(grenade);
        character2.carryWeapon(spear);
        character2.carryWeapon(sword);
        character2.carryWeapon(crossBow);

        character1.learnSpell(spell1);
        character1.learnSpell(spell2);
        character1.learnSpell(spell6);
        character2.learnSpell(spell1);
        character2.learnSpell(spell3);
        character2.learnSpell(spell4);
        character2.learnSpell(spell5);
        for (Integer key: characterMap.keySet()) {
            Character character = characterMap.get(key);
            System.out.println(key + ": " + character.getForename() + " is added to the game.");
        }

    }
}
