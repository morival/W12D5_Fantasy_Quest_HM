package units.monsters;

public enum MonsterType {

    TROLL("Troll", 18, 0, 300),
    KOBOLD("Kobold", 13, 1, 325),
    GIANT_SPIDER("Giant Spider", 19, 1, 550),
    OGRE("Ogre", 14, 1, 400),
    HYDRA("Hydra", 28, 0, 575),
    DRAGON("Dragon", 60, 6, 2200);

    private final String name;
    private final int attack;
    private final int defence;
    private final int hp;

    MonsterType(String name, int attack, int defence, int hp){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hp = hp;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }
}
