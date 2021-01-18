package units.heroes.mages;

import items.magic.Spell;
import units.heroes.Player;

import java.util.ArrayList;

public abstract class Mage extends Player {

    int mana;
    ArrayList<Spell> magicBook;

    public Mage(String name, int attack, int defence, int hp, int gold, int mana) {
        super(name, attack, defence, hp, gold);
        this.mana = mana;
        this.magicBook = new ArrayList<Spell>();
    }

    public int getMana() {
        return mana;
    }

    public ArrayList<Spell> getMagicBook() {
        return magicBook;
    }

    public int magicBookCount() {
        return magicBook.size();
    }

    public void addSpell(Spell spell) {
        magicBook.add(spell);
    }

    public void removeSpell(Spell spell) {
        magicBook.remove(spell);
    }
}
