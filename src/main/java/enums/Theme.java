package enums;

import jakarta.persistence.EnumeratedValue;

public enum Theme {
    LIGHT("Light"),
    DARK("Dark"),
    SYSTEM("System");
    @EnumeratedValue
    private final String value;

    Theme(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


