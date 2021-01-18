package units.heroes.warriors;

import items.Item;
import units.Unit;

public class Knight extends Warrior{
    public Knight(String name, int attack, int defence, int hp, int gold) {
        super("Sir Lancelot", 29, 4, 650, 0);
    }


    @Override
    public int getDamageTo(Unit defender) {
        return 0;
    }
}
