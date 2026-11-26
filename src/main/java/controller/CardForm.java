package controller;

import enums.CardCategory;
import enums.CardType;
import enums.CompletionCardStatus;
import enums.ProgressCardStatus;

import java.time.LocalDateTime;

public class CardForm {
    private CardType cardType;
    private String name;
    private String content;
    private Long userId;

    private CompletionCardStatus todoStatus;

    private LocalDateTime deadline;
    private ProgressCardStatus taskStatus;

    private CardCategory category;
    private ProgressCardStatus goalStatus;
    private int goalProgress;

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CompletionCardStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(CompletionCardStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public ProgressCardStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(ProgressCardStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public CardCategory getCategory() {
        return category;
    }

    public void setCategory(CardCategory category) {
        this.category = category;
    }

    public ProgressCardStatus getGoalStatus() {
        return goalStatus;
    }

    public void setGoalStatus(ProgressCardStatus goalStatus) {
        this.goalStatus = goalStatus;
    }

    public int getGoalProgress() {
        return goalProgress;
    }

    public void setGoalProgress(int goalProgress) {
        this.goalProgress = goalProgress;
    }
}
