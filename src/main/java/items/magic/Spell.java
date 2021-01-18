package items.magic;

public class Spell {

    SpellName spellName;
    int magicDamage;
    int manaCost;

    public Spell(SpellName spellName, int magicDamage, int manaCost) {
        this.spellName = spellName;
        this.magicDamage = magicDamage;
        this.manaCost = manaCost;
    }

    public SpellName getSpellName() {
        return spellName;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public int getManaCost() {
        return manaCost;
    }

}


