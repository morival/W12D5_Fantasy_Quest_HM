package units.heroes.warriors;


import units.Unit;

public class Barbarian extends Warrior{

    public Barbarian(String name, int attack, int defence, int hp, int gold) {
        super("Barbarian", 32, 2, 725, 0);
    }


    @Override
    public int getDamageTo() {
        return 0;
    }
}
