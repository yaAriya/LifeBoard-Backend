package entity;


import enums.CompletionCardStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@DiscriminatorValue("TODO")
@Entity
public class TodoCard extends Card {
    private CompletionCardStatus status;

    public TodoCard() {
    }

    public TodoCard(Long id, String name, String content, LocalDateTime createdAt, CompletionCardStatus status, User user) {
        super(id, name, content, createdAt, user);
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getContent() + "," + getCreatedAt() + "," + status + "," + getUser();
    }

    public CompletionCardStatus getStatus() {
        return status;
    }

    public void setStatus(CompletionCardStatus status) {
        this.status = status;
    }
}
