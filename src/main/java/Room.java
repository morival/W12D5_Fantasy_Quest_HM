import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import items.Item;
import items.treasures.Gold;
import units.Unit;

public class Room {

    private ArrayList<Unit> party;
    private ArrayList<Unit> opponents;
    private ArrayList<Item> treasure;
    private Object Gold;

    public Room() {
        this.party = new ArrayList<Unit>();
        this.opponents = new ArrayList<Unit>();
        this.treasure = new ArrayList<Item>();
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
                unit.addInventory(item);
            }
//            Unit unit2 = team2.get(rand.nextInt(team2.size()));
//            setFightTurn(unit, unit2);
        }
    }

    public void partyLootTreasure() {
        partyLootGold();
        partyLootItems();
        treasure.clear();
    }


    // Duel
    public int getDamageCount(Unit attacker, Unit defender) {
        double defence = 1-(0.06 * defender.getTotalDefenceValue())/(1+(0.06 * defender.getTotalDefenceValue()));
        double damage = attacker.getTotalAttackValue() * defence;
        return (int) damage;
    }

    public void getHPReductionFromDamage(Unit attacker, Unit defender) {
        double defence = 1-(0.06 * defender.getTotalDefenceValue())/(1+(0.06 * defender.getTotalDefenceValue()));
        double damage = attacker.getTotalAttackValue() * defence;
        defender.reduceHP((int) damage);
    }

    public void setFightTurn(Unit a, Unit b) {
        getHPReductionFromDamage(a, b);
        System.out.println( b.getName() + " has " + b.getHp() + " HP after receiving " + getDamageCount(a, b) + " damage from " + a.getName());
    }


    // Battle
    public ArrayList<Unit> setBattle(ArrayList<Unit> team1, ArrayList<Unit> team2) {
        if (team1.size() <= 0 && team2.size() <= 0) {
            System.out.println("No units in both teams");
            return null;
        } else if (team1.size() > 0 && team2.size() <= 0) {
            System.out.println("Your party won!");
            getTeamRemainingHP(team1);
            return team1;
        } else if (team1.size() <= 0 && team2.size() > 0) {
            System.out.println(team2.toString() + "Your party died");
            return null;
        } else {
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
            for (Unit unit : team2) {
                if (team1.size() > 0) {
                    Unit unit1 = team1.get(rand.nextInt(team1.size()));
                    setFightTurn(unit, unit1);
                    if (unit1.getHp() <= 0) {
                        System.out.println(unit1.getName() + " died");
                        team1.remove(unit1);
                    }
                }
            }
            setBattle(team1, team2);
        }
        return team1;
    }

    public void getTeamRemainingHP(ArrayList<Unit> team) {
        for (Unit unit : team) {
            System.out.println(unit.getName() + " survived with " + unit.getHp() + " HP");
        }
    }

    public void setRoom (ArrayList<Unit> team1, ArrayList<Unit> team2) {
        ArrayList<String> teamNames = new ArrayList<>();
        for (Unit unit1 : team1) {
            teamNames.add(unit1.getName());
        }
        System.out.println(Arrays.toString(teamNames.toArray())
                .replace(",", " and")
                .replace("[", "")
                .replace("]", "")
                + " after long journey encountered few monsters on their path.");
        System.out.println("Prepare for battle!");
//        if (setBattle(team1, team2) != null) {
            ArrayList<Unit> team = setBattle(team1, team2);
        System.out.println("Your party found a treasure!");
        ArrayList<String> treasureNames = new ArrayList<>();
        for (Item item : treasure) {
            treasureNames.add(item.getName());
        }
        System.out.println(Arrays.toString(treasureNames.toArray())
                .replace(",", " and")
                .replace("[", "")
                .replace("]", ""));
    }
}
