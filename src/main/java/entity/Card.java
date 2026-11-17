package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Card() {
    }

    public Card(Long id, String name, String content, LocalDateTime createdAt, User user) {
        this.id = id;
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
