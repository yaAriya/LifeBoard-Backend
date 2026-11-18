package enums;

import jakarta.persistence.EnumeratedValue;

public enum Language {
    ENGLISH("English"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    FRENCH("French");

    @EnumeratedValue
    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
