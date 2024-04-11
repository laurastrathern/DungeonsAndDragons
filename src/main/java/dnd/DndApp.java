package dnd;

public class DndApp {

    public static void main(String[] args) {

        Game game = new Game();
        game.setSpellMap();

        Character character1 = new Ranger("Yksi", "Erdbeere", "Halfling", 17, 4, -2, 2, 6);
        Character character2 = new Warlock("Malinova", "Borboleta", "Triton", 16, 3, 4, 3, 5);
        Character character3 = new Monk("Olivia", "Hewitt", "Orc", 18, 5, -3, 1, 5);

        Weapon longBow = new Weapon("Long Bow", 3, 90);
        Weapon broadSword = new Weapon("Gina", 2, 5);
        Weapon shortSword = new Weapon("Short Sword", 2, 2);
        Weapon slingshot = new Weapon("Slingshot", 1, 50);
        Weapon ninjaStars = new Weapon("Ninja Stars", 4, 100);
        Weapon mace = new Weapon("Mace", 3, 10);
        Weapon javelin = new Weapon("Javelin", 2, 120);
        Weapon sickle = new Weapon("Sickle", 2, 4);

        character1.carryWeapon(longBow);
        character1.carryWeapon(broadSword);
        character2.carryWeapon(shortSword);
        character2.carryWeapon(slingshot);
        character2.carryWeapon(ninjaStars);
        character3.carryWeapon(mace);
        character3.carryWeapon(javelin);
        character3.carryWeapon(sickle);

        character1.learnSpell(game.spellMap.get(1));
        character1.learnSpell(game.spellMap.get(2));
        character1.learnSpell(game.spellMap.get(7));
        character2.learnSpell(game.spellMap.get(1));
        character2.learnSpell(game.spellMap.get(4));
        character2.learnSpell(game.spellMap.get(5));
        character2.learnSpell(game.spellMap.get(6));
        character3.learnSpell(game.spellMap.get(8));
        character3.learnSpell(game.spellMap.get(9));
        character3.learnSpell(game.spellMap.get(10));

        game.addCharacterToGame(character1);
        game.addCharacterToGame(character2);

        game.beginGame();

    }
}
