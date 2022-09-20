package dnd;

public class DndApp {

    public static void main(String[] args) {

        Game game = new Game();

        Character character1 = new Character("Yksi", "Erdbeere", "Halfling", "Ranger", 25, 17, 4, -2, 2);
        Character character2 = new Character("Malinova", "Borboleta", "Triton", "Warlock", 20, 16, 3, 4, 3);

        Weapon longBow = new Weapon("Long Bow", 3, 90);
        Weapon broadSword = new Weapon("Gina", 2, 5);
        Weapon shortSword = new Weapon("Short Sword", 2, 2);
        Weapon slingshot = new Weapon("Slingshot", 1, 50);
        Weapon ninjaStars = new Weapon("Ninja Stars", 4, 100);

//        Spell spell1 = new SpellBuilder().setName("Acid Splash").setStrength(5).setRangeInMeters(27).setHoursEffective(0.25).createSpell();
//        Spell spell2 = new SpellBuilder().setName("Animal Friendship").setStrength(4).setRangeInMeters(5).createSpell();
//        Spell spell3 = new SpellBuilder().setName("Anti-magic Field").setStrength(5).setRangeInMeters(50).createSpell();
//        Spell spell4 = new SpellBuilder().setName("Black Tentacles").setStrength(4).setRangeInMeters(27).setHoursEffective(0.20).createSpell();
//        Spell spell5 = new SpellBuilder().setName("Snilloc's Snowball Swarm").setStrength(6).setRangeInMeters(30).createSpell();

        Spell spell1 = new Spell("Acid Splash", 5, 27, 5, "Attack");
        Spell spell2 = new Spell("Animal Friendship", 4,5);
        Spell spell3 = new Spell("Anti-magic Field", 5,50);
        Spell spell4 = new Spell("Black Tentacles", 4, 27, 1, "Attack");
        Spell spell5 = new Spell("Snilloc's Snowball Swarm", 6, 30, 5, "Attack");
        Spell spell6 = new Spell("Aura of Vitality", 4, 20, 1, "Heal");

        character1.carryWeapon(longBow);
        character1.carryWeapon(broadSword);
        character2.carryWeapon(shortSword);
        character2.carryWeapon(slingshot);
        character2.carryWeapon(ninjaStars);

        character1.learnSpell(spell1);
        character1.learnSpell(spell2);
        character1.learnSpell(spell6);
        character2.learnSpell(spell1);
        character2.learnSpell(spell3);
        character2.learnSpell(spell4);
        character2.learnSpell(spell5);


        game.addCharacterToGame(character1);
        game.addCharacterToGame(character2);

        game.beginGame();

    }
}
