package dnd;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    LinkedHashMap<Integer, Character> characterMap = new LinkedHashMap<Integer, Character>();
    LinkedHashMap<Integer, String> actionMap = new LinkedHashMap<Integer, String>();

    public void beginGame() {
        System.out.println("A new game has begun.");
        this.generateNPC();
        this.setActionMap();
        this.getCharacterAction();

    }

    public void setActionMap() {
        actionMap.put(1, "speak");
        actionMap.put(2, "attack");
        actionMap.put(3, "cast a spell");
        actionMap.put(4, "charm");
        actionMap.put(5, "intimidate");
        actionMap.put(6, "investigate");
        actionMap.put(7, "exit game");
    }

    public void getCharacterAction() {
        for (Character character: characterMap.values()) {

                switch(getChosenAction(character)) {
                    case 1:
                        System.out.println("Type dialogue here:");
                        String dialogue = input.nextLine();
                        character.speak(dialogue, getChosenTarget());
                        break;
                    case 2:
                        character.attack(getChosenTarget(), getChosenWeapon(character));
                        break;
                    case 3:
                        character.castSpellOnCharacter(getChosenTarget(), getChosenSpell(character));
                        break;
                    case 7:
                        System.out.println(character.getForename() + " has exited the game. Thank you for playing.");
                        //characterMap.remove(character);
                        for (Integer key: characterMap.keySet()) {
                            Character remainingCharacter = characterMap.get(key);
                            System.out.println(remainingCharacter.getForename() + " is still in the game.");
                        }
                        break;
                }


        }

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



    public void addCharacterToGame(Character character) {
        characterMap.put(characterMap.size()+1, character);
    }

    public void beginCombat() {

    }

    public void generateNPC() {
        Character character1 = new Character(true, "Schokolade", "Kuhlschrank", "Dragonborn", "Monk", 25, 16, 5, -3);
        Character character2 = new Character(true, "Nelja", "Valkoinen", "Elf", "Fighter", 20, 15, 3, 1);
        characterMap.put(characterMap.size()+1, character1);
        characterMap.put(characterMap.size()+1, character2);
        for (Integer key: characterMap.keySet()) {
            Character character = characterMap.get(key);
            System.out.println(key + ": " + character.getForename() + " is added to the game.");
        }

    }
}
