package units.heroes.mages;


import items.magic.Spell;
import items.magic.SpellName;
import units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Wizard extends Mage{




    public Wizard(String name, int attack, int defence, int hp, int gold, int mana) {
        super("Wizard", 24, 3, 450, 0, 285);

        createSpells();
    }

    public void createSpells() {
        magicBook.add(new Spell(SpellName.LIGHTNING, 75, 70));
        magicBook.add(new Spell(SpellName.FIREBALL, 50, 45));
        magicBook.add(new Spell(SpellName.BLIZZARD, 40, 30));
    }

    public Spell getRandomSpell() {
        Spell randomSpell = null;
        Random rand = new Random();
        Spell spell = magicBook.get(rand.nextInt(magicBookCount()));
        if (getMana() > spell.getManaCost()) {
            randomSpell = spell;
        } else {
            getRandomSpell();
        }
        return randomSpell;
    }

    public int getCheapestManaSpellCost() {
        Spell cheapestSpell = Collections.min(magicBook, Comparator.comparingInt(Spell::getManaCost));
        return cheapestSpell.getManaCost();
    }

    public boolean chooseIfUseMagic() {
        Random rand = new Random();
        if (getMana() > getCheapestManaSpellCost()) {
            return rand.nextBoolean();
        } else {
            return false;
        }
    }
    public void selectAttackType() {
        if (chooseIfUseMagic()) {
            getRandomSpell();
        } else {

        }
    }

    @Override
    public int getDamageTo() {
        return 1;
//        return this.getTotalAttackValue();
    }
}
