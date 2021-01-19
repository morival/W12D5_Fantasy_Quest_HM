package units.heroes.mages;


import items.Item;
import items.magic.Spell;
import items.magic.SpellName;
import units.Unit;

import java.util.*;


public class Wizard extends Mage{




    public Wizard(String name, int attack, int defence, int hp, int gold, int mana) {
        super("Wizard", 24, 3, 450, 0, 285);

        createSpells();
    }

    public void createSpells() {
        magicBook.add(new Spell(SpellName.LIGHTNING, 100, 70));
        magicBook.add(new Spell(SpellName.FIREBALL, 75, 45));
        magicBook.add(new Spell(SpellName.BLIZZARD, 40, 30));
    }

    public Spell getRandomSpell() {
        ArrayList<Spell> spellCandidates = new ArrayList<Spell>(magicBook);
        Iterator<Spell> itr = spellCandidates.iterator();

        while(itr.hasNext()) {
            Spell spell = itr.next();
            if(spell.getManaCost() > getMana()) {
                itr.remove();
            }
        }
        if(spellCandidates.isEmpty()) {
            return null;
        }
        else{
            Random rand = new Random();
            return spellCandidates.get(rand.nextInt(spellCandidates.size()));
        }
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
        }
        return returnedDamage;
    }

    @Override
    public int getDamageTo() {
        return 1;
    }

    @Override
    public String getProfession() {
        return "Wizard";
    }
}
