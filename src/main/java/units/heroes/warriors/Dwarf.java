package units.heroes.warriors;


import units.Unit;

public class Dwarf extends Warrior{

    public Dwarf(String name, int attack, int defence, int hp, int gold) {
        super("Mountain King", 31, 2, 700, 0);
    }


    @Override
    public int getDamageTo() {
        return 0;
    }

    @Override
    public int selectAttackType() {
        return 0;
    }
}
