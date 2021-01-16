package units.heroes.warriors;

import units.heroes.Player;

public abstract class Warrior extends Player {

//    private Weapon weapon;
//    private Armour armour;

    public Warrior(String name, int attack, int defence, int hp) {
        super(name, attack, defence, hp);
//        this.weapon = weapon;
//        this.armour = armour;
    }
}
