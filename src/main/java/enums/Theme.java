package enums;

public enum Theme {
    LIGHT("Light"),
    DARK("Dark"),
    SYSTEM("System");

    private final String value;

    Theme(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


