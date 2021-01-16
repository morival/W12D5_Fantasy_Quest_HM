package items.weapons;

import items.Item;

public class Weapon extends Item {

    private WeaponType weaponType;

    public Weapon(String name, WeaponType weaponType) {
        super(weaponType.name());
        this.weaponType = weaponType;
    }

    public String getWeaponName() {
        return getName();
    }

    public int getDamageValue() {
        return this.weaponType.getDamageValue();
    }

    public WeaponType getType() {
        return weaponType;
    }
}
