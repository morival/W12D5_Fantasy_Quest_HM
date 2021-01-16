import items.weapons.Weapon;
import items.weapons.WeaponType;
import org.junit.Before;
import org.junit.Test;
import units.heroes.warriors.Barbarian;

import static org.junit.Assert.assertEquals;

public class BarbarianTest {

    Barbarian barbarian;
    Weapon weapon;
    Weapon weapon2;

    @Before
    public void setUp() {
        weapon = new Weapon("Sword", WeaponType.SWORD);
        weapon2 = new Weapon("Mace", WeaponType.MACE);
        barbarian = new Barbarian("Barbarian", 32, 2, 725);
    }

    @Test
    public void hasName() {
        assertEquals("Barbarian", barbarian.getName());
    }

    @Test
    public void hasAttackValue() {
        assertEquals(32, barbarian.getAttack());
    }

    @Test
    public void hasDefenceValue() {
        assertEquals(2, barbarian.getDefence());
    }

    @Test
    public void hasHPValue() {
        assertEquals(725, barbarian.getHp());
    }

    @Test
    public void canAddWeaponToInventory() {
        assertEquals(0, barbarian.inventoryCount());
        barbarian.addToInventory(weapon);
        assertEquals(weapon, barbarian.getInventory().get(0));
        assertEquals(1, barbarian.inventoryCount());
        assertEquals("SWORD", barbarian.getInventory().get(0).getName());
        assertEquals(5, barbarian.getInventory().get(0).getDamageValue());
    }


    @Test
    public void canActivateStrongestWeaponInInventory() {
        barbarian.addToInventory(weapon);
        barbarian.addToInventory(weapon2);
        assertEquals(37,barbarian.getTotalAttackValue());
        assertEquals(2, barbarian.inventoryCount());
    }
}
