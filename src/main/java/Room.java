import java.util.ArrayList;
import java.util.Random;
import units.Unit;

public class Room {

    private ArrayList<Unit> party;
    private ArrayList<Unit> opponents;

    public Room() {
        this.party = new ArrayList<Unit>();
        this.opponents = new ArrayList<Unit>();
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
    public ArrayList<Unit> getOpponents() {
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
        getHPReductionFromDamage(b, a);
        System.out.println( a.getName() + " has " + a.getHp() + " HP after receiving " + getDamageCount(b, a) + " damage from " + b.getName());
    }


    // Battle
    public ArrayList<Unit> setEnemies(ArrayList<Unit> team1, ArrayList<Unit> team2){
        ArrayList<Unit> victoriousTeam = null;
        if (team1.size() <= 0 && team2.size() <= 0) {
            System.out.println("No units in both teams");
            return null;
        } else if (team1.size() > 0 && team2.size() <= 0) {
            System.out.println("Your party won!");
            victoriousTeam = team1;
            return victoriousTeam;
        } else if (team1.size() <= 0 && team2.size() > 0) {
            System.out.println(team2.toString() + "Your party died");
            victoriousTeam = team2;
            return victoriousTeam;
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
            setEnemies(team1, team2);
        } return null;
    }

    public void setRoom (ArrayList<Unit> team1, ArrayList<Unit> team2) {
        if (setEnemies(team1, team2) == team1) {

        }
    }
}
