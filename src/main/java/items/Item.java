package items;

public abstract class Item {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getDamageValue();

    public abstract int getDefenceValue();

    public abstract int getAmountOfGold();
}
