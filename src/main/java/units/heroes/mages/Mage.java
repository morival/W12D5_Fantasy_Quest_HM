package units.heroes.mages;

import items.magic.MagicBook;
import units.heroes.Player;

public abstract class Mage extends Player {

    int mana;
//    MagicBook magicBook;

    public Mage(String name, int attack, int defence, int hp, int gold, int mana) {
        super(name, attack, defence, hp, gold);
        this.mana = mana;
//        this.magicBook = magicBook;

    }
}
