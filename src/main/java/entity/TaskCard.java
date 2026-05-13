package entity;

import enums.CardStatus;

import java.time.LocalDateTime;

public class TaskCard extends Card {
    private LocalDateTime dueDateTime;
    private CardStatus status;

    public TaskCard() {
    }

    public TaskCard(long id, String name, LocalDateTime dueDateTime, LocalDateTime createdAt, CardStatus status, User user) {
        super(id, name, createdAt, user);
        this.dueDateTime = dueDateTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + dueDateTime + "," + getCreatedAt() + "," + status + "," + getUser();
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
