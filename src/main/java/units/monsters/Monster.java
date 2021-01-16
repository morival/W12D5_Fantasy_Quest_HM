package units.monsters;

import units.Unit;

public class Monster extends Unit {

    MonsterType monsterType;

    public Monster(String name, int attack, int defence, int hp, MonsterType monsterType) {
        super(monsterType.name(), monsterType.getAttack(), monsterType.getDefence(), monsterType.getHp());
        this.monsterType = monsterType;
    }

    @Override
    public int getTotalAttackValue() {
        return getAttack();
    }

    @Override
    public int getTotalDefenceValue() {
        return getDefence();
    }
}
