package enums;

public enum CardCategory {
    LEARNING("learning"),
    WORK("work"),
    PERSONAL("personal"),
    HEALTH("health"),
    TRAVEL("travel"),
    FINANCE("finance"),
    CREATIVE("creative"),
    HOME("home"),
    SOCIAL("social");

    private final String value;

    CardCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
