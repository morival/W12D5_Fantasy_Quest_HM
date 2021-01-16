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

    public void setFightTillKill(Unit a, Unit b) {
        setFightTurn(a, b);
//        System.out.println( "Hero received " + getUnit2Damage(unit2) + " damage and has " + unit1.getHP() + " HP.");
//        System.out.println( "Enemy received " + getUnit1Damage(unit1) + " damage and has " + unit2.getHP() + " HP.")
    }


}
