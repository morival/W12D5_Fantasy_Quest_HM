package items.potions;

import items.Item;

public class Potion extends Item {

    PotionType potionType;

    public Potion(String name, PotionType potionType) {
        super(potionType.name());
        this.potionType = potionType;
    }

    @Override
    public int getDamageValue() {
        return 0;
    }

    @Override
    public int getDefenceValue() {
        return 0;
    }
}
