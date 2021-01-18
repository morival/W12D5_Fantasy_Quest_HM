package units.heroes.mages;

import units.heroes.Player;

public abstract class Mage extends Player {

    int mana;

    public Mage(String name, int attack, int defence, int hp, int gold, int mana) {
        super(name, attack, defence, hp, gold);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }
}
