package enums;

public enum DeviceType {
    LAPTOP("Laptop"),
    TABLET("Tablet"),
    SMARTPHONE("Smartphone"),
    DESKTOP("Decktop");

    private final String value;

    DeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
