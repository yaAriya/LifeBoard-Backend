package enums;

public enum CardType {
    TODO("Todo"),
    TASK("Task"),
    GOAL("Goal");

    private final String value;

    CardType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
