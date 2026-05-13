package entity;

import enums.Language;
import enums.Theme;
import enums.Timezone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String email;
    private int password;
    private Theme theme;
    private Language language;
    private Timezone timezone;
    private List<Card> cards;
    private List<Device> devices;

    public User() {
    }

    public User(long id, String name, String email, int password, Theme theme, Language language, Timezone timezone, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.theme = theme;
        this.language = language;
        this.timezone = timezone;
        this.devices = devices;
        this.cards = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return user.id == id;
    }

    @Override
    public String toString() {
        List<Long> devicesId = getDevices().stream()
                .map(Device::getId)
                .sorted()
                .toList();
        List<Long> cardsId = getCards().stream()
                .map(Card::getId)
                .sorted()
                .toList();

        return name + "," + email + "," + password + "," + theme + "," + language + "," + "(" + cardsId + ")" + "(" + devicesId + ")";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
