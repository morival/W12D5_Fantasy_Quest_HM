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
        Spell selectedSpell = null;
        Random rand = new Random();
        Spell randomSpell = magicBook.get(rand.nextInt(magicBookCount()));
        if (getMana() < randomSpell.getManaCost()) {
            getRandomSpell();
        } else if (getMana() >= randomSpell.getManaCost()) {
            selectedSpell = randomSpell;
        }
        return selectedSpell;
    }

    public int getCheapestManaSpellCost() {
        Spell cheapestSpell = Collections.min(magicBook, Comparator.comparingInt(Spell::getManaCost));
        return cheapestSpell.getManaCost();
    }

    public boolean checkIfCanCastAnySpell() {
        if (getMana() >= getCheapestManaSpellCost()) {
            return true;
        } else {
            return false;
        }
    }
    public int selectAttackType() {
        int returnedDamage = 0;
        if (checkIfCanCastAnySpell() == true) {
            Spell selectedSpell = getRandomSpell();
            reduceMana(selectedSpell);
            System.out.println(selectedSpell.getSpellName() + " - " + selectedSpell.getMagicDamage() + "   Wizard's Mana Points: " + getMana());
            returnedDamage = selectedSpell.getMagicDamage();
        } else if (checkIfCanCastAnySpell() == false){

        }
        return returnedDamage;
    }

    @Override
    public int getDamageTo() {
        return 1;
    }
}
