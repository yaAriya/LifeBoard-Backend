package enums;

public enum Language {
    ENGLISH("English"),
    RUSSIAN("Русский"),
    SPANISH("Español"),
    FRENCH("Français");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
