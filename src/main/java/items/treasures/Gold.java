package items.treasures;

import items.Item;

public class Gold extends Item {

    int amountOfGold;

    public Gold(String name, int amountOfGold) {
        super(amountOfGold + " Gold");
        this.amountOfGold = amountOfGold;
    }

    public int getAmountOfGold() {
        return amountOfGold;
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
