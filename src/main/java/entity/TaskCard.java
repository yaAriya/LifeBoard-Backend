package entity;

import enums.ProgressCardStatus;

import java.time.LocalDateTime;

public class TaskCard extends Card {
    private LocalDateTime deadline;
    private ProgressCardStatus status;

    public TaskCard() {
    }

    public TaskCard(long id, String name, LocalDateTime dueDateTime, LocalDateTime createdAt, ProgressCardStatus status, User user) {
        super(id, name, createdAt, user);
        this.deadline = dueDateTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + deadline + "," + getCreatedAt() + "," + status + "," + getUser();
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public ProgressCardStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressCardStatus status) {
        this.status = status;
    }
}
