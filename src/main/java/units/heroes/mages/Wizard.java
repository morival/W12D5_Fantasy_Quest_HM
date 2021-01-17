package units.heroes.mages;

import items.magic.MagicBook;
import items.magic.ElementalMagic;
import items.magic.ElementalSpells;


public class Wizard extends Mage{

    ElementalSpells elementalSpells;

    public Wizard(String name, int attack, int defence, int hp, int gold, int mana, ElementalSpells elementalSpells) {
        super("Wizard", 24, 3, 450, 0, 285);
        this.elementalSpells = elementalSpells;
    }
}
