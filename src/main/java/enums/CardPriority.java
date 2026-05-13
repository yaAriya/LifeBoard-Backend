package enums;

public enum CardPriority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String value;

    CardPriority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
