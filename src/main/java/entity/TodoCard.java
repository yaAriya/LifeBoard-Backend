package entity;


import enums.CompletionCardStatus;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class TodoCard extends Card {
    private CompletionCardStatus status;

    public TodoCard() {
    }

    public TodoCard(Long id, String name, LocalDateTime createdAt, CompletionCardStatus status, User user) {
        super(id, name, createdAt, user);
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getCreatedAt() + "," + status + "," + getUser();
    }
}
