package enums;

public enum DeviceStatus {
    OFFLINE("Offline"),
    ONLINE("Online");

    private final String value;

    DeviceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
