package items.magic;

public enum ElementalSpells {

    LIGHTNING(75, 75),
    FIREBALL(50, 50),
    BLIZZARD(30, 20);

    private final int damageValue;
    private final int manaCost;

    ElementalSpells(int damageValue, int manaCost) {
        this.damageValue = damageValue;
        this.manaCost = manaCost;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public int getManaCost() {
        return manaCost;
    }

}
