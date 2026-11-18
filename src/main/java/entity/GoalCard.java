package entity;

import enums.CardCategory;
import enums.ProgressCardStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@DiscriminatorValue("GOAL")
@Entity
public class GoalCard extends Card {
    private CardCategory category;
    private ProgressCardStatus status;
    private int progress;
    private LocalDateTime deadline;

    public GoalCard() {
    }

    public GoalCard(Long id, String name, String content, LocalDateTime createdAt, CardCategory category,
                    ProgressCardStatus status, int progress, LocalDateTime deadline, User user) {
        super(id, name, content, createdAt, user);
        this.category = category;
        this.status = status;
        this.progress = progress;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getContent() + "," + getCreatedAt() + "," + category + "," +
                status + "," + progress + "," + getUser();
    }

    public CardCategory getCategory() {
        return category;
    }

    public void setCategory(CardCategory category) {
        this.category = category;
    }

    public ProgressCardStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressCardStatus status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
