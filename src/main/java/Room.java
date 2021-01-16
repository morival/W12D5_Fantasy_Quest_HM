import java.util.ArrayList;
import units.Unit;

public class Room {

    private ArrayList<Unit> party;
    private ArrayList<Unit> opponents;

    public Room() {
        this.party = new ArrayList<Unit>();
        this.opponents = new ArrayList<Unit>();
    }


    public ArrayList<Unit> getParty() {
        return party;
    }

    public ArrayList<Unit> getOpponents() {
        return opponents;
    }
}
