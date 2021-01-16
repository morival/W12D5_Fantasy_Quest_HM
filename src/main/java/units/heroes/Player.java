package units.heroes;
import items.Item;
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

    public Item findWeaponInInventory() {
        Item weapon = Collections.max(inventory, Comparator.comparingInt(Item::getDamageValue));
        return weapon;
    }

    public int getAttackModifierFromWeapon(Item item) {
        return item.getDamageValue();
    }

    public int getTotalAttackValue() {
        int TotalAttackValue = this.getAttack() + getAttackModifierFromWeapon(findWeaponInInventory());
        return TotalAttackValue;
    }
}
