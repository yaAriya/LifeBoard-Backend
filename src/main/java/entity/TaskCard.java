package entity;

import enums.ProgressCardStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@DiscriminatorValue("Task")
@Entity
public class TaskCard extends Card {
    private LocalDateTime deadline;
    @Enumerated(EnumType.STRING)
    private ProgressCardStatus status;

    public TaskCard() {
    }

    public TaskCard(Long id, String name, String content, LocalDateTime dueDateTime, LocalDateTime createdAt, ProgressCardStatus status, User user) {
        super(id, name, content, createdAt, user);
        this.deadline = dueDateTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getContent() + "," +
                deadline + "," + getCreatedAt() + "," + status + "," + getUser();
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
