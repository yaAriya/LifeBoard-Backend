package entity;

import enums.Language;
import enums.Theme;
import enums.Timezone;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'LIGHT'")
    private Theme theme = Theme.LIGHT;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ENGLISH'")
    private Language language = Language.ENGLISH;

    @Enumerated(EnumType.STRING)
    @Column(name = "timezone", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'UTC_M01'")
    private Timezone timezone = Timezone.UTC_M01;

    @OneToMany(mappedBy = "user")
    private List<Card> cards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Device> devices = new ArrayList<>();

    public User() {
    }

    public User(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, int age, String email, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, int age, String email, String password,
                Theme theme, Language language, Timezone timezone, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.age = age;
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
        return name + "," + email + "," + password + "," + theme + "," + language + ",";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
