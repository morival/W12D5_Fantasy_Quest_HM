package items.potions;

public enum PotionType {

    HEALING_POTION("Healing Potion", 200, "healing"),
    DURABILITY_POTION("Durability Potion", 2, "defence"),
    AUDACITY_POTION("Audacity Potion", 5, "attack");


    private final String name;
    private final int value;
    private final String effect;

    PotionType(String name, int value, String effect) {
        this.name = name;
        this.value = value;
        this.effect = effect;
    }
}
