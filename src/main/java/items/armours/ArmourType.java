package items.armours;

public enum ArmourType {

    NON(0),
    HIDE(1),
    LEATHER(2),
    SCALE_MAIL(3),
    BREST_PLATE(4),
    CHAIN_MAIL(5);

    private final int armourStrength;

    ArmourType(int armourStrength){
        this.armourStrength = armourStrength;
    }

    public int getArmourStrength(){
        return armourStrength;
    }
}
