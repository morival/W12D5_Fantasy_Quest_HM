package units;

import items.Item;

import java.util.ArrayList;

public abstract class Unit {
    private String name;
    private int attack;
    private int defence;
    private int hp;

    public Unit(String name, int attack, int defence, int hp) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public void reduceHP(int damage) {
        hp -= damage;
    }

    public abstract int getTotalAttackValue();
    public abstract int getTotalDefenceValue();

    public abstract void addGold(int dividedAmount);

    public abstract void addToInventory(Item item);

    public abstract int inventoryCount();

    public abstract int getDamageTo(Unit defender);
}
