import items.armours.Armour;
import items.armours.ArmourType;
import items.weapons.Weapon;
import items.weapons.WeaponType;
import org.junit.Before;
import org.junit.Test;
import units.heroes.warriors.Barbarian;

import static org.junit.Assert.assertEquals;

public class BarbarianTest {

    Weapon weapon;
    Weapon weapon2;
    Armour armour;
    Armour armour2;
    Barbarian barbarian;

    @Before
    public void setUp() {
        weapon = new Weapon("Sword", WeaponType.SWORD);
        weapon2 = new Weapon("Mace", WeaponType.MACE);
        armour = new Armour("Leather", ArmourType.LEATHER);
        armour2 = new Armour("Chain_Mail", ArmourType.CHAIN_MAIL);
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
        barbarian.addToInventory(armour2);
        assertEquals(32,barbarian.getTotalAttackValue());
        barbarian.addToInventory(weapon);
        barbarian.addToInventory(weapon2);
        assertEquals(37,barbarian.getTotalAttackValue());
        assertEquals(3, barbarian.inventoryCount());
    }

    @Test
    public void canAddAndActivateStrongestArmourInInventory() {
        barbarian.addToInventory(weapon);
        assertEquals(1, barbarian.inventoryCount());
        assertEquals(2, barbarian.getTotalDefenceValue());
        barbarian.addToInventory(armour);
        barbarian.addToInventory(armour2);
        assertEquals(3, barbarian.inventoryCount());
        assertEquals(7, barbarian.getTotalDefenceValue());
    }

    @Test
    public void canAutoFitBestWeaponAndArmourFromInventory() {
        barbarian.addToInventory(weapon);
        barbarian.addToInventory(weapon2);
        barbarian.addToInventory(armour2);
        barbarian.addToInventory(armour);
        assertEquals(37, barbarian.getTotalAttackValue());
        assertEquals(7, barbarian.getTotalDefenceValue());
    }

}
