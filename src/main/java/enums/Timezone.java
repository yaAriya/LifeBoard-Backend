package enums;

public enum Timezone {
    UTC_M12("UTC-12:00"),
    UTC_M11("UTC-11:00"),
    UTC_M10("UTC-10:00"),
    UTC_M09("UTC-09:00"),
    UTC_M08("UTC-08:00"),
    UTC_M07("UTC-07:00"),
    UTC_M06("UTC-06:00"),
    UTC_M05("UTC-05:00"),
    UTC_M04("UTC-04:00"),
    UTC_M03("UTC-03:00"),
    UTC_M02("UTC-02:00"),
    UTC_M01("UTC-01:00"),
    UTC_P00("UTC+00:00"),
    UTC_P01("UTC+01:00"),
    UTC_P02("UTC+02:00"),
    UTC_P03("UTC+03:00"),
    UTC_P04("UTC+04:00"),
    UTC_P05("UTC+05:00"),
    UTC_P06("UTC+06:00"),
    UTC_P07("UTC+07:00"),
    UTC_P08("UTC+08:00"),
    UTC_P09("UTC+09:00"),
    UTC_P10("UTC+10:00"),
    UTC_P11("UTC+11:00"),
    UTC_P12("UTC+12:00");

    private final String value;

    Timezone(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
