package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Card {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private User user;

    public Card() {
    }

    public Card(long id, String name, LocalDateTime createdAt, User user) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.user = user;
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
        if ((obj == null) || !(obj instanceof Card)) {
            return false;
        }
        Card card = (Card) obj;
        return card.id == id;
    }

    @Override
    public String toString() {
        return name + "," + name + "," + createdAt + "," + user;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
