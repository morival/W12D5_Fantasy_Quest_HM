package units.heroes.mages;


import items.magic.Spell;
import items.magic.SpellName;
import units.Unit;

import java.util.ArrayList;


public class Wizard extends Mage{

    private ArrayList<Spell> magicBook;


    public Wizard(String name, int attack, int defence, int hp, int gold, int mana) {
        super("Wizard", 24, 3, 450, 0, 285);
        this.magicBook = new ArrayList<Spell>();
        createSpells();
    }

    public void createSpells() {
        magicBook.add(new Spell(SpellName.LIGHTNING, 75, 70));
        magicBook.add(new Spell(SpellName.FIREBALL, 50, 45));
        magicBook.add(new Spell(SpellName.BLIZZARD, 40, 30));
    }


    @Override
    public int getDamageTo(Unit defender) {
        return this.getTotalAttackValue();
    }
}
