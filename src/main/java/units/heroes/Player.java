package units.heroes;
import items.Item;
import items.armours.Armour;
import items.armours.ArmourType;
import items.weapons.Weapon;
import items.weapons.WeaponType;
import units.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class Player extends Unit{

    ArrayList<Item> inventory;

    public Player(String name, int attack, int defence, int hp) {
        super(name, attack, defence, hp);
        this.inventory = new ArrayList<Item>();
    }

    // Inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int inventoryCount() {
        return inventory.size();
    }

    public void addToInventory(Item item) {
        if(inventory.size() > 6) {
            return;
        } else {
            inventory.add(item);
        }
    }

    //Weapon Inventory
    public Item findWeaponInInventory() {
        if(inventoryCount() > 0) {
            Item weapon = Collections.max(inventory, Comparator.comparingInt(Item::getDamageValue));
            return weapon;
        } else {
            Item non = new Weapon("No Weapon", WeaponType.NON);
            return non;
        }
    }

    public int getTotalAttackValue() {
        int TotalAttackValue = this.getAttack() + findWeaponInInventory().getDamageValue();
        return TotalAttackValue;
    }

    // Armour Inventory
    public Item findArmourInInventory() {
        if(inventoryCount() > 0) {
            Item armour = Collections.max(inventory, Comparator.comparingInt(Item::getDefenceValue));
            return armour;
        } else {
            Item non = new Armour("No Armour", ArmourType.NON);
            return non;
        }
    }

    public int getTotalDefenceValue() {
        int TotalDefenceValue = this.getDefence() + findArmourInInventory().getDefenceValue();
        return TotalDefenceValue;
    }

}
