import items.armours.Armour;
import items.armours.ArmourType;
import items.treasures.Gold;
import items.weapons.Weapon;
import items.weapons.WeaponType;
import org.junit.Before;
import org.junit.Test;
import units.Unit;
import units.heroes.Player;
import units.heroes.mages.Wizard;
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
    Gold gold2;
    Player barbarian;
    Player dwarf;
    Player wizard;
    Monster ogre;
    Monster troll;
    Monster kobold;
    Monster giantSpider;
    Monster dragon;
    String story1;
    String story2;
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
        gold2 = new Gold("Gold", 10000);
        barbarian = new Barbarian("Barbarian", 32, 2, 725, 0);
        dwarf = new Dwarf("Mountain King", 31, 2, 700, 0);
        wizard = new Wizard("Wizard", 24, 3, 450, 0, 285);
        ogre = new Monster("Ogre", 14, 1, 400, MonsterType.OGRE);
        troll = new Monster("TROLL", 18, 0, 300, MonsterType.TROLL);
        kobold = new Monster("KOBOLD", 13, 1, 325, MonsterType.KOBOLD);
        giantSpider = new Monster("GIANT SPIDER", 19, 1, 550, MonsterType.GIANT_SPIDER);
        dragon = new Monster("DRAGON", 60, 6, 2200, MonsterType.DRAGON);
        story1 = " traveled far beyond the Misty Mountains. After a long journey they encountered few monsters on their path. Prepare for battle!";
        story2 = " reached the Dragon Liar. The greed pushed them against something they never fought before...";
        room = new Room(story1);
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

    public void setParty2() {
        equipBarbarian();
        equipDwarf();
        room.addUnitToParty(barbarian);
        room.addUnitToParty(dwarf);
        room.addUnitToParty(wizard);
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

    public void setRoom() {
        setParty();
        setOpponents();
        setTreasure();
        room.setQuest(room.getParty(), room.getOpponents(), story1);
    }

    public void setRoom2() {
        setParty2();
        room.addUnitToOpponents(dragon);
        room.addItemToTreasure(gold2);
        room.setQuest(room.getParty(), room.getOpponents(), story2);
    }

    public void setRoom3() {
        room.addUnitToParty(wizard);
        room.addUnitToOpponents(troll);
        room.setQuest(room.getParty(), room.getOpponents(), story1);
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
        assertEquals(2, room.partyCount());
        assertEquals(3, room.treasureCount());
        assertEquals(2, barbarian.inventoryCount());
        assertEquals(2, dwarf.inventoryCount());
        assertEquals(4, room.itemsOwnedByHeroes());
        room.partyLootTreasure();
        assertEquals(barbarian, room.getParty().get(0));
        assertEquals(6, room.itemsOwnedByHeroes());
        assertEquals(0, room.treasureCount());
        assertEquals(100, barbarian.getGold());
        assertEquals(100, dwarf.getGold());
    }

//    @Test
//    public void canReduceHP() {
//        room.getHPReductionFromDamage(barbarian, troll);
//        assertEquals(268, troll.getHp());
//    }

    @Test
    public void canGetPhysicalDamage() {
        assertEquals(31, room.getPhysicalDamage(dwarf, troll));
        assertEquals(30, room.getPhysicalDamage(barbarian, kobold));
    }




    // GAME TEST

//    @Test
//    public void canSetTeamsForCombat() {
//        setRoom();
//        assertEquals(2, room.partyCount());
//        assertEquals(0, room.opponentsCount());
//        assertEquals(6, room.itemsOwnedByHeroes());
//        assertEquals(0, room.treasureCount());
//        assertEquals(100, barbarian.getGold());
//        assertEquals(100, dwarf.getGold());
//    }

    @Test
    public void canOverkillWithDragon() {
        setRoom2();
        assertEquals(0, room.opponentsCount());
    }

//    @Test
//    public void canRandomlyThrowSpells() {
//        setRoom3();
//        assertEquals(1, room.partyCount());
//        assertEquals(0, room.opponentsCount());
//    }

}
