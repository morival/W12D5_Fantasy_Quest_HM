package units.monsters;

import items.Item;
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

    @Override
    public void addGold(int dividedAmount) {

    }

    @Override
    public void addToInventory(Item item) {

    }

    @Override
    public int inventoryCount() {
        return 0;
    }

    @Override
    public int getDamageTo() {
        return 0;
    }
}
