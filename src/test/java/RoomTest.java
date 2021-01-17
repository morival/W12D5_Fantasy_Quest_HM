import items.armours.Armour;
import items.armours.ArmourType;
import items.treasures.Gold;
import items.weapons.Weapon;
import items.weapons.WeaponType;
import org.junit.Before;
import org.junit.Test;
import units.heroes.Player;
import units.heroes.warriors.Barbarian;
import units.heroes.warriors.Dwarf;
import units.monsters.Monster;
import units.monsters.MonsterType;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    Weapon weapon1;
    Weapon weapon2;
    Weapon weapon3;
    Weapon weapon4;
    Armour armour1;
    Armour armour2;
    Armour armour3;
    Armour armour4;
    Gold gold;
    Player barbarian;
    Player dwarf;
    Monster ogre;
    Monster troll;
    Monster kobold;
    Monster giantSpider;
    Monster dragon;
    Room room;

    @Before
    public void setUp() {
        weapon1 = new Weapon("Mace", WeaponType.MACE);
        weapon2 = new Weapon("Dagger", WeaponType.DAGGER);
        weapon3 = new Weapon("War Hammer", WeaponType.WAR_HAMMER);
        weapon4 = new Weapon("Battleaxe", WeaponType.BATTLEAXE);
        armour1 = new Armour("Hide", ArmourType.HIDE);
        armour2 = new Armour("Leather", ArmourType.LEATHER);
        armour3 = new Armour("Breast Plate", ArmourType.BREST_PLATE);
        armour4 = new Armour("Chain Mail", ArmourType.CHAIN_MAIL);
        gold = new Gold("Gold", 200);
        barbarian = new Barbarian("Barbarian", 32, 2, 725, 0);
        dwarf = new Dwarf("Mountain King", 31, 2, 700, 0);
        ogre = new Monster("Ogre", 14, 1, 400, MonsterType.OGRE);
        troll = new Monster("TROLL", 18, 0, 300, MonsterType.TROLL);
        kobold = new Monster("KOBOLD", 13, 1, 325, MonsterType.KOBOLD);
        giantSpider = new Monster("GIANT SPIDER", 19, 1, 550, MonsterType.GIANT_SPIDER);
        dragon = new Monster("DRAGON", 60, 6, 2200, MonsterType.DRAGON);
        room = new Room();
    }
    public void equipBarbarian() {
        barbarian.addToInventory(weapon1);
        barbarian.addToInventory(armour2);
    }

    public void equipDwarf() {
        dwarf.addToInventory(weapon3);
        dwarf.addToInventory(armour3);
    }

    public void setParty() {
        equipBarbarian();
        equipDwarf();
        room.addUnitToParty(barbarian);
        room.addUnitToParty(dwarf);
    }

    public void setOpponents() {
        room.addUnitToOpponents(ogre);
        room.addUnitToOpponents(troll);
        room.addUnitToOpponents(kobold);
        room.addUnitToOpponents(giantSpider);
    }

    public void setTreasure() {
        room.addItemToTreasure(gold);
        room.addItemToTreasure(weapon4);
        room.addItemToTreasure(armour4);
    }


    // Tests
    @Test
    public void canCheckStats() {
        assertEquals("OGRE", ogre.getName());
        assertEquals(14, ogre.getAttack());
        assertEquals(1, ogre.getDefence());
        assertEquals(400, ogre.getHp());
        assertEquals("Mountain King", dwarf.getName());
        assertEquals(31, dwarf.getAttack());
        assertEquals(2, dwarf.getDefence());
        assertEquals(700, dwarf.getHp());
    }

    @Test
    public void canAddAndRemoveUnit() {
        setParty();
        setOpponents();
        room.removeUnitFromParty(barbarian);
        room.removeUnitFromOpponents(ogre);
        assertEquals(1, room.partyCount());
        assertEquals(3, room.opponentsCount());
        assertEquals(dwarf, room.getParty().get(0));
        assertEquals(troll, room.getOpponents().get(0));
        assertEquals(kobold, room.getOpponents().get(1));
    }

    @Test
    public void canAddAndRemoveItemFromTreasure() {
        room.addItemToTreasure(armour1);
        room.addItemToTreasure(weapon2);
        assertEquals(2, room.treasureCount());
        room.removeItemFromTreasure(armour1);
        assertEquals(1, room.treasureCount());
    }

    @Test
    public void canSetTreasureGold() {
        assertEquals(0, room.treasureGoldCount());
        room.addItemToTreasure(gold);
        assertEquals(200, room.treasureGoldCount());
        assertEquals(1, room.treasureCount());
    }

    @Test
    public void canShareGoldBetweenHeroes() {
        room.addItemToTreasure(gold);
        room.addUnitToParty(barbarian);
        room.addUnitToParty(dwarf);
        assertEquals(200, room.treasureGoldCount());
        assertEquals(0, barbarian.getGold());
        assertEquals(0, dwarf.getGold());
        room.partyLootTreasure();
        assertEquals(0, room.treasureGoldCount());
        assertEquals(100, barbarian.getGold());
        assertEquals(100, dwarf.getGold());
    }

    @Test
    public void canShareTreasuresBetweenHeroes() {
        setParty();
        setTreasure();
        assertEquals(3, room.treasureCount());
        assertEquals(4, room.itemsOwnedByHeroes());
        room.partyLootTreasure();
        assertEquals(6, room.itemsOwnedByHeroes());
        assertEquals(0, room.treasureCount());

    }

    @Test
    public void canReduceHP() {
        room.getHPReductionFromDamage(barbarian, troll);
        assertEquals(268, troll.getHp());
    }

    @Test
    public void canSetTeamsForCombat() {
        setParty();
        setTreasure();
        System.out.println();
        setOpponents();
        room.setRoom(room.getParty(), room.getOpponents());
        assertEquals(2, room.partyCount());
        assertEquals(0, room.opponentsCount());
    }

}
