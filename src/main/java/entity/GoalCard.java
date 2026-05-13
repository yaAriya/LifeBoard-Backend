package entity;

import enums.CardCategory;
import enums.CardPriority;
import enums.CardStatus;

import java.time.LocalDateTime;

public class GoalCard extends Card {
    private CardCategory category;
    private CardStatus status;
    private CardPriority priority;
    private int progress;

    public GoalCard() {
    }

    public GoalCard(long id, String name, LocalDateTime createdAt, CardCategory category,
                    CardStatus status, CardPriority priority, int progress, User user) {
        super(id, name, createdAt, user);
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getCreatedAt() + "," + category + "," +
                status + "," + priority + "," + progress + "," + getUser();
    }

    public CardCategory getCategory() {
        return category;
    }

    public void setCategory(CardCategory category) {
        this.category = category;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public CardPriority getPriority() {
        return priority;
    }

    public void setPriority(CardPriority priority) {
        this.priority = priority;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
