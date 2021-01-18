import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import items.Item;
import units.Unit;

public class Room {

    private ArrayList<Unit> party;
    private ArrayList<Unit> opponents;
    private ArrayList<Item> treasure;
    private String story;

    public Room(String story) {
        this.party = new ArrayList<Unit>();
        this.opponents = new ArrayList<Unit>();
        this.treasure = new ArrayList<Item>();
        this.story = story;
    }


    // Party
    public ArrayList<Unit> getParty() {
        return party;
    }

    public void addUnitToParty(Unit unit) {
        party.add(unit);
    }

    public void removeUnitFromParty(Unit unit) {
        if (party.contains(unit)) {
            party.remove(unit);
        } else {
            System.out.println("Unit is not a member of the Party");
        }
    }

    public int partyCount(){
        return party.size();
    }


    // Opponents
    public ArrayList<Unit> getOpponents(){
        return opponents;
    }

    public void addUnitToOpponents(Unit unit) {
        opponents.add(unit);
    }

    public void removeUnitFromOpponents(Unit unit) {
        if (opponents.contains(unit)) {
            opponents.remove(unit);
        } else {
            System.out.println("Unit is not an opponent");
        }
    }

    public int opponentsCount(){
        return opponents.size();
    }


    // Treasure
    public ArrayList<Item> getTreasure() {
        return treasure;
    }

    public void addItemToTreasure(Item item) {
        treasure.add(item);
    }

    public void removeItemFromTreasure(Item item) {
        if (treasure.contains(item)) {
            treasure.remove(item);
        } else {
            System.out.println("Item is not part of the treasure");
        }
    }

    public int itemsOwnedByHeroes() {
        int TotalItemCount = 0;
        for (Unit unit : party) {
            TotalItemCount += unit.inventoryCount();
        } return TotalItemCount;
    }

    public int treasureCount() {
        return treasure.size();
    }

    public int treasureGoldCount() {
        int amountOfGold = 0;
        for(Item item : treasure) {
            if (item.getName().contains("Gold") ) {
                amountOfGold = item.getAmountOfGold();
            }
        }
        return amountOfGold;
    }


    // Loot
    public void partyLootGold() {
        int dividedAmount = treasureGoldCount() / party.size();
        for (Unit unit : party) {
            unit.addGold(dividedAmount);
        };
    }


    public void partyLootItems() {
        for (Item item : treasure) {
            Random rand = new Random();
            Unit unit = party.get(rand.nextInt(party.size()));
            if (!item.getName().contains("Gold") ) {
                unit.addToInventory(item);
            }
        }
    }

    public void partyLootTreasure() {
        partyLootGold();
        partyLootItems();
        treasure.clear();
    }


    // Duel
    public int getPhysicalDamage(Unit attacker, Unit defender) {
        int physicalDamage = 0;
            double defence = 1 - (0.06 * defender.getTotalDefenceValue()) / (1 + (0.06 * defender.getTotalDefenceValue()));
            double damage = attacker.getTotalAttackValue() * defence;
            physicalDamage += (int) damage;
        return physicalDamage;
    }

    public int getDamageCount(Unit attacker, Unit defender) {
        int damageCount = 0;
        if (attacker.getProfession() == "Wizard") {
            Random rand = new Random();
            if (rand.nextBoolean() == true) {
                int magicAttack = attacker.selectAttackType();
                if (magicAttack != 0) {
                    damageCount += magicAttack;
                } else {
                    damageCount += getPhysicalDamage(attacker, defender);
                }
            } else {
                damageCount += getPhysicalDamage(attacker, defender);
            }
        } else {
            damageCount += getPhysicalDamage(attacker, defender);
        }
        System.out.println( defender.getName() + " has " + (defender.getHp()-damageCount) + " HP after receiving " + damageCount + " damage from " + attacker.getName());
        return damageCount;
    }

    public void getHPReductionFromDamage(Unit attacker, Unit defender) {
        defender.reduceHP(getDamageCount(attacker, defender));
    }

    public void setFightTurn(Unit a, Unit b) {
        getHPReductionFromDamage(a, b);
    }


    // Battle
    public void attackRandomEnemy(ArrayList<Unit> team1, ArrayList<Unit> team2) {
        Random rand = new Random();
        for (Unit unit : team1) {
            if (team2.size() > 0) {
                Unit unit2 = team2.get(rand.nextInt(team2.size()));
                setFightTurn(unit, unit2);
                if (unit2.getHp() <= 0) {
                    System.out.println(unit2.getName() + " died");
                    team2.remove(unit2);
                }
            }
        }
    }

    public ArrayList<Unit> setBattle(ArrayList<Unit> team1, ArrayList<Unit> team2) {
         if (team1.size() > 0 && team2.size() <= 0) {
            System.out.println("Your party won!");
            getTeamRemainingHP(team1);
            return team1;
        } else if (team1.size() <= 0 && team2.size() > 0) {
            System.out.println("Your party is dead");
            return null;
        } else {
            attackRandomEnemy(team1, team2);
            attackRandomEnemy(team2, team1);
            setBattle(team1, team2);
        }
        return team1;
    }

    public void getTeamRemainingHP(ArrayList<Unit> team) {
        for (Unit unit : team) {
            System.out.println(unit.getName() + " survived with " + unit.getHp() + " HP");
        }
    }

    public void setQuest (ArrayList<Unit> team1, ArrayList<Unit> team2, String story) {
        ArrayList<String> partyNames = new ArrayList<>();
        for (Unit unit1 : team1) {
            partyNames.add(unit1.getName());
        }
        System.out.println(Arrays.toString(partyNames.toArray())
                .replace(",", " and")
                .replace("[", "")
                .replace("]", "")
                + story);
//        if (setBattle(team1, team2) != null) {
            ArrayList<Unit> team = setBattle(team1, team2);
        if (team1.size() > 0) {
            System.out.println("Your party found a treasure!");
            ArrayList<String> treasureNames = new ArrayList<>();
            for (Item item : treasure) {
                treasureNames.add(item.getName());
            }
            System.out.println(Arrays.toString(treasureNames.toArray())
                    .replace(",", " and")
                    .replace("[", "")
                    .replace("]", ""));
            partyLootTreasure();
        } else {
            System.out.println("GAME OVER");
        }
    }


}
