package units.heroes.warriors;

import items.Item;

public class Knight extends Warrior{
    public Knight(String name, int attack, int defence, int hp, int gold) {
        super("Sir Lancelot", 29, 4, 650, 0);
    }

    @Override
    public void addInventory(Item item) {

    }
}
