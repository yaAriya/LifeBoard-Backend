package entity;

import enums.CardCategory;
import enums.ProgressCardStatus;

import java.time.LocalDateTime;

public class GoalCard extends Card {
    private CardCategory category;
    private ProgressCardStatus status;
    private int progress;
    private LocalDateTime deadline;

    public GoalCard() {
    }

    public GoalCard(long id, String name, LocalDateTime createdAt, CardCategory category,
                    ProgressCardStatus status, int progress, LocalDateTime deadline, User user) {
        super(id, name, createdAt, user);
        this.category = category;
        this.status = status;
        this.progress = progress;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getCreatedAt() + "," + category + "," +
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
