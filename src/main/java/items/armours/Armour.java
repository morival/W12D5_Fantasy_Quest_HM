package items.armours;

import items.Item;

public class Armour extends Item {

    private ArmourType armourType;

    public Armour(String name, ArmourType armourType) {
        super(armourType.name());
        this.armourType = armourType;
    }

    public String getArmourName() {
        return getName();
    }

    public int getDefenceValue() {
        return armourType.getArmourStrength();
    }

    public ArmourType getArmourType() {
        return armourType;
    }

    @Override
    public int getDamageValue() {
        return 0;
    }

    @Override
    public int getAmountOfGold() {
        return 0;
    }
}
