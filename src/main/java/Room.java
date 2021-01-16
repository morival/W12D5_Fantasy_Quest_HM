import java.util.ArrayList;
import units.Unit;
import units.heroes.Player;

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
        getHPReductionFromDamage(b, a);
    }

    public Unit setFightTillDead(Unit a, Unit b) {
        setFightTurn(a, b);
//        System.out.println( a.getName() + " received " + getDamageCount(b, a) + " damage and has " + a.getHp() + " HP.");
//        System.out.println( b.getName() + " received " + getDamageCount(a, b) + " damage and has " + b.getHp() + " HP.");
        if (a.getHp() > 0 && b.getHp() > 0) {
            return setFightTillDead(a, b);
        } else if (a.getHp() <= 0) {
            System.out.println(b.getName() + " won!");
            return b;
        } else if (b.getHp() <= 0) {
            System.out.println(a.getName() + " won!");
            return a;
        } else {
            System.out.println("Error");
            return null;
        }
    }

    public String setEnemies(ArrayList<Unit> team1, ArrayList<Unit> team2){
        
    }


}
